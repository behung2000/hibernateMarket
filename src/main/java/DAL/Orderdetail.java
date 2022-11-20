package DAL;

import jakarta.persistence.*;

@Entity
@IdClass(OrderdetailPK.class)
public class Orderdetail {
    @Id
    @Column(name = "OrderID")
    private int orderId;
    @Id
    @Column(name = "VegetableID")
    private int vegetableId;
    @Basic
    @Column(name = "Quantity")
    private byte quantity;
    @Basic
    @Column(name = "Price")
    private double price;
    @ManyToOne
    @JoinColumn(
        name = "OrderID",
        referencedColumnName = "OrderID",
        nullable = false,
        insertable = false,
        updatable = false)
    private Order orderByOrderId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getVegetableId() {
        return vegetableId;
    }

    public void setVegetableId(int vegetableId) {
        this.vegetableId = vegetableId;
    }

    public byte getQuantity() {
        return quantity;
    }

    public void setQuantity(byte quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orderdetail that = (Orderdetail) o;

        if (orderId != that.orderId) return false;
        if (vegetableId != that.vegetableId) return false;
        if (quantity != that.quantity) return false;
        if (Double.compare(that.price, price) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = orderId;
        result = 31 * result + vegetableId;
        result = 31 * result + (int) quantity;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public Order getOrderByOrderId() {
        return orderByOrderId;
    }

    public void setOrderByOrderId(Order orderByOrderId) {
        this.orderByOrderId = orderByOrderId;
    }
}
