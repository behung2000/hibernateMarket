package hibernatebanhang.DAL;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CustomersDAL {
    Session session;

    public CustomersDAL()
    {
        session = HibernateUtils.getSessionFactory().openSession();
    }

    public List<Customers> getList() {
        List<Customers> customers = new ArrayList<>();
        session.beginTransaction();
        customers = session.createQuery("FROM Customers", Customers.class).list();
        session.getTransaction().commit();
        return customers;
    }

    public Customers getCustomers(int CustomersID)
    {
        Customers obj;
        session.beginTransaction();
        obj = session.get(Customers.class, CustomersID);
        session.getTransaction().commit();
        return obj;

    }

    public List getCustomersInCategory(int categoryID)
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
}
