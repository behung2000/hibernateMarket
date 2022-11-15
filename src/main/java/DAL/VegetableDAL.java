package DAL;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class VegetableDAL {
    
    Session session;
    
    public VegetableDAL()
    {
        session = HibernateUtils.getSessionFactory().openSession();
    }

    public Vegetable getVegetable(int vegetableID)
    {
        Vegetable obj;
        session.beginTransaction();
        obj = session.get(Vegetable.class, vegetableID);
        session.getTransaction().commit();
        return obj;
        
    }

    public List getVegetableInCategory(int categoryID)
    {
        List list;
        session.beginTransaction();
        Query q = session.createQuery("FROM Vegetable WHERE CatagoryID = :categoryID");
        q.setParameter("categoryID", categoryID);
        list = q.list();
        session.getTransaction().commit();
        return list;
    }

    public void addVegetable(Vegetable obj)
    {
        session.save(obj);
    }

    public void updateVegetable(Vegetable obj)
    {
        session.update(obj);
    }

    public void deleteVegetable(Vegetable obj)
    {
        session.delete(obj);
    }

    public List<Vegetable> getList() {
        session.beginTransaction();
        List<Vegetable> list = session.createQuery("FROM Vegetable ORDER BY id ASC", Vegetable.class).list();
        session.getTransaction().commit();
        return list;
    }
}
