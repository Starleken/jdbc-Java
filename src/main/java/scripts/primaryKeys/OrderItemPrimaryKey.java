package scripts.primaryKeys;

import java.util.Objects;

public class OrderItemPrimaryKey {
    private int orderId;
    private int productId;

    public OrderItemPrimaryKey() {
    }

    public OrderItemPrimaryKey(int orderId, int productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemPrimaryKey that = (OrderItemPrimaryKey) o;
        return orderId == that.orderId && productId == that.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId);
    }

    @Override
    public String toString() {
        return "OrderItemPrimaryKey{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                '}';
    }
}
