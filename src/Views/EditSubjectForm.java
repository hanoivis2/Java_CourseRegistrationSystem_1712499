package Views;

import javax.swing.*;

import DAO.SubjectDAO;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URISyntaxException;
import static javax.swing.JOptionPane.showMessageDialog;
import Models.Subject;

public class EditSubjectForm extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JTextField txt_id;
	JTextField txt_credits;
	JTextArea txt_name;
	JButton btn_confirm;
	JFrame frame;
	private Action action;
	
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
			}
		});
	
	}
	
	
	public EditSubjectForm(Action actionFresh, Subject subjectData) throws IOException, URISyntaxException {
		super(new BorderLayout());

		
		this.action = actionFresh;
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		
        frame = new JFrame("Edit subject");
        frame.setPreferredSize(new Dimension(600,550));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2 - 300, dim.height/2 - 275);
		
		Container pane = frame.getContentPane();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		
		JPanel row1 = new JPanel();
		JPanel row2 = new JPanel();
		JPanel row3 = new JPanel();
		JPanel buttonRow = new JPanel();
		SpringLayout layoutRow1 = new SpringLayout();
		SpringLayout layoutRow2 = new SpringLayout();
		SpringLayout layoutRow3 = new SpringLayout();
		SpringLayout layoutButtonRow = new SpringLayout();
		row1.setLayout(layoutRow1);	
		row2.setLayout(layoutRow2);	
		row3.setLayout(layoutRow3);	
		buttonRow.setLayout(layoutButtonRow);	

		row1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		row2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		row3.setMaximumSize(new Dimension(Integer.MAX_VALUE, frame.getPreferredSize().height - 200));
		buttonRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
		
		btn_confirm = new JButton("Edit subject");
		btn_confirm.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_confirm.addActionListener(this);
		btn_confirm.setActionCommand("Confirm");
		btn_confirm.setPreferredSize(new Dimension(0,60));
		
		
		JLabel lbl_row1 = new JLabel("Subject' id: ");
		JLabel lbl_row2 = new JLabel("Credits: ");
		JLabel lbl_row3 = new JLabel("Subject's name: ");

		
		lbl_row1.setPreferredSize(new Dimension(175,50));
		lbl_row2.setPreferredSize(new Dimension(175,50));
		lbl_row3.setPreferredSize(new Dimension(175,50));
		
	
		txt_id = new JTextField("Enter subject' id...", 15);
		txt_id.setForeground(Color.GRAY);
		txt_id.setPreferredSize(new Dimension(0,50));
		txt_id.setEditable(false);
		txt_id.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (txt_id.getText().equals("Enter subject' id...")) {
		        	txt_id.setText("");
		        	txt_id.setForeground(Color.BLACK);
		        }
		    }
		    
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (txt_id.getText().isEmpty()) {
		        	txt_id.setForeground(Color.GRAY);
		        	txt_id.setText("Enter subject' id...");
		        }
		    }
	    });
		
		txt_credits = new JTextField("Enter subject' credits...", 15);
		txt_credits.setForeground(Color.BLACK);
		txt_credits.setPreferredSize(new Dimension(0,50));
		txt_credits.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (txt_credits.getText().equals("Enter subject' credits...")) {
		        	txt_credits.setText("");
		        	txt_credits.setForeground(Color.BLACK);
		        }
		    }
		    
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (txt_credits.getText().isEmpty()) {
		        	txt_credits.setForeground(Color.GRAY);
		        	txt_credits.setText("Enter subject' id...");
		        }
		    }
	    });
		

		
		txt_name = new JTextArea("Enter subject's name...", 0, 1);
		txt_name.setForeground(Color.BLACK);
		txt_name.setPreferredSize(new Dimension(0,50));
		txt_name.setLineWrap(true);
		txt_name.setBorder(BorderFactory.createCompoundBorder(
				txt_name.getBorder(), 
		        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		txt_name.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (txt_name.getText().equals("Enter subject's name...")) {
		        	txt_name.setText("");
		        	txt_name.setForeground(Color.BLACK);
		        }
		    }
		    
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (txt_name.getText().isEmpty()) {
		        	txt_name.setForeground(Color.GRAY);
		        	txt_name.setText("Enter subject's name...");
		        }
		    }
	    });
		
		
		row1.add(lbl_row1);
		row1.add(txt_id);
		
		row2.add(lbl_row2);
		row2.add(txt_credits);
		
		row3.add(lbl_row3);
		row3.add(txt_name);
		
		
        buttonRow.add(btn_confirm);
		
        layoutRow1.putConstraint(SpringLayout.NORTH, lbl_row1, 0, SpringLayout.NORTH, row1);
        layoutRow1.putConstraint(SpringLayout.SOUTH, lbl_row1, 0, SpringLayout.SOUTH, row1);
        layoutRow1.putConstraint(SpringLayout.NORTH, txt_id, 10, SpringLayout.NORTH, row1);
        layoutRow1.putConstraint(SpringLayout.SOUTH, txt_id, 0, SpringLayout.SOUTH, row1);
        layoutRow1.putConstraint(SpringLayout.WEST, lbl_row1, 15, SpringLayout.WEST, row1);
        layoutRow1.putConstraint(SpringLayout.WEST, txt_id, 5, SpringLayout.EAST, lbl_row1);
        layoutRow1.putConstraint(SpringLayout.EAST, txt_id, -15, SpringLayout.EAST, row1);
        
        layoutRow2.putConstraint(SpringLayout.NORTH, lbl_row2, 0, SpringLayout.NORTH, row2);
        layoutRow2.putConstraint(SpringLayout.NORTH, txt_credits, 10, SpringLayout.NORTH, row2);
        layoutRow2.putConstraint(SpringLayout.SOUTH, txt_credits, 0, SpringLayout.SOUTH, row2);
        layoutRow2.putConstraint(SpringLayout.WEST, lbl_row2, 15, SpringLayout.WEST, row2);
        layoutRow2.putConstraint(SpringLayout.WEST, txt_credits, 5, SpringLayout.EAST, lbl_row2);
        layoutRow2.putConstraint(SpringLayout.EAST, txt_credits, -15, SpringLayout.EAST, row2);
        
        layoutRow3.putConstraint(SpringLayout.NORTH, lbl_row3, 0, SpringLayout.NORTH, row3);
        layoutRow3.putConstraint(SpringLayout.NORTH, txt_name, 10, SpringLayout.NORTH, row3);
        layoutRow3.putConstraint(SpringLayout.SOUTH, txt_name, 0, SpringLayout.SOUTH, row3);
        layoutRow3.putConstraint(SpringLayout.WEST, lbl_row3, 15, SpringLayout.WEST, row3);
        layoutRow3.putConstraint(SpringLayout.WEST, txt_name, 5, SpringLayout.EAST, lbl_row3);
        layoutRow3.putConstraint(SpringLayout.EAST, txt_name, -15, SpringLayout.EAST, row3);
        
      
        layoutButtonRow.putConstraint(SpringLayout.NORTH, btn_confirm, 10, SpringLayout.NORTH, buttonRow);
        layoutButtonRow.putConstraint(SpringLayout.SOUTH, btn_confirm, -10, SpringLayout.SOUTH, buttonRow);
		layoutButtonRow.putConstraint(SpringLayout.WEST, btn_confirm, 15, SpringLayout.WEST, buttonRow);
		layoutButtonRow.putConstraint(SpringLayout.EAST, btn_confirm, -15, SpringLayout.EAST, buttonRow);
		
		
		
		row1.setAlignmentY(Component.CENTER_ALIGNMENT);
		row2.setAlignmentY(Component.CENTER_ALIGNMENT);
		buttonRow.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		pane.add(row1, BorderLayout.CENTER);
		pane.add(row2, BorderLayout.CENTER);
		pane.add(row3, BorderLayout.CENTER);
		pane.add(buttonRow, BorderLayout.CENTER);
		
		add(pane);
		
		txt_id.setText(subjectData.getId());
		txt_credits.setText(String.valueOf(subjectData.getCredits()));
		txt_name.setText(subjectData.getName());

        this.setOpaque(true); 
        frame.setContentPane(this);
        
        
        frame.pack();
        frame.setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		String strActionCommand = e.getActionCommand();
		if (strActionCommand.equals("Confirm"))
		{
			
			try {
				short credits = Short.parseShort(txt_credits.getText());
				if (txt_id.getText().equals("Enter subject' id...")) {
					showMessageDialog(null, "Please fill in subject id!");
				}
				else {
					Subject newSubject = new Subject();
					newSubject.setId(txt_id.getText());
					newSubject.setCredits(credits);
					newSubject.setName(txt_name.getText());
					int status = SubjectDAO.updateSubject(newSubject);
					if (status == -1) {
						showMessageDialog(null, "This subject's id has already been existed!");
					}
					else {
							
						ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "refresh");
						action.actionPerformed(event);
						showMessageDialog(null, "Edited successfully!");
					}
				}
			} catch (Exception e1) {
				showMessageDialog(null, "Invalid credit!");
			}
		}
		
	}
}


