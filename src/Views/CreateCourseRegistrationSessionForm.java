package Views;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class CreateCourseRegistrationSessionForm extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JDateChooser startDateChooser;
	JDateChooser endDateChooser;
	JTextField txt_description;
	JButton btn_confirm;
	JFrame frame;
	
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
			}
		});
	
		JComponent mainMenu = new CreateCourseRegistrationSessionForm();
		mainMenu.setOpaque(true);
		mainMenu.setVisible(true);
	}
	
	
	public CreateCourseRegistrationSessionForm() throws IOException, URISyntaxException {
		super(new BorderLayout());

		JFrame.setDefaultLookAndFeelDecorated(true);
		
        frame = new JFrame("Add session for current semester");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600,400));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2 - 300, dim.height/2 - 200);
		
		Container pane = frame.getContentPane();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		
		JPanel firstRow = new JPanel();
		JPanel secondRow = new JPanel();
		JPanel thirdRow = new JPanel();
		JPanel buttonRow = new JPanel();
		SpringLayout layoutFirstRow = new SpringLayout();
		SpringLayout layoutSecondRow = new SpringLayout();
		SpringLayout layoutThirdRow = new SpringLayout();
		SpringLayout layoutButtonRow = new SpringLayout();
		firstRow.setLayout(layoutFirstRow);	
		secondRow.setLayout(layoutSecondRow);
		thirdRow.setLayout(layoutThirdRow);
		buttonRow.setLayout(layoutButtonRow);
		
		firstRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
		secondRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
		thirdRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, frame.getPreferredSize().height - 240));
		buttonRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
		
		btn_confirm = new JButton("Add session");
		btn_confirm.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_confirm.addActionListener(this);
		btn_confirm.setActionCommand("Confirm");
		btn_confirm.setPreferredSize(new Dimension(0,60));
		
		startDateChooser = new JDateChooser();
		startDateChooser.setDateFormatString("dd/MM/yyyy");
		startDateChooser.getCalendarButton().setPreferredSize(new Dimension(175, 0));
		startDateChooser.getCalendarButton().setIcon(null);
		startDateChooser.getCalendarButton().setText("Choose session start date");
		
		endDateChooser = new JDateChooser();
		endDateChooser.setDateFormatString("dd/MM/yyyy");
		endDateChooser.getCalendarButton().setPreferredSize(new Dimension(175, 0));
		endDateChooser.getCalendarButton().setIcon(null);
		endDateChooser.getCalendarButton().setText("Choose session end date");
		
		JLabel lbl_startDate = new JLabel("Start date: ");
		JLabel lbl_endDate = new JLabel("End date: ");
		JLabel lbl_description = new JLabel("Descripton: ");
		
		lbl_startDate.setPreferredSize(new Dimension(100,50));
		lbl_endDate.setPreferredSize(new Dimension(100,50));
		lbl_description.setPreferredSize(new Dimension(100,50));
		
		
		txt_description = new JTextField("Enter description or note for this session...", 15);
		txt_description.setForeground(Color.GRAY);
		txt_description.setPreferredSize(new Dimension(0,50));
		txt_description.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (txt_description.getText().equals("Enter description or note for this session...")) {
		        	txt_description.setText("");
		        	txt_description.setForeground(Color.BLACK);
		        }
		    }
		    
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (txt_description.getText().isEmpty()) {
		        	txt_description.setForeground(Color.GRAY);
		        	txt_description.setText("Enter description or note for this session...");
		        }
		    }
	    });
		
		
		firstRow.add(lbl_startDate);
		firstRow.add(startDateChooser);
		
		secondRow.add(lbl_endDate);
		secondRow.add(endDateChooser);
		
		thirdRow.add(lbl_description);
		thirdRow.add(txt_description);
		
        buttonRow.add(btn_confirm);
		
        layoutFirstRow.putConstraint(SpringLayout.NORTH, lbl_startDate, 10, SpringLayout.NORTH, firstRow);
        layoutFirstRow.putConstraint(SpringLayout.SOUTH, lbl_startDate, 0, SpringLayout.SOUTH, firstRow);
        layoutFirstRow.putConstraint(SpringLayout.NORTH, startDateChooser, 10, SpringLayout.NORTH, firstRow);
        layoutFirstRow.putConstraint(SpringLayout.SOUTH, startDateChooser, 0, SpringLayout.SOUTH, firstRow);
        layoutFirstRow.putConstraint(SpringLayout.WEST, lbl_startDate, 15, SpringLayout.WEST, firstRow);
        layoutFirstRow.putConstraint(SpringLayout.WEST, startDateChooser, 5, SpringLayout.EAST, lbl_startDate);
        layoutFirstRow.putConstraint(SpringLayout.EAST, startDateChooser, -5, SpringLayout.EAST, firstRow);
        
        layoutSecondRow.putConstraint(SpringLayout.NORTH, lbl_endDate, 10, SpringLayout.NORTH, secondRow);
        layoutSecondRow.putConstraint(SpringLayout.SOUTH, lbl_endDate, 0, SpringLayout.SOUTH, secondRow);
        layoutSecondRow.putConstraint(SpringLayout.NORTH, endDateChooser, 10, SpringLayout.NORTH, secondRow);
        layoutSecondRow.putConstraint(SpringLayout.SOUTH, endDateChooser, 0, SpringLayout.SOUTH, secondRow);
        layoutSecondRow.putConstraint(SpringLayout.WEST, lbl_endDate, 15, SpringLayout.WEST, secondRow);
        layoutSecondRow.putConstraint(SpringLayout.WEST, endDateChooser, 5, SpringLayout.EAST, lbl_endDate);
        layoutSecondRow.putConstraint(SpringLayout.EAST, endDateChooser, -5, SpringLayout.EAST, secondRow);
        
        
        layoutThirdRow.putConstraint(SpringLayout.NORTH, lbl_description, 0, SpringLayout.NORTH, thirdRow);
        layoutThirdRow.putConstraint(SpringLayout.NORTH, txt_description, 10, SpringLayout.NORTH, thirdRow);
        layoutThirdRow.putConstraint(SpringLayout.SOUTH, txt_description, 0, SpringLayout.SOUTH, thirdRow);
        layoutThirdRow.putConstraint(SpringLayout.WEST, lbl_description, 15, SpringLayout.WEST, thirdRow);
        layoutThirdRow.putConstraint(SpringLayout.WEST, txt_description, 5, SpringLayout.EAST, lbl_description);
        layoutThirdRow.putConstraint(SpringLayout.EAST, txt_description, -15, SpringLayout.EAST, thirdRow);
        
      
        layoutButtonRow.putConstraint(SpringLayout.NORTH, btn_confirm, 10, SpringLayout.NORTH, buttonRow);
        layoutButtonRow.putConstraint(SpringLayout.SOUTH, btn_confirm, -10, SpringLayout.SOUTH, buttonRow);
		layoutButtonRow.putConstraint(SpringLayout.WEST, btn_confirm, 15, SpringLayout.WEST, buttonRow);
		layoutButtonRow.putConstraint(SpringLayout.EAST, btn_confirm, -15, SpringLayout.EAST, buttonRow);
		
		
		
		firstRow.setAlignmentY(Component.CENTER_ALIGNMENT);
		secondRow.setAlignmentY(Component.CENTER_ALIGNMENT);
		thirdRow.setAlignmentY(Component.CENTER_ALIGNMENT);
		buttonRow.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		pane.add(firstRow, BorderLayout.CENTER);
		pane.add(secondRow, BorderLayout.CENTER);
		pane.add(thirdRow, BorderLayout.CENTER);
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
				System.out.println("Invalid date");
			}
			

			
		}
		
	}
}

