package DAL;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
