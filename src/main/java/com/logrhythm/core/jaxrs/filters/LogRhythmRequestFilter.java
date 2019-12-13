package com.logrhythm.core.jaxrs.filters;

import com.logrhythm.core.model.v1.Response;
import io.jaegertracing.internal.JaegerSpanContext;
import io.opentracing.Span;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import java.io.IOException;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import org.slf4j.MDC;

@ApplicationScoped
@Provider
public class LogRhythmRequestFilter implements ContainerRequestFilter, ContainerResponseFilter {

  public static final String REQUEST_ID = "requestId";
  private Tracer tracer;

  @Inject
  public LogRhythmRequestFilter(Tracer tracer) {
    this.tracer = tracer;
  }

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    String requestId = establishRequestId(requestContext);
    setupMDC(requestId);
  }

  @Override
  public void filter(ContainerRequestContext req, ContainerResponseContext res) throws IOException {

    String requestId = (String) req.getProperty(REQUEST_ID);
    decorateResponse(res, requestId);
  }

  private String establishRequestId(ContainerRequestContext req) {

    String requestId;
    Span s = tracer.activeSpan();
    SpanContext ctx = s == null ? null : s.context();

    if (ctx != null && ctx instanceof JaegerSpanContext) {
      // TODO: Fix this downcasting once Quarkus upgrades to Jaeger 0.35+
      requestId = Long.toHexString(((JaegerSpanContext) ctx).getSpanId());
    } else {
      requestId = UUID.randomUUID().toString();
    }

    req.setProperty(REQUEST_ID, requestId);

    return requestId;
  }

  private void setupMDC(String requestId) {
    MDC.clear();
    MDC.put(REQUEST_ID, requestId);
  }

  private void decorateResponse(ContainerResponseContext res, String requestId) {

    if (requestId != null && !requestId.isEmpty()) {
      Object o = res.getEntity();
      if (o instanceof Response) {
        ((Response) o).setRequestId(requestId);
      }
    }
  }
}
