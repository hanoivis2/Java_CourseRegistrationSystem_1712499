package DAO;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Models.StudentRegisterCourseID;
import Models.StudentRegisterCourse;

@SuppressWarnings("deprecation")
public class StudentRegisterCourseDAO {

	@SuppressWarnings("unchecked")
	public static StudentRegisterCourse getRegisterById(StudentRegisterCourseID registerId) {
		StudentRegisterCourse result = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			result = (StudentRegisterCourse) session.get(StudentRegisterCourse.class, registerId);
		} 
		
		catch (HibernateException ex) { 
			
		} 
		
		finally { 
			session.close();
		}
		
		return result;
	}
	
	public static int addRegister(StudentRegisterCourse newRegister) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		if (StudentRegisterCourseDAO.getRegisterById(newRegister.getId())!=null) {
			return -1; 
		}
		
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction(); 
			session.save(newRegister);
			transaction.commit();
		} 
		
		catch (HibernateException ex) { 
			
		} 
		
		finally { 
			session.close();
		}
		
		return 1;
	}
	
	
	public static int deleteSemester(StudentRegisterCourse registerToDelete) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		if (StudentRegisterCourseDAO.getRegisterById(registerToDelete.getId())!=null) {
			Transaction transaction = null;
			
			try {
				transaction = session.beginTransaction(); 
				session.delete(registerToDelete);
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