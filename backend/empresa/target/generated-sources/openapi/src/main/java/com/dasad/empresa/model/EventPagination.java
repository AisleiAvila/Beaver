package com.dasad.empresa.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * EventPagination
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-02T10:06:15.811723800Z[Europe/Lisbon]", comments = "Generator version: 7.9.0")
public class EventPagination {

  private Integer length;

  private Integer pageIndex;

  private Integer pageSize;

  private Integer previousPageIndex;

  public EventPagination length(Integer length) {
    this.length = length;
    return this;
  }

  /**
   * Get length
   * @return length
   */
  
  @Schema(name = "length", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("length")
  public Integer getLength() {
    return length;
  }

  public void setLength(Integer length) {
    this.length = length;
  }

  public EventPagination pageIndex(Integer pageIndex) {
    this.pageIndex = pageIndex;
    return this;
  }

  /**
   * Get pageIndex
   * @return pageIndex
   */
  
  @Schema(name = "pageIndex", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("pageIndex")
  public Integer getPageIndex() {
    return pageIndex;
  }

  public void setPageIndex(Integer pageIndex) {
    this.pageIndex = pageIndex;
  }

  public EventPagination pageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return this;
  }

  /**
   * Get pageSize
   * @return pageSize
   */
  
  @Schema(name = "pageSize", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("pageSize")
  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public EventPagination previousPageIndex(Integer previousPageIndex) {
    this.previousPageIndex = previousPageIndex;
    return this;
  }

  /**
   * Get previousPageIndex
   * @return previousPageIndex
   */
  
  @Schema(name = "previousPageIndex", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("previousPageIndex")
  public Integer getPreviousPageIndex() {
    return previousPageIndex;
  }

  public void setPreviousPageIndex(Integer previousPageIndex) {
    this.previousPageIndex = previousPageIndex;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EventPagination eventPagination = (EventPagination) o;
    return Objects.equals(this.length, eventPagination.length) &&
        Objects.equals(this.pageIndex, eventPagination.pageIndex) &&
        Objects.equals(this.pageSize, eventPagination.pageSize) &&
        Objects.equals(this.previousPageIndex, eventPagination.previousPageIndex);
  }

  @Override
  public int hashCode() {
    return Objects.hash(length, pageIndex, pageSize, previousPageIndex);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EventPagination {\n");
    sb.append("    length: ").append(toIndentedString(length)).append("\n");
    sb.append("    pageIndex: ").append(toIndentedString(pageIndex)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
    sb.append("    previousPageIndex: ").append(toIndentedString(previousPageIndex)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

