package com.logrhythm.core.model.v1;

public class UnaryResponse<EntityType> extends Response {
  private EntityType content;

  public UnaryResponse() {}

  public UnaryResponse(EntityType entity) {
    this.content = entity;
  }

  public EntityType getContent() {
    return content;
  }

  public void setContent(EntityType content) {
    this.content = content;
  }
}
