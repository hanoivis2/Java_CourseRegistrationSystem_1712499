package Views;

import javax.swing.*;


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class CreateCourseForm extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JTextField txt_courseName;
	JTextField txt_credits;
	JTextField txt_theoryTeacherName;
	JTextField txt_roomName;
	JComboBox<String> daysInWeekList;
	JComboBox<String> shiftsList;
	JTextField txt_maxAmountStudent;
	JButton btn_confirm;
	JFrame frame;
	
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
			}
		});
	
		JComponent mainMenu = new CreateCourseForm();
		mainMenu.setOpaque(true);
		mainMenu.setVisible(true);
	}
	
	
	public CreateCourseForm() throws IOException, URISyntaxException {
		super(new BorderLayout());

		JFrame.setDefaultLookAndFeelDecorated(true);
		
        frame = new JFrame("Add course for current semester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		row1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
		row2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
		row3.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
		row4.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
		row5.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
		row6.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
		row7.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
		buttonRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, frame.getPreferredSize().height - 560));
		
		btn_confirm = new JButton("Add course");
		btn_confirm.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_confirm.addActionListener(this);
		btn_confirm.setActionCommand("Confirm");
		btn_confirm.setPreferredSize(new Dimension(0,60));
		
		
		JLabel lbl_row1 = new JLabel("Course's name: ");
		JLabel lbl_row2 = new JLabel("Credits amount: ");
		JLabel lbl_row3 = new JLabel("Theory teacher's name: ");
		JLabel lbl_row4 = new JLabel("Room's name: ");
		JLabel lbl_row5 = new JLabel("Day in week: ");
		
		JLabel lbl_row6 = new JLabel("Shift: ");

		String[] shifts = {"Ca 1, 7:30 – 9:30", "Ca 2, 9:30 – 11:30", "Ca 3, 13:30 – 15:30", "Ca 4, 15:30 – 17:30"};
	   shiftsList = new JComboBox<>(shifts);
	    shiftsList.setMaximumSize(new Dimension(0, 60));
	    
	    
		JLabel lbl_row7 = new JLabel("Max students amount: ");
		
		String[] daysInWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	    daysInWeekList = new JComboBox<>(daysInWeek);
	    shiftsList.setMaximumSize(new Dimension(0, 60));
		
		lbl_row1.setPreferredSize(new Dimension(200,50));
		lbl_row2.setPreferredSize(new Dimension(200,50));
		lbl_row3.setPreferredSize(new Dimension(200,50));
		lbl_row4.setPreferredSize(new Dimension(200,50));
		lbl_row5.setPreferredSize(new Dimension(200,50));
		lbl_row6.setPreferredSize(new Dimension(200,50));
		lbl_row7.setPreferredSize(new Dimension(200,50));
		
	
		txt_courseName = new JTextField("Enter course's name...", 15);
		txt_courseName.setForeground(Color.GRAY);
		txt_courseName.setPreferredSize(new Dimension(0,50));
		txt_courseName.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (txt_courseName.getText().equals("Enter course's name...")) {
		        	txt_courseName.setText("");
		        	txt_courseName.setForeground(Color.BLACK);
		        }
		    }
		    
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (txt_courseName.getText().isEmpty()) {
		        	txt_courseName.setForeground(Color.GRAY);
		        	txt_courseName.setText("Enter course's name...");
		        }
		    }
	    });
		
		txt_theoryTeacherName = new JTextField("Enter theory teacher's name...", 15);
		txt_theoryTeacherName.setForeground(Color.GRAY);
		txt_theoryTeacherName.setPreferredSize(new Dimension(0,50));
		txt_theoryTeacherName.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (txt_theoryTeacherName.getText().equals("Enter theory teacher's name...")) {
		        	txt_theoryTeacherName.setText("");
		        	txt_theoryTeacherName.setForeground(Color.BLACK);
		        }
		    }
		    
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (txt_theoryTeacherName.getText().isEmpty()) {
		        	txt_theoryTeacherName.setForeground(Color.GRAY);
		        	txt_theoryTeacherName.setText("Enter theory teacher's name...");
		        }
		    }
	    });
		
		txt_credits = new JTextField("Enter credits amount...", 15);
		txt_credits.setForeground(Color.GRAY);
		txt_credits.setPreferredSize(new Dimension(0,50));
		txt_credits.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (txt_credits.getText().equals("Enter credits amount...")) {
		        	txt_credits.setText("");
		        	txt_credits.setForeground(Color.BLACK);
		        }
		    }
		    
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (txt_credits.getText().isEmpty()) {
		        	txt_credits.setForeground(Color.GRAY);
		        	txt_credits.setText("Enter credits amount...");
		        }
		    }
	    });
		
		txt_theoryTeacherName = new JTextField("Enter theory teacher's name...", 15);
		txt_theoryTeacherName.setForeground(Color.GRAY);
		txt_theoryTeacherName.setPreferredSize(new Dimension(0,50));
		txt_theoryTeacherName.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (txt_theoryTeacherName.getText().equals("Enter theory teacher's name...")) {
		        	txt_theoryTeacherName.setText("");
		        	txt_theoryTeacherName.setForeground(Color.BLACK);
		        }
		    }
		    
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (txt_theoryTeacherName.getText().isEmpty()) {
		        	txt_theoryTeacherName.setForeground(Color.GRAY);
		        	txt_theoryTeacherName.setText("Enter theory teacher's name...");
		        }
		    }
	    });
		
		txt_roomName = new JTextField("Enter room's name...", 15);
		txt_roomName.setForeground(Color.GRAY);
		txt_roomName.setPreferredSize(new Dimension(0,50));
		txt_roomName.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (txt_roomName.getText().equals("Enter room's name...")) {
		        	txt_roomName.setText("");
		        	txt_roomName.setForeground(Color.BLACK);
		        }
		    }
		    
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (txt_roomName.getText().isEmpty()) {
		        	txt_roomName.setForeground(Color.GRAY);
		        	txt_roomName.setText("Enter room's name...");
		        }
		    }
	    });
		
		
		
		txt_maxAmountStudent = new JTextField("Enter max amount of students...", 15);
		txt_maxAmountStudent.setForeground(Color.GRAY);
		txt_maxAmountStudent.setPreferredSize(new Dimension(0,50));
		txt_maxAmountStudent.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (txt_maxAmountStudent.getText().equals("Enter max amount of students...")) {
		        	txt_maxAmountStudent.setText("");
		        	txt_maxAmountStudent.setForeground(Color.BLACK);
		        }
		    }
		    
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (txt_maxAmountStudent.getText().isEmpty()) {
		        	txt_maxAmountStudent.setForeground(Color.GRAY);
		        	txt_maxAmountStudent.setText("Enter max amount of students...");
		        }
		    }
	    });
		
		
		
		row1.add(lbl_row1);
		row1.add(txt_courseName);
		
		row2.add(lbl_row2);
		row2.add(txt_credits);
		
		row3.add(lbl_row3);
		row3.add(txt_theoryTeacherName);
		
		row4.add(lbl_row4);
		row4.add(txt_roomName);
		
		row5.add(lbl_row5);
		row5.add(daysInWeekList);
		
		row6.add(lbl_row6);
		row6.add(shiftsList);
		
		row7.add(lbl_row7);
		row7.add(txt_maxAmountStudent);
		
	
		
		
		
        buttonRow.add(btn_confirm);
		
        layoutRow1.putConstraint(SpringLayout.NORTH, lbl_row1, 0, SpringLayout.NORTH, row1);
        layoutRow1.putConstraint(SpringLayout.SOUTH, lbl_row1, 0, SpringLayout.SOUTH, row1);
        layoutRow1.putConstraint(SpringLayout.NORTH, txt_courseName, 10, SpringLayout.NORTH, row1);
        layoutRow1.putConstraint(SpringLayout.SOUTH, txt_courseName, 0, SpringLayout.SOUTH, row1);
        layoutRow1.putConstraint(SpringLayout.WEST, lbl_row1, 15, SpringLayout.WEST, row1);
        layoutRow1.putConstraint(SpringLayout.WEST, txt_courseName, 5, SpringLayout.EAST, lbl_row1);
        layoutRow1.putConstraint(SpringLayout.EAST, txt_courseName, -15, SpringLayout.EAST, row1);
        
        layoutRow2.putConstraint(SpringLayout.NORTH, lbl_row2, 0, SpringLayout.NORTH, row2);
        layoutRow2.putConstraint(SpringLayout.SOUTH, lbl_row2, 0, SpringLayout.SOUTH, row2);
        layoutRow2.putConstraint(SpringLayout.NORTH, txt_credits, 10, SpringLayout.NORTH, row2);
        layoutRow2.putConstraint(SpringLayout.SOUTH, txt_credits, 0, SpringLayout.SOUTH, row2);
        layoutRow2.putConstraint(SpringLayout.WEST, lbl_row2, 15, SpringLayout.WEST, row2);
        layoutRow2.putConstraint(SpringLayout.WEST, txt_credits, 5, SpringLayout.EAST, lbl_row2);
        layoutRow2.putConstraint(SpringLayout.EAST, txt_credits, -15, SpringLayout.EAST, row2);
        
        layoutRow3.putConstraint(SpringLayout.NORTH, lbl_row3, 0, SpringLayout.NORTH, row3);
        layoutRow3.putConstraint(SpringLayout.SOUTH, lbl_row3, 0, SpringLayout.SOUTH, row3);
        layoutRow3.putConstraint(SpringLayout.NORTH, txt_theoryTeacherName, 10, SpringLayout.NORTH, row3);
        layoutRow3.putConstraint(SpringLayout.SOUTH, txt_theoryTeacherName, 0, SpringLayout.SOUTH, row3);
        layoutRow3.putConstraint(SpringLayout.WEST, lbl_row3, 15, SpringLayout.WEST, row3);
        layoutRow3.putConstraint(SpringLayout.WEST, txt_theoryTeacherName, 5, SpringLayout.EAST, lbl_row3);
        layoutRow3.putConstraint(SpringLayout.EAST, txt_theoryTeacherName, -15, SpringLayout.EAST, row3);
        
        layoutRow4.putConstraint(SpringLayout.NORTH, lbl_row4, 0, SpringLayout.NORTH, row4);
        layoutRow4.putConstraint(SpringLayout.SOUTH, lbl_row4, 0, SpringLayout.SOUTH, row4);
        layoutRow4.putConstraint(SpringLayout.NORTH, txt_roomName, 10, SpringLayout.NORTH, row4);
        layoutRow4.putConstraint(SpringLayout.SOUTH, txt_roomName, 0, SpringLayout.SOUTH, row4);
        layoutRow4.putConstraint(SpringLayout.WEST, lbl_row4, 15, SpringLayout.WEST, row4);
        layoutRow4.putConstraint(SpringLayout.WEST, txt_roomName, 5, SpringLayout.EAST, lbl_row4);
        layoutRow4.putConstraint(SpringLayout.EAST, txt_roomName, -15, SpringLayout.EAST, row4);
        
        layoutRow5.putConstraint(SpringLayout.NORTH, lbl_row5, 0, SpringLayout.NORTH, row5);
        layoutRow5.putConstraint(SpringLayout.SOUTH, lbl_row5, 0, SpringLayout.SOUTH, row5);
        layoutRow5.putConstraint(SpringLayout.NORTH, daysInWeekList, 0, SpringLayout.NORTH, row5);
        layoutRow5.putConstraint(SpringLayout.SOUTH, daysInWeekList, 0, SpringLayout.SOUTH, row5);
        layoutRow5.putConstraint(SpringLayout.WEST, lbl_row5, 15, SpringLayout.WEST, row5);
        layoutRow5.putConstraint(SpringLayout.WEST, daysInWeekList, 5, SpringLayout.EAST, lbl_row5);
        layoutRow5.putConstraint(SpringLayout.EAST, daysInWeekList, -15, SpringLayout.EAST, row5);
        
        layoutRow6.putConstraint(SpringLayout.NORTH, lbl_row6, 0, SpringLayout.NORTH, row6);
        layoutRow6.putConstraint(SpringLayout.SOUTH, lbl_row6, 0, SpringLayout.SOUTH, row6);
        layoutRow6.putConstraint(SpringLayout.NORTH, shiftsList, 0, SpringLayout.NORTH, row6);
        layoutRow6.putConstraint(SpringLayout.SOUTH, shiftsList, 0, SpringLayout.SOUTH, row6);
        layoutRow6.putConstraint(SpringLayout.WEST, lbl_row6, 15, SpringLayout.WEST, row6);
        layoutRow6.putConstraint(SpringLayout.WEST, shiftsList, 5, SpringLayout.EAST, lbl_row6);
        layoutRow6.putConstraint(SpringLayout.EAST, shiftsList, -15, SpringLayout.EAST, row6);
        
        
        layoutRow7.putConstraint(SpringLayout.NORTH, lbl_row7, 0, SpringLayout.NORTH, row7);
        layoutRow7.putConstraint(SpringLayout.SOUTH, lbl_row7, 0, SpringLayout.SOUTH, row7);
        layoutRow7.putConstraint(SpringLayout.NORTH, txt_maxAmountStudent, 10, SpringLayout.NORTH, row7);
        layoutRow7.putConstraint(SpringLayout.SOUTH, txt_maxAmountStudent, 0, SpringLayout.SOUTH, row7);
        layoutRow7.putConstraint(SpringLayout.WEST, lbl_row7, 15, SpringLayout.WEST, row7);
        layoutRow7.putConstraint(SpringLayout.WEST, txt_maxAmountStudent, 5, SpringLayout.EAST, lbl_row7);
        layoutRow7.putConstraint(SpringLayout.EAST, txt_maxAmountStudent, -15, SpringLayout.EAST, row7);
        
       
        
      
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
	
	
	public void actionPerformed(ActionEvent e)
	{
		String strActionCommand = e.getActionCommand();
		if (strActionCommand.equals("Confirm"))
		{
//			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

			try {
//				String startDate = dateFormat.format(startDateChooser.getDate());
//				String endDate = dateFormat.format(endDateChooser.getDate());
				
			} catch (Exception e1) {
				System.out.println("Invalid date!");
			}
			

			
		}
		
	}
}


