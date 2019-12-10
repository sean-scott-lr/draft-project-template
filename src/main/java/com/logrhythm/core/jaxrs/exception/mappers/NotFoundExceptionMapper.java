package com.logrhythm.core.jaxrs.exception.mappers;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper extends BaseExceptionMapper
    implements ExceptionMapper<NotFoundException> {

  public Response toResponse(NotFoundException e) {
    return super.createResponse(e);
  }
}
