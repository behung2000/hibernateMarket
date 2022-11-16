package DAL;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Entity
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
