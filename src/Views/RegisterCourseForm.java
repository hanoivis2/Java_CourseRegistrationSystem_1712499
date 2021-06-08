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
import DAO.StudentAccountDAO;
import DAO.StudentRegisterCourseDAO;
import Models.Course;
import Models.RegistrationSession;
import Models.Semester;
import Models.StudentAccount;
import Models.StudentRegisterCourse;
import Models.StudentRegisterCourseID;

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
	JTable tbl_courseList;
	JFrame frame;
	List<Course> courses;
	List<RegistrationSession> sessions;
	Semester currentSemester;
	private boolean[] selectedCourses;
	
	private String studentId;
	private Action action;
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
			}
		});

	}
	
	
	public RegisterCourseForm(String studentId, Action action) throws IOException, URISyntaxException {
		super(new BorderLayout());
		
		this.studentId = studentId;
		this.action = action;
		
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
		
        selectedCourses = new boolean[courses.size()];
        
        for (int i = 0; i < selectedCourses.length; i++) {
        	selectedCourses[i] = false;
        }
		
        for (int i = 0; i < courses.size(); i++) {
			List<Course> registredCourses = new ArrayList<Course>(StudentAccountDAO.getStudentAccountById(studentId).getCourses());
			for (int j = 0; j < registredCourses.size(); j++) {
				if (courses.get(i).getId().equals(registredCourses.get(j).getId())) {
					selectedCourses[i] = true;
				}
			}
		}

		JFrame.setDefaultLookAndFeelDecorated(true);
		
        frame = new JFrame("Course Registration");
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
		
		
		
		int[] columnsWidth = { 100, 0, 70, 200, 80, 80, 60, 80, 80, 80 };
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
				return 10;
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
					return item.getTheoryTeacherName();
				case 4:
					return item.getRoomName();
				case 5:
					return item.getDayInWeek();
				case 6:
					return item.getShift();
				case 7:
					return item.getMaxAmountStudent();
				case 8:
					List<StudentAccount> students = new ArrayList<StudentAccount>(item.getStudents());
					return students.size();
				default:
					return selectedCourses[rowIndex];
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
					return "Theory teacher's name";
				case 4:
					return "Room name";
				case 5:
					return "Day in week";
				case 6:
					return "Shift";
				case 7:
					return "Max slots";
				case 8:
					return "Registered";
				default:
					return "Register";
				}
			}
		}
		

		
		tbl_courseList = new JTable();		
		tbl_courseList.setModel(new CoursesListTableModel());
		tbl_courseList.setRowSelectionAllowed(true);
		tbl_courseList.setRowHeight(30);
		tbl_courseList.setBackground(Color.DARK_GRAY);
		tbl_courseList.getTableHeader().setPreferredSize(new Dimension(0, 30));
		((DefaultTableCellRenderer)tbl_courseList.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		
		int i = 0;
		for (int width : columnsWidth) {
		    TableColumn column = tbl_courseList.getColumnModel().getColumn(i++);
		    
		    column.setMinWidth(20);
		    column.setMaxWidth(width);
		    if (i == 2) {
		    	column.setMaxWidth(Integer.MAX_VALUE);
		    }
		    column.setPreferredWidth(width);
		    
		    if (i == 10) {
		    	Action actionSetCurrentSemester = new AbstractAction()
				{
					private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent e)
				    {
						
						String[] commandTokens = e.getActionCommand().split("-");
				        String command = commandTokens[0];
				        boolean value = Boolean.parseBoolean(commandTokens[1]);
				        int row = Integer.parseInt(commandTokens[2]);
				        
				        System.out.println(command + "- " + value + " - " + row);
				        
				        if (value == true) {
				        	selectedCourses[row] = false;
				        }
				        else {
				        	int selectedCoursesCount = 0;
				        	for (int i = 0; i < selectedCourses.length; i++) {
				        		if (selectedCourses[i] == true) {
				        			selectedCoursesCount++;
				        			
				        			if (i != row) {
				        				Course item = courses.get(i);
				        				Course selectedItem = courses.get(row);
				        				
				        				if (item.getDayInWeek().equals(selectedItem.getDayInWeek())
				        						&& item.getShift() == selectedItem.getShift()) {
				        					showMessageDialog(null, "You can not register 2 courses at same time!");
				        					return;
				        				}
				        			}
				        			
				        		}
				        	}
				        	
				        	if (selectedCoursesCount >= 8) {
				        		showMessageDialog(null, "You can only register 8 courses at maximum!");
				        		return;
				        	}
				        	else {
				        		selectedCourses[row] = true;
				        	}
				     
				        }
				        
				        tbl_courseList.revalidate();
			        	tbl_courseList.repaint();
			        	
			        	revalidate();
			        	repaint();
				        
				    }
				};
		    	
				tbl_courseList.getColumnModel().getColumn(i-1).setCellRenderer(new ActionRegisterCourseCellRenderer(tbl_courseList, 
						actionSetCurrentSemester));
				tbl_courseList.getColumnModel().getColumn(i-1).setCellEditor(new ActionRegisterCourseCellRenderer(tbl_courseList, 
						actionSetCurrentSemester));
		    }
		    else {
		    	tbl_courseList.getColumnModel().getColumn(i-1).setCellRenderer(new RegisterCourseRenderer());
		    }
		    
		    
		}
		
		
		
		scrollView = new JScrollPane(tbl_courseList);

		
		
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
			List< StudentRegisterCourse> allRegisters = StudentRegisterCourseDAO.getAllRegister();
			for (int i = 0; i < allRegisters.size(); i++) {
				if (allRegisters.get(i).getId().getStudentId().equals(this.studentId)) {
					
					Semester currentSemester = new Semester();
					
					for (Semester item:SemesterDAO.getSemesterList()) {
						if (item.getIsCurrentSemester() == 1) {
							currentSemester = item;
							break;
						}
					}
					
					if (currentSemester.getId().getName().equals(allRegisters.get(i).getId().getSemesterName())
							&& currentSemester.getId().getSchoolYear().equals(allRegisters.get(i).getId().getSemesterSchoolYear())) {
						StudentRegisterCourseDAO.deleteRegister(allRegisters.get(i));
					}
					
				}
			}
			
			for (int i = 0; i < this.selectedCourses.length; i++) {
				if (this.selectedCourses[i] == true) {
					
					StudentRegisterCourse registerInfo = new StudentRegisterCourse();
					
					StudentRegisterCourseID id = new StudentRegisterCourseID();
					id.setStudentId(this.studentId);
					id.setSubjectId(courses.get(i).getSubject().getId());
					id.setSubjectName(courses.get(i).getSubject().getName());
					id.setSubjectCredits(courses.get(i).getSubject().getCredits());
					id.setSemesterName(courses.get(i).getSemester().getId().getName());
					id.setSemesterSchoolYear(courses.get(i).getSemester().getId().getSchoolYear());
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					String createDate = dateFormat.format(new Date());
					
					registerInfo.setId(id);
					registerInfo.setCreateDate(createDate);
					
					StudentRegisterCourseDAO.addRegister(registerInfo);
					
				}
			}
			
			ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "refresh");
			action.actionPerformed(event);
			this.frame.dispose();
			showMessageDialog(null, "Register successfully!");
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




