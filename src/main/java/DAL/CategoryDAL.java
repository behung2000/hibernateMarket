package DAL;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CategoryDAL {

    private Session session;

    public CategoryDAL() {
    }

    private void openSession() {
        session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
    }

    private void closeSession() {
        session.getTransaction().commit();
        session.close();
    }

    public List loadCategory() {
        List<Category> category;
        openSession();
        category = session.createQuery("FROM Category", Category.class).list();
        closeSession();
        return category;

    }
    public Category getCategory(int CategoryID)
    {
        openSession();
        Category c = session.get(Category.class, CategoryID);
        closeSession();
        return c;
    }
    public void addCategory(Category c)
    {
        openSession();
        session.save(c);
        closeSession();
    }
    public void updateCategory(Category c)
    {
        openSession();
        session.update(c);
        closeSession();
    }

    public void deleteCategory(Category c)
    {
        openSession();
        session.delete(c);
        closeSession();
    }

    public Category getCategoryWithName(String name) {
        Category category = null;
        openSession();
        Query query = session.createQuery("FROM Category AS c WHERE c.Name = :name", Category.class);
        query.setParameter("name", name);
        category = (Category) query.getSingleResult();
        closeSession();
        return category;
    }


    /*
    public void deleteVegetable(Vegetable vegetable) {
        Category category = getCategory(vegetable.getCatagory().getCatagoryID());
        Set<Vegetable> vegetables = category.getListVegetable();
        if (vegetable != null) {
            openSession();
            vegetables.remove(vegetable);
            closeSession();
            System.out.println("Success");
        }
    }
     */

}
