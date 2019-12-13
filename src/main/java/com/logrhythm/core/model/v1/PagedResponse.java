package com.logrhythm.core.model.v1;

import java.util.List;

/** Standard response which contains multiple results */
public class PagedResponse<ElementType, PagingType extends PaginationDetail> extends Response {

  private PagingType paging = null;
  private List<ElementType> content = null;

  public PagedResponse(PagingType paging, List<ElementType> content) {
    this.paging = paging;
    this.content = content;
  }

  public PagingType getPaging() {
    return paging;
  }

  public void setPaging(PagingType paging) {
    this.paging = paging;
  }

  public List<ElementType> getContent() {
    return content;
  }

  public void setContent(List<ElementType> content) {
    this.content = content;
  }
}
