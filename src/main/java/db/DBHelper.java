package db;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public abstract class DBHelper {

    private static Session session;
    private static Transaction transaction;

    public static void save(Object object){
        session = HibernateUtil.getSessionFactory().openSession();

        try{transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();
        } catch (Throwable e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    protected static <T extends Object> List<T> getAll(Class<T> searchingClass){
        List<T> results = null;
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            Criteria cr = session.createCriteria(searchingClass);
            results = cr.list();
        } catch(Throwable e){
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    protected static <T extends Object> T find(int id, Class<T> searchingClass){
        session = HibernateUtil.getSessionFactory().openSession();
        T result = null;

        try {
            Criteria cr = session.createCriteria(searchingClass);
            cr.add(Restrictions.eq("id", id));
            result = (T) cr.uniqueResult();
        } catch(Throwable e){
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    protected static <T extends Object> List<T> orderByCriterion(String columnName, Class<T> searchingClass, boolean isAscending){
        List<T> results = null;
        session = HibernateUtil.getSessionFactory().openSession();
        try{
            Criteria cr = session.createCriteria(searchingClass);
            if(isAscending){
                cr.addOrder(Order.asc(columnName));
            }else {
                cr.addOrder(Order.desc(columnName));
            }
            results = cr.list();
        }catch (Throwable e){
            transaction.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
        return results;
    }

    protected static <T,R> List<T> findManyListToOne(R oneObject, Class<T> manyObjectClass, String manyColumnRelationshipTitle){
        session = HibernateUtil.getSessionFactory().openSession();
        List<T> results = null;

        try {
            Criteria cr = session.createCriteria(manyObjectClass);
            cr.add(Restrictions.eq(manyColumnRelationshipTitle, oneObject));
            results = cr.list();
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }

        return results;
    }

}
