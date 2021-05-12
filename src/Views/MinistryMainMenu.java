package Views;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class MinistryMainMenu extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton btn_ministryAccountManagement;
	JButton btn_subjectManagement;
	JButton btn_semesterManagement;
	JButton btn_classManagement;
	JButton btn_studentInClassManagement;
	JButton btn_studentInCourseManagement;
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
		 super(new GridLayout(3,4));
		 
		 JFrame.setDefaultLookAndFeelDecorated(true);
         frame = new JFrame("Ministry Main Menu");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setPreferredSize(new Dimension(1200,600));
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
         frame.setLocation(dim.width/2 - 600, dim.height/2 - 300);
	
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
		 
         btn_studentInClassManagement = new JButton("Student in class management");
         btn_studentInClassManagement.addActionListener(this);
         btn_studentInClassManagement.setActionCommand("StudentInClassManagement");
         
         btn_studentInCourseManagement = new JButton("Student in course management");
         btn_studentInCourseManagement.addActionListener(this);
         btn_studentInCourseManagement.setActionCommand("StudentInCourseManagement");
		 
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
		 
		 add(btn_ministryAccountManagement);
		 add(btn_subjectManagement);
		 add(btn_semesterManagement);
		 add(btn_classManagement);
		 add(btn_studentInClassManagement);
		 add(btn_studentInCourseManagement);
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
			
	    }
		else if (strActionCommand.equals("SubjectManagement"))
		{

		}
		else if (strActionCommand.equals("SemesterManagement"))
		{
			
		     
	    }
		else if (strActionCommand.equals("ClassManagement"))
		{
			
	    }
		else if (strActionCommand.equals("StudentInClassManagement"))
		{
			
	    }
		else if (strActionCommand.equals("StudentInCourseManagement"))
		{
			
	    }
		else if (strActionCommand.equals("CoursesRegistrationSessionManagement"))
		{
			
	    }
		else if (strActionCommand.equals("CoursesManagement"))
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