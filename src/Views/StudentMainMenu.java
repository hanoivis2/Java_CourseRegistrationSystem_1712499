package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URISyntaxException;


public class StudentMainMenu extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton btn_myAccount;
	JButton btn_reviewCourses;
	JButton btn_logout;
	JButton btn_exit;
	
	JFrame frame;
	
	private String studentId;
	
	
	public final static boolean RIGHT_TO_LEFT = false;
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
			}
		});

	}
	
	public StudentMainMenu(String studentId) {
		 super(new GridLayout(2,2));
		 
		 this.studentId = studentId;
		 
		 JFrame.setDefaultLookAndFeelDecorated(true);
         frame = new JFrame("Student Main Menu");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setPreferredSize(new Dimension(900,300));
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
         frame.setLocation(dim.width/2 - 450, dim.height/2 - 150);
	
         btn_myAccount = new JButton("My account");
         btn_myAccount.addActionListener(this);
         btn_myAccount.setActionCommand("MyAccount");
		 
         btn_reviewCourses = new JButton("Review courses");
         btn_reviewCourses.addActionListener(this);
         btn_reviewCourses.setActionCommand("ReviewCourses");
         
         btn_logout = new JButton("Log out");
         btn_logout.addActionListener(this);
         btn_logout.setActionCommand("Logout");
         
         btn_exit = new JButton("Exit");
         btn_exit.addActionListener(this);
         btn_exit.setActionCommand("Exit");
		 
		 add(btn_myAccount);
		 add(btn_reviewCourses);
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
		if (strActionCommand.equals("MyAccount"))
		{
			
			
			try {
				JComponent myStudentAccount;
				myStudentAccount = new MyStudentAccount(this.studentId);
				myStudentAccount.setOpaque(true);
				myStudentAccount.setVisible(true);
			} catch (IOException e1) {

			} catch (URISyntaxException e1) {

			}
			
	    }
		else if (strActionCommand.equals("ReviewCourses"))
		{	
			try {
				JComponent courseList;
				courseList = new CourseList(this.studentId);
				courseList.setOpaque(true);
				courseList.setVisible(true);
			} catch (IOException e1) {
				
			} catch (URISyntaxException e1) {
				
			}
			
		}
		else if (strActionCommand.equals("Logout"))
		{
			this.frame.dispose();
			JComponent loginBox = new LoginBox();
			loginBox.setOpaque(true);
			loginBox.setVisible(true);
		}
		else if (strActionCommand.equals("Exit"))
		{
			this.frame.dispose();
			System.exit(0);
	    }
	}
}