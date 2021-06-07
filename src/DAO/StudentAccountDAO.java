package DAO;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
//import org.hibernate.Transaction;
import org.hibernate.Transaction;

import Models.StudentAccount;

@SuppressWarnings("deprecation")
public class StudentAccountDAO {

	@SuppressWarnings("unchecked")
	public static List<StudentAccount> getStudentAccountList() {
		List<StudentAccount> result = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			String hql = "SELECT c FROM StudentAccount c"; 
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
	
	@SuppressWarnings("unchecked")
	public static List<StudentAccount> getStudentAccountByClassIdList(String classId) {
		List<StudentAccount> result = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			String hql = "SELECT c FROM StudentAccount c WHERE c.classId = " + "'" + classId + "'"; 
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
	
	public static int addStudentAccount(StudentAccount newStudentAccount) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		if (StudentAccountDAO.getStudentAccountById(newStudentAccount.getId())!=null) {
			return -1; 
		}
		
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction(); 
			session.save(newStudentAccount);
			transaction.commit();
		} 
		
		catch (HibernateException ex) { 
			
		} 
		
		finally { 
			session.close();
		}
		
		return 1;
	}
	
	public static StudentAccount getStudentAccountById(String id) {
		StudentAccount result = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			result = (StudentAccount) session.get(StudentAccount.class, id);
		} 
		
		catch (HibernateException ex) { 
			
		} 
		
		finally { 
			session.close();
		}
		
		return result;
	}
		
	public static int updateStudentAccount(StudentAccount studentAccountToEdit) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		if (StudentAccountDAO.getStudentAccountById(studentAccountToEdit.getId())!=null) {
			Transaction transaction = null;
			
			try {
				transaction = session.beginTransaction(); 
				session.update(studentAccountToEdit);
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
	
	public static int deleteStudentAccount(StudentAccount studentAccountToDelete) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		if (StudentAccountDAO.getStudentAccountById(studentAccountToDelete.getId())!=null) {
			Transaction transaction = null;
			
			try {
				transaction = session.beginTransaction(); 
				session.delete(studentAccountToDelete);
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
