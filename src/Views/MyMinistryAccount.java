package Views;

import javax.swing.*;

import DAO.MinistryAccountDAO;
import Models.MinistryAccount;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class MyMinistryAccount extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JTextField txt_fullName;
	JTextField txt_ministryId;
	JPasswordField txt_password;
	JPasswordField txt_confirmPassword;
	JTextArea txt_description;
	JButton btn_confirm;
	JFrame frame;
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
			}
		});
	
	}
	
	
	public MyMinistryAccount(String ministryId) throws IOException, URISyntaxException {
		super(new BorderLayout());


		JFrame.setDefaultLookAndFeelDecorated(true);
		
        frame = new JFrame("My account");
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
		JPanel buttonRow = new JPanel();
		SpringLayout layoutRow1 = new SpringLayout();
		SpringLayout layoutRow2 = new SpringLayout();
		SpringLayout layoutRow3 = new SpringLayout();
		SpringLayout layoutRow4 = new SpringLayout();
		SpringLayout layoutRow5 = new SpringLayout();
		SpringLayout layoutButtonRow = new SpringLayout();
		row1.setLayout(layoutRow1);	
		row2.setLayout(layoutRow2);	
		row3.setLayout(layoutRow3);	
		row4.setLayout(layoutRow4);	
		row5.setLayout(layoutRow5);	
		buttonRow.setLayout(layoutButtonRow);	

		row1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		row2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		row3.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		row4.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		row5.setMaximumSize(new Dimension(Integer.MAX_VALUE, frame.getPreferredSize().height - 320));
		buttonRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
		
		btn_confirm = new JButton("Edit account");
		btn_confirm.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_confirm.addActionListener(this);
		btn_confirm.setActionCommand("Confirm");
		btn_confirm.setPreferredSize(new Dimension(0,60));
		
		
		JLabel lbl_row1 = new JLabel("Full name: ");
		JLabel lbl_row2 = new JLabel("Username: ");
		JLabel lbl_row3 = new JLabel("Password: ");
		JLabel lbl_row4 = new JLabel("Confirm password: ");
		JLabel lbl_row5 = new JLabel("Description: ");

		
		lbl_row1.setPreferredSize(new Dimension(175,50));
		lbl_row2.setPreferredSize(new Dimension(175,50));
		lbl_row3.setPreferredSize(new Dimension(175,50));
		lbl_row4.setPreferredSize(new Dimension(175,50));
		lbl_row5.setPreferredSize(new Dimension(175,50));
		
	
		txt_fullName = new JTextField("Enter ministry's full name...", 15);
		txt_fullName.setForeground(Color.BLACK);
		txt_fullName.setPreferredSize(new Dimension(0,50));
		txt_fullName.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (txt_fullName.getText().equals("Enter ministry's full name...")) {
		        	txt_fullName.setText("");
		        	txt_fullName.setForeground(Color.BLACK);
		        }
		    }
		    
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (txt_fullName.getText().isEmpty()) {
		        	txt_fullName.setForeground(Color.GRAY);
		        	txt_fullName.setText("Enter ministry's full name...");
		        }
		    }
	    });
		
		txt_ministryId = new JTextField("Enter ministry's id...", 15);
		txt_ministryId.setForeground(Color.GRAY);
		txt_ministryId.setEditable(false);
		txt_ministryId.setPreferredSize(new Dimension(0,50));
		txt_ministryId.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (txt_ministryId.getText().equals("Enter ministry's id...")) {
		        	txt_ministryId.setText("");
		        	txt_ministryId.setForeground(Color.BLACK);
		        }
		    }
		    
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (txt_ministryId.getText().isEmpty()) {
		        	txt_ministryId.setForeground(Color.GRAY);
		        	txt_ministryId.setText("Enter ministry's id...");
		        }
		    }
	    });
		
		txt_password = new JPasswordField();
		txt_password.setForeground(Color.BLACK);
		txt_password.setPreferredSize(new Dimension(0,50));

		
		txt_confirmPassword = new JPasswordField();
		txt_confirmPassword.setForeground(Color.BLACK);
		txt_confirmPassword.setPreferredSize(new Dimension(0,50));

		
		txt_description = new JTextArea("Enter description...", 0, 1);
		txt_description.setForeground(Color.BLACK);
		txt_description.setPreferredSize(new Dimension(0,50));
		txt_description.setLineWrap(true);
		txt_description.setBorder(BorderFactory.createCompoundBorder(
				txt_description.getBorder(), 
		        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		txt_description.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (txt_description.getText().equals("Enter description...")) {
		        	txt_description.setText("");
		        	txt_description.setForeground(Color.BLACK);
		        }
		    }
		    
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (txt_description.getText().isEmpty()) {
		        	txt_description.setForeground(Color.GRAY);
		        	txt_description.setText("Enter description...");
		        }
		    }
	    });
		
		
		row1.add(lbl_row1);
		row1.add(txt_fullName);
		
		row2.add(lbl_row2);
		row2.add(txt_ministryId);
		
		row3.add(lbl_row3);
		row3.add(txt_password);
		
		row4.add(lbl_row4);
		row4.add(txt_confirmPassword);
		
		row5.add(lbl_row5);
		row5.add(txt_description);
		
		
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
        layoutRow2.putConstraint(SpringLayout.NORTH, txt_ministryId, 10, SpringLayout.NORTH, row2);
        layoutRow2.putConstraint(SpringLayout.SOUTH, txt_ministryId, 0, SpringLayout.SOUTH, row2);
        layoutRow2.putConstraint(SpringLayout.WEST, lbl_row2, 15, SpringLayout.WEST, row2);
        layoutRow2.putConstraint(SpringLayout.WEST, txt_ministryId, 5, SpringLayout.EAST, lbl_row2);
        layoutRow2.putConstraint(SpringLayout.EAST, txt_ministryId, -15, SpringLayout.EAST, row2);
        
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
        layoutRow5.putConstraint(SpringLayout.NORTH, txt_description, 10, SpringLayout.NORTH, row5);
        layoutRow5.putConstraint(SpringLayout.SOUTH, txt_description, 0, SpringLayout.SOUTH, row5);
        layoutRow5.putConstraint(SpringLayout.WEST, lbl_row5, 15, SpringLayout.WEST, row4);
        layoutRow5.putConstraint(SpringLayout.WEST, txt_description, 5, SpringLayout.EAST, lbl_row5);
        layoutRow5.putConstraint(SpringLayout.EAST, txt_description, -15, SpringLayout.EAST, row5);
        
      
        layoutButtonRow.putConstraint(SpringLayout.NORTH, btn_confirm, 10, SpringLayout.NORTH, buttonRow);
        layoutButtonRow.putConstraint(SpringLayout.SOUTH, btn_confirm, -10, SpringLayout.SOUTH, buttonRow);
		layoutButtonRow.putConstraint(SpringLayout.WEST, btn_confirm, 15, SpringLayout.WEST, buttonRow);
		layoutButtonRow.putConstraint(SpringLayout.EAST, btn_confirm, -15, SpringLayout.EAST, buttonRow);
		
		
		
		row1.setAlignmentY(Component.CENTER_ALIGNMENT);
		row2.setAlignmentY(Component.CENTER_ALIGNMENT);
		row3.setAlignmentY(Component.CENTER_ALIGNMENT);
		row4.setAlignmentY(Component.CENTER_ALIGNMENT);
		buttonRow.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		pane.add(row1, BorderLayout.CENTER);
		pane.add(row2, BorderLayout.CENTER);
		pane.add(row3, BorderLayout.CENTER);
		pane.add(row4, BorderLayout.CENTER);
		pane.add(row5, BorderLayout.CENTER);
		pane.add(buttonRow, BorderLayout.CENTER);
		
		add(pane);
		
		MinistryAccount accountData = MinistryAccountDAO.getMinistryAccountById(ministryId);
		
		txt_fullName.setText(accountData.getFullname());
		txt_ministryId.setText(accountData.getUsername());
		txt_password.setText(accountData.getPassword());
		txt_confirmPassword.setText(accountData.getPassword());
		txt_description.setText(accountData.getDescription());
		

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
			
			
			if (txt_fullName.getText().equals("Enter ministry's full name...")) {
				showMessageDialog(null, "Please ministry's full !");
			}
			else if (txt_ministryId.getText().equals("Enter ministry's id...")) {
				showMessageDialog(null, "Please ministry's id !");
			}
			else if (txt_password.getText().isEmpty() || txt_confirmPassword.getText().isEmpty()) {
				showMessageDialog(null, "Enter password !");
			}
			else if (!txt_password.getText().equals(txt_confirmPassword.getText())) {
				showMessageDialog(null, "Confirm passoword does not match !");
			}
			else {
				MinistryAccount newAccount = new MinistryAccount();
				
				newAccount.setUsername(txt_ministryId.getText());
				newAccount.setFullname(txt_fullName.getText());
				newAccount.setPassword(txt_password.getText());
				if (txt_description.getText().equals("Enter description...")) {
					newAccount.setDescription("");
				}
				else {
					newAccount.setDescription(txt_description.getText());
				}
				
				if (!txt_password.getText().equals(txt_confirmPassword.getText())) {
					showMessageDialog(null, "Confirm passoword does not match !");
				}
				else {
					int status = MinistryAccountDAO.updateMinistryAccount(newAccount);
					if (status == -1) {
						showMessageDialog(null, "This username has already been created!");
					}
					else {
						this.frame.dispose();
						showMessageDialog(null, "Edited and changed password successfully!");
					}
				}
				
				
			}
			

			
		}
		
	}
}


