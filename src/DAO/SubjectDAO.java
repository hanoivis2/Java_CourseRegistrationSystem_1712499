package DAO;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Models.Subject;

@SuppressWarnings("deprecation")
public class SubjectDAO {

	@SuppressWarnings("unchecked")
	public static List<Subject> getSubjectList() {
		List<Subject> result = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			String hql = "SELECT s FROM Subject s"; 
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
	
	public static int addSubject(Subject newSubject) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		if (SubjectDAO.getSubjectById(newSubject.getId())!=null) {
			return -1; 
		}
		
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction(); 
			session.save(newSubject);
			transaction.commit();
		} 
		
		catch (HibernateException ex) { 
			
		} 
		
		finally { 
			session.close();
		}
		
		return 1;
	}
	
	public static Subject getSubjectById(String id) {
		Subject result = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			result = (Subject) session.get(Subject.class, id);
		} 
		
		catch (HibernateException ex) { 
			
		} 
		
		finally { 
			session.close();
		}
		
		return result;
	}
		
	public static int updateSubject(Subject subjectToEdit) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		if (SubjectDAO.getSubjectById(subjectToEdit.getId())!=null) {
			Transaction transaction = null;
			
			try {
				transaction = session.beginTransaction(); 
				session.update(subjectToEdit);
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
	
	public static int deleteSubject(Subject subjectToDelete) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		if (SubjectDAO.getSubjectById(subjectToDelete.getId())!=null) {
			Transaction transaction = null;
			
			try {
				transaction = session.beginTransaction(); 
				session.delete(subjectToDelete);
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
