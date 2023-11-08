package entities;

import java.util.Objects;

public class OrderItemNote {
    private Integer id;
    private Integer orderId;
    private Integer productId;
    private String note;

    public OrderItemNote() {
    }

    public OrderItemNote(Integer id, Integer orderId, Integer productId, String note) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.note = note;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemNote that = (OrderItemNote) o;
        return id == that.id && orderId == that.orderId && productId == that.productId && Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, productId, note);
    }

    @Override
    public String toString() {
        return "OrderItemNote{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", note='" + note + '\'' +
                '}';
    }
}
