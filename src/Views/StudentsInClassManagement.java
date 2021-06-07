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

import DAO.StudentAccountDAO;
import Models.StudentAccount;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class StudentsInClassManagement extends JPanel implements ActionListener {
	

	private static final long serialVersionUID = 1L;
	public String className;
	
	JLabel lbl_title;
	JButton btn_addStudent;
	JScrollPane scrollView;
	JTable tbl_students;
	JLabel lbl_search;
	JTextField txt_search;
	JFrame frame;
	List<StudentAccount> students;
	List<StudentAccount> studentsFilter;
	
	private Action action;
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
			}
		});
		
	}
	
	
	public StudentsInClassManagement(Action action, String className) throws IOException, URISyntaxException {
		super(new BorderLayout());
		
		this.action = action;
		
		this.className = className;
		
		students = new ArrayList<StudentAccount>();
		studentsFilter = new ArrayList<StudentAccount>();
		
		students = StudentAccountDAO.getStudentAccountByClassIdList(className);
		students.sort(StudentAccount.studentIdAscendingComparator);
	
		studentsFilter.removeAll(studentsFilter);
		studentsFilter.addAll(students);

		JFrame.setDefaultLookAndFeelDecorated(true);
		
        frame = new JFrame("Students in class management");
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
	
		
		
		lbl_title = new JLabel("Students in " + className);
		lbl_title.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbl_title.setHorizontalAlignment(JLabel.CENTER);
		lbl_title.setFont(new Font("Helvetica", Font.BOLD, 16));
		
		btn_addStudent = new JButton("Add new student");
		btn_addStudent.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_addStudent.addActionListener(this);
		btn_addStudent.setActionCommand("Create");
		
		
		lbl_search = new JLabel("Enter course's name to search: ");       
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
		
		int[] columnsWidth = { 100, 240, 100, 150, 80, 80 };
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
				return 6;
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				
				StudentAccount item = studentsFilter.get(rowIndex);
				
				switch (columnIndex) {
				case 0:
					return item.getId();
				case 1:
					return item.getFullName();
				case 2:
					return item.getBirthday();
				case 3:
					return item.getBirthplace();
				case 4:
					return (item.getGender() == 1) ? "Male" : "Female";
				default:
					return "";
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
					return "Birthday";
				case 3:
					return "Birthplace";
				case 4:
					return "Gender";
				default:
					return "";
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
		    if (i == 2) {
		    	column.setMaxWidth(Integer.MAX_VALUE);
		    }
		    else {
		    	column.setPreferredWidth(width);
		    }
		    
		    if (i == 6) {
		    	
		    	Action actionDelete = new AbstractAction()
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
								
								StudentAccount accountToEdit = studentsFilter.get(row);
					        	Action actionEdit = new AbstractAction()
								{
									private static final long serialVersionUID = 1L;

									public void actionPerformed(ActionEvent e)
								    {
								        
										students.removeAll(students);
										
										students = StudentAccountDAO.getStudentAccountByClassIdList(className);
										students.sort(StudentAccount.studentIdAscendingComparator);
									
										studentsFilter.removeAll(studentsFilter);
										studentsFilter.addAll(students);
								        
										revalidate();
								        repaint();
								        
								        ActionEvent event = new ActionEvent(
								    			this,
								    			ActionEvent.ACTION_PERFORMED,
								    			"refresh");
								    		action.actionPerformed(event);
								    }
								};
								
								JComponent editStudentAccountForm;
								editStudentAccountForm = new EditStudentAccountForm(actionEdit, accountToEdit);
								editStudentAccountForm.setOpaque(true);
								editStudentAccountForm.setVisible(true);
							} catch (IOException e1) {
								
							} catch (URISyntaxException e1) {
								
							}
							
				        }
				        else {
				        	StudentAccount accountToReset = studentsFilter.get(row);
							int input = JOptionPane.showConfirmDialog(null, "Are you sure to reset password for " + accountToReset.getId() + " - " + accountToReset.getFullName() +"?");
							// 0=yes, 1=no, 2=cancel
							
							if(input == 0) {
								accountToReset.setId(accountToReset.getId());
								accountToReset.setFullName(accountToReset.getFullName());
								accountToReset.setPassword("1111");
								accountToReset.setBirthday(accountToReset.getBirthday());
								accountToReset.setBirthplace(accountToReset.getBirthplace());
								int status = StudentAccountDAO.updateStudentAccount(accountToReset);
								if (status == -1) {
									showMessageDialog(null, "This account is not existed!");
								}
								else {
									
									students.removeAll(students);
									
									
									students = StudentAccountDAO.getStudentAccountByClassIdList(className);
									students.sort(StudentAccount.studentIdAscendingComparator);
								
									studentsFilter.removeAll(studentsFilter);
									studentsFilter.addAll(students);
							        
									revalidate();
							        repaint();
									
									showMessageDialog(null, accountToReset.getId() + " has been reseted to '1111'!");
								}
								
							}
				        }
				        
				    }
				};
		    	
		    	tbl_students.getColumnModel().getColumn(i-1).setCellRenderer(new StudentsInClassCellRenderer(tbl_students, actionDelete));
		    	tbl_students.getColumnModel().getColumn(i-1).setCellEditor(new StudentsInClassCellRenderer(tbl_students, actionDelete));
		    }
		    else {
		    	tbl_students.getColumnModel().getColumn(i-1).setCellRenderer(new RowStudentsInClassRenderer());
		    }
		    
		    
		}
		
		
		
		scrollView = new JScrollPane(tbl_students);

		
		
		firstRow.add(lbl_title);
		searchRow.add(lbl_search);
		searchRow.add(txt_search);
		secondRow.add(btn_addStudent);
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
        
		
        layoutSecondRow.putConstraint(SpringLayout.NORTH, btn_addStudent, 10, SpringLayout.NORTH, secondRow);
        layoutSecondRow.putConstraint(SpringLayout.SOUTH, btn_addStudent, -10, SpringLayout.SOUTH, secondRow);
        layoutSecondRow.putConstraint(SpringLayout.EAST, btn_addStudent, -15, SpringLayout.EAST, secondRow);
        
       
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
				        
						students.removeAll(students);
						studentsFilter.removeAll(studentsFilter);
						
						
						students = StudentAccountDAO.getStudentAccountByClassIdList(className);
						students.sort(StudentAccount.studentIdAscendingComparator);
					
						studentsFilter.removeAll(studentsFilter);
						studentsFilter.addAll(students);
						
						tbl_students.revalidate();
						tbl_students.repaint();
				        
						revalidate();
				        repaint();
				        
				        ActionEvent event = new ActionEvent(
				    			this,
				    			ActionEvent.ACTION_PERFORMED,
				    			"refresh");
				    		action.actionPerformed(event);
				    }
				};
				
				JComponent createStudentAccountForm;
				createStudentAccountForm = new CreateStudentAccountForm(actionRefresh, this.className);
				createStudentAccountForm.setOpaque(true);
				createStudentAccountForm.setVisible(true);
				
			} catch (IOException | URISyntaxException e1) {
				showMessageDialog(null, "Error!");
			}
	    }

	}


	
}

class RowStudentsInClassRenderer extends JLabel implements  TableCellRenderer
{

  
  private static final long serialVersionUID = 1L;

  public RowStudentsInClassRenderer() {
   
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


class StudentsInClassCellRenderer extends AbstractCellEditor implements  TableCellEditor, TableCellRenderer, ActionListener
{
	static final long serialVersionUID = 1L;
	private JTable table;
	private Action action;
	
	private Object editorValue;
	
	public StudentsInClassCellRenderer(JTable table, Action action) {

		this.table = table;
		this.action= action;

	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object obj,
	    boolean selected, boolean focused, int row, int col) {
	
		
		JButton btn_edit = new JButton();
		Border emptyBorder2 = BorderFactory.createEmptyBorder();
		btn_edit.setBorder(emptyBorder2);
		btn_edit.setPreferredSize(new Dimension(30, 30));
		btn_edit.setBackground(Color.white);
		btn_edit.setForeground(Color.white);
		  
		ImageIcon icon2 = new ImageIcon("img/edit.png");
		Image scaleImage2 = icon2.getImage().getScaledInstance(25, 25,Image.SCALE_SMOOTH);
		btn_edit.setIcon(new ImageIcon(scaleImage2));
		btn_edit.addActionListener(this);
		btn_edit.setMnemonic(KeyEvent.VK_D);
		
		JButton btn_passwordReset = new JButton();
		Border emptyBorder3 = BorderFactory.createEmptyBorder();
		btn_passwordReset.setBorder(emptyBorder3);
		btn_passwordReset.setPreferredSize(new Dimension(30, 30));
		btn_passwordReset.setBackground(Color.white);
		btn_passwordReset.setForeground(Color.white);
		  
		ImageIcon icon3 = new ImageIcon("img/passwordReset.png");
		Image scaleImage3 = icon3.getImage().getScaledInstance(25, 25,Image.SCALE_SMOOTH);
		btn_passwordReset.setIcon(new ImageIcon(scaleImage3));
		btn_passwordReset.addActionListener(this);
		btn_passwordReset.setMnemonic(KeyEvent.VK_D);

		
		JPanel view_button = new JPanel();
		view_button.setLayout(new GridLayout(1,2));
		view_button.setBackground(Color.white);
		
		view_button.add(btn_edit);
		view_button.add(btn_passwordReset);
	
		return view_button;
	}
	  
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		
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
		
		JButton btn_passwordReset = new JButton();
		Border emptyBorder3 = BorderFactory.createEmptyBorder();
		btn_passwordReset.setBorder(emptyBorder3);
		btn_passwordReset.setPreferredSize(new Dimension(30, 30));
		btn_passwordReset.setBackground(Color.white);
		btn_passwordReset.setForeground(Color.white);
		btn_passwordReset.setActionCommand("resetPassword");
		  
		ImageIcon icon3 = new ImageIcon("img/passwordReset.png");
		Image scaleImage3 = icon3.getImage().getScaledInstance(25, 25,Image.SCALE_SMOOTH);
		btn_passwordReset.setIcon(new ImageIcon(scaleImage3));
		btn_passwordReset.addActionListener(this);
		btn_passwordReset.setMnemonic(KeyEvent.VK_D);

		
		JPanel view_button = new JPanel();
		view_button.setLayout(new GridLayout(1,2));
		view_button.setBackground(Color.white);
		
		view_button.add(btn_edit);
		view_button.add(btn_passwordReset);
		
	
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
	
	






