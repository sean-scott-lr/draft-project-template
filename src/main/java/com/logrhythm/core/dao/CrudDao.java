package com.logrhythm.core.dao;

import java.io.Serializable;

public interface CrudDao<EntityType, IdType extends Serializable> {

  void persist(EntityType entity);
  EntityType findById(IdType id);
  void delete(IdType id);
  void delete(EntityType entity);

}
