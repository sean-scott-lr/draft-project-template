package com.logrhythm.core.jaxrs.exception.mappers;

import com.logrhythm.core.model.v1.Response;
import java.util.List;
import java.util.Locale;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseExceptionMapper {

  private static final Logger LOG = LoggerFactory.getLogger(BaseExceptionMapper.class);
  @javax.ws.rs.core.Context private HttpHeaders requestHeaders;

  Locale getRequestedLocale() {
    Locale result = null;
    List<Locale> locales = requestHeaders.getAcceptableLanguages();
    if (locales != null && !locales.isEmpty()) {
      for (int i = 0, size = locales.size(); i < size; i++) {
        Locale l = locales.get(i);
        if (isSupported(l)) {
          result = l;
          break;
        }
      }
    }

    return result != null ? result : Locale.getDefault();
  }

  protected boolean isSupported(Locale l) {

    // TODO: We need to support multiple locales.
    boolean supported = l.getLanguage().equals("en");

    if (!supported) {
      LOG.debug("Unsupported locale " + l.getLanguage() + " requested.");
    }

    return supported;
  }

  protected javax.ws.rs.core.Response createResponse(Throwable t) {
    return javax.ws.rs.core.Response.status(Status.INTERNAL_SERVER_ERROR)
        .type(MediaType.APPLICATION_JSON)
        .entity(
            Response.error(
                Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                Status.INTERNAL_SERVER_ERROR.getReasonPhrase()))
        .build();
  }

  protected javax.ws.rs.core.Response createResponse(WebApplicationException e) {
    return javax.ws.rs.core.Response.status(e.getResponse().getStatus())
        .type(MediaType.APPLICATION_JSON)
        .entity(Response.error(e.getResponse().getStatus(), e.getLocalizedMessage()))
        .build();
  }
}
