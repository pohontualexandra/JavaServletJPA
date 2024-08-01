package model;

import java.time.LocalDate;

public class Order {
    private Long orderId;
    private LocalDate orderDate;
    private Long userId;
    private Long productId;
    private String address;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;

    public Order() {}

    public Order(Long orderId, LocalDate orderDate, Long userId, Long productId, String address, Double totalPrice, Integer quantity, Double unitPrice) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.userId = userId;
        this.productId = productId;
        this.address = address;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Order(Long userId, LocalDate orderDate, String address, Double totalPrice) {
        this.userId = userId;
        this.orderDate = orderDate;
        this.address = address;
        this.totalPrice = totalPrice;
    }

    public Order(Long userId, Long orderId, LocalDate orderDate, String address, Double totalPrice) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.address = address;
        this.totalPrice = totalPrice;
    }

    public Order(Long orderId, Long userId, Long productId, Integer quantity, Double unitPrice) {
        this.orderId = orderId;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
