package com.stu.shop_management.entity;

import java.math.BigDecimal;
import java.sql.Date;

public class Order {

    private Long orderId;

    private Long customerId;

    private Date orderDate;

    private BigDecimal totalAmount;

    public Order() {
    }

    public Order(Long orderId,
                 Long customerId,
                 Date orderDate,
                 BigDecimal totalAmount) {

        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}