package Views;

import javax.swing.*;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import DAO.CourseDAO;
import DAO.StudentRegisterCourseDAO;
import Models.Course;
import Models.CourseID;
import Models.StudentAccount;
import Models.StudentRegisterCourse;
import Models.StudentRegisterCourseID;


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class StudentsInCourseManagement extends JPanel implements ActionListener {
	

	private static final long serialVersionUID = 1L;
	public CourseID courseId;
	
	JLabel lbl_title;
	JScrollPane scrollView;
	JTable tbl_students;
	JLabel lbl_search;
	JTextField txt_search;
	JFrame frame;
	List<StudentAccount> students;
	List<StudentAccount> studentsFilter;

	
	public static void main(String[] args) throws IOException, URISyntaxException {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
			}
		});
		
	}
	
	
	public StudentsInCourseManagement(CourseID courseId) throws IOException, URISyntaxException {
		super(new BorderLayout());
	
		
		this.courseId = courseId;
		
		students = new ArrayList<StudentAccount>();
		studentsFilter = new ArrayList<StudentAccount>();
		
		students = new ArrayList<StudentAccount>(CourseDAO.getCourseById(courseId).getStudents());
		students.sort(StudentAccount.studentIdAscendingComparator);
	
		studentsFilter.removeAll(studentsFilter);
		studentsFilter.addAll(students);

		JFrame.setDefaultLookAndFeelDecorated(true);
		
        frame = new JFrame("Students");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setPreferredSize(new Dimension(dim.width - 50,dim.height - 100));
        frame.setLocation(50, 50);
		
		Container pane = frame.getContentPane();
		pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
		
		JPanel firstRow = new JPanel();
		JPanel searchRow = new JPanel();
		JPanel secondRow = new JPanel();
		JPanel thirdRow = new JPanel();
		SpringLayout layoutFirstRow = new SpringLayout();
		SpringLayout layoutSearchRow = new SpringLayout();
		SpringLayout layoutSecondRow = new SpringLayout();
		SpringLayout layoutThirdRow = new SpringLayout();
		firstRow.setLayout(layoutFirstRow);	
		searchRow.setLayout(layoutSearchRow);
		secondRow.setLayout(layoutSecondRow);
		thirdRow.setLayout(layoutThirdRow);
		
		firstRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		searchRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		secondRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
		thirdRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, frame.getPreferredSize().height - 180));
	
		
		
		lbl_title = new JLabel("Students in course");
		lbl_title.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbl_title.setHorizontalAlignment(JLabel.CENTER);
		lbl_title.setFont(new Font("Helvetica", Font.BOLD, 16));
		
		
		lbl_search = new JLabel("Enter student's name or id to search: ");       
        lbl_search.setPreferredSize(new Dimension(200,40));
		
		
        txt_search = new JTextField("Enter name or id to search ...", 15);
        txt_search.setForeground(Color.GRAY);
        txt_search.setPreferredSize(new Dimension(0,50));
        txt_search.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (txt_search.getText().equals("Enter name or id to search ...")) {
		        	txt_search.setText("");
		        	txt_search.setForeground(Color.BLACK);
		        }
		    }
		    
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (txt_search.getText().isEmpty()) {
		        	txt_search.setForeground(Color.GRAY);
		        	txt_search.setText("Enter name or id to search ...");
		        }
		        
		        studentsFilter.removeAll(studentsFilter);
		        studentsFilter.addAll(students);
				revalidate();
		        repaint();
		    }
	    });
        txt_search.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				
				if (txt_search.getText().equals("")) {
					studentsFilter.removeAll(studentsFilter);
					studentsFilter.addAll(students);
				}
				else {
					Predicate<StudentAccount> predicateString = s -> {
			            return !s.getFullName().toLowerCase().contains(txt_search.getText().toLowerCase())
			            		&& !String.valueOf(s.getId()).contains(txt_search.getText().toLowerCase());
			        };
			        studentsFilter.removeAll(studentsFilter);
			        studentsFilter.addAll(students);
			        studentsFilter.removeIf(predicateString);
				}
				
				revalidate();
		        repaint();
				
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				
				if (txt_search.getText().equals("")) {
					studentsFilter.removeAll(studentsFilter);
					studentsFilter.addAll(students);
				}
				else {
					Predicate<StudentAccount> predicateString = s -> {
			            return !s.getFullName().toLowerCase().contains(txt_search.getText().toLowerCase())
			            		&& !String.valueOf(s.getId()).contains(txt_search.getText().toLowerCase());
			        };
			        studentsFilter.removeAll(studentsFilter);
			        studentsFilter.addAll(students);
			        studentsFilter.removeIf(predicateString);
				}
				
				revalidate();
		        repaint();
				
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				
				if (txt_search.getText().equals("")) {
					studentsFilter.removeAll(studentsFilter);
					studentsFilter.addAll(students);
				}
				else {
					Predicate<StudentAccount> predicateString = s -> {
			            return !s.getFullName().toLowerCase().contains(txt_search.getText().toLowerCase())
			            		&& !String.valueOf(s.getId()).contains(txt_search.getText().toLowerCase());
			        };
			        studentsFilter.removeAll(studentsFilter);
			        studentsFilter.addAll(students);
			        studentsFilter.removeIf(predicateString);
				}
				
				revalidate();
		        repaint();
				
			}
		});
		
		int[] columnsWidth = { 100, 200, 100, 150, 200, 180, 120 };
		class CoursesListTableModel extends AbstractTableModel {

			private static final long serialVersionUID = 1L;

			@Override
			public int getRowCount() {
				return studentsFilter.size();
			}
			
			@Override
			public boolean isCellEditable(int r, int c) {
			    return true;
			}

			@Override
			public int getColumnCount() {
				return 7;
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				
				StudentAccount item = studentsFilter.get(rowIndex);
				Course course = CourseDAO.getCourseById(courseId);
				
				StudentRegisterCourseID id = new StudentRegisterCourseID();
				id.setDayInWeek( course.getId().getDayInWeek());
				id.setRoomName(course.getId().getRoomName());
				id.setSemesterName(course.getId().getSemesterName());
				id.setSemesterSchoolYear(course.getId().getSemesterSchoolYear());
				id.setShift(course.getId().getShift());
				id.setStudentId(item.getId());
				id.setSubjectCredits(course.getSubject().getCredits());
				id.setSubjectId(course.getSubject().getId());
				id.setSubjectName(course.getSubject().getName());
				id.setTheoryTeacherName(course.getId().getTheoryTeacherName());
				
				StudentRegisterCourse registerInfo = StudentRegisterCourseDAO.getRegisterById(id);
				
				switch (columnIndex) {
				case 0:
					return item.getId();
				case 1:
					return item.getFullName();
				case 2:
					return course.getSubject().getId();
				case 3:
					return course.getSubject().getName();
				case 4:
					return course.getId().getTheoryTeacherName();
				case 5:
					return "shift " + course.getId().getShift() + " / " + course.getId().getDayInWeek();
				default:
					return registerInfo.getCreateDate();
				}
			}
			
			@Override
			public String getColumnName(int column) {
				switch (column) {
				case 0:
					return "Student's ID";
				case 1:
					return "Student's name";
				case 2:
					return "Subject's ID";
				case 3:
					return "Subject's name";
				case 4:
					return "Theory teacher's name";
				case 5:
					return "Schedule";
				default:
					return "Register date";
				}
			}
		}
		

		
		tbl_students = new JTable();		
		tbl_students.setModel(new CoursesListTableModel());
		tbl_students.setRowSelectionAllowed(true);
		tbl_students.setRowHeight(30);
		tbl_students.setBackground(Color.DARK_GRAY);
		tbl_students.getTableHeader().setPreferredSize(new Dimension(0, 30));
		((DefaultTableCellRenderer)tbl_students.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		
		int i = 0;
		for (int width : columnsWidth) {
		    TableColumn column = tbl_students.getColumnModel().getColumn(i++);
		    column.setMinWidth(20);
		    column.setMaxWidth(width);
		    if (i == 4) {
		    	column.setMaxWidth(Integer.MAX_VALUE);
		    }
		    else {
		    	column.setPreferredWidth(width);
		    }
		    
		    tbl_students.getColumnModel().getColumn(i-1).setCellRenderer(new RowStudentsInCourseRenderer());
		    
		    
		}
		
		
		
		scrollView = new JScrollPane(tbl_students);

		
		
		firstRow.add(lbl_title);
		searchRow.add(lbl_search);
		searchRow.add(txt_search);
		thirdRow.add(scrollView);
		
		layoutFirstRow.putConstraint(SpringLayout.NORTH, lbl_title, 10, SpringLayout.NORTH, firstRow);
		layoutFirstRow.putConstraint(SpringLayout.SOUTH, lbl_title, -10, SpringLayout.SOUTH, firstRow);
        layoutFirstRow.putConstraint(SpringLayout.WEST, lbl_title, 15, SpringLayout.WEST, firstRow);
        layoutFirstRow.putConstraint(SpringLayout.EAST, lbl_title, -15, SpringLayout.EAST, firstRow);
        
        layoutSearchRow.putConstraint(SpringLayout.NORTH, lbl_search, 10, SpringLayout.NORTH, searchRow);
        layoutSearchRow.putConstraint(SpringLayout.SOUTH, lbl_search, -10, SpringLayout.SOUTH, searchRow);
        layoutSearchRow.putConstraint(SpringLayout.NORTH, txt_search, 10, SpringLayout.NORTH, searchRow);
        layoutSearchRow.putConstraint(SpringLayout.SOUTH, txt_search, -10, SpringLayout.SOUTH, searchRow);
        layoutSearchRow.putConstraint(SpringLayout.WEST, lbl_search, 15, SpringLayout.WEST, searchRow);
        layoutSearchRow.putConstraint(SpringLayout.WEST, txt_search, 15, SpringLayout.EAST, lbl_search);
        layoutSearchRow.putConstraint(SpringLayout.EAST, txt_search, -15, SpringLayout.EAST, searchRow);
    
        
       
        layoutThirdRow.putConstraint(SpringLayout.NORTH, scrollView, 25, SpringLayout.NORTH, thirdRow);
        layoutThirdRow.putConstraint(SpringLayout.SOUTH, scrollView, -25, SpringLayout.SOUTH, thirdRow);
        layoutThirdRow.putConstraint(SpringLayout.WEST, scrollView, 15, SpringLayout.WEST, thirdRow);
        layoutThirdRow.putConstraint(SpringLayout.EAST, scrollView, -15, SpringLayout.EAST, thirdRow);

		
		
		firstRow.setAlignmentY(Component.CENTER_ALIGNMENT);
		searchRow.setAlignmentY(Component.CENTER_ALIGNMENT);
		secondRow.setAlignmentY(Component.CENTER_ALIGNMENT);
		thirdRow.setAlignmentY(Component.CENTER_ALIGNMENT);
		
		pane.add(firstRow, BorderLayout.CENTER);
		pane.add(searchRow, BorderLayout.CENTER);
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
		if (strActionCommand.equals("Create"))
		{
			
	    }

	}


	
}

class RowStudentsInCourseRenderer extends JLabel implements  TableCellRenderer
{

  
  private static final long serialVersionUID = 1L;

  public RowStudentsInCourseRenderer() {
   
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



	
	






