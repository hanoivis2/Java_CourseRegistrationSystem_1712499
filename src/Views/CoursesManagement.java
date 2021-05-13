package Views;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.EventObject;

public class CoursesManagement extends JPanel implements ActionListener {
	

	private static final long serialVersionUID = 1L;
	JLabel lbl_title;
	JButton btn_createCourse;
	JScrollPane scrollView;
	JTable tbl_coursesList;
	JFrame frame;
	
	
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
		SpringLayout layoutSecondRow = new SpringLayout();
		SpringLayout layoutThirdRow = new SpringLayout();
		firstRow.setLayout(layoutFirstRow);	
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
		
		btn_createCourse = new JButton("Create New Course");
		btn_createCourse.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_createCourse.addActionListener(this);
		btn_createCourse.setActionCommand("Create");
		
		int[] columnsWidth = { 100, 30, 70, 150, 90, 90, 50, 80, 50 };
		class CoursesListTableModel extends AbstractTableModel {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public int getRowCount() {
				return 100;
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
				switch (columnIndex) {
				case 0:
					return "MH000123" + rowIndex;
				case 1:
					return "Thi giac may tinh " + rowIndex;
				case 2:
					return "4";
				case 3:
					return "Thai Hung Van";
				case 4:
					return "E.302";
				case 5:
					return "Thu 2";
				case 6:
					return "4";
				case 7:
					return "150";
				default:
					return "Ngu hoc";
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
		
		String[] columnNames = {"Course ID", "Course name", "Credits", "Theory teacher's name", "Room name", "Day in week", "Shift", "Max amount", ""};
		Object[][] data =
		{
		    {"MH000123", "Thi giac may tinh", "4", "Thai Hung Van", "E.302", "Thu 2", "4", "150","Ngu hoc"},
		    {"MH000123", "Thi giac may tinh", "4", "Thai Hung Van", "E.302", "Thu 2", "4", "150","Ngu hoc"},
		    {"MH000123", "Thi giac may tinh", "4", "Thai Hung Van", "E.302", "Thu 2", "4", "150","Ngu hoc"},
		    {"MH000123", "Thi giac may tinh", "4", "Thai Hung Van", "E.302", "Thu 2", "4", "150","Ngu hoc"},
		};
		 
		
		
//		tbl_coursesList.setModel(new DefaultTableModel(data, columnNames));
		
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
		    	
		    	Action delete = new AbstractAction()
				{
				    public void actionPerformed(ActionEvent e)
				    {
				    	JTable table = (JTable)e.getSource();
				        int modelRow = Integer.valueOf( e.getActionCommand() );
				        System.out.println("Ngu " + modelRow);
				    }
				};
		    	
		    	tbl_coursesList.getColumnModel().getColumn(i-1).setCellRenderer(new ButtonDeleteRenderer(tbl_coursesList, delete));
		    	tbl_coursesList.getColumnModel().getColumn(i-1).setCellEditor(new ButtonDeleteRenderer(tbl_coursesList, delete));
		    }
		    else {
		    	tbl_coursesList.getColumnModel().getColumn(i-1).setCellRenderer(new RowSessionListRenderer());
		    }
		    
		    
		}
		
		
		
		scrollView = new JScrollPane(tbl_coursesList);

		
		
		firstRow.add(lbl_title);
		secondRow.add(btn_createCourse);
		thirdRow.add(scrollView);
		
		layoutFirstRow.putConstraint(SpringLayout.NORTH, lbl_title, 10, SpringLayout.NORTH, firstRow);
		layoutFirstRow.putConstraint(SpringLayout.SOUTH, lbl_title, -10, SpringLayout.SOUTH, firstRow);
        layoutFirstRow.putConstraint(SpringLayout.WEST, lbl_title, 15, SpringLayout.WEST, firstRow);
        layoutFirstRow.putConstraint(SpringLayout.EAST, lbl_title, -15, SpringLayout.EAST, firstRow);
		
        layoutSecondRow.putConstraint(SpringLayout.NORTH, btn_createCourse, 10, SpringLayout.NORTH, secondRow);
        layoutSecondRow.putConstraint(SpringLayout.SOUTH, btn_createCourse, -10, SpringLayout.SOUTH, secondRow);
        layoutSecondRow.putConstraint(SpringLayout.EAST, btn_createCourse, -15, SpringLayout.EAST, secondRow);
        
        
		
        layoutThirdRow.putConstraint(SpringLayout.NORTH, scrollView, 25, SpringLayout.NORTH, thirdRow);
        layoutThirdRow.putConstraint(SpringLayout.SOUTH, scrollView, -25, SpringLayout.SOUTH, thirdRow);
        layoutThirdRow.putConstraint(SpringLayout.WEST, scrollView, 15, SpringLayout.WEST, thirdRow);
        layoutThirdRow.putConstraint(SpringLayout.EAST, scrollView, -15, SpringLayout.EAST, thirdRow);

		
		
		firstRow.setAlignmentY(Component.CENTER_ALIGNMENT);
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
		
		int row = tbl_coursesList.convertRowIndexToModel( tbl_coursesList.getEditingRow() );
		System.out.println("Delete row " + row);
		
		String strActionCommand = e.getActionCommand();
		if (strActionCommand.equals("Create"))
		{
			System.out.println("ngu");
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

class ButtonDeleteRenderer extends AbstractCellEditor implements  TableCellEditor, TableCellRenderer, ActionListener
{
	static final long serialVersionUID = 1L;
	private JTable table;
	private Action action;
	
	private Object editorValue;
	private boolean isButtonColumnEditor;
	
	public ButtonDeleteRenderer(JTable table, Action action) {
	   
	  
		this.table = table;
		this.action = action;

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
		
	
		return btn_delete;
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
	
		this.editorValue = value;
		return btn_delete;
	}
	  
	@Override
	public Object getCellEditorValue() {
		return editorValue;
	}

	

	public void actionPerformed(ActionEvent e)
	{
		int row = table.convertRowIndexToModel( table.getEditingRow() );
		fireEditingStopped();

		//  Invoke the Action

		ActionEvent event = new ActionEvent(
			table,
			ActionEvent.ACTION_PERFORMED,
			"" + row);
		action.actionPerformed(event);
	}
	
	
}






