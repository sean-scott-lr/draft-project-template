package com.logrhythm.svc._servicePackage_.v1.dao.impl;

import com.logrhythm.core.dao.BaseDao;
import com.logrhythm.core.model.v1.Identifier;
import com.logrhythm.core.model.v1.LimitOffsetPaginationDetail;
import com.logrhythm.core.model.v1.PagedResponse;
import com.logrhythm.svc._servicePackage_.v1.dao._entityClass_Dao;
import com.logrhythm.svc._servicePackage_.v1.model._entityClass_;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class _entityClass_DaoImpl extends BaseDao<_entityClass_>
    implements _entityClass_Dao, PanacheRepositoryBase<_entityClass_, Identifier> {

  private static Logger LOG = LoggerFactory.getLogger(_entityClass_DaoImpl.class);

  public _entityClass_DaoImpl() {
    super(_entityClass_.class);
  }

  @Override
  public PagedResponse<_entityClass_, LimitOffsetPaginationDetail> findByAge(
      Identifier tenantId, int age, LimitOffsetPaginationDetail pagination) {
    List<_entityClass_> entities =
        find("tenant_id=?1 and age=?2", tenantId.getTenantId(), age).page(0, 10).list();

    return null;
  }
}
