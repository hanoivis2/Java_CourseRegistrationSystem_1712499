package DAO;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Models.MinistryAccount;

@SuppressWarnings("deprecation")
public class MinistryAccountDAO {

	@SuppressWarnings("unchecked")
	public static List<MinistryAccount> getMinistryAccountList() {
		List<MinistryAccount> result = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			String hql = "SELECT s FROM MinistryAccount s"; 
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
	
	public static int addMinistryAccount(MinistryAccount newMinistryAccount) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		if (MinistryAccountDAO.getMinistryAccountById(newMinistryAccount.getUsername())!=null) {
			return -1; 
		}
		
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction(); 
			session.save(newMinistryAccount);
			transaction.commit();
		} 
		
		catch (HibernateException ex) { 
			
		} 
		
		finally { 
			session.close();
		}
		
		return 1;
	}
	
	public static MinistryAccount getMinistryAccountById(String ministryAccountID) {
		MinistryAccount result = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			result = (MinistryAccount) session.get(MinistryAccount.class, ministryAccountID);
		} 
		
		catch (HibernateException ex) { 
			
		} 
		
		finally { 
			session.close();
		}
		
		return result;
	}
		
	public static int updateMinistryAccount(MinistryAccount ministryAccountToEdit) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		if (MinistryAccountDAO.getMinistryAccountById(ministryAccountToEdit.getUsername())!=null) {
			Transaction transaction = null;
			
			try {
				transaction = session.beginTransaction(); 
				session.update(ministryAccountToEdit);
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
	
	public static int deleteMinistryAccount(MinistryAccount ministryAccountToDelete) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		if (MinistryAccountDAO.getMinistryAccountById(ministryAccountToDelete.getUsername())!=null) {
			Transaction transaction = null;
			
			try {
				transaction = session.beginTransaction(); 
				session.delete(ministryAccountToDelete);
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
