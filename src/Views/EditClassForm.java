package Views;

import javax.swing.*;

import DAO.ClassDAO;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URISyntaxException;
import static javax.swing.JOptionPane.showMessageDialog;
import Models.Class;

public class EditClassForm extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JTextField txt_id;
	JTextArea txt_description;
	JButton btn_confirm;
	JFrame frame;
	private Action action;
	
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
			}
		});
	
	}
	
	
	public EditClassForm(Action actionFresh, Class classData) throws IOException, URISyntaxException {
		super(new BorderLayout());

		
		this.action = actionFresh;
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		
        frame = new JFrame("Edit class information");
        frame.setPreferredSize(new Dimension(600,550));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2 - 300, dim.height/2 - 275);
		
		Container pane = frame.getContentPane();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		
		JPanel row1 = new JPanel();
		JPanel row2 = new JPanel();
		JPanel buttonRow = new JPanel();
		SpringLayout layoutRow1 = new SpringLayout();
		SpringLayout layoutRow2 = new SpringLayout();
		SpringLayout layoutButtonRow = new SpringLayout();
		row1.setLayout(layoutRow1);	
		row2.setLayout(layoutRow2);	
		buttonRow.setLayout(layoutButtonRow);	

		row1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		row2.setMaximumSize(new Dimension(Integer.MAX_VALUE, frame.getPreferredSize().height - 140));
		buttonRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
		
		btn_confirm = new JButton("Confirm edit");
		btn_confirm.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_confirm.addActionListener(this);
		btn_confirm.setActionCommand("Confirm");
		btn_confirm.setPreferredSize(new Dimension(0,60));
		
		
		JLabel lbl_row1 = new JLabel("Class' id: ");
		JLabel lbl_row2 = new JLabel("Description: ");

		
		lbl_row1.setPreferredSize(new Dimension(175,50));
		lbl_row2.setPreferredSize(new Dimension(175,50));
		
	
		txt_id = new JTextField("Enter class' id...", 15);
		txt_id.setForeground(Color.GRAY);
		txt_id.setPreferredSize(new Dimension(0,50));
		txt_id.setEditable(false);
		txt_id.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (txt_id.getText().equals("Enter class' id...")) {
		        	txt_id.setText("");
		        	txt_id.setForeground(Color.BLACK);
		        }
		    }
		    
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (txt_id.getText().isEmpty()) {
		        	txt_id.setForeground(Color.GRAY);
		        	txt_id.setText("Enter class' id...");
		        }
		    }
	    });
		

		
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
		row1.add(txt_id);
		
		row2.add(lbl_row2);
		row2.add(txt_description);
		
		
        buttonRow.add(btn_confirm);
		
        layoutRow1.putConstraint(SpringLayout.NORTH, lbl_row1, 0, SpringLayout.NORTH, row1);
        layoutRow1.putConstraint(SpringLayout.SOUTH, lbl_row1, 0, SpringLayout.SOUTH, row1);
        layoutRow1.putConstraint(SpringLayout.NORTH, txt_id, 10, SpringLayout.NORTH, row1);
        layoutRow1.putConstraint(SpringLayout.SOUTH, txt_id, 0, SpringLayout.SOUTH, row1);
        layoutRow1.putConstraint(SpringLayout.WEST, lbl_row1, 15, SpringLayout.WEST, row1);
        layoutRow1.putConstraint(SpringLayout.WEST, txt_id, 5, SpringLayout.EAST, lbl_row1);
        layoutRow1.putConstraint(SpringLayout.EAST, txt_id, -15, SpringLayout.EAST, row1);
        
        layoutRow2.putConstraint(SpringLayout.NORTH, lbl_row2, 0, SpringLayout.NORTH, row2);
        layoutRow2.putConstraint(SpringLayout.NORTH, txt_description, 10, SpringLayout.NORTH, row2);
        layoutRow2.putConstraint(SpringLayout.SOUTH, txt_description, 0, SpringLayout.SOUTH, row2);
        layoutRow2.putConstraint(SpringLayout.WEST, lbl_row2, 15, SpringLayout.WEST, row2);
        layoutRow2.putConstraint(SpringLayout.WEST, txt_description, 5, SpringLayout.EAST, lbl_row2);
        layoutRow2.putConstraint(SpringLayout.EAST, txt_description, -15, SpringLayout.EAST, row2);
        
      
        layoutButtonRow.putConstraint(SpringLayout.NORTH, btn_confirm, 10, SpringLayout.NORTH, buttonRow);
        layoutButtonRow.putConstraint(SpringLayout.SOUTH, btn_confirm, -10, SpringLayout.SOUTH, buttonRow);
		layoutButtonRow.putConstraint(SpringLayout.WEST, btn_confirm, 15, SpringLayout.WEST, buttonRow);
		layoutButtonRow.putConstraint(SpringLayout.EAST, btn_confirm, -15, SpringLayout.EAST, buttonRow);
		
		
		
		row1.setAlignmentY(Component.CENTER_ALIGNMENT);
		buttonRow.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		pane.add(row1, BorderLayout.CENTER);
		pane.add(row2, BorderLayout.CENTER);
		pane.add(buttonRow, BorderLayout.CENTER);
		
		add(pane);
		
		txt_id.setText(classData.getId());
		txt_description.setText(classData.getDescription());
		

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

			if (txt_id.getText().equals("Enter class' id...")) {
				showMessageDialog(null, "Please fill in class id!");
			}
			else {
				Class newClass = new Class();
				newClass.setId(txt_id.getText());
				newClass.setDescription(txt_description.getText());
				int status = ClassDAO.updateClass(newClass);
				if (status == -1) {
					showMessageDialog(null, "This class is not existed!");
				}
				else {
						
					ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "refresh");
					action.actionPerformed(event);
					showMessageDialog(null, "Edited successfully!");
				}
			}
			
		}
		
	}
}


