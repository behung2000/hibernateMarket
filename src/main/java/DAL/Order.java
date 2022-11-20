package DAL;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;

@Entity
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "OrderID")
    private int orderId;
    @Basic
    @Column(name = "CustomerID")
    private int customerId;
    @Basic
    @Column(name = "Date")
    private Date date;
    @Basic
    @Column(name = "Total")
    private double total;
    @Basic
    @Column(name = "Note")
    private String note;
    @OneToMany(mappedBy = "orderByOrderId")
    private Collection<Orderdetail> orderdetailsByOrderId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (customerId != order.customerId) return false;
        if (Double.compare(order.total, total) != 0) return false;
        if (date != null ? !date.equals(order.date) : order.date != null) return false;
        if (note != null ? !note.equals(order.note) : order.note != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = orderId;
        result = 31 * result + customerId;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        temp = Double.doubleToLongBits(total);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (note != null ? note.hashCode() : 0);
        return result;
    }

    public Collection<Orderdetail> getOrderdetailsByOrderId() {
        return orderdetailsByOrderId;
    }

    public void setOrderdetailsByOrderId(Collection<Orderdetail> orderdetailsByOrderId) {
        this.orderdetailsByOrderId = orderdetailsByOrderId;
    }
}
