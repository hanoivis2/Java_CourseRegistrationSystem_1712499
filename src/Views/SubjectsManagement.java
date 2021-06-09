package Views;

import javax.swing.*;


import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import DAO.CourseDAO;
import DAO.StudentRegisterCourseDAO;
import DAO.SubjectDAO;
import Models.Course;
import Models.StudentRegisterCourse;
import Models.Subject;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SubjectsManagement extends JPanel implements ActionListener {
	

	private static final long serialVersionUID = 1L;
	JLabel lbl_title;
	JButton btn_addSubject;
	JScrollPane scrollView;
	JTable tbl_subjectsList;
	JLabel lbl_search;
	JTextField txt_search;
	JFrame frame;
	List<Subject> subjects;
	List<Subject> subjectsFilter;
	
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
			}
		});
		
	
	}
	
	
	public SubjectsManagement() throws IOException, URISyntaxException {
		super(new BorderLayout());
		
		subjects = new ArrayList<Subject>();
		subjectsFilter = new ArrayList<Subject>();
		
		subjects = SubjectDAO.getSubjectList();
		subjects.sort(Subject.subjectIdAscendingComparator);
	
		subjectsFilter.removeAll(subjectsFilter);
		subjectsFilter.addAll(subjects);

		JFrame.setDefaultLookAndFeelDecorated(true);
		
        frame = new JFrame("Subjects Management");
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
	
		
		
		lbl_title = new JLabel("Subjects list");
		lbl_title.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbl_title.setHorizontalAlignment(JLabel.CENTER);
		lbl_title.setFont(new Font("Helvetica", Font.BOLD, 16));
		
		btn_addSubject = new JButton("Add subject");
		btn_addSubject.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_addSubject.addActionListener(this);
		btn_addSubject.setActionCommand("Create");
		
		
		lbl_search = new JLabel("Enter subject's name to search: ");       
        lbl_search.setPreferredSize(new Dimension(220,40));
		
		
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
		        
		        subjectsFilter.removeAll(subjectsFilter);
		        subjectsFilter.addAll(subjects);
				revalidate();
		        repaint();
		    }
	    });
        txt_search.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				
				if (txt_search.getText().equals("")) {
					subjectsFilter.removeAll(subjectsFilter);
					subjectsFilter.addAll(subjects);
				}
				else {
					Predicate<Subject> predicateString = s -> {
						return !s.getName().toLowerCase().contains(txt_search.getText().toLowerCase())
								&& !s.getId().toLowerCase().contains(txt_search.getText().toLowerCase());
			        };
			        subjectsFilter.removeAll(subjectsFilter);
			        subjectsFilter.addAll(subjects);
			        subjectsFilter.removeIf(predicateString);
				}
				
				revalidate();
		        repaint();
				
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				
				if (txt_search.getText().equals("")) {
					subjectsFilter.removeAll(subjectsFilter);
					subjectsFilter.addAll(subjects);
				}
				else {
					Predicate<Subject> predicateString = s -> {
						return !s.getName().toLowerCase().contains(txt_search.getText().toLowerCase())
								&& !s.getId().toLowerCase().contains(txt_search.getText().toLowerCase());
			        };
			        subjectsFilter.removeAll(subjectsFilter);
			        subjectsFilter.addAll(subjects);
			        subjectsFilter.removeIf(predicateString);
				}
				
				revalidate();
		        repaint();
				
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				
				if (txt_search.getText().equals("")) {
					subjectsFilter.removeAll(subjectsFilter);
					subjectsFilter.addAll(subjects);
				}
				else {
					Predicate<Subject> predicateString = s -> {
						return !s.getName().toLowerCase().contains(txt_search.getText().toLowerCase())
								&& !s.getId().toLowerCase().contains(txt_search.getText().toLowerCase());
			        };
			        subjectsFilter.removeAll(subjectsFilter);
			        subjectsFilter.addAll(subjects);
			        subjectsFilter.removeIf(predicateString);
				}
				
				revalidate();
		        repaint();
				
			}
		});
		
		int[] columnsWidth = { 120, 0, 100, 80 };
		class CoursesListTableModel extends AbstractTableModel {

			private static final long serialVersionUID = 1L;

			@Override
			public int getRowCount() {
				return subjectsFilter.size();
			}
			
			@Override
			public boolean isCellEditable(int r, int c) {
			    return true;
			}

			@Override
			public int getColumnCount() {
				return 4;
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				
				Subject item = subjectsFilter.get(rowIndex);
				
				switch (columnIndex) {
				case 0:
					return item.getId();
				case 1:
					return item.getName();
				case 2:
					return item.getCredits();
				default:
					return "";
				}
			}
			
			@Override
			public String getColumnName(int column) {
				switch (column) {
				case 0:
					return "Subject's id";
				case 1:
					return "Subject's name";
				case 2:
					return "Credits";
				default:
					return "";
				}
			}
		}
		

		
		tbl_subjectsList = new JTable();		
		tbl_subjectsList.setModel(new CoursesListTableModel());
		tbl_subjectsList.setRowSelectionAllowed(true);
		tbl_subjectsList.setRowHeight(30);
		tbl_subjectsList.setBackground(Color.DARK_GRAY);
		tbl_subjectsList.getTableHeader().setPreferredSize(new Dimension(0, 30));
		((DefaultTableCellRenderer)tbl_subjectsList.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		
		int i = 0;
		for (int width : columnsWidth) {
		    TableColumn column = tbl_subjectsList.getColumnModel().getColumn(i++);
		    column.setMinWidth(20);
		    column.setMaxWidth(width);
		    if (i == 2) {
		    	column.setMaxWidth(Integer.MAX_VALUE);
		    }
		    column.setPreferredWidth(width);
		    
		    if (i == 4) {
		    	
		    	Action actionMinistryAccount = new AbstractAction()
				{
					private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent e)
				    {
						String[] commandTokens = e.getActionCommand().split("-");
				        String command = commandTokens[0];
				        int row = Integer.parseInt(commandTokens[1]);
				        
				        System.out.println(e.getActionCommand());
				        
				        if(command.equals("edit")) {				        	
							try {
								
								Subject subjectToEdit = subjectsFilter.get(row);
					        	Action actionEdit = new AbstractAction()
								{
									private static final long serialVersionUID = 1L;

									public void actionPerformed(ActionEvent e)
								    {
								        
										subjects.removeAll(subjects);
										subjectsFilter.removeAll(subjectsFilter);
										
										
										subjects = SubjectDAO.getSubjectList();
										subjects.sort(Subject.subjectIdAscendingComparator);
									
										subjectsFilter.removeAll(subjectsFilter);
										subjectsFilter.addAll(subjects);
										
										tbl_subjectsList.revalidate();
										tbl_subjectsList.repaint();
								        
										revalidate();
								        repaint();
								    }
								};
								
								JComponent editSubjectForm;
								editSubjectForm = new EditSubjectForm(actionEdit, subjectToEdit);
								editSubjectForm.setOpaque(true);
								editSubjectForm.setVisible(true);
							} catch (IOException e1) {
								
							} catch (URISyntaxException e1) {
								
							}
							
				        }
				        else if(command.equals("delete")) {
				        	
				        	Subject subjectToDelete = subjectsFilter.get(row);
							int input = JOptionPane.showConfirmDialog(null, "Are you sure to delete " + subjectToDelete.getName() +"? ("
									+ "also delete its register course information and courses)");
							// 0=yes, 1=no, 2=cancel
							
							if(input == 0) {
								
								List<StudentRegisterCourse> registerInfos = StudentRegisterCourseDAO.getAllRegister();
								Predicate<StudentRegisterCourse> predicateString = s -> {
									return !s.getId().getSubjectId().equals(subjectToDelete.getId());
						        };
						        registerInfos.removeIf(predicateString);
						        for (StudentRegisterCourse info:registerInfos) {
						        	StudentRegisterCourseDAO.deleteRegister(info);
						        }
						        
						        List<Course> coursesInSemester = CourseDAO.getCourseList();
						        Predicate<Course> predicateString2 = s -> {
						        	return !s.getId().getSubjectId().equals(subjectToDelete.getId());
						        };
						        coursesInSemester.removeIf(predicateString2);
						        for (Course course:coursesInSemester) {
						        	CourseDAO.deleteCourse(course);
						        }
								
								int status = SubjectDAO.deleteSubject(subjectToDelete);
								if (status == -1) {
									showMessageDialog(null, "This subject is not existed!");
								}
								else {
									
									subjects.remove(row);
									subjectsFilter.removeAll(subjectsFilter);
									
			
								
									subjectsFilter.removeAll(subjectsFilter);
									subjectsFilter.addAll(subjects);
							        
									
									
									revalidate();
							        repaint();
									
									showMessageDialog(null, "Deleted successfully!");
								}
							}
				        }
				        else {
				        	
				        }
				        
				    }
				};
		    	
				tbl_subjectsList.getColumnModel().getColumn(i-1).setCellRenderer(new SubjectsManagementActionCellRenderer(tbl_subjectsList, actionMinistryAccount));
				tbl_subjectsList.getColumnModel().getColumn(i-1).setCellEditor(new SubjectsManagementActionCellRenderer(tbl_subjectsList, actionMinistryAccount));
		    }
		    else {
		    	tbl_subjectsList.getColumnModel().getColumn(i-1).setCellRenderer(new RowSubjectsManagementListRenderer());
		    }
		    
		    
		}
		
		
		
		scrollView = new JScrollPane(tbl_subjectsList);

		
		
		firstRow.add(lbl_title);
		searchRow.add(lbl_search);
		searchRow.add(txt_search);
		secondRow.add(btn_addSubject);
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
        
		
        layoutSecondRow.putConstraint(SpringLayout.NORTH, btn_addSubject, 10, SpringLayout.NORTH, secondRow);
        layoutSecondRow.putConstraint(SpringLayout.SOUTH, btn_addSubject, -10, SpringLayout.SOUTH, secondRow);
        layoutSecondRow.putConstraint(SpringLayout.EAST, btn_addSubject, -15, SpringLayout.EAST, secondRow);
        
       
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
			try {
				
				Action actionRefresh = new AbstractAction()
				{
					private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent e)
				    {
						subjects.removeAll(subjects);
						subjectsFilter.removeAll(subjectsFilter);
						
						
						subjects = SubjectDAO.getSubjectList();
						subjects.sort(Subject.subjectIdAscendingComparator);
					
						subjectsFilter.addAll(subjects);
						
						tbl_subjectsList.revalidate();
						tbl_subjectsList.repaint();
				        
						revalidate();
				        repaint();
				    }
				};
				
				JComponent createSubjectForm;
				createSubjectForm = new CreateSubjectForm(actionRefresh);
				createSubjectForm.setOpaque(true);
				createSubjectForm.setVisible(true);
				
			} catch (IOException | URISyntaxException e1) {
				showMessageDialog(null, "Error!");
			}
	    }

	}

}

