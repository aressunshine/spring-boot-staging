package com.bruce.staging.common.model;

/**
 * CommonReq
 *
 * @author Brucezou
 */
public class CommonReq implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页码
     */
    private Long pageNumber;

    /**
     * 每页数量
     */
    private Long pageSize;

    /**
     * 排序字段
     */
    private String orderField;

    /**
     * 排序方式
     */
    private String orderType;

    public Long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
