package Views;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URISyntaxException;


public class MinistryMainMenu extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton btn_myAccount;
	JButton btn_ministryAccountManagement;
	JButton btn_subjectManagement;
	JButton btn_semesterManagement;
	JButton btn_classManagement;
	JButton btn_couresRegistrationSessionManagement;
	JButton btn_coursesManagement;
	JButton btn_logout;
	JButton btn_exit;
	
	JFrame frame;
	
	
	public final static boolean RIGHT_TO_LEFT = false;
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
			}
		});

		JComponent mainMenu = new MinistryMainMenu();
		mainMenu.setOpaque(true);
		mainMenu.setVisible(true);
	}
	
	public MinistryMainMenu() {
		 super(new GridLayout(3,3));
		 
		 JFrame.setDefaultLookAndFeelDecorated(true);
         frame = new JFrame("Ministry Main Menu");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setPreferredSize(new Dimension(1200,600));
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
         frame.setLocation(dim.width/2 - 600, dim.height/2 - 300);
         
         btn_myAccount = new JButton("My account");
         btn_myAccount.addActionListener(this);
         btn_myAccount.setActionCommand("MyAccount");
	
         btn_ministryAccountManagement = new JButton("Ministry account management");
         btn_ministryAccountManagement.addActionListener(this);
         btn_ministryAccountManagement.setActionCommand("MinistryAccountManagement");
		 
         btn_subjectManagement = new JButton("Subject management");
         btn_subjectManagement.addActionListener(this);
         btn_subjectManagement.setActionCommand("SubjectManagement");
		 
         btn_semesterManagement = new JButton("Semester Management");
         btn_semesterManagement.addActionListener(this);
         btn_semesterManagement.setActionCommand("SemesterManagement");
		 
         btn_classManagement = new JButton("Class management");
         btn_classManagement.addActionListener(this);
         btn_classManagement.setActionCommand("ClassManagement");
		 
		 
         btn_couresRegistrationSessionManagement = new JButton("Courses Registration Session Management");
         btn_couresRegistrationSessionManagement.addActionListener(this);
         btn_couresRegistrationSessionManagement.setActionCommand("CoursesRegistrationSessionManagement");
		 
         btn_coursesManagement = new JButton("Courses Management");
         btn_coursesManagement.addActionListener(this);
         btn_coursesManagement.setActionCommand("CoursesManagement");
         
         btn_logout = new JButton("Log out");
         btn_logout.addActionListener(this);
         btn_logout.setActionCommand("Logout");
         
         btn_exit = new JButton("Exit");
         btn_exit.addActionListener(this);
         btn_exit.setActionCommand("Exit");
		 
         add(btn_myAccount);
		 add(btn_ministryAccountManagement);
		 add(btn_subjectManagement);
		 add(btn_semesterManagement);
		 add(btn_classManagement);
		 add(btn_couresRegistrationSessionManagement);
		 add(btn_coursesManagement);
		 add(btn_logout);
		 add(btn_exit);
		 
         this.setOpaque(true); 
         frame.setContentPane(this);
         frame.pack();
         frame.setVisible(true);
		 
	}

	public void actionPerformed(ActionEvent e)
	{
		String strActionCommand = e.getActionCommand();
		if (strActionCommand.equals("MinistryAccountManagement"))
		{
			try {
				JComponent ministryAccountManagement = new MinistryAccountManagement();
				ministryAccountManagement.setOpaque(true);
				ministryAccountManagement.setVisible(true);
			} catch (IOException e1) {
				
			} catch (URISyntaxException e1) {
				
			}
	    }
		else if (strActionCommand.equals("SubjectManagement"))
		{
			try {
				JComponent subjectsManagement = new SubjectsManagement();
				subjectsManagement.setOpaque(true);
				subjectsManagement.setVisible(true);
			} catch (IOException e1) {
				
			} catch (URISyntaxException e1) {
				
			}
		}
		else if (strActionCommand.equals("SemesterManagement"))
		{
			try {
				JComponent semestersManagement = new SemestersManagement();
				semestersManagement.setOpaque(true);
				semestersManagement.setVisible(true);
			} catch (IOException e1) {
				
			} catch (URISyntaxException e1) {
				
			}
		     
	    }
		else if (strActionCommand.equals("ClassManagement"))
		{
			try {
				JComponent classManagement = new ClassesManagement();
				classManagement.setOpaque(true);
				classManagement.setVisible(true);
			} catch (IOException e1) {
				
			} catch (URISyntaxException e1) {
				
			}
			
	    }
		else if (strActionCommand.equals("CoursesRegistrationSessionManagement"))
		{
			try {
				JComponent coursesRegistrationSessionManagement = new CoursesRegistrationSessionManagement();
				coursesRegistrationSessionManagement.setOpaque(true);
				coursesRegistrationSessionManagement.setVisible(true);
			} catch (IOException e1) {
				
			} catch (URISyntaxException e1) {
				
			}
			
	    }
		else if (strActionCommand.equals("CoursesManagement"))
		{
			try {
				JComponent coursesManagement = new CoursesManagement();
				coursesManagement.setOpaque(true);
				coursesManagement.setVisible(true);
			} catch (IOException e1) {
				
			} catch (URISyntaxException e1) {
				
			}
			
	    }
		else if (strActionCommand.equals("MyAccount"))
		{
			
	    }
		else if (strActionCommand.equals("Logout"))
		{
			
	    }
		else if (strActionCommand.equals("Exit"))
		{
			this.frame.dispose();
			System.exit(0);
	    }
	}
}