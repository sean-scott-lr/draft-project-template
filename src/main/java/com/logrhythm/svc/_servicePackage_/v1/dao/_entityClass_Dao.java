package com.logrhythm.svc._servicePackage_.v1.dao;

import com.logrhythm.core.dao.CrudDao;
import com.logrhythm.core.model.v1.Identifier;
import com.logrhythm.core.model.v1.LimitOffsetPaginationDetail;
import com.logrhythm.core.model.v1.PagedResponse;
import com.logrhythm.svc._servicePackage_.v1.model._entityClass_;
import org.eclipse.microprofile.reactive.streams.operators.spi.Stage.Limit;

public interface _entityClass_Dao extends CrudDao<_entityClass_, Identifier> {

  PagedResponse<_entityClass_,LimitOffsetPaginationDetail> findByAge(Identifier tenantId, int age, LimitOffsetPaginationDetail pagination);

}
