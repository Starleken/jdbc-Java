package entities;

import java.sql.Date;
import java.util.Objects;

public class Order {
    private Integer id;
    private Integer customerId;
    private Date date;
    private Integer statusId;
    private String comments;
    private Date shippedDate;
    private Integer shipperId;

    public Order() {
    }

    public Order(Integer id, Integer customerId, Date date, Integer statusId, String comments, Date shippedDate, Integer shipperId) {
        this.id = id;
        this.customerId = customerId;
        this.date = date;
        this.statusId = statusId;
        this.comments = comments;
        this.shippedDate = shippedDate;
        this.shipperId = shipperId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    public Integer getShipperId() {
        return shipperId;
    }

    public void setShipperId(int shipperId) {
        this.shipperId = shipperId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && customerId == order.customerId && statusId == order.statusId && shipperId == order.shipperId && Objects.equals(date, order.date) && Objects.equals(comments, order.comments) && Objects.equals(shippedDate, order.shippedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, date, statusId, comments, shippedDate, shipperId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", date=" + date +
                ", statusId=" + statusId +
                ", comments='" + comments + '\'' +
                ", shippedDate=" + shippedDate +
                ", shipperId=" + shipperId +
                '}';
    }
}
