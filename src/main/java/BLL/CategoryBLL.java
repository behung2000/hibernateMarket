
package BLL;

import DAL.Category;
import DAL.CategoryDAL;
import DAL.Vegetable;

import java.util.List;

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

    public Category[] convertList(List<Category> list)
    {
        int rows = list.size();
        Category[] newList = new Category[rows];
        for(int i = 0; i < rows; i++)
        {
            newList[i] = list.get(i);
            
        }
        return newList;
    }

    public void update(Category category) {
        cateDAL.updateCategory(category);
    }


}
