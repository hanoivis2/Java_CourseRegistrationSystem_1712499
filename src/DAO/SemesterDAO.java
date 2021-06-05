package DAO;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Models.Semester;
import Models.SemesterID;

@SuppressWarnings("deprecation")
public class SemesterDAO {

	@SuppressWarnings("unchecked")
	public static List<Semester> getSemesterList() {
		List<Semester> result = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			String hql = "SELECT s FROM Semester s"; 
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
	
	public static int addSemester(Semester newSemester) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		if (SemesterDAO.getSemesterById(newSemester.getId())!=null) {
			return -1; 
		}
		
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction(); 
			session.save(newSemester);
			transaction.commit();
		} 
		
		catch (HibernateException ex) { 
			
		} 
		
		finally { 
			session.close();
		}
		
		return 1;
	}
	
	public static Semester getSemesterById(SemesterID semesterID) {
		Semester result = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			result = (Semester) session.get(Semester.class, semesterID);
		} 
		
		catch (HibernateException ex) { 
			
		} 
		
		finally { 
			session.close();
		}
		
		return result;
	}
		
	public static int updateSemester(Semester semesterToEdit) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		if (SemesterDAO.getSemesterById(semesterToEdit.getId())!=null) {
			Transaction transaction = null;
			
			try {
				transaction = session.beginTransaction(); 
				session.update(semesterToEdit);
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
	
	public static int deleteSemester(Semester semesterToDelete) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		
		if (SemesterDAO.getSemesterById(semesterToDelete.getId())!=null) {
			Transaction transaction = null;
			
			try {
				transaction = session.beginTransaction(); 
				session.delete(semesterToDelete);
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
