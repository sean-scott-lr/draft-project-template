package com.logrhythm.svc._servicePackage_.v1.model;

import com.logrhythm.core.model.v1.BaseModel;
import com.logrhythm.core.validation.constraints.HasSubstring;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "_entityTableName_")
public class _entityClass_ extends BaseModel {

  //Please dont annotate fields, annotate the getters
  private String firstName;
  private String lastName;
  private Integer age;

  @Column(name = "first_name")
  @NotBlank
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Column(name = "last_name")
  @HasSubstring("foo")
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Column(name = "age")
  @Min(21)
  @Max(30)
  @NotNull
  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }
}
