package db;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
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

    public static void update(Object object){
        session = HibernateUtil.getSessionFactory().openSession();

        try{transaction = session.beginTransaction();
            session.update(object);
            transaction.commit();
        } catch (Throwable e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void delete (Object object){
        session = HibernateUtil.getSessionFactory().openSession();

        try{transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
        } catch (Throwable e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
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

    protected static <T extends Object> List<T> findManyListForOne(Object oneObject, Class<T> manyObjectClass, String relationshipParameter){
        session = HibernateUtil.getSessionFactory().openSession();
        List<T> results = null;

        try {
            Criteria cr = session.createCriteria(manyObjectClass);
            cr.add(Restrictions.eq(relationshipParameter, oneObject));
            results = cr.list();
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }

        return results;
    }

    protected static <T extends Object> double getAverageQuantity(String columnName, Class<T> searchingClass){
        Double average = null;
        session = HibernateUtil.getSessionFactory().openSession();

        try{
            transaction = session.beginTransaction();
            Criteria cr = session.createCriteria(searchingClass);
            cr.setProjection(Projections.avg(columnName));
            average = (Double) cr.uniqueResult();
        }catch (Throwable e){
            transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return average;
    }

    protected static <OBJECT, ASSOCIATION extends IDB> List<OBJECT> getAssociationsForAnObject(OBJECT object, ASSOCIATION associationObject, Class<ASSOCIATION> associationClass, String associationRelationshipParameter){
        List<OBJECT> results = null;
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            Criteria cr = session.createCriteria(associationClass);
            cr.createAlias(associationRelationshipParameter, "single_object");
            cr.add(Restrictions.eq("single_object.id", associationObject.getId()));
            results = cr.list();
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return results;
    }
}