class RowSubjectsManagementListRenderer extends JLabel implements  TableCellRenderer
{

  
  private static final long serialVersionUID = 1L;

  public RowSubjectsManagementListRenderer() {
   
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

class SubjectsManagementActionCellRenderer extends AbstractCellEditor implements  TableCellEditor, TableCellRenderer, ActionListener
{
	static final long serialVersionUID = 1L;
	private JTable table;
	private Action action;
	
	private Object editorValue;
	
	public SubjectsManagementActionCellRenderer(JTable table, Action action) {

		this.table = table;
		this.action= action;

	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object obj,
	    boolean selected, boolean focused, int row, int col) {
	
		JButton btn_delete = new JButton();
		Border emptyBorder = BorderFactory.createEmptyBorder();
		btn_delete.setBorder(emptyBorder);
		btn_delete.setPreferredSize(new Dimension(30, 30));
		btn_delete.setBackground(Color.white);
		btn_delete.setForeground(Color.white);
		btn_delete.setActionCommand("delete");
		  
		ImageIcon icon = new ImageIcon("img/delete.png");
		Image scaleImage = icon.getImage().getScaledInstance(25, 25,Image.SCALE_SMOOTH);
		btn_delete.setIcon(new ImageIcon(scaleImage));
		btn_delete.addActionListener(this);
		btn_delete.setMnemonic(KeyEvent.VK_D);
		
		JButton btn_edit = new JButton();
		Border emptyBorder2 = BorderFactory.createEmptyBorder();
		btn_edit.setBorder(emptyBorder2);
		btn_edit.setPreferredSize(new Dimension(30, 30));
		btn_edit.setBackground(Color.white);
		btn_edit.setForeground(Color.white);
		btn_edit.setActionCommand("edit");
		  
		ImageIcon icon2 = new ImageIcon("img/edit.png");
		Image scaleImage2 = icon2.getImage().getScaledInstance(25, 25,Image.SCALE_SMOOTH);
		btn_edit.setIcon(new ImageIcon(scaleImage2));
		btn_edit.addActionListener(this);
		btn_edit.setMnemonic(KeyEvent.VK_D);

		
		JPanel view_button = new JPanel();
		view_button.setLayout(new GridLayout(1,3));
		view_button.setBackground(Color.white);
		
		view_button.add(btn_edit);
		view_button.add(btn_delete);
	
		return view_button;
	}
	  
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			
		JButton btn_delete = new JButton();
		Border emptyBorder = BorderFactory.createEmptyBorder();
		btn_delete.setBorder(emptyBorder);
		btn_delete.setPreferredSize(new Dimension(30, 30));
		btn_delete.setBackground(Color.white);
		btn_delete.setForeground(Color.white);
		btn_delete.setActionCommand("delete");
		  
		ImageIcon icon = new ImageIcon("img/delete.png");
		Image scaleImage = icon.getImage().getScaledInstance(25, 25,Image.SCALE_SMOOTH);
		btn_delete.setIcon(new ImageIcon(scaleImage));
		btn_delete.addActionListener(this);
		btn_delete.setMnemonic(KeyEvent.VK_D);
		
		JButton btn_edit = new JButton();
		Border emptyBorder2 = BorderFactory.createEmptyBorder();
		btn_edit.setBorder(emptyBorder2);
		btn_edit.setPreferredSize(new Dimension(30, 30));
		btn_edit.setBackground(Color.white);
		btn_edit.setForeground(Color.white);
		btn_edit.setActionCommand("edit");
		  
		ImageIcon icon2 = new ImageIcon("img/edit.png");
		Image scaleImage2 = icon2.getImage().getScaledInstance(25, 25,Image.SCALE_SMOOTH);
		btn_edit.setIcon(new ImageIcon(scaleImage2));
		btn_edit.addActionListener(this);
		btn_edit.setMnemonic(KeyEvent.VK_D);


		
		JPanel view_button = new JPanel();
		view_button.setLayout(new GridLayout(1,3));
		view_button.setBackground(Color.white);
		
		view_button.add(btn_edit);
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
			e.getActionCommand() + "-" + row);
		action.actionPerformed(event);
	}
	
	
}






