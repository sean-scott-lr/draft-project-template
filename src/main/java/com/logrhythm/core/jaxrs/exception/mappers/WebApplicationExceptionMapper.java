package com.logrhythm.core.jaxrs.exception.mappers;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class WebApplicationExceptionMapper extends BaseExceptionMapper
    implements ExceptionMapper<WebApplicationException> {

  public Response toResponse(WebApplicationException e) {
    return super.createResponse(e);
  }
}
