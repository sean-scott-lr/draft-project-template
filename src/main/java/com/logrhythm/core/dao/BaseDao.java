package com.logrhythm.core.dao;

import com.logrhythm.core.model.v1.Identifier;
import io.quarkus.hibernate.orm.panache.runtime.JpaOperations;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseDao<EntityType> implements CrudDao<EntityType, Identifier>{

  private static final Logger LOG = LoggerFactory.getLogger(BaseDao.class);
  private Class<EntityType> persistentClass;

  public BaseDao(Class<EntityType> persistentClass) {
    this.persistentClass = persistentClass;
  }

  protected Class<EntityType> getPersistentClass(){
    return persistentClass;
  }

  @Override
  @Transactional(TxType.MANDATORY)
  public void persist(EntityType entity){
    LOG.info( "persist " + entity );
    JpaOperations.persist(entity);
  }

  @Override
  @Transactional(TxType.SUPPORTS)
  public EntityType findById(Identifier id) {
    LOG.info( "findById " + id);
    EntityType e =  (EntityType)JpaOperations.findById(getPersistentClass(), id);

    LOG.info( "findById " + e);

    return e;
  }

  @Override
  @Transactional(TxType.REQUIRED)
  public void delete(Identifier identifier) {
    EntityType entity = findById(identifier);
    //Yeah, this is dumb.  But, infrequent... so oh well.
    if( entity != null ){
      delete(entity);
    }
  }

  @Override
  @Transactional(TxType.REQUIRED)
  public void delete(EntityType entity) {
    JpaOperations.delete(entity);
  }
}
