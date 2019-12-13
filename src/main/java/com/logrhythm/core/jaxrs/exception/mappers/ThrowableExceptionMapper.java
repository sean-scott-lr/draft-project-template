package com.logrhythm.core.jaxrs.exception.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class ThrowableExceptionMapper extends BaseExceptionMapper
    implements ExceptionMapper<Throwable> {

  private static Logger LOG = LoggerFactory.getLogger(ThrowableExceptionMapper.class);

  public Response toResponse(Throwable t) {
    LOG.error("Unhandled Exception", t);
    return super.createResponse(t);
  }
}
