package Views;

import javax.swing.*;



import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import DAO.CourseDAO;
import DAO.RegistrationSessionDAO;
import DAO.SemesterDAO;
import Models.Course;
import Models.MinistryAccount;
import Models.RegistrationSession;
import Models.Semester;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class RegisterCourseForm extends JPanel implements ActionListener {
	

	private static final long serialVersionUID = 1L;
	JLabel lbl_title;
	JButton btn_register;
	JScrollPane scrollView;
	JTable tbl_semesterList;
	JFrame frame;
	List<Course> courses;
	List<RegistrationSession> sessions;
	Semester currentSemester;
	
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
			}
		});
		
		JComponent mainMenu = new RegisterCourseForm();
		mainMenu.setOpaque(true);
		mainMenu.setVisible(true);
		
	}
	
	
	public RegisterCourseForm() throws IOException, URISyntaxException {
		super(new BorderLayout());
		
		courses = new ArrayList<Course>();
		courses = CourseDAO.getCourseList();
		
		sessions = new ArrayList<RegistrationSession>();
		sessions = RegistrationSessionDAO.getRegistrationSessionList();
		
		for (Semester item:SemesterDAO.getSemesterList()) {
			if (item.getIsCurrentSemester() == 1) {
				this.currentSemester = item;
				break;
			}
		}
		
		Predicate<Course> predicateString = s -> {
			return !(s.getSemester().getId().equals(this.currentSemester.getId()));
        };
		courses.removeIf(predicateString);
		
		Predicate<RegistrationSession> predicateString2 = s -> {
			return !(s.getSemester().getId().equals(this.currentSemester.getId()));
        };
        sessions.removeIf(predicateString2);
		
        int flag = 0;
        for (RegistrationSession item:sessions)
        {
        	Date sessionStartDate;
    		Date sessionEndDate;
    		Date today = new Date();
    		
    		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    		try {
    			sessionStartDate = dateFormat.parse(item.getId().getStartDate());
    			sessionEndDate = dateFormat.parse(item.getId().getEndDate());
    			
    			if (today.after(sessionStartDate) && today.before(sessionEndDate)) {
    				flag = 1;
    				break;
    			}
    			
    		} catch (ParseException e1) {
    			
    			e1.printStackTrace();
    		}
        }
        
        if (flag == 0) {
        	courses.removeAll(courses);
        }
		
		

		JFrame.setDefaultLookAndFeelDecorated(true);
		
        frame = new JFrame("Semesters Management");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setPreferredSize(new Dimension(dim.width - 100,dim.height - 100));
        frame.setLocation(50, 50);
		
		Container pane = frame.getContentPane();
		pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
		
		JPanel firstRow = new JPanel();
		JPanel secondRow = new JPanel();
		JPanel thirdRow = new JPanel();
		SpringLayout layoutFirstRow = new SpringLayout();
		SpringLayout layoutSecondRow = new SpringLayout();
		SpringLayout layoutThirdRow = new SpringLayout();
		firstRow.setLayout(layoutFirstRow);	
		secondRow.setLayout(layoutSecondRow);
		thirdRow.setLayout(layoutThirdRow);
		
		firstRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		secondRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		thirdRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, frame.getPreferredSize().height - 120));
	
		
		
		lbl_title = new JLabel("Courses in this registration session");
		lbl_title.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbl_title.setHorizontalAlignment(JLabel.CENTER);
		lbl_title.setFont(new Font("Helvetica", Font.BOLD, 16));
		
		btn_register = new JButton("Register");
		btn_register.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_register.addActionListener(this);
		btn_register.setActionCommand("Register");
		
		
		
		int[] columnsWidth = { 135, 135, 135, 135, 0, 150 };
		class CoursesListTableModel extends AbstractTableModel {

			private static final long serialVersionUID = 1L;

			@Override
			public int getRowCount() {
				return courses.size();
			}
			
			@Override
			public boolean isCellEditable(int r, int c) {
			    return true;
			}

			@Override
			public int getColumnCount() {
				return 6;
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				
				Course item = courses.get(rowIndex);
				
				switch (columnIndex) {
				case 0:
					return item.getSubject().getId();
				case 1:
					return item.getSubject().getName();
				case 2:
					return item.getSubject().getCredits();
				case 3:
					return item.getSemester().getId().getName();
				case 4:
					return item.getSemester().getId().getSchoolYear();
				default:
					return false;
				}
			}
			
			@Override
			public String getColumnName(int column) {
				switch (column) {
				case 0:
					return "Subject id";
				case 1:
					return "Subject name";
				case 2:
					return "Credits";
				case 3:
					return "Semester's name";
				case 4:
					return "School year";
				default:
					return "Register";
				}
			}
		}
		

		
		tbl_semesterList = new JTable();		
		tbl_semesterList.setModel(new CoursesListTableModel());
		tbl_semesterList.setRowSelectionAllowed(true);
		tbl_semesterList.setRowHeight(30);
		tbl_semesterList.setBackground(Color.DARK_GRAY);
		tbl_semesterList.getTableHeader().setPreferredSize(new Dimension(0, 30));
		((DefaultTableCellRenderer)tbl_semesterList.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		
		int i = 0;
		for (int width : columnsWidth) {
		    TableColumn column = tbl_semesterList.getColumnModel().getColumn(i++);
		    
		    column.setMinWidth(20);
		    column.setMaxWidth(width);
		    if (i == 5) {
		    	column.setMaxWidth(Integer.MAX_VALUE);
		    }
		    column.setPreferredWidth(width);
		    
		    if (i == 6) {
		    	Action actionSetCurrentSemester = new AbstractAction()
				{
					private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent e)
				    {
						
				        
				    }
				};
		    	
				tbl_semesterList.getColumnModel().getColumn(i-1).setCellRenderer(new ActionRegisterCourseCellRenderer(tbl_semesterList, 
						actionSetCurrentSemester));
				tbl_semesterList.getColumnModel().getColumn(i-1).setCellEditor(new ActionRegisterCourseCellRenderer(tbl_semesterList, 
						actionSetCurrentSemester));
		    }
		    else {
		    	tbl_semesterList.getColumnModel().getColumn(i-1).setCellRenderer(new RegisterCourseRenderer());
		    }
		    
		    
		}
		
		
		
		scrollView = new JScrollPane(tbl_semesterList);

		
		
		firstRow.add(lbl_title);
		secondRow.add(btn_register);
		thirdRow.add(scrollView);
		
		layoutFirstRow.putConstraint(SpringLayout.NORTH, lbl_title, 10, SpringLayout.NORTH, firstRow);
		layoutFirstRow.putConstraint(SpringLayout.SOUTH, lbl_title, -10, SpringLayout.SOUTH, firstRow);
        layoutFirstRow.putConstraint(SpringLayout.WEST, lbl_title, 15, SpringLayout.WEST, firstRow);
        layoutFirstRow.putConstraint(SpringLayout.EAST, lbl_title, -15, SpringLayout.EAST, firstRow);

        
		
        layoutSecondRow.putConstraint(SpringLayout.NORTH, btn_register, 10, SpringLayout.NORTH, secondRow);
        layoutSecondRow.putConstraint(SpringLayout.SOUTH, btn_register, -10, SpringLayout.SOUTH, secondRow);
        layoutSecondRow.putConstraint(SpringLayout.EAST, btn_register, -15, SpringLayout.EAST, secondRow);
        
       
        layoutThirdRow.putConstraint(SpringLayout.NORTH, scrollView, 25, SpringLayout.NORTH, thirdRow);
        layoutThirdRow.putConstraint(SpringLayout.SOUTH, scrollView, -25, SpringLayout.SOUTH, thirdRow);
        layoutThirdRow.putConstraint(SpringLayout.WEST, scrollView, 15, SpringLayout.WEST, thirdRow);
        layoutThirdRow.putConstraint(SpringLayout.EAST, scrollView, -15, SpringLayout.EAST, thirdRow);

		
		
		firstRow.setAlignmentY(Component.CENTER_ALIGNMENT);
		secondRow.setAlignmentY(Component.CENTER_ALIGNMENT);
		thirdRow.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		pane.add(firstRow, BorderLayout.CENTER);
		pane.add(secondRow, BorderLayout.CENTER);
		pane.add(thirdRow, BorderLayout.CENTER);
		
		
		add(pane);
		

        this.setOpaque(true); 
        frame.setContentPane(this);
        
        
        frame.pack();
        frame.setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		
		String strActionCommand = e.getActionCommand();
		if (strActionCommand.equals("Register"))
		{
			
	    }

	}

}

