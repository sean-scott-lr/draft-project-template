package com.logrhythm.core.dao;

import com.google.common.flogger.FluentLogger;
import com.logrhythm.core.model.v1.Identifier;
import io.quarkus.hibernate.orm.panache.runtime.JpaOperations;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseDao<EntityType> implements CrudDao<EntityType, Identifier> {

  private static final Logger LOG = LoggerFactory.getLogger(BaseDao.class);
  private static final FluentLogger logger = FluentLogger.forEnclosingClass();

  private Class<EntityType> persistentClass;

  public BaseDao(Class<EntityType> persistentClass) {
    this.persistentClass = persistentClass;
  }

  protected Class<EntityType> getPersistentClass() {
    return persistentClass;
  }

  @Override
  @Transactional(TxType.MANDATORY)
  public void persist(EntityType entity) {
    logger.atFinest().log("persist %s", entity);
    JpaOperations.persist(entity);
  }

  @Override
  @Transactional(TxType.SUPPORTS)
  public EntityType findById(Identifier id) {
    logger.atFinest().log("findById %s", id);
    return (EntityType) JpaOperations.findById(getPersistentClass(), id);
  }

  @Override
  @Transactional(TxType.REQUIRED)
  public void delete(Identifier identifier) {
    EntityType entity = findById(identifier);
    // Yeah, this is dumb.  But, infrequent... so oh well.
    if (entity != null) {
      delete(entity);
    }
  }

  @Override
  @Transactional(TxType.REQUIRED)
  public void delete(EntityType entity) {
    JpaOperations.delete(entity);
  }
}
