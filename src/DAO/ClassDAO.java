package DAO;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
//import org.hibernate.Transaction;
import org.hibernate.Transaction;

import Models.Class;

@SuppressWarnings("deprecation")
public class ClassDAO {

	@SuppressWarnings("unchecked")
	public static List<Class> getClassList() {
		List<Class> result = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			String hql = "SELECT c FROM Class c"; 
			@SuppressWarnings("rawtypes")
			Query query = session.createQuery(hql);
			result = query.list();
		} 
		
		catch (HibernateException ex) { 
			
		} 
		
		finally { 
			session.close();
		}
		
		
		
		return result;
	}
	
	public static int addClass(Class newClass) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		if (ClassDAO.getClassById(newClass.getId())!=null) {
			return -1; 
		}
		
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction(); 
			session.save(newClass);
			transaction.commit();
		} 
		
		catch (HibernateException ex) { 
			
		} 
		
		finally { 
			session.close();
		}
		
		return 1;
	}
	
	public static Class getClassById(String id) {
		Class result = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			result = (Class) session.get(Class.class, id);
		} 
		
		catch (HibernateException ex) { 
			
		} 
		
		finally { 
			session.close();
		}
		
		return result;
	}
		
	public static int updateClass(Class classToEdit) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		if (ClassDAO.getClassById(classToEdit.getId())!=null) {
			Transaction transaction = null;
			
			try {
				transaction = session.beginTransaction(); 
				session.update(classToEdit);
				transaction.commit();
			} 
			
			catch (HibernateException ex) { 
				
			} 
			
			finally { 
				session.close();
			}
		}
		else {
			return -1;
		}
		
		
		return 1;
	}
	
	public static int deleteClass(Class classToDelete) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		if (ClassDAO.getClassById(classToDelete.getId())!=null) {
			Transaction transaction = null;
			
			try {
				transaction = session.beginTransaction(); 
				session.delete(classToDelete);
				transaction.commit();
			} 
			
			catch (HibernateException ex) { 
				
			} 
			
			finally { 
				session.close();
			}
		}
		else {
			return -1;
		}
		
		
		return 1;
	}
	
}
