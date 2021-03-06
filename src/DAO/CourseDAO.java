package DAO;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
//import org.hibernate.Transaction;
import org.hibernate.Transaction;

import Models.Course;
import Models.CourseID;

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
	
	public static int addCourse(Course newCourse) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		if (CourseDAO.getCourseById(newCourse.getId())!=null) {
			return -1; 
		}
		
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction(); 
			session.save(newCourse);
			transaction.commit();
		} 
		
		catch (HibernateException ex) { 
			System.out.println(ex.getMessage());
		} 
		
		finally { 
			session.close();
		}
		
		return 1;
	}
	
	public static Course getCourseById(CourseID courseID) {
		Course result = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			result = (Course) session.get(Course.class, courseID);
		} 
		
		catch (HibernateException ex) { 
			
		} 
		
		finally { 
			session.close();
		}
		
		return result;
	}
		
	public static int updateClass(Course courseToEdit) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		if (CourseDAO.getCourseById(courseToEdit.getId())!=null) {
			Transaction transaction = null;
			
			try {
				transaction = session.beginTransaction(); 
				session.update(courseToEdit);
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
	
	public static int deleteCourse(Course courseToDelete) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		if (CourseDAO.getCourseById(courseToDelete.getId())!=null) {
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
		}
		else {
			return -1;
		}
		
		
		return 1;
	}
	
}
