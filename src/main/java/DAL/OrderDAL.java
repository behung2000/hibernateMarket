package DAL;

import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class OrderDAL {
    private Session session;

    public OrderDAL() {

    }

    private void openSession() {
        session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
    }

    private void closeSession() {
        session.getTransaction().commit();
        session.close();
    }

    public List<Ordered> getOrderList() {
        List<Ordered> list = new ArrayList<>();
        openSession();
        list = session.createQuery("FROM Ordered o ORDER BY o.orderId asc", Ordered.class).list();
        closeSession();
        return list;
    }

    public void save(Ordered order) {
        openSession();
        session.save(order);
        closeSession();
    }
}
