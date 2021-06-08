package Views;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import DAO.MinistryAccountDAO;
import DAO.StudentAccountDAO;
import Models.MinistryAccount;
import Models.StudentAccount;


public class LoginBox extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField txt_username;
	JPasswordField txt_password;
	JButton btn_login;
	
	JFrame frame;
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
			}
		});
		
		JComponent mainMenu = new LoginBox();
		mainMenu.setOpaque(true);
		mainMenu.setVisible(true);
	}
	
	public LoginBox() {
		super(new BorderLayout());

		JFrame.setDefaultLookAndFeelDecorated(true);
        
        frame = new JFrame("Login box");
        frame.setPreferredSize(new Dimension(400,175));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2 - 200, dim.height/2 - 135);
		
		Container pane = frame.getContentPane();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		
		JPanel aboveRow = new JPanel();
		SpringLayout layoutTop = new SpringLayout();
		aboveRow.setLayout(layoutTop);
		
		JPanel bellowRow = new JPanel();
		SpringLayout layoutBot = new SpringLayout();
		bellowRow.setLayout(layoutBot);
		
		JLabel lbl_username = new JLabel("Username: ");
        txt_username = new JTextField("", 15);
        lbl_username.setPreferredSize(new Dimension(120,30));
        txt_username.setPreferredSize(new Dimension(0,30));
        aboveRow.add(lbl_username);
        aboveRow.add(txt_username);
		
		JLabel lbl_password = new JLabel("Password: ");
        txt_password = new JPasswordField("", 15);
        lbl_password.setPreferredSize(new Dimension(120,30));
        txt_password.setPreferredSize(new Dimension(0,30));
        bellowRow.add(lbl_password);
        bellowRow.add(txt_password);
		
		layoutTop.putConstraint(SpringLayout.WEST, lbl_username, 15, SpringLayout.WEST, aboveRow);
		layoutTop.putConstraint(SpringLayout.NORTH, lbl_username, 15, SpringLayout.NORTH, aboveRow);
		layoutTop.putConstraint(SpringLayout.EAST, aboveRow, 15, SpringLayout.EAST, txt_username);
		layoutTop.putConstraint(SpringLayout.NORTH, txt_username, 15, SpringLayout.NORTH, aboveRow);
		layoutTop.putConstraint(SpringLayout.WEST, txt_username, 5, SpringLayout.EAST, lbl_username);
		
		layoutBot.putConstraint(SpringLayout.WEST, lbl_password, 15, SpringLayout.WEST, bellowRow);
		layoutBot.putConstraint(SpringLayout.NORTH, lbl_password, 15, SpringLayout.NORTH, bellowRow);
		layoutBot.putConstraint(SpringLayout.EAST, bellowRow, 15, SpringLayout.EAST, txt_password);
		layoutBot.putConstraint(SpringLayout.NORTH, txt_password, 15, SpringLayout.NORTH, bellowRow);
		layoutBot.putConstraint(SpringLayout.WEST, txt_password, 5, SpringLayout.EAST, lbl_password);
		
		aboveRow.setAlignmentY(Component.CENTER_ALIGNMENT);
		bellowRow.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		btn_login = new JButton("Login");
		btn_login.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_login.addActionListener(this);
		btn_login.setActionCommand("Login");
		
		pane.add(aboveRow, BorderLayout.CENTER);
		pane.add(bellowRow, BorderLayout.CENTER);
		pane.add(btn_login, BorderLayout.CENTER);
		
		add(pane);
		

        this.setOpaque(true); //content panes must be opaque
        frame.setContentPane(this);
        
        
        frame.pack();
        frame.setVisible(true);
	}
	
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e)
	{
		String strActionCommand = e.getActionCommand();
		if (strActionCommand.equals("Login")) {
			
			String username = txt_username.getText();
			String password = txt_password.getText();
			
			if (username.length() < 2 || password.isEmpty()) {
				showMessageDialog(null, "Username or password is not valid!");
			}
			else {
				String firstTwoChars = username.substring(0, 2);
				
				if (firstTwoChars.equals("GV")) {
					if (MinistryAccountDAO.getMinistryAccountById(username) == null) {
						showMessageDialog(null, "Username or password is incorrect!");
					}
					else {
						MinistryAccount account = MinistryAccountDAO.getMinistryAccountById(username);
						if (account.getPassword().equals(password)) {
							JComponent ministryMainMenu = new MinistryMainMenu(username);
							ministryMainMenu.setOpaque(true);
							ministryMainMenu.setVisible(true);
						}
						else {
							showMessageDialog(null, "Username or password is incorrect!");
						}
					}
				}
				else {
					if (StudentAccountDAO.getStudentAccountById(username) == null) {
						showMessageDialog(null, "Username or password is incorrect!");
					}
					else {
						StudentAccount account = StudentAccountDAO.getStudentAccountById(username);
						if (account.getPassword().equals(password)) {
							JComponent studentMainMenu = new StudentMainMenu(username);
							studentMainMenu.setOpaque(true);
							studentMainMenu.setVisible(true);
						}
						else {
							showMessageDialog(null, "Username or password is incorrect!");
						}
					}
				}
			}
			
		}

	}
}
