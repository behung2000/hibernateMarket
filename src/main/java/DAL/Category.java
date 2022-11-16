package DAL;


import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table
@NamedQuery(
        name = "findAll.Vegetable",
        query = "SELECT distinct v FROM Category AS c INNER JOIN Vegetable AS v ON v.catagory.CatagoryID = c.id ORDER BY v.id ASC"
)
public class Category {

    @Id
    private int CatagoryID;
    @Column
    private String Name;
    @Column
    private String Description;

    @JsonManagedReference
    @OneToMany(mappedBy = "catagory", cascade = CascadeType.ALL, orphanRemoval=true)
    private Set<Vegetable> listVegetable;

    @Override
    public String toString()
    {
        return this.Name;
    }
}

