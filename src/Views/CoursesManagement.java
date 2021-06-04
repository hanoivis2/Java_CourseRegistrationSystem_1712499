package Views;

import javax.swing.*;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import DAO.CourseDAO;
import Models.Course;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CoursesManagement extends JPanel implements ActionListener {
	

	private static final long serialVersionUID = 1L;
	JLabel lbl_title;
	JButton btn_createCourse;
	JScrollPane scrollView;
	JTable tbl_coursesList;
	JLabel lbl_search;
	JTextField txt_search;
	JFrame frame;
	List<Course> courses;
	List<Course> coursesFilter;
	
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
			}
		});
		
	
		JComponent mainMenu = new CoursesManagement();
		mainMenu.setOpaque(true);
		mainMenu.setVisible(true);
	}
	
	
	public CoursesManagement() throws IOException, URISyntaxException {
		super(new BorderLayout());
		
		courses = new ArrayList<Course>();
		coursesFilter = new ArrayList<Course>();
		
		courses = CourseDAO.getCourseList();
	
		coursesFilter.removeAll(coursesFilter);
		coursesFilter.addAll(courses);

		JFrame.setDefaultLookAndFeelDecorated(true);
		
        frame = new JFrame("Courses Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setPreferredSize(new Dimension(dim.width - 100,dim.height - 100));
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
	
		
		
		lbl_title = new JLabel("Courses in this semester");
		lbl_title.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbl_title.setHorizontalAlignment(JLabel.CENTER);
		lbl_title.setFont(new Font("Helvetica", Font.BOLD, 16));
		
		btn_createCourse = new JButton("Create new course");
		btn_createCourse.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_createCourse.addActionListener(this);
		btn_createCourse.setActionCommand("Create");
		
		
		lbl_search = new JLabel("Enter course's name to search: ");       
        lbl_search.setPreferredSize(new Dimension(200,40));
		
		
        txt_search = new JTextField("Enter search text...", 15);
        txt_search.setForeground(Color.GRAY);
        txt_search.setPreferredSize(new Dimension(0,50));
        txt_search.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (txt_search.getText().equals("Enter search text...")) {
		        	txt_search.setText("");
		        	txt_search.setForeground(Color.BLACK);
		        }
		    }
		    
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (txt_search.getText().isEmpty()) {
		        	txt_search.setForeground(Color.GRAY);
		        	txt_search.setText("Enter search text...");
		        }
		        
		        coursesFilter.removeAll(coursesFilter);
				coursesFilter.addAll(courses);
				revalidate();
		        repaint();
		    }
	    });
        txt_search.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				
				if (txt_search.getText().equals("")) {
					coursesFilter.removeAll(coursesFilter);
					coursesFilter.addAll(courses);
				}
				else {
					Predicate<Course> predicateString = s -> {
			            return !s.getSubjectId().toLowerCase().contains(txt_search.getText().toLowerCase());
			        };
			        coursesFilter.removeAll(coursesFilter);
					coursesFilter.addAll(courses);
			        coursesFilter.removeIf(predicateString);
				}
				
				revalidate();
		        repaint();
				
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				
				if (txt_search.getText().equals("")) {
					coursesFilter.removeAll(coursesFilter);
					coursesFilter.addAll(courses);
				}
				else {
					Predicate<Course> predicateString = s -> {
						return !s.getSubjectId().toLowerCase().contains(txt_search.getText().toLowerCase());
			        };
			        coursesFilter.removeAll(coursesFilter);
					coursesFilter.addAll(courses);
			        coursesFilter.removeIf(predicateString);
				}
				
				revalidate();
		        repaint();
				
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				
				if (txt_search.getText().equals("")) {
					coursesFilter.removeAll(coursesFilter);
					coursesFilter.addAll(courses);
				}
				else {
					Predicate<Course> predicateString = s -> {
						return !s.getSubjectId().toLowerCase().contains(txt_search.getText().toLowerCase());
			        };
			        coursesFilter.removeAll(coursesFilter);
					coursesFilter.addAll(courses);
			        coursesFilter.removeIf(predicateString);
				}
				
				revalidate();
		        repaint();
				
			}
		});
		
		int[] columnsWidth = { 100, 30, 70, 150, 90, 90, 50, 80, 50 };
		class CoursesListTableModel extends AbstractTableModel {

			private static final long serialVersionUID = 1L;

			@Override
			public int getRowCount() {
				return coursesFilter.size();
			}
			
			@Override
			public boolean isCellEditable(int r, int c) {
			    return true;
			}

			@Override
			public int getColumnCount() {
				return 9;
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				
				Course item = coursesFilter.get(rowIndex);
				
				switch (columnIndex) {
				case 0:
					return item.getSubjectId();
				case 1:
					return item.getSubjectId();
				case 2:
					return item.getSubjectId();
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
				default:
					return "";
				}
			}
			
			@Override
			public String getColumnName(int column) {
				switch (column) {
				case 0:
					return "Course ID";
				case 1:
					return "Course name";
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
					return "Max amount";
				default:
					return "";
				}
			}
		}
		

		
		tbl_coursesList = new JTable();		
		tbl_coursesList.setModel(new CoursesListTableModel());
		tbl_coursesList.setRowSelectionAllowed(true);
		tbl_coursesList.setRowHeight(30);
		tbl_coursesList.setBackground(Color.DARK_GRAY);
		tbl_coursesList.getTableHeader().setPreferredSize(new Dimension(0, 30));
		((DefaultTableCellRenderer)tbl_coursesList.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		
		int i = 0;
		for (int width : columnsWidth) {
		    TableColumn column = tbl_coursesList.getColumnModel().getColumn(i++);
		    column.setMinWidth(20);
		    column.setMaxWidth(width);
		    if (i == 2) {
		    	column.setMaxWidth(Integer.MAX_VALUE);
		    }
		    column.setPreferredWidth(width);
		    
		    if (i == 9) {
		    	
		    	Action actionDelete = new AbstractAction()
				{
					private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent e)
				    {
//				        int modelRow = Integer.valueOf( e.getActionCommand() );
				        
				    }
				};
		    	
		    	tbl_coursesList.getColumnModel().getColumn(i-1).setCellRenderer(new CourseActionCellRenderer(tbl_coursesList, actionDelete));
		    	tbl_coursesList.getColumnModel().getColumn(i-1).setCellEditor(new CourseActionCellRenderer(tbl_coursesList, actionDelete));
		    }
		    else {
		    	tbl_coursesList.getColumnModel().getColumn(i-1).setCellRenderer(new RowSessionListRenderer());
		    }
		    
		    
		}
		
		
		
		scrollView = new JScrollPane(tbl_coursesList);

		
		
		firstRow.add(lbl_title);
		searchRow.add(lbl_search);
		searchRow.add(txt_search);
		secondRow.add(btn_createCourse);
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
        
		
        layoutSecondRow.putConstraint(SpringLayout.NORTH, btn_createCourse, 10, SpringLayout.NORTH, secondRow);
        layoutSecondRow.putConstraint(SpringLayout.SOUTH, btn_createCourse, -10, SpringLayout.SOUTH, secondRow);
        layoutSecondRow.putConstraint(SpringLayout.EAST, btn_createCourse, -15, SpringLayout.EAST, secondRow);
        
       
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

class RowSessionListRenderer extends JLabel implements  TableCellRenderer
{

  
  private static final long serialVersionUID = 1L;

  public RowSessionListRenderer() {
   
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

class CourseActionCellRenderer extends AbstractCellEditor implements  TableCellEditor, TableCellRenderer, ActionListener
{
	static final long serialVersionUID = 1L;
	private JTable table;
	private Action actionDelete;
	
	private Object editorValue;
	
	public CourseActionCellRenderer(JTable table, Action actionDelete) {

		this.table = table;
		this.actionDelete = actionDelete;

	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object obj,
	    boolean selected, boolean focused, int row, int col) {
	
		JButton btn_delete = new JButton();
		  
		btn_delete.setBackground(Color.white);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		btn_delete.setBorder(emptyBorder);
		btn_delete.setPreferredSize(new Dimension(30, 30));
		btn_delete.setBackground(Color.white);
		btn_delete.setForeground(Color.white);
		  
		ImageIcon icon = new ImageIcon("img/delete.png");
		Image scaleImage = icon.getImage().getScaledInstance(25, 25,Image.SCALE_SMOOTH);
		btn_delete.setIcon(new ImageIcon(scaleImage));
		btn_delete.addActionListener(this);
		btn_delete.setMnemonic(KeyEvent.VK_D);
		  
		if (focused) {
			btn_delete.setBorder( new LineBorder(Color.BLUE) );
		}
		
		JPanel view_button = new JPanel();
		view_button.setLayout(new BorderLayout());
		view_button.setBackground(Color.white);
		view_button.add(btn_delete);
	
		return view_button;
	}
	  
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			
		JButton btn_delete = new JButton();
		  
		btn_delete.setBackground(Color.white);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		btn_delete.setBorder(emptyBorder);
		btn_delete.setPreferredSize(new Dimension(30, 30));
		btn_delete.setBackground(Color.white);
		btn_delete.setForeground(Color.white);
		  
		ImageIcon icon = new ImageIcon("img/delete.png");
		Image scaleImage = icon.getImage().getScaledInstance(25, 25,Image.SCALE_SMOOTH);
		btn_delete.setIcon(new ImageIcon(scaleImage));
		btn_delete.addActionListener(this);
		btn_delete.setMnemonic(KeyEvent.VK_D);

		
		JPanel view_button = new JPanel();
		view_button.setLayout(new BorderLayout());
		view_button.setBackground(Color.white);
		view_button.add(btn_delete);
	
		this.editorValue = value;
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
			"" + row);
		actionDelete.actionPerformed(event);
	}
	
	
}






