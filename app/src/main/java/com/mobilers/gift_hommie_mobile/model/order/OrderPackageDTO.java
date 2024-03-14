package com.mobilers.gift_hommie_mobile.model.order;

import java.util.List;

public class OrderPackageDTO {
    private List<OrderItemDTO> orderDetails;
    private String createTime;
    private String status;

    public OrderPackageDTO(List<OrderItemDTO> orderDetails, String createTime, String status) {
        this.orderDetails = orderDetails;
        this.createTime = createTime;
        this.status = status;
    }

    public List<OrderItemDTO> getOrderItem() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderItemDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