class RegisterCourseRenderer extends JLabel implements  TableCellRenderer
{

  
  private static final long serialVersionUID = 1L;

  public RegisterCourseRenderer() {
   
    setOpaque(true);
  }
  @Override
  public Component getTableCellRendererComponent(JTable table, Object obj,
      boolean selected, boolean focused, int row, int col) {

      setText((obj==null) ? "":obj.toString());
      setHorizontalAlignment(SwingConstants.CENTER);
      setBackground(Color.white);

    return this;
  }

}


class ActionRegisterCourseCellRenderer extends AbstractCellEditor implements  TableCellEditor, TableCellRenderer, ActionListener
{
	static final long serialVersionUID = 1L;
	private JTable table;
	private Action action;
	private boolean isChecked;
	private Object editorValue;
	
	public ActionRegisterCourseCellRenderer(JTable table, Action action) {

		this.table = table;
		this.action= action;
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object obj,
	    boolean selected, boolean focused, int row, int col) {
		
		this.isChecked = (boolean) obj;
		
		JCheckBox checkbox = new JCheckBox();
		Border emptyBorder2 = BorderFactory.createEmptyBorder();
		checkbox.setBorder(emptyBorder2);
		checkbox.setPreferredSize(new Dimension(30, 30));
		checkbox.setBackground(Color.white);
		checkbox.setForeground(Color.white);
		checkbox.addActionListener(this);
		checkbox.setActionCommand("checkbox");
		checkbox.setAlignmentX(JCheckBox.CENTER);
		checkbox.setAlignmentY(JCheckBox.CENTER);
		checkbox.setHorizontalAlignment(JCheckBox.CENTER);
		checkbox.setVerticalAlignment(JCheckBox.CENTER);
		checkbox.setSelected(isChecked);
		
		
		JPanel view_button = new JPanel();
		view_button.setLayout(new GridLayout(1,3));
		view_button.setBackground(Color.white);
		view_button.add(checkbox);
	
		return view_button;
	}
	  
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		
		this.isChecked = (boolean) value;
			
