package com.logrhythm.core.model.v1;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseModel {

  private Identifier id;

  @JsonUnwrapped
  @EmbeddedId
  public Identifier getId() {
    return id;
  }

  public void setId(Identifier id) {
    this.id = id;
  }

  public String toString() {
    return "id=" + id;
  }
}
