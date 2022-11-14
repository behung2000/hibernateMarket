package hibernatebanhang.DAL;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table
public class Orderdetail {
    @Column(name = "OrderID")
    private int orderId;

    @Column(name = "VegetableID")
    private int vegetableId;

    @Column(name = "Quantity")
    private byte quantity;

    @Column(name = "Price")
    private double price;
}