		JCheckBox checkbox = new JCheckBox();
		Border emptyBorder2 = BorderFactory.createEmptyBorder();
		checkbox.setBorder(emptyBorder2);
		checkbox.setPreferredSize(new Dimension(30, 30));
		checkbox.setBackground(Color.white);
		checkbox.setForeground(Color.white);
		checkbox.addActionListener(this);
		checkbox.setActionCommand("checkbox");
		checkbox.setAlignmentX(JCheckBox.CENTER);
		checkbox.setAlignmentY(JCheckBox.CENTER);
		checkbox.setHorizontalAlignment(JCheckBox.CENTER);
		checkbox.setVerticalAlignment(JCheckBox.CENTER);
		checkbox.setSelected(isChecked);

		
		JPanel view_button = new JPanel();
		view_button.setLayout(new GridLayout(1,3));
		view_button.setBackground(Color.white);
		view_button.add(checkbox);
	
		return view_button;
	}
	  
	@Override
	public Object getCellEditorValue() {
		return editorValue;
	}

	public void actionPerformed(ActionEvent e)
	{
	
		int row = table.convertRowIndexToModel( table.getEditingRow() );
		fireEditingStopped();

		ActionEvent event = new ActionEvent(
			table,
			ActionEvent.ACTION_PERFORMED,
			e.getActionCommand() + "-" + isChecked + "-"+ row);
		action.actionPerformed(event);
	}
	
	
}




