package com.example.productcatalog.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Order;

@Getter
@Setter
public class DataTableRequestDTO<T> {

    private static final String ASC = "asc";
    private static final String defaultSortColumn = "createdDate";

    private String sortColumn;
    private String sortOrder;
    private Integer pageSize;
    private Integer pageIndex;
    private Boolean fetchAllRecords = Boolean.FALSE;

    private T query;

    public String getSortColumn() {
        if (StringUtils.isEmpty(this.sortColumn)) {
            this.sortColumn = defaultSortColumn;
        }
        return this.sortColumn;
    }

    @JsonIgnore
    public Order getOrderCriteria() {
        if (ASC.equalsIgnoreCase(sortOrder)) {
            return Order.asc(getSortColumn());
        } else {
            return Order.desc(getSortColumn());
        }
    }

    public Integer getPageSize() {
        if ((pageSize == null) || (pageSize <= 0)) {
            return 10;
        }
        return pageSize;
    }

    public Integer getPageIndex() {
        if ((pageIndex == null) || (pageIndex < 0)) {
            return 0;
        }
        return pageIndex;
    }

    @JsonIgnore
    public Integer getMax() {
        return getPageSize();
    }

    public Integer getOffset() {
        return getPageIndex() * getPageSize();
    }
}
