package DAO;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Models.RegistrationSession;
import Models.RegistrationSessionID;

@SuppressWarnings("deprecation")
public class RegistrationSessionDAO {

	@SuppressWarnings("unchecked")
	public static List<RegistrationSession> getRegistrationSessionList() {
		List<RegistrationSession> result = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			String hql = "SELECT s FROM RegistrationSession s"; 
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
	
	public static int addRegistrationSession(RegistrationSession newRegistrationSession) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		if (RegistrationSessionDAO.getRegistrationSessionById(newRegistrationSession.getId())!=null) {
			return -1; 
		}
		
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction(); 
			session.save(newRegistrationSession);
			transaction.commit();
		} 
		
		catch (HibernateException ex) { 
			
		} 
		
		finally { 
			session.close();
		}
		
		return 1;
	}
	
	public static RegistrationSession getRegistrationSessionById(RegistrationSessionID registrationSessionID) {
		RegistrationSession result = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			result = (RegistrationSession) session.get(RegistrationSession.class, registrationSessionID);
		} 
		
		catch (HibernateException ex) { 
			
		} 
		
		finally { 
			session.close();
		}
		
		return result;
	}
		
	public static int updateRegistrationSession(RegistrationSession registrationSessionToEdit) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		if (RegistrationSessionDAO.getRegistrationSessionById(registrationSessionToEdit.getId())!=null) {
			Transaction transaction = null;
			
			try {
				transaction = session.beginTransaction(); 
				session.update(registrationSessionToEdit);
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
	
	public static int deleteRegistrationSession(RegistrationSession registrationSessionToDelete) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		if (RegistrationSessionDAO.getRegistrationSessionById(registrationSessionToDelete.getId())!=null) {
			Transaction transaction = null;
			
			try {
				transaction = session.beginTransaction(); 
				session.delete(registrationSessionToDelete);
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
