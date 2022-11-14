package hibernatebanhang.DAL;

import com.google.gson.Gson;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Customers {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CustomerID")
    private int customerId;

    @Column(name = "Password")
    private String password;

    @Column(name = "Fullname")
    private String fullname;

    @Column(name = "Address")
    private String address;

    @Column(name = "City")
    private String city;

    public String toString() {
        return new Gson().toJson(this);
    }

}
