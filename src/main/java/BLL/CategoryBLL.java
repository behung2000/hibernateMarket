
package BLL;

import hibernatebanhang.DAL.Category;
import hibernatebanhang.DAL.CategoryDAL;

import java.util.List;

/**
 *
 * @author caothanh
 */
public class CategoryBLL {
    
    private CategoryDAL cateDAL;
    
    
    public CategoryBLL()
    {
        cateDAL = new CategoryDAL();
    }
    
    public List loadCategory()
    {
        List list;
        list = cateDAL.loadCategory();
        
        return list;
    }

    public Category[] convertList1 (List<Category> list)
    {
        int rows = list.size();
        Category[] newList = new Category[rows];
        for(int i = 0; i < rows; i++)
        {
            newList[i] = list.get(i);
            
        }
        return newList;
    }

    public Object[][] convertList(List<Category> list)
    {
        int rows = list.size();
        int cols = 4;
        Object[][] obj = new Object[rows][cols];
        for(int i = 0; i < rows; i++)
        {
            obj[i][0] = list.get(i).getCatagoryID();
            obj[i][1] = list.get(i).getName();
            obj[i][2] = list.get(i).getDescription();
            obj[i][3] = list.get(i).getListVegetable().size();
        }
        return obj;
    }

    public void newCategory(Category c)
    {
        cateDAL.addCategory(c);
    }

    public Category getCategory(int CategoryID)
    {
        Category c = cateDAL.getCategory(CategoryID);
        return c;
    }
}
