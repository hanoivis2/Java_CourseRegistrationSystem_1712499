package Views;

import javax.swing.*;


import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import DAO.RegistrationSessionDAO;
import Models.RegistrationSession;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

public class CoursesRegistrationSessionManagement extends JPanel implements ActionListener {
	

	private static final long serialVersionUID = 1L;
	JLabel lbl_title;
	JButton btn_createSession;
	JScrollPane scrollView;
	JTable tbl_sessionsList;
	JFrame frame;
	
	List<RegistrationSession> registrationSessions;
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
			}
		});
	
	}
	
	
	public CoursesRegistrationSessionManagement() throws IOException, URISyntaxException {
		super(new BorderLayout());

		JFrame.setDefaultLookAndFeelDecorated(true);
		
		registrationSessions = new ArrayList<RegistrationSession>();
		
		
		registrationSessions = RegistrationSessionDAO.getRegistrationSessionList();
		registrationSessions.sort(RegistrationSession.registrationSessionAscendingComparator);
	
        frame = new JFrame("Courses Registration Session Management");
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
	
		
		lbl_title = new JLabel("Sessions List");
		lbl_title.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbl_title.setHorizontalAlignment(JLabel.CENTER);
		lbl_title.setFont(new Font("Helvetica", Font.BOLD, 16));
		
		btn_createSession = new JButton("Create New Session");
		btn_createSession.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_createSession.addActionListener(this);
		btn_createSession.setActionCommand("Create");
		
		int[] columnsWidth = { 150, 150, 150, 100 };
		class SessionsListTableModel extends AbstractTableModel {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public int getRowCount() {
				return registrationSessions.size();
			}

			@Override
			public int getColumnCount() {
				return 4;
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				
				RegistrationSession item = registrationSessions.get(rowIndex);
				
				switch (columnIndex) {
				case 0:
					return item.getSemesterName() + " / " + item.getSemesterSchoolYear();
				case 1:
					return item.getId().getStartDate();
				case 2:
					return item.getId().getEndDate();
				default:
					return item.getDescription();
				}
			}
			
			@Override
			public String getColumnName(int column) {
				switch (column) {
				case 0:
					return "Session ID";
				case 1:
					return "Begin date";
				case 2:
					return "End date";
				default:
					return "Description";
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

		    return this;
		  }


		}
		
		tbl_sessionsList = new JTable();
		tbl_sessionsList.setModel(new SessionsListTableModel());
		tbl_sessionsList.setRowHeight(30);
		tbl_sessionsList.getTableHeader().setPreferredSize(new Dimension(0, 30));
		((DefaultTableCellRenderer)tbl_sessionsList.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		
		int i = 0;
		for (int width : columnsWidth) {
		    TableColumn column = tbl_sessionsList.getColumnModel().getColumn(i++);
		    column.setMinWidth(20);
		    column.setMaxWidth(width);
		    if (i == 4) {
		    	column.setMaxWidth(Integer.MAX_VALUE);
		    }
		    column.setPreferredWidth(width);
		    
		    
		    tbl_sessionsList.getColumnModel().getColumn(i-1).setCellRenderer(new RowSessionListRenderer());
		}

		scrollView = new JScrollPane(tbl_sessionsList);

		
		
		firstRow.add(lbl_title);
		secondRow.add(btn_createSession);
		thirdRow.add(scrollView);
		
		layoutFirstRow.putConstraint(SpringLayout.NORTH, lbl_title, 10, SpringLayout.NORTH, firstRow);
		layoutFirstRow.putConstraint(SpringLayout.SOUTH, lbl_title, -10, SpringLayout.SOUTH, firstRow);
        layoutFirstRow.putConstraint(SpringLayout.WEST, lbl_title, 15, SpringLayout.WEST, firstRow);
        layoutFirstRow.putConstraint(SpringLayout.EAST, lbl_title, -15, SpringLayout.EAST, firstRow);
		
        layoutSecondRow.putConstraint(SpringLayout.NORTH, btn_createSession, 10, SpringLayout.NORTH, secondRow);
        layoutSecondRow.putConstraint(SpringLayout.SOUTH, btn_createSession, -10, SpringLayout.SOUTH, secondRow);
        layoutSecondRow.putConstraint(SpringLayout.EAST, btn_createSession, -15, SpringLayout.EAST, secondRow);
        
        
		
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
				        
						registrationSessions.removeAll(registrationSessions);
						
						registrationSessions = RegistrationSessionDAO.getRegistrationSessionList();
						registrationSessions.sort(RegistrationSession.registrationSessionAscendingComparator);
					

						tbl_sessionsList.revalidate();
						tbl_sessionsList.repaint();
				        
						revalidate();
				        repaint();
				    }
				};
				
				JComponent createCourseRegistrationSessionForm;
				createCourseRegistrationSessionForm = new CreateCourseRegistrationSessionForm(actionRefresh);
				createCourseRegistrationSessionForm.setOpaque(true);
				createCourseRegistrationSessionForm.setVisible(true);
				
			} catch (IOException | URISyntaxException e1) {
				showMessageDialog(null, "Error!");
			}
	    }

	}
}

