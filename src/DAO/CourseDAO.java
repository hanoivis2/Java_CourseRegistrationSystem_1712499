package DAO;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
//import org.hibernate.Transaction;
import org.hibernate.Transaction;

import Models.Course;

@SuppressWarnings("deprecation")
public class CourseDAO {

	@SuppressWarnings("unchecked")
	public static List<Course> getCourseList() {
		List<Course> result = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			String hql = "SELECT c FROM Course c"; 
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
	
	public static int addcourse(Course newCourse) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction(); 
			session.save(newCourse);
			transaction.commit();
		} 
		
		catch (HibernateException ex) { 
			
		} 
		
		finally { 
			session.close();
		}
		
		return 1;
	}
		
	public static int updateClass(Course classToEdit) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
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
		
		
		return 1;
	}
	
	public static int deleteCourse(Course courseToDelete) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction(); 
			session.delete(courseToDelete);
			transaction.commit();
		} 
		
		catch (HibernateException ex) { 
			
		} 
		
		finally { 
			session.close();
		}
		
		
		return 1;
	}
	
}
