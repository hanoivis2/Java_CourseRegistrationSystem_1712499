package Views;

import javax.swing.*;



import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import DAO.SemesterDAO;
import Models.Semester;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class SemestersManagement extends JPanel implements ActionListener {
	

	private static final long serialVersionUID = 1L;
	JLabel lbl_title;
	JButton btn_createClass;
	JScrollPane scrollView;
	JTable tbl_semesterList;
	JFrame frame;
	List<Semester> semesters;
	List<Semester> semestersFilter;
	
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
			}
		});
		
	
		JComponent mainMenu = new SemestersManagement();
		mainMenu.setOpaque(true);
		mainMenu.setVisible(true);
	}
	
	
	public SemestersManagement() throws IOException, URISyntaxException {
		super(new BorderLayout());
		
		semesters = new ArrayList<Semester>();
		semestersFilter = new ArrayList<Semester>();
		
		semesters = SemesterDAO.getSemesterList();
		semesters.sort(Semester.semesterAscendingComparator);
	
		semestersFilter.removeAll(semestersFilter);
		semestersFilter.addAll(semesters);

		JFrame.setDefaultLookAndFeelDecorated(true);
		
        frame = new JFrame("Semesters Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	
		
		
		lbl_title = new JLabel("Semesters list");
		lbl_title.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbl_title.setHorizontalAlignment(JLabel.CENTER);
		lbl_title.setFont(new Font("Helvetica", Font.BOLD, 16));
		
		btn_createClass = new JButton("Add semester");
		btn_createClass.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_createClass.addActionListener(this);
		btn_createClass.setActionCommand("Create");
		
		
		
		int[] columnsWidth = { 135, 135, 135, 135, 0, 150, 80 };
		class CoursesListTableModel extends AbstractTableModel {

			private static final long serialVersionUID = 1L;

			@Override
			public int getRowCount() {
				return semestersFilter.size();
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
				
				Semester item = semestersFilter.get(rowIndex);
				
				switch (columnIndex) {
				case 0:
					return item.getId().getName();
				case 1:
					return item.getId().getSchoolYear();
				case 2:
					return item.getStartDate();
				case 3:
					return item.getEndDate();
				case 4:
					return item.getDescription();
				case 5:
					return (item.getIsCurrentSemester() == 1) ? true : false;
				default:
					return "";
				}
			}
			
			@Override
			public String getColumnName(int column) {
				switch (column) {
				case 0:
					return "Semester's name";
				case 1:
					return "School year";
				case 2:
					return "Start date";
				case 3:
					return "End date";
				case 4:
					return "Description";
				case 5:
					return "Is current semester";
				default:
					return "";
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
		    if (i == 7) {
		    	
		    	Action actionSemester = new AbstractAction()
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
								
								Semester semesterToEdit = semestersFilter.get(row);
					        	Action actionEdit = new AbstractAction()
								{
									private static final long serialVersionUID = 1L;

									public void actionPerformed(ActionEvent e)
								    {
								        
										semesters.removeAll(semesters);
										semestersFilter.removeAll(semestersFilter);
										
										
										semesters = SemesterDAO.getSemesterList();
										semesters.sort(Semester.semesterAscendingComparator);
									
										semestersFilter.removeAll(semestersFilter);
										semestersFilter.addAll(semesters);
								        
										revalidate();
								        repaint();
								    }
								};
								
								JComponent editSemesterForm;
								editSemesterForm = new EditSemesterForm(actionEdit, semesterToEdit);
								editSemesterForm.setOpaque(true);
								editSemesterForm.setVisible(true);
							} catch (IOException e1) {
								
							} catch (URISyntaxException e1) {
								
							}
							
				        }
				        else {
				        	Semester semesterToDelete = semestersFilter.get(row);
				        	if (semesterToDelete.getIsCurrentSemester() == 1) {
								showMessageDialog(null, "Please set another semester as current semester before deleting!");
							}
				        	else {
				        		int input = JOptionPane.showConfirmDialog(null, "Are you sure to delete " + semesterToDelete.getId() +"?");
								// 0=yes, 1=no, 2=cancel
								
								if(input == 0) {
									int status = SemesterDAO.deleteSemester(semesterToDelete);
									if (status == -1) {
										showMessageDialog(null, "This class is not existed!");
									}
									else {
										
										semesters.remove(row);
										semestersFilter.removeAll(semestersFilter);
										
				
									
										semestersFilter.removeAll(semestersFilter);
										semestersFilter.addAll(semesters);
								        
										revalidate();
								        repaint();
										
										showMessageDialog(null, "Deleted successfully!");
									}
								}
				        	}
				        }
				    }
				};
		    	
				tbl_semesterList.getColumnModel().getColumn(i-1).setCellRenderer(new SemestersManagementActionCellRenderer(tbl_semesterList, actionSemester));
				tbl_semesterList.getColumnModel().getColumn(i-1).setCellEditor(new SemestersManagementActionCellRenderer(tbl_semesterList, actionSemester));
		    }
		    else if (i == 6) {
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
				        	//Show message
				        	showMessageDialog(null, "This semester has already been current semester!");
				        }
				        else {
				        	//Set current semester
				        	for (Semester item : semesters) {
								if (item.getId().equals(semesters.get(row).getId())) {
									item.setIsCurrentSemester((short) 1); 
								}
								else {
									item.setIsCurrentSemester((short) 0); 
								}
								SemesterDAO.updateSemester(item);
							}
				        	
				        	showMessageDialog(null, "Set successfully!");
				        	
				        	semesters.removeAll(semesters);
				    		
				    		semesters = SemesterDAO.getSemesterList();
				    		semesters.sort(Semester.semesterAscendingComparator);
				    	
				    		semestersFilter.removeAll(semestersFilter);
				    		semestersFilter.addAll(semesters);
				        	
				        	tbl_semesterList.revalidate();
				        	tbl_semesterList.repaint();
				        	
				        	revalidate();
				        	repaint();
				        }
				        
				    }
				};
		    	
				tbl_semesterList.getColumnModel().getColumn(i-1).setCellRenderer(new ActionSetCurrentSemesterCellRenderer(tbl_semesterList, 
						actionSetCurrentSemester));
				tbl_semesterList.getColumnModel().getColumn(i-1).setCellEditor(new ActionSetCurrentSemesterCellRenderer(tbl_semesterList, 
						actionSetCurrentSemester));
		    }
		    else {
		    	tbl_semesterList.getColumnModel().getColumn(i-1).setCellRenderer(new RowSemestersManagementRenderer());
		    }
		    
		    
		}
		
		
		
		scrollView = new JScrollPane(tbl_semesterList);

		
		
		firstRow.add(lbl_title);
		secondRow.add(btn_createClass);
		thirdRow.add(scrollView);
		
		layoutFirstRow.putConstraint(SpringLayout.NORTH, lbl_title, 10, SpringLayout.NORTH, firstRow);
		layoutFirstRow.putConstraint(SpringLayout.SOUTH, lbl_title, -10, SpringLayout.SOUTH, firstRow);
        layoutFirstRow.putConstraint(SpringLayout.WEST, lbl_title, 15, SpringLayout.WEST, firstRow);
        layoutFirstRow.putConstraint(SpringLayout.EAST, lbl_title, -15, SpringLayout.EAST, firstRow);

        
		
        layoutSecondRow.putConstraint(SpringLayout.NORTH, btn_createClass, 10, SpringLayout.NORTH, secondRow);
        layoutSecondRow.putConstraint(SpringLayout.SOUTH, btn_createClass, -10, SpringLayout.SOUTH, secondRow);
        layoutSecondRow.putConstraint(SpringLayout.EAST, btn_createClass, -15, SpringLayout.EAST, secondRow);
        
       
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
		if (strActionCommand.equals("Create"))
		{
			try {
				
				Action actionRefresh = new AbstractAction()
				{
					private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent e)
				    {
				        
						semesters.removeAll(semesters);
						semestersFilter.removeAll(semestersFilter);
						
						
						semesters = SemesterDAO.getSemesterList();
						semesters.sort(Semester.semesterAscendingComparator);
					
						semestersFilter.removeAll(semestersFilter);
						semestersFilter.addAll(semesters);
						
						tbl_semesterList.revalidate();
						tbl_semesterList.repaint();
				        
						revalidate();
				        repaint();
				    }
				};
				
				JComponent createSemesterForm;
				createSemesterForm = new CreateSemesterForm(actionRefresh);
				createSemesterForm.setOpaque(true);
				createSemesterForm.setVisible(true);
				
			} catch (IOException | URISyntaxException e1) {
				showMessageDialog(null, "Error!");
			}
	    }

	}

}

class RowSemestersManagementRenderer extends JLabel implements  TableCellRenderer
{

  
  private static final long serialVersionUID = 1L;

  public RowSemestersManagementRenderer() {
   
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

class SemestersManagementActionCellRenderer extends AbstractCellEditor implements  TableCellEditor, TableCellRenderer, ActionListener
{
	static final long serialVersionUID = 1L;
	private JTable table;
	private Action action;
	
	private Object editorValue;
	
	public SemestersManagementActionCellRenderer(JTable table, Action action) {

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
		
		JButton btn_delete = new JButton();
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
		view_button.setLayout(new GridLayout(1,3));
		view_button.setBackground(Color.white);
		
		view_button.add(btn_edit);
		view_button.add(btn_delete);
		
	
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

class ActionSetCurrentSemesterCellRenderer extends AbstractCellEditor implements  TableCellEditor, TableCellRenderer, ActionListener
{
	static final long serialVersionUID = 1L;
	private JTable table;
	private Action action;
	private boolean isChecked;
	private Object editorValue;
	
	public ActionSetCurrentSemesterCellRenderer(JTable table, Action action) {

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




