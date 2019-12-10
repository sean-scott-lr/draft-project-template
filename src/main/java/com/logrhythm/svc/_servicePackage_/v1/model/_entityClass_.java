package com.logrhythm.svc._servicePackage_.v1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.logrhythm.core.model.v1.Identifier;
import com.logrhythm.core.validation.constraints.HasSubstring;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="_entityTableName_")
public class _entityClass_  {

  @EmbeddedId
  @JsonUnwrapped
  private Identifier id;

  @NotBlank
  @Column(name = "first_name")
  private String firstName;

  // @NotBlank(message = "{com.logrhythm.validation.constraints.Custom.message}")
  @HasSubstring("foo")
  @Column(name = "last_name")
  private String lastName = "bar";

  @Min(21)
  @Max(30)
  @NotNull
  @Column(name = "age")
  private Integer age;

  public Identifier getId() {
    return id;
  }

  public void setId(Identifier id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String toString(){
    return "id=" + id;
  }
}
