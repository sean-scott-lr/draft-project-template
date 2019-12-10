package com.logrhythm.svc._servicePackage_.v1;

import com.logrhythm.svc._servicePackage_.v1.dao._entityClass_Dao;
import com.logrhythm.svc._servicePackage_.v1.model._entityClass_;
import java.util.UUID;
import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.eclipse.microprofile.metrics.annotation.Timed;

@ApplicationScoped
@Timed
@DenyAll
public class _serviceClass_Service {

  private _entityClass_Dao _entityInstance_Dao;

  @Inject
  public _serviceClass_Service(_entityClass_Dao _entityInstance_Dao) {
    this._entityInstance_Dao = _entityInstance_Dao;
  }

  /*Note: Method name ends up in validation message by default */
  /*Note: Parameter name ends up in validation message*/
  @Transactional(TxType.REQUIRED)
  @RolesAllowed( "_entityEntitlement_-create")
  public _entityClass_ create_entityClass_(@Valid @NotNull _entityClass_ _entityInstance_) {
     _entityInstance_.getId().setId(UUID.randomUUID().toString());
     _entityInstance_Dao.persist(_entityInstance_);
     _entityInstance_Dao.findByAge(_entityInstance_.getId(),30,null);
     return _entityInstance_Dao.findById(_entityInstance_.getId());
  }
}
