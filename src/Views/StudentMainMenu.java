package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class StudentMainMenu extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton btn_registerCourses;
	JButton btn_reviewCourses;
	JButton btn_logout;
	JButton btn_exit;
	
	JFrame frame;
	
	
	public final static boolean RIGHT_TO_LEFT = false;
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
			}
		});

		JComponent mainMenu = new StudentMainMenu();
		mainMenu.setOpaque(true);
		mainMenu.setVisible(true);
	}
	
	public StudentMainMenu() {
		 super(new GridLayout(2,2));
		 
		 JFrame.setDefaultLookAndFeelDecorated(true);
         frame = new JFrame("Student Main Menu");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setPreferredSize(new Dimension(900,300));
         Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
         frame.setLocation(dim.width/2 - 450, dim.height/2 - 150);
	
         btn_registerCourses = new JButton("Register courses");
         btn_registerCourses.addActionListener(this);
         btn_registerCourses.setActionCommand("RegisterCourses");
		 
         btn_reviewCourses = new JButton("Review courses");
         btn_reviewCourses.addActionListener(this);
         btn_reviewCourses.setActionCommand("ReviewCourses");
         
         btn_logout = new JButton("Log out");
         btn_logout.addActionListener(this);
         btn_logout.setActionCommand("Logout");
         
         btn_exit = new JButton("Exit");
         btn_exit.addActionListener(this);
         btn_exit.setActionCommand("Exit");
		 
		 add(btn_registerCourses);
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
		if (strActionCommand.equals("RegisterCourses"))
		{
			
	    }
		else if (strActionCommand.equals("ReviewCourses"))
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