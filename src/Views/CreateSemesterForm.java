package Views;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import DAO.SemesterDAO;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

import Models.Semester;
import Models.SemesterID;

public class CreateSemesterForm extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JTextField txt_name;
	JTextField txt_schoolYear;
	JDateChooser startDateChooser;
	JDateChooser endDateChooser;
	JTextArea txt_description;
	JCheckBox checkBoxCurrentSemester;
	JButton btn_confirm;
	JFrame frame;
	private Action action;
	
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
			}
		});
		
	
	}
	
	
	public CreateSemesterForm(Action actionFresh) throws IOException, URISyntaxException {
		super(new BorderLayout());

		
		this.action = actionFresh;
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		frame = new JFrame("Add semester");
	    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    frame.setPreferredSize(new Dimension(dim.width - 100,dim.height - 200));
	    frame.setLocation(50, 100);
			
		Container pane = frame.getContentPane();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		
		JPanel row1 = new JPanel();
		JPanel row2 = new JPanel();
		JPanel row3 = new JPanel();
		JPanel row4 = new JPanel();
		JPanel row5 = new JPanel();
		JPanel row6 = new JPanel();
		JPanel buttonRow = new JPanel();
		SpringLayout layoutRow1 = new SpringLayout();
		SpringLayout layoutRow2 = new SpringLayout();
		SpringLayout layoutRow3 = new SpringLayout();
		SpringLayout layoutRow4 = new SpringLayout();
		SpringLayout layoutRow5 = new SpringLayout();
		SpringLayout layoutRow6 = new SpringLayout();
		SpringLayout layoutButtonRow = new SpringLayout();
		row1.setLayout(layoutRow1);	
		row2.setLayout(layoutRow2);
		row3.setLayout(layoutRow3);
		row4.setLayout(layoutRow4);
		row5.setLayout(layoutRow5);
		row6.setLayout(layoutRow6);
		buttonRow.setLayout(layoutButtonRow);
		
		row1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
		row2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
		row3.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
		row4.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
		row5.setMaximumSize(new Dimension(Integer.MAX_VALUE, frame.getPreferredSize().height - 480));
		row6.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
		buttonRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
		
		btn_confirm = new JButton("Add semester");
		btn_confirm.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_confirm.addActionListener(this);
		btn_confirm.setActionCommand("Confirm");
		btn_confirm.setPreferredSize(new Dimension(0,60));
		
		
		JLabel lbl_row1 = new JLabel("Semester's name: ");
		JLabel lbl_row2 = new JLabel("Semester's school year: ");
		JLabel lbl_row3 = new JLabel("Semester's start date: ");
		JLabel lbl_row4 = new JLabel("Semester's end date: ");
		JLabel lbl_row5 = new JLabel("Semester's description: ");

		
		lbl_row1.setPreferredSize(new Dimension(175,50));
		lbl_row2.setPreferredSize(new Dimension(175,50));
		lbl_row3.setPreferredSize(new Dimension(175,50));
		lbl_row4.setPreferredSize(new Dimension(175,50));
		lbl_row5.setPreferredSize(new Dimension(175,50));
		
	
		txt_name = new JTextField("Enter semester's name...", 15);
		txt_name.setForeground(Color.GRAY);
		txt_name.setPreferredSize(new Dimension(0,50));
		txt_name.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (txt_name.getText().equals("Enter semester's name...")) {
		        	txt_name.setText("");
		        	txt_name.setForeground(Color.BLACK);
		        }
		    }
		    
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (txt_name.getText().isEmpty()) {
		        	txt_name.setForeground(Color.GRAY);
		        	txt_name.setText("Enter semester's name...");
		        }
		    }
	    });
		
		txt_schoolYear = new JTextField("Enter semester's school year...", 15);
		txt_schoolYear.setForeground(Color.GRAY);
		txt_schoolYear.setPreferredSize(new Dimension(0,50));
		txt_schoolYear.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (txt_schoolYear.getText().equals("Enter semester's school year...")) {
		        	txt_schoolYear.setText("");
		        	txt_schoolYear.setForeground(Color.BLACK);
		        }
		    }
		    
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (txt_schoolYear.getText().isEmpty()) {
		        	txt_schoolYear.setForeground(Color.GRAY);
		        	txt_schoolYear.setText("Enter semester's school year...");
		        }
		    }
	    });
		
		startDateChooser = new JDateChooser();
		startDateChooser.setDateFormatString("dd/MM/yyyy");
		startDateChooser.getCalendarButton().setPreferredSize(new Dimension(175, 0));
		startDateChooser.getCalendarButton().setIcon(null);
		startDateChooser.getCalendarButton().setText("Choose semester start date");
		
		endDateChooser = new JDateChooser();
		endDateChooser.setDateFormatString("dd/MM/yyyy");
		endDateChooser.getCalendarButton().setPreferredSize(new Dimension(175, 0));
		endDateChooser.getCalendarButton().setIcon(null);
		endDateChooser.getCalendarButton().setText("Choose semester end date");
		
		txt_description = new JTextArea("Enter description...", 0, 1);
		txt_description.setForeground(Color.GRAY);
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
		
		checkBoxCurrentSemester = new JCheckBox("Is current semester");
		checkBoxCurrentSemester.setHorizontalTextPosition(SwingConstants.LEFT);
		
		
		row1.add(lbl_row1);
		row1.add(txt_name);
		
		row2.add(lbl_row2);
		row2.add(txt_schoolYear);
		
		row3.add(lbl_row3);
		row3.add(startDateChooser);
		
		row4.add(lbl_row4);
		row4.add(endDateChooser);
		
		row5.add(lbl_row5);
		row5.add(txt_description);
		
		row6.add(checkBoxCurrentSemester);
		
		
		
        buttonRow.add(btn_confirm);
		
        layoutRow1.putConstraint(SpringLayout.NORTH, lbl_row1, 0, SpringLayout.NORTH, row1);
        layoutRow1.putConstraint(SpringLayout.SOUTH, lbl_row1, 0, SpringLayout.SOUTH, row1);
        layoutRow1.putConstraint(SpringLayout.NORTH, txt_name, 10, SpringLayout.NORTH, row1);
        layoutRow1.putConstraint(SpringLayout.SOUTH, txt_name, 0, SpringLayout.SOUTH, row1);
        layoutRow1.putConstraint(SpringLayout.WEST, lbl_row1, 15, SpringLayout.WEST, row1);
        layoutRow1.putConstraint(SpringLayout.WEST, txt_name, 5, SpringLayout.EAST, lbl_row1);
        layoutRow1.putConstraint(SpringLayout.EAST, txt_name, -15, SpringLayout.EAST, row1);
        
        layoutRow2.putConstraint(SpringLayout.NORTH, lbl_row2, 0, SpringLayout.NORTH, row2);
        layoutRow2.putConstraint(SpringLayout.SOUTH, lbl_row2, 0, SpringLayout.SOUTH, row2);
        layoutRow2.putConstraint(SpringLayout.NORTH, txt_schoolYear, 10, SpringLayout.NORTH, row2);
        layoutRow2.putConstraint(SpringLayout.SOUTH, txt_schoolYear, 0, SpringLayout.SOUTH, row2);
        layoutRow2.putConstraint(SpringLayout.WEST, lbl_row2, 15, SpringLayout.WEST, row2);
        layoutRow2.putConstraint(SpringLayout.WEST, txt_schoolYear, 5, SpringLayout.EAST, lbl_row2);
        layoutRow2.putConstraint(SpringLayout.EAST, txt_schoolYear, -15, SpringLayout.EAST, row2);
        
        layoutRow3.putConstraint(SpringLayout.NORTH, lbl_row3, 10, SpringLayout.NORTH, row3);
        layoutRow3.putConstraint(SpringLayout.SOUTH, lbl_row3, 0, SpringLayout.SOUTH, row3);
        layoutRow3.putConstraint(SpringLayout.NORTH, startDateChooser, 10, SpringLayout.NORTH, row3);
        layoutRow3.putConstraint(SpringLayout.SOUTH, startDateChooser, 0, SpringLayout.SOUTH, row3);
        layoutRow3.putConstraint(SpringLayout.WEST, lbl_row3, 15, SpringLayout.WEST, row3);
        layoutRow3.putConstraint(SpringLayout.WEST, startDateChooser, 5, SpringLayout.EAST, lbl_row3);
        layoutRow3.putConstraint(SpringLayout.EAST, startDateChooser, -5, SpringLayout.EAST, row3);
        
        layoutRow4.putConstraint(SpringLayout.NORTH, lbl_row4, 10, SpringLayout.NORTH, row4);
        layoutRow4.putConstraint(SpringLayout.SOUTH, lbl_row4, 0, SpringLayout.SOUTH, row4);
        layoutRow4.putConstraint(SpringLayout.NORTH, endDateChooser, 10, SpringLayout.NORTH, row4);
        layoutRow4.putConstraint(SpringLayout.SOUTH, endDateChooser, 0, SpringLayout.SOUTH, row4);
        layoutRow4.putConstraint(SpringLayout.WEST, lbl_row4, 15, SpringLayout.WEST, row4);
        layoutRow4.putConstraint(SpringLayout.WEST, endDateChooser, 5, SpringLayout.EAST, lbl_row4);
        layoutRow4.putConstraint(SpringLayout.EAST, endDateChooser, -5, SpringLayout.EAST, row4);
        
        layoutRow5.putConstraint(SpringLayout.NORTH, lbl_row5, 0, SpringLayout.NORTH, row5);
        layoutRow5.putConstraint(SpringLayout.NORTH, txt_description, 10, SpringLayout.NORTH, row5);
        layoutRow5.putConstraint(SpringLayout.SOUTH, txt_description, 0, SpringLayout.SOUTH, row5);
        layoutRow5.putConstraint(SpringLayout.WEST, lbl_row5, 15, SpringLayout.WEST, row5);
        layoutRow5.putConstraint(SpringLayout.WEST, txt_description, 5, SpringLayout.EAST, lbl_row5);
        layoutRow5.putConstraint(SpringLayout.EAST, txt_description, -15, SpringLayout.EAST, row5);
        
        layoutRow6.putConstraint(SpringLayout.NORTH, checkBoxCurrentSemester, 0, SpringLayout.NORTH, row6);
        layoutRow6.putConstraint(SpringLayout.SOUTH, checkBoxCurrentSemester, 0, SpringLayout.SOUTH, row6);
        layoutRow6.putConstraint(SpringLayout.WEST, checkBoxCurrentSemester, 15, SpringLayout.WEST, row6);
        
      
        layoutButtonRow.putConstraint(SpringLayout.NORTH, btn_confirm, 10, SpringLayout.NORTH, buttonRow);
        layoutButtonRow.putConstraint(SpringLayout.SOUTH, btn_confirm, -10, SpringLayout.SOUTH, buttonRow);
		layoutButtonRow.putConstraint(SpringLayout.WEST, btn_confirm, 15, SpringLayout.WEST, buttonRow);
		layoutButtonRow.putConstraint(SpringLayout.EAST, btn_confirm, -15, SpringLayout.EAST, buttonRow);
		
		
		
		row1.setAlignmentY(Component.CENTER_ALIGNMENT);
		row2.setAlignmentY(Component.CENTER_ALIGNMENT);
		row3.setAlignmentY(Component.CENTER_ALIGNMENT);
		row4.setAlignmentY(Component.CENTER_ALIGNMENT);
		row5.setAlignmentY(Component.CENTER_ALIGNMENT);
		row6.setAlignmentY(Component.CENTER_ALIGNMENT);
		buttonRow.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		pane.add(row1, BorderLayout.CENTER);
		pane.add(row2, BorderLayout.CENTER);
		pane.add(row3, BorderLayout.CENTER);
		pane.add(row4, BorderLayout.CENTER);
		pane.add(row5, BorderLayout.CENTER);
		pane.add(row6, BorderLayout.CENTER);
		pane.add(buttonRow, BorderLayout.CENTER);
		
		add(pane);
		

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
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				String startDate = dateFormat.format(startDateChooser.getDate());
				String endDate = dateFormat.format(endDateChooser.getDate());
				
				if (txt_name.getText().equals("Enter semester's name...")) {
					showMessageDialog(null, "Please fill in semester's name!");
				}
				else if (txt_schoolYear.getText().equals("Enter semester's school year...")) {
					showMessageDialog(null, "Please fill in semester's school year!");
				}
				else {
					Semester newSemester = new Semester();
					
					SemesterID semesterId = new SemesterID();
					semesterId.setName(txt_name.getText());
					semesterId.setSchoolYear(txt_schoolYear.getText());
					newSemester.setId(semesterId);
					newSemester.setStartDate(startDate);
					newSemester.setEndDate(endDate);
					newSemester.setDescription(txt_description.getText());
					
					
					
					int status = SemesterDAO.addSemester(newSemester);
					if (status == -1) {
						showMessageDialog(null, "This semester has already been existed!");
					}
					else {
						if (checkBoxCurrentSemester.isSelected()) {
							
							List<Semester> semesters = SemesterDAO.getSemesterList();
							
							for (Semester item : semesters) {
								if (item.getId().equals(newSemester.getId())) {
									item.setIsCurrentSemester((short) 1); 
								}
								else {
									item.setIsCurrentSemester((short) 0); 
								}
								SemesterDAO.updateSemester(item);
							}
						}
							
						ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "refresh");
						action.actionPerformed(event);
						showMessageDialog(null, "Added successfully!");
					}
				}
			} catch (Exception e1) {
				showMessageDialog(null, "Invalid date!");
				System.out.println(e1.getStackTrace());
			}
			
		}
		
	}
}


