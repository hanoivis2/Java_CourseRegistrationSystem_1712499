package Views;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import DAO.ClassDAO;
import DAO.StudentAccountDAO;
import Models.StudentAccount;
import Models.Class;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;

public class CreateStudentAccountForm extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JTextField txt_fullName;
	JTextField txt_Id;
	JPasswordField txt_password;
	JPasswordField txt_confirmPassword;
	JDateChooser birthdayChooser;
	JTextField txt_birthplace;
	JComboBox<String> gender;
	JButton btn_confirm;
	JFrame frame;
	
	private Action action;
	private String classId;
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
			}
		});
	
	}
	
	
	public CreateStudentAccountForm(Action action, String classId) throws IOException, URISyntaxException {
		super(new BorderLayout());
		
		this.action = action;
		this.classId = classId;

		JFrame.setDefaultLookAndFeelDecorated(true);
		
        frame = new JFrame("Add student account");
        frame.setPreferredSize(new Dimension(600,550));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2 - 300, dim.height/2 - 275);
		
		Container pane = frame.getContentPane();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		
		JPanel row1 = new JPanel();
		JPanel row2 = new JPanel();
		JPanel row3 = new JPanel();
		JPanel row4 = new JPanel();
		JPanel row5 = new JPanel();
		JPanel row6 = new JPanel();
		JPanel row7 = new JPanel();
		JPanel buttonRow = new JPanel();
		SpringLayout layoutRow1 = new SpringLayout();
		SpringLayout layoutRow2 = new SpringLayout();
		SpringLayout layoutRow3 = new SpringLayout();
		SpringLayout layoutRow4 = new SpringLayout();
		SpringLayout layoutRow5 = new SpringLayout();
		SpringLayout layoutRow6 = new SpringLayout();
		SpringLayout layoutRow7 = new SpringLayout();
		SpringLayout layoutButtonRow = new SpringLayout();
		row1.setLayout(layoutRow1);	
		row2.setLayout(layoutRow2);	
		row3.setLayout(layoutRow3);	
		row4.setLayout(layoutRow4);	
		row5.setLayout(layoutRow5);	
		row6.setLayout(layoutRow6);	
		row7.setLayout(layoutRow7);	
		buttonRow.setLayout(layoutButtonRow);	

		row1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		row2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		row3.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		row4.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		row5.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		row6.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		row7.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		buttonRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, frame.getPreferredSize().height - 300));
		
		btn_confirm = new JButton("Add account");
		btn_confirm.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_confirm.addActionListener(this);
		btn_confirm.setActionCommand("Confirm");
		btn_confirm.setPreferredSize(new Dimension(0,60));
		
		
		JLabel lbl_row1 = new JLabel("Student's full name: ");
		JLabel lbl_row2 = new JLabel("Student's id: ");
		JLabel lbl_row3 = new JLabel("Password: ");
		JLabel lbl_row4 = new JLabel("Confirm password: ");
		JLabel lbl_row5 = new JLabel("Birthday: ");
		JLabel lbl_row6 = new JLabel("Birthplace: ");
		JLabel lbl_row7 = new JLabel("Gender: ");
		
		
		String[] genders = {"Female", "Male"};
		this.gender = new JComboBox<>(genders);
		this.gender.setMaximumSize(new Dimension(0, 60));

		
		lbl_row1.setPreferredSize(new Dimension(175,50));
		lbl_row2.setPreferredSize(new Dimension(175,50));
		lbl_row3.setPreferredSize(new Dimension(175,50));
		lbl_row4.setPreferredSize(new Dimension(175,50));
		lbl_row5.setPreferredSize(new Dimension(175,50));
		lbl_row6.setPreferredSize(new Dimension(175,50));
		lbl_row7.setPreferredSize(new Dimension(175,50));
		
	
		txt_fullName = new JTextField("Enter student's full name...", 15);
		txt_fullName.setForeground(Color.GRAY);
		txt_fullName.setPreferredSize(new Dimension(0,50));
		txt_fullName.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (txt_fullName.getText().equals("Enter student's full name...")) {
		        	txt_fullName.setText("");
		        	txt_fullName.setForeground(Color.BLACK);
		        }
		    }
		    
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (txt_fullName.getText().isEmpty()) {
		        	txt_fullName.setForeground(Color.GRAY);
		        	txt_fullName.setText("Enter student's full name...");
		        }
		    }
	    });
		
		txt_Id = new JTextField("Enter student's id...", 15);
		txt_Id.setForeground(Color.GRAY);
		txt_Id.setPreferredSize(new Dimension(0,50));
		txt_Id.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (txt_Id.getText().equals("Enter student's id...")) {
		        	txt_Id.setText("");
		        	txt_Id.setForeground(Color.BLACK);
		        }
		    }
		    
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (txt_Id.getText().isEmpty()) {
		        	txt_Id.setForeground(Color.GRAY);
		        	txt_Id.setText("Enter student's id...");
		        }
		    }
	    });
		
		txt_password = new JPasswordField();
		txt_password.setForeground(Color.BLACK);
		txt_password.setPreferredSize(new Dimension(0,50));

		
		txt_confirmPassword = new JPasswordField();
		txt_confirmPassword.setForeground(Color.BLACK);
		txt_confirmPassword.setPreferredSize(new Dimension(0,50));

		
		birthdayChooser = new JDateChooser();
		birthdayChooser.setDateFormatString("dd/MM/yyyy");
		birthdayChooser.getCalendarButton().setPreferredSize(new Dimension(175, 0));
		birthdayChooser.getCalendarButton().setIcon(null);
		birthdayChooser.getCalendarButton().setText("Choose birthday");
		
		txt_birthplace = new JTextField("Enter student's place of birth...", 15);
		txt_birthplace.setForeground(Color.GRAY);
		txt_birthplace.setPreferredSize(new Dimension(0,50));
		txt_birthplace.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (txt_birthplace.getText().equals("Enter student's place of birth...")) {
		        	txt_birthplace.setText("");
		        	txt_birthplace.setForeground(Color.BLACK);
		        }
		    }
		    
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (txt_birthplace.getText().isEmpty()) {
		        	txt_birthplace.setForeground(Color.GRAY);
		        	txt_birthplace.setText("Enter student's place of birth...");
		        }
		    }
	    });
		
		row1.add(lbl_row1);
		row1.add(txt_fullName);
		
		row2.add(lbl_row2);
		row2.add(txt_Id);
		
		row3.add(lbl_row3);
		row3.add(txt_password);
		
		row4.add(lbl_row4);
		row4.add(txt_confirmPassword);
		
		row5.add(lbl_row5);
		row5.add(birthdayChooser);
		
		row6.add(lbl_row6);
		row6.add(txt_birthplace);
		
		row7.add(lbl_row7);
		row7.add(gender);
		
		
        buttonRow.add(btn_confirm);
		
        layoutRow1.putConstraint(SpringLayout.NORTH, lbl_row1, 0, SpringLayout.NORTH, row1);
        layoutRow1.putConstraint(SpringLayout.SOUTH, lbl_row1, 0, SpringLayout.SOUTH, row1);
        layoutRow1.putConstraint(SpringLayout.NORTH, txt_fullName, 10, SpringLayout.NORTH, row1);
        layoutRow1.putConstraint(SpringLayout.SOUTH, txt_fullName, 0, SpringLayout.SOUTH, row1);
        layoutRow1.putConstraint(SpringLayout.WEST, lbl_row1, 15, SpringLayout.WEST, row1);
        layoutRow1.putConstraint(SpringLayout.WEST, txt_fullName, 5, SpringLayout.EAST, lbl_row1);
        layoutRow1.putConstraint(SpringLayout.EAST, txt_fullName, -15, SpringLayout.EAST, row1);
        
        layoutRow2.putConstraint(SpringLayout.NORTH, lbl_row2, 0, SpringLayout.NORTH, row2);
        layoutRow2.putConstraint(SpringLayout.SOUTH, lbl_row2, 0, SpringLayout.SOUTH, row2);
        layoutRow2.putConstraint(SpringLayout.NORTH, txt_Id, 10, SpringLayout.NORTH, row2);
        layoutRow2.putConstraint(SpringLayout.SOUTH, txt_Id, 0, SpringLayout.SOUTH, row2);
        layoutRow2.putConstraint(SpringLayout.WEST, lbl_row2, 15, SpringLayout.WEST, row2);
        layoutRow2.putConstraint(SpringLayout.WEST, txt_Id, 5, SpringLayout.EAST, lbl_row2);
        layoutRow2.putConstraint(SpringLayout.EAST, txt_Id, -15, SpringLayout.EAST, row2);
        
        layoutRow3.putConstraint(SpringLayout.NORTH, lbl_row3, 0, SpringLayout.NORTH, row3);
        layoutRow3.putConstraint(SpringLayout.SOUTH, lbl_row3, 0, SpringLayout.SOUTH, row3);
        layoutRow3.putConstraint(SpringLayout.NORTH, txt_password, 10, SpringLayout.NORTH, row3);
        layoutRow3.putConstraint(SpringLayout.SOUTH, txt_password, 0, SpringLayout.SOUTH, row3);
        layoutRow3.putConstraint(SpringLayout.WEST, lbl_row3, 15, SpringLayout.WEST, row3);
        layoutRow3.putConstraint(SpringLayout.WEST, txt_password, 5, SpringLayout.EAST, lbl_row3);
        layoutRow3.putConstraint(SpringLayout.EAST, txt_password, -15, SpringLayout.EAST, row3);
        
        layoutRow4.putConstraint(SpringLayout.NORTH, lbl_row4, 0, SpringLayout.NORTH, row4);
        layoutRow4.putConstraint(SpringLayout.SOUTH, lbl_row4, 0, SpringLayout.SOUTH, row4);
        layoutRow4.putConstraint(SpringLayout.NORTH, txt_confirmPassword, 10, SpringLayout.NORTH, row4);
        layoutRow4.putConstraint(SpringLayout.SOUTH, txt_confirmPassword, 0, SpringLayout.SOUTH, row4);
        layoutRow4.putConstraint(SpringLayout.WEST, lbl_row4, 15, SpringLayout.WEST, row4);
        layoutRow4.putConstraint(SpringLayout.WEST, txt_confirmPassword, 5, SpringLayout.EAST, lbl_row4);
        layoutRow4.putConstraint(SpringLayout.EAST, txt_confirmPassword, -15, SpringLayout.EAST, row4);
        
        layoutRow5.putConstraint(SpringLayout.NORTH, lbl_row5, 0, SpringLayout.NORTH, row5);
        layoutRow5.putConstraint(SpringLayout.SOUTH, lbl_row5, 0, SpringLayout.SOUTH, row5);
        layoutRow5.putConstraint(SpringLayout.NORTH, birthdayChooser, 10, SpringLayout.NORTH, row5);
        layoutRow5.putConstraint(SpringLayout.SOUTH, birthdayChooser, 0, SpringLayout.SOUTH, row5);
        layoutRow5.putConstraint(SpringLayout.WEST, lbl_row5, 15, SpringLayout.WEST, row5);
        layoutRow5.putConstraint(SpringLayout.WEST, birthdayChooser, 5, SpringLayout.EAST, lbl_row5);
        layoutRow5.putConstraint(SpringLayout.EAST, birthdayChooser, -15, SpringLayout.EAST, row5);
        
        layoutRow6.putConstraint(SpringLayout.NORTH, lbl_row6, 0, SpringLayout.NORTH, row6);
        layoutRow6.putConstraint(SpringLayout.SOUTH, lbl_row6, 0, SpringLayout.SOUTH, row6);
        layoutRow6.putConstraint(SpringLayout.NORTH, txt_birthplace, 10, SpringLayout.NORTH, row6);
        layoutRow6.putConstraint(SpringLayout.SOUTH, txt_birthplace, 0, SpringLayout.SOUTH, row6);
        layoutRow6.putConstraint(SpringLayout.WEST, lbl_row6, 15, SpringLayout.WEST, row6);
        layoutRow6.putConstraint(SpringLayout.WEST, txt_birthplace, 5, SpringLayout.EAST, lbl_row6);
        layoutRow6.putConstraint(SpringLayout.EAST, txt_birthplace, -15, SpringLayout.EAST, row6);
        
        layoutRow7.putConstraint(SpringLayout.NORTH, lbl_row7, 0, SpringLayout.NORTH, row7);
        layoutRow7.putConstraint(SpringLayout.SOUTH, lbl_row7, 0, SpringLayout.SOUTH, row7);
        layoutRow7.putConstraint(SpringLayout.NORTH, gender, 10, SpringLayout.NORTH, row7);
        layoutRow7.putConstraint(SpringLayout.SOUTH, gender, 0, SpringLayout.SOUTH, row7);
        layoutRow7.putConstraint(SpringLayout.WEST, lbl_row7, 15, SpringLayout.WEST, row7);
        layoutRow7.putConstraint(SpringLayout.WEST, gender, 5, SpringLayout.EAST, lbl_row7);
        layoutRow7.putConstraint(SpringLayout.EAST, gender, -15, SpringLayout.EAST, row7);
        
      
        layoutButtonRow.putConstraint(SpringLayout.SOUTH, btn_confirm, -10, SpringLayout.SOUTH, buttonRow);
		layoutButtonRow.putConstraint(SpringLayout.WEST, btn_confirm, 15, SpringLayout.WEST, buttonRow);
		layoutButtonRow.putConstraint(SpringLayout.EAST, btn_confirm, -15, SpringLayout.EAST, buttonRow);
		
		
		
		row1.setAlignmentY(Component.CENTER_ALIGNMENT);
		row2.setAlignmentY(Component.CENTER_ALIGNMENT);
		row3.setAlignmentY(Component.CENTER_ALIGNMENT);
		row4.setAlignmentY(Component.CENTER_ALIGNMENT);
		row5.setAlignmentY(Component.CENTER_ALIGNMENT);
		row6.setAlignmentY(Component.CENTER_ALIGNMENT);
		row7.setAlignmentY(Component.CENTER_ALIGNMENT);
		buttonRow.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		pane.add(row1, BorderLayout.CENTER);
		pane.add(row2, BorderLayout.CENTER);
		pane.add(row3, BorderLayout.CENTER);
		pane.add(row4, BorderLayout.CENTER);
		pane.add(row5, BorderLayout.CENTER);
		pane.add(row6, BorderLayout.CENTER);
		pane.add(row7, BorderLayout.CENTER);
		pane.add(buttonRow, BorderLayout.CENTER);
		
		add(pane);
		

        this.setOpaque(true); 
        frame.setContentPane(this);
        
        
        frame.pack();
        frame.setVisible(true);
	}
	
	
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e)
	{
		String strActionCommand = e.getActionCommand();
		if (strActionCommand.equals("Confirm"))
		{
			
			
			if (txt_fullName.getText().equals("Enter student's full name...")) {
				showMessageDialog(null, "Please ministry's full !");
			}
			else if (txt_Id.getText().equals("Enter student's id...")) {
				showMessageDialog(null, "Please ministry's id !");
			}
			else if (txt_password.getText().isEmpty() || txt_confirmPassword.getText().isEmpty()) {
				showMessageDialog(null, "Enter password !");
			}
			else if (!txt_password.getText().equals(txt_confirmPassword.getText())) {
				showMessageDialog(null, "Confirm passoword does not match !");
			}
			else {
				
				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					String birthday = dateFormat.format(birthdayChooser.getDate());
					
					StudentAccount newAccount = new StudentAccount();
					newAccount.setId(txt_Id.getText());
					newAccount.setBirthday(birthday);
					newAccount.setFullName(txt_fullName.getText());
					newAccount.setPassword(txt_password.getText());
					newAccount.setClassId(this.classId);
					newAccount.setGender((short) gender.getSelectedIndex());
					Class mainClass = ClassDAO.getClassById(this.classId);
					newAccount.setMainClass(mainClass);
					
					if (txt_birthplace.getText().equals("Enter student's place of birth...")) {
						newAccount.setBirthplace("");
					}
					else {
						newAccount.setBirthplace(txt_birthplace.getText());
					}
					int status = StudentAccountDAO.addStudentAccount(newAccount);
					if (status == -1) {
						showMessageDialog(null, "This username has already been created!");
					}
					else {
						
						mainClass.setTotalStudents((short) (mainClass.getTotalStudents() + 1));
						if (gender.getSelectedIndex() == 0) {
							mainClass.setTotalFemale((short) (mainClass.getTotalFemale() + 1));
						}
						else {
							mainClass.setTotalMale((short) (mainClass.getTotalMale() + 1));
						}
						
						ClassDAO.updateClass(mainClass);
						
							
						ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "refresh");
						action.actionPerformed(event);
						showMessageDialog(null, "Added successfully!");
					}
					
				} catch (Exception e1) {
					showMessageDialog(null, "Invalid date");
				}
				
				
			}
			

			
		}
		
	}
}


