package Entities;

import java.util.Objects;

public class Shipper {
    private int shipperId;
    private String name;

    public Shipper() {
    }

    public Shipper(int shipperId, String name) {
        this.shipperId = shipperId;
        this.name = name;
    }

    public int getShipperId() {
        return shipperId;
    }

    public void setShipperId(int shipperId) {
        this.shipperId = shipperId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shipper shipper = (Shipper) o;
        return shipperId == shipper.shipperId && Objects.equals(name, shipper.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shipperId, name);
    }

    @Override
    public String toString() {
        return "Shipper{" +
                "shipperId=" + shipperId +
                ", name='" + name + '\'' +
                '}';
    }
}
