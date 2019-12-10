package com.logrhythm.core.model.v1;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public class LimitOffsetPaginationDetail extends PaginationDetail {

  @DefaultValue("10")
  @QueryParam("pageSize")
  public int pageSize = 10;

  @DefaultValue("1")
  @QueryParam("pageNumber")
  public int pageNumber = 1;

  private int numberOfElements;
  private int totalElements;

  public LimitOffsetPaginationDetail() {}

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }

  public int getNumberOfElements() {
    return numberOfElements;
  }

  public void setNumberOfElements(int numberOfElements) {
    this.numberOfElements = numberOfElements;
  }

  public int getTotalElements() {
    return totalElements;
  }

  public void setTotalElements(int totalElements) {
    this.totalElements = totalElements;
  }
}
