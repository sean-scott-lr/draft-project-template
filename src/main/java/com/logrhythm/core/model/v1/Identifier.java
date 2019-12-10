package com.logrhythm.core.model.v1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.ws.rs.PathParam;

@Embeddable
public class Identifier implements Serializable {

  @Column(name = "id", nullable = false)
  private String id;

  @Column(name = "tenant_id", nullable = false)
  @JsonIgnore
  @PathParam("tenantId")
  @NotNull
  private String tenantId;

  public Identifier(String tenantId, String id){
    this.tenantId = tenantId;
    this.id = id;
  }

  public Identifier(String tenantId){
    this(tenantId, null );
  }

  public Identifier(){
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTenantId() {
    return tenantId;
  }

  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

  @Override
  public String toString(){
    return "("+tenantId+","+id+")";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Identifier that = (Identifier) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(tenantId, that.tenantId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, tenantId);
  }
}
