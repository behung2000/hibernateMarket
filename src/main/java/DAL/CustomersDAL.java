package DAL;

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
        customers = session.createQuery("FROM Customers ORDER BY customerId ASC", Customers.class).list();
        session.getTransaction().commit();
        return customers;
    }

    public void addCustomers(Customers obj)
    {
        session.saveOrUpdate(obj);
    }

    public void updateCustomers(Customers obj)
    {
        session.update(obj);
    }

    public void deleteCustomers(Customers obj)
    {
        session.delete(obj);
    }

    public Customers getCustomers(int CustomersID)
    {
        Customers obj;
        session.beginTransaction();
        obj = session.get(Customers.class, CustomersID);
        session.getTransaction().commit();
        return obj;
    }


    public List getCustomersInName(String name)
    {
        List list;
        session.beginTransaction();
        Query q = session.createQuery("FROM Customers WHERE fullname LIKE CONCAT('%',:name,'%')");
        q.setParameter("name", name);
        list = q.list();
        session.getTransaction().commit();
        return list;
    }
}
