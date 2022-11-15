
package DAL;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
/**
 *
 * @author caothanh
 */
@Data
@Entity
@Table
public class Category {
    
    @Id
    private int CatagoryID;
    @Column
    private String Name;
    @Column
    private String Description;
    
    @OneToMany (mappedBy = "catagory")  
    private List<Vegetable> listVegetable;
 
    @Override
    public String toString()
    {
        return this.Name;
    }
}

