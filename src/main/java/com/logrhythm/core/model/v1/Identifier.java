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

  public static final String TENANT_ID = "tenantId";
  public static final String ID = "id";
  public static final String TENANT_URI_FRAGMENT = "tenants/{" + TENANT_ID + "}";
  public static final String ID_URI_FRAGMENT = "{" + ID + "}";
  private String id;
  private String tenantId;

  /*
  public Identifier(String tenantId, String id) {
    this.tenantId = tenantId;
    this.id = id;
  }

  public Identifier(String tenantId) {
    this(tenantId, null);
  }*/

  public Identifier() {}

  @Column(name = "id", nullable = false)
  public String getId() {
    return id;
  }

  @PathParam(ID)
  public void setId(String id) {
    this.id = id;
  }

  @Column(name = "tenant_id", nullable = false)
  @NotNull
  @JsonIgnore
  public String getTenantId() {
    return tenantId;
  }

  @PathParam(TENANT_ID)
  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

  @Override
  public String toString() {
    return "(" + tenantId + "," + id + ")";
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
    return Objects.equals(id, that.id) && Objects.equals(tenantId, that.tenantId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, tenantId);
  }
}
