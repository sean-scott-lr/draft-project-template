package com.logrhythm.svc._servicePackage_.v1.rest;

import com.logrhythm.core.model.v1.Identifier;
import com.logrhythm.core.model.v1.UnaryResponse;
import com.logrhythm.svc._servicePackage_.v1._serviceClass_Service;
import com.logrhythm.svc._servicePackage_.v1.model._entityClass_;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@RequestScoped
@Timed
@Path("/_serviceRoute_/v0/tenants/{tenantId}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "_serviceName_")
public class _serviceClass_Resource {

  private _serviceClass_Service _serviceInstance;

  @Inject
  public _serviceClass_Resource( _serviceClass_Service _serviceInstance ){
    this._serviceInstance = _serviceInstance;
  }

  @Path( "/_entityRoute_")
  @POST
  public UnaryResponse<_entityClass_> create(@PathParam("tenantId") String tenantId, _entityClass_ _entityInstance_) {
    _entityInstance_.setId(new Identifier(tenantId));
    return new UnaryResponse<_entityClass_>(_serviceInstance.create_entityClass_( _entityInstance_)) {};
  }
}
