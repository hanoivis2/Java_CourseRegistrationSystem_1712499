package DAO;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Models.Student;

@SuppressWarnings("deprecation")
public class StudentDAO {

	@SuppressWarnings("unchecked")
	public static List<Student> getStudentList() {
		List<Student> result = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			String hql = "SELECT stu FROM Student stu"; 
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
	
	public static Student getStudentById(int id) {
		Student result = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			result = (Student) session.get(Student.class, id);
		} 
		
		catch (HibernateException ex) { 
			
		} 
		
		finally { 
			session.close();
		}
		
		return result;
	}
	
	public static int addStudent(Student student) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		if (StudentDAO.getStudentById(student.getId())!=null) {
			return -1; 
		}
		
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction(); 
			session.save(student);
			transaction.commit();
		} 
		
		catch (HibernateException ex) { 
			
		} 
		
		finally { 
			session.close();
		}
		
		return 1;
	}
	
	public static int updateStudent(Student student) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		if (StudentDAO.getStudentById(student.getId())!=null) {
			Transaction transaction = null;
			
			try {
				transaction = session.beginTransaction(); 
				session.update(student);
				transaction.commit();
			} 
			
			catch (HibernateException ex) { 
				
			} 
			
			finally { 
				session.close();
			}
		}
		
		
		
		return 1;
	}
	
}
