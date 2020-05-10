package databaseInfo;

import model.Point;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DatabaseManager {
    private SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    public DatabaseManager() {
    }

    public void addPoint(Point p) {
        try (Session s = sessionFactory.openSession()) {
            Transaction tr = s.beginTransaction();
            s.save(p);
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Point> getPoints(String username) {
        List<Point> Points = null;
        try (Session ses = sessionFactory.openSession()) {
            Query query = ses.createQuery("FROM Point where  username = :usr");
            query.setParameter("usr", username);
            Points = query.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Points;
    }

    public void deletePoints(String username) {
        try (Session s = sessionFactory.openSession()) {
            Transaction tx = s.beginTransaction();
            Query q = s.createQuery("delete Point where username = :usr");
            q.setParameter("usr", username);
            q.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
