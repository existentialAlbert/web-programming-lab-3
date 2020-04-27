package databaseInfo;

import model.Point;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DatabaseManager {
    private int a;
    private SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    /*static {
        try {
            sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        } catch (Exception e0) {
            e0.printStackTrace();
        }
    }*/
    public DatabaseManager() {
    }

    public void add(Point p, String username) {
        try (Session s = sessionFactory.openSession()) {
            s.save(p);
            Transaction tr = s.beginTransaction();
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Point> getList(String username) {
        List Points = null;
        try (Session ses = sessionFactory.openSession()) {
            Query query = ses.createQuery("FROM Point where  username = :usr");
            query.setParameter("usr", username);
            Points = query.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Points;
    }

    public void truncate(String username) {
        try (Session s = sessionFactory.openSession()) {
            Transaction tx = s.beginTransaction();
            Query q = s.createQuery("delete Point where username = :usr");
            q.setParameter("usr", username);
            int result = q.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
