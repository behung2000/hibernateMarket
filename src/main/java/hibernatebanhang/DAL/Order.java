package hibernatebanhang.DAL;

import lombok.Data;
import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "OrderID")
    private int orderId;

    @Column(name = "CustomerID")
    private int customerId;

    @Column(name = "Date")
    private Date date;

    @Column(name = "Total")
    private double total;

    @Column(name = "Note")
    private String note;
}
