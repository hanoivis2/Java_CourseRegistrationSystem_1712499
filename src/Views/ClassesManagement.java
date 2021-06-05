package Views;

import javax.swing.*;


import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Models.Class;
import DAO.ClassDAO;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class ClassesManagement extends JPanel implements ActionListener {
	

	private static final long serialVersionUID = 1L;
	JLabel lbl_title;
	JButton btn_createClass;
	JScrollPane scrollView;
	JTable tbl_classes;
	JFrame frame;
	List<Class> classes;
	List<Class> classesFilter;
	
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
			}
		});
		
	
		JComponent mainMenu = new ClassesManagement();
		mainMenu.setOpaque(true);
		mainMenu.setVisible(true);
	}
	
	
	public ClassesManagement() throws IOException, URISyntaxException {
		super(new BorderLayout());
		
		classes = new ArrayList<Class>();
		classesFilter = new ArrayList<Class>();
		
		
		classes = ClassDAO.getClassList();
		classes.sort(Class.classIdAscendingComparator);
	
		classesFilter.removeAll(classesFilter);
		classesFilter.addAll(classes);

		JFrame.setDefaultLookAndFeelDecorated(true);
		
        frame = new JFrame("Classes Management");
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
	
		
		
		lbl_title = new JLabel("Classes list");
		lbl_title.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbl_title.setHorizontalAlignment(JLabel.CENTER);
		lbl_title.setFont(new Font("Helvetica", Font.BOLD, 16));
		
		btn_createClass = new JButton("Add class");
		btn_createClass.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_createClass.addActionListener(this);
		btn_createClass.setActionCommand("Create");
		
		
		
		int[] columnsWidth = { 100, 100, 100, 100, 0, 80 };
		class CoursesListTableModel extends AbstractTableModel {

			private static final long serialVersionUID = 1L;

			@Override
			public int getRowCount() {
				return classesFilter.size();
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
				
				Class item = classesFilter.get(rowIndex);
				
				switch (columnIndex) {
				case 0:
					return item.getId();
				case 1:
					return item.getTotalStudents();
				case 2:
					return item.getTotalMale();
				case 3:
					return item.getTotalFemale();
				case 4:
					return item.getDescription();
				default:
					return "";
				}
			}
			
			@Override
			public String getColumnName(int column) {
				switch (column) {
				case 0:
					return "Class' id";
				case 1:
					return "Total students";
				case 2:
					return "Total male";
				case 3:
					return "Total female";
				case 4:
					return "Description";
				default:
					return "";
				}
			}
		}
		

		
		tbl_classes = new JTable();		
		tbl_classes.setModel(new CoursesListTableModel());
		tbl_classes.setRowSelectionAllowed(true);
		tbl_classes.setRowHeight(30);
		tbl_classes.setBackground(Color.DARK_GRAY);
		tbl_classes.getTableHeader().setPreferredSize(new Dimension(0, 30));
		((DefaultTableCellRenderer)tbl_classes.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		
		int i = 0;
		for (int width : columnsWidth) {
		    TableColumn column = tbl_classes.getColumnModel().getColumn(i++);
		    column.setMinWidth(20);
		    column.setMaxWidth(width);
		    if (i == 5) {
		    	column.setMaxWidth(Integer.MAX_VALUE);
		    }
		    column.setPreferredWidth(width);
		    
		    if (i == 6) {
		    	
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
								
								Class classToEdit = classesFilter.get(row);
					        	Action actionEdit = new AbstractAction()
								{
									private static final long serialVersionUID = 1L;

									public void actionPerformed(ActionEvent e)
								    {
								        
										classes.removeAll(classes);
										classesFilter.removeAll(classesFilter);
										
										
										classes = ClassDAO.getClassList();
										classes.sort(Class.classIdAscendingComparator);
									
										classesFilter.removeAll(classesFilter);
										classesFilter.addAll(classes);
								        
										revalidate();
								        repaint();
								    }
								};
								
								JComponent editClassForm;
								editClassForm = new EditClassForm(actionEdit, classToEdit);
								editClassForm.setOpaque(true);
								editClassForm.setVisible(true);
							} catch (IOException e1) {
								
							} catch (URISyntaxException e1) {
								
							}
							
				        }
				        else {
				        	
				        	Class classToDelete = classesFilter.get(row);
							int input = JOptionPane.showConfirmDialog(null, "Are you sure to delete " + classToDelete.getId() +"?");
							// 0=yes, 1=no, 2=cancel
							
							if(input == 0) {
								int status = ClassDAO.deleteClass(classToDelete);
								if (status == -1) {
									showMessageDialog(null, "This class is not existed!");
								}
								else {
									
									classes.remove(row);
									classesFilter.removeAll(classesFilter);
									
			
								
									classesFilter.removeAll(classesFilter);
									classesFilter.addAll(classes);
							        
									revalidate();
							        repaint();
									
									showMessageDialog(null, "Deleted successfully!");
								}
							}
				        }
				    }
				};
		    	
				tbl_classes.getColumnModel().getColumn(i-1).setCellRenderer(new ClassesManagementActionCellRenderer(tbl_classes, actionMinistryAccount));
				tbl_classes.getColumnModel().getColumn(i-1).setCellEditor(new ClassesManagementActionCellRenderer(tbl_classes, actionMinistryAccount));
		    }
		    else {
		    	tbl_classes.getColumnModel().getColumn(i-1).setCellRenderer(new RowClassesManagementRenderer());
		    }
		    
		    
		}
		
		
		
		scrollView = new JScrollPane(tbl_classes);

		
		
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
				        
						classes.removeAll(classes);
						classesFilter.removeAll(classesFilter);
						
						
						classes = ClassDAO.getClassList();
						classes.sort(Class.classIdAscendingComparator);
					
						classesFilter.removeAll(classesFilter);
						classesFilter.addAll(classes);
						
						tbl_classes.revalidate();
						tbl_classes.repaint();
				        
						revalidate();
				        repaint();
				    }
				};
				
				JComponent createClassForm;
				createClassForm = new CreateClassForm(actionRefresh);
				createClassForm.setOpaque(true);
				createClassForm.setVisible(true);
				
			} catch (IOException | URISyntaxException e1) {
				showMessageDialog(null, "Error!");
			}
			
	    }

	}

}

class RowClassesManagementRenderer extends JLabel implements  TableCellRenderer
{

  
  private static final long serialVersionUID = 1L;

  public RowClassesManagementRenderer() {
   
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

class ClassesManagementActionCellRenderer extends AbstractCellEditor implements  TableCellEditor, TableCellRenderer, ActionListener
{
	static final long serialVersionUID = 1L;
	private JTable table;
	private Action action;
	
	private Object editorValue;
	
	public ClassesManagementActionCellRenderer(JTable table, Action action) {

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
		  
		ImageIcon icon2 = new ImageIcon("img/edit.png");
		Image scaleImage2 = icon2.getImage().getScaledInstance(25, 25,Image.SCALE_SMOOTH);
		btn_edit.setIcon(new ImageIcon(scaleImage2));
		btn_edit.addActionListener(this);
		btn_edit.setMnemonic(KeyEvent.VK_D);
		btn_edit.setActionCommand("edit");
		
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
		btn_delete.setActionCommand("delete");
		
		
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






