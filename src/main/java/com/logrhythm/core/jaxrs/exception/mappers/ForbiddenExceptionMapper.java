package com.logrhythm.core.jaxrs.exception.mappers;

import com.logrhythm.core.model.v1.Response;
import io.quarkus.security.ForbiddenException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ForbiddenExceptionMapper extends BaseExceptionMapper
    implements ExceptionMapper<ForbiddenException> {

  @Override
  public javax.ws.rs.core.Response toResponse(ForbiddenException e) {
    return javax.ws.rs.core.Response.status(javax.ws.rs.core.Response.Status.FORBIDDEN)
        .type(MediaType.APPLICATION_JSON)
        .entity(
            Response.error(javax.ws.rs.core.Response.Status.FORBIDDEN.getStatusCode(), e.getLocalizedMessage()))
        .build();
  }
}
