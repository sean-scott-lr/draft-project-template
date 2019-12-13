package com.logrhythm.svc._servicePackage_.v1.rest;

import com.google.common.flogger.FluentLogger;
import com.logrhythm.core.model.v1.Identifier;
import com.logrhythm.core.model.v1.UnaryResponse;
import com.logrhythm.svc._servicePackage_.v1._serviceClass_Service;
import com.logrhythm.svc._servicePackage_.v1.model._entityClass_;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@ApplicationScoped
// @Timed
@Path("/_serviceRoute_/v1.preview/" + Identifier.TENANT_URI_FRAGMENT)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "_serviceName_")
public class _serviceClass_Resource {

  // private static Logger LOG = LoggerFactory.getLogger(_serviceClass_Resource.class);
  private static final FluentLogger logger = FluentLogger.forEnclosingClass();

  private _serviceClass_Service _serviceInstance;

  @Inject
  public _serviceClass_Resource(_serviceClass_Service _serviceInstance) {
    this._serviceInstance = _serviceInstance;
  }

  @Path("/_entityRoute_")
  @POST
  public UnaryResponse<_entityClass_> create(
      @BeanParam Identifier id, _entityClass_ _entityInstance_) {
    /* LOG.trace( "Caller was " + (ctx.getUserPrincipal() == null ? null : ctx.getUserPrincipal().getName()));*/
    logger.atFinest().log("create( %s, %s )", id, _entityInstance_);
    _entityInstance_.setId(id);
    return new UnaryResponse<_entityClass_>(
        _serviceInstance.create_entityClass_(_entityInstance_)) {};
  }
}
