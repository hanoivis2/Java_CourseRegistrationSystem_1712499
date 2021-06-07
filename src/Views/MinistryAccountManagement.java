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

import DAO.MinistryAccountDAO;
import Models.MinistryAccount;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class MinistryAccountManagement extends JPanel implements ActionListener {
	

	private static final long serialVersionUID = 1L;
	JLabel lbl_title;
	JButton btn_createAccount;
	JScrollPane scrollView;
	JTable tbl_ministryAccountList;
	JLabel lbl_search;
	JTextField txt_search;
	JFrame frame;
	List<MinistryAccount> ministryAccounts;
	List<MinistryAccount> ministryAccountsFilter;
	
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
			}
		});
		
	}
	
	
	public MinistryAccountManagement() throws IOException, URISyntaxException {
		super(new BorderLayout());
		
		ministryAccounts = new ArrayList<MinistryAccount>();
		ministryAccountsFilter = new ArrayList<MinistryAccount>();
		
		ministryAccounts = MinistryAccountDAO.getMinistryAccountList();
		ministryAccounts.sort(MinistryAccount.ministryAccountAscendingComparator);
	
		ministryAccountsFilter.removeAll(ministryAccountsFilter);
		ministryAccountsFilter.addAll(ministryAccounts);

		JFrame.setDefaultLookAndFeelDecorated(true);
		
        frame = new JFrame("Ministry accounts Management");
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
	
		
		
		lbl_title = new JLabel("Ministry accounts");
		lbl_title.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbl_title.setHorizontalAlignment(JLabel.CENTER);
		lbl_title.setFont(new Font("Helvetica", Font.BOLD, 16));
		
		btn_createAccount = new JButton("Create new ministry account");
		btn_createAccount.setAlignmentX(Component.CENTER_ALIGNMENT);
		btn_createAccount.addActionListener(this);
		btn_createAccount.setActionCommand("Create");
		
		
		lbl_search = new JLabel("Enter ministry's name to search: ");       
        lbl_search.setPreferredSize(new Dimension(220,40));
		
		
        txt_search = new JTextField("Enter ministry's name to search...", 15);
        txt_search.setForeground(Color.GRAY);
        txt_search.setPreferredSize(new Dimension(0,50));
        txt_search.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (txt_search.getText().equals("Enter ministry's name to search...")) {
		        	txt_search.setText("");
		        	txt_search.setForeground(Color.BLACK);
		        }
		    }
		    
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (txt_search.getText().isEmpty()) {
		        	txt_search.setForeground(Color.GRAY);
		        	txt_search.setText("Enter ministry's name to search...");
		        }
		        
		        ministryAccountsFilter.removeAll(ministryAccountsFilter);
		        ministryAccountsFilter.addAll(ministryAccounts);
				revalidate();
		        repaint();
		    }
	    });
        txt_search.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				
				if (txt_search.getText().equals("")) {
					ministryAccountsFilter.removeAll(ministryAccountsFilter);
					ministryAccountsFilter.addAll(ministryAccounts);
				}
				else {
					Predicate<MinistryAccount> predicateString = s -> {
						return !s.getFullname().toLowerCase().contains(txt_search.getText().toLowerCase());
			        };
			        ministryAccountsFilter.removeAll(ministryAccountsFilter);
			        ministryAccountsFilter.addAll(ministryAccounts);
			        ministryAccountsFilter.removeIf(predicateString);
				}
				
				revalidate();
		        repaint();
				
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				
				if (txt_search.getText().equals("")) {
					ministryAccountsFilter.removeAll(ministryAccountsFilter);
					ministryAccountsFilter.addAll(ministryAccounts);
				}
				else {
					Predicate<MinistryAccount> predicateString = s -> {
						return !s.getFullname().toLowerCase().contains(txt_search.getText().toLowerCase());
			        };
			        ministryAccountsFilter.removeAll(ministryAccountsFilter);
			        ministryAccountsFilter.addAll(ministryAccounts);
			        ministryAccountsFilter.removeIf(predicateString);
				}
				
				revalidate();
		        repaint();
				
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				
				if (txt_search.getText().equals("")) {
					ministryAccountsFilter.removeAll(ministryAccountsFilter);
					ministryAccountsFilter.addAll(ministryAccounts);
				}
				else {
					Predicate<MinistryAccount> predicateString = s -> {
						return !s.getFullname().toLowerCase().contains(txt_search.getText().toLowerCase());
			        };
			        ministryAccountsFilter.removeAll(ministryAccountsFilter);
			        ministryAccountsFilter.addAll(ministryAccounts);
			        ministryAccountsFilter.removeIf(predicateString);
				}
				
				revalidate();
		        repaint();
				
			}
		});
		
		int[] columnsWidth = { 100, 300, 70, 120 };
		class CoursesListTableModel extends AbstractTableModel {

			private static final long serialVersionUID = 1L;

			@Override
			public int getRowCount() {
				return ministryAccountsFilter.size();
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
				
				MinistryAccount item = ministryAccountsFilter.get(rowIndex);
				
				switch (columnIndex) {
				case 0:
					return item.getUsername();
				case 1:
					return item.getFullname();
				case 2:
					return item.getDescription();
				default:
					return "";
				}
			}
			
			@Override
			public String getColumnName(int column) {
				switch (column) {
				case 0:
					return "Username";
				case 1:
					return "Full name";
				case 2:
					return "Description";
				default:
					return "";
				}
			}
		}
		

		
		tbl_ministryAccountList = new JTable();		
		tbl_ministryAccountList.setModel(new CoursesListTableModel());
		tbl_ministryAccountList.setRowSelectionAllowed(true);
		tbl_ministryAccountList.setRowHeight(30);
		tbl_ministryAccountList.setBackground(Color.DARK_GRAY);
		tbl_ministryAccountList.getTableHeader().setPreferredSize(new Dimension(0, 30));
		((DefaultTableCellRenderer)tbl_ministryAccountList.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		
		int i = 0;
		for (int width : columnsWidth) {
		    TableColumn column = tbl_ministryAccountList.getColumnModel().getColumn(i++);
		    column.setMinWidth(20);
		    column.setMaxWidth(width);
		    if (i == 3) {
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
								
								MinistryAccount accountToEdit = ministryAccountsFilter.get(row);
					        	Action actionEdit = new AbstractAction()
								{
									private static final long serialVersionUID = 1L;

									public void actionPerformed(ActionEvent e)
								    {
								        
										ministryAccounts.removeAll(ministryAccounts);
										
										
										ministryAccounts = MinistryAccountDAO.getMinistryAccountList();
										ministryAccounts.sort(MinistryAccount.ministryAccountAscendingComparator);
									
										ministryAccountsFilter.removeAll(ministryAccountsFilter);
										ministryAccountsFilter.addAll(ministryAccounts);
								        
										revalidate();
								        repaint();
								    }
								};
								
								JComponent editAccountForm;
								editAccountForm = new EditMinistryAccountForm(actionEdit, accountToEdit);
								editAccountForm.setOpaque(true);
								editAccountForm.setVisible(true);
							} catch (IOException e1) {
								
							} catch (URISyntaxException e1) {
								
							}
							
				        }
				        else if(command.equals("delete")) {
				        	MinistryAccount accountToDelete = ministryAccountsFilter.get(row);
							int input = JOptionPane.showConfirmDialog(null, "Are you sure to delete " + accountToDelete.getUsername() + " - " + accountToDelete.getFullname() +"?");
							// 0=yes, 1=no, 2=cancel
							
							if(input == 0) {
								int status = MinistryAccountDAO.deleteMinistryAccount(accountToDelete);
								if (status == -1) {
									showMessageDialog(null, "This account is not existed!");
								}
								else {
									
									ministryAccounts.removeAll(ministryAccounts);
									
									
									ministryAccounts = MinistryAccountDAO.getMinistryAccountList();
									ministryAccounts.sort(MinistryAccount.ministryAccountAscendingComparator);
								
									ministryAccountsFilter.removeAll(ministryAccountsFilter);
									ministryAccountsFilter.addAll(ministryAccounts);
							        
									revalidate();
							        repaint();
									
									showMessageDialog(null, "Deleted successfully!");
								}
							}
				        }
				        else {
				        	MinistryAccount accountToReset = ministryAccountsFilter.get(row);
							int input = JOptionPane.showConfirmDialog(null, "Are you sure to reset password for " + accountToReset.getUsername() + " - " + accountToReset.getFullname() +"?");
							// 0=yes, 1=no, 2=cancel
							
							if(input == 0) {
								accountToReset.setUsername(accountToReset.getUsername());
								accountToReset.setFullname(accountToReset.getFullname());
								accountToReset.setPassword("1111");
								accountToReset.setDescription(accountToReset.getDescription());
								int status = MinistryAccountDAO.updateMinistryAccount(accountToReset);
								if (status == -1) {
									showMessageDialog(null, "This account is not existed!");
								}
								else {
									
									ministryAccounts.removeAll(ministryAccounts);
									
									
									ministryAccounts = MinistryAccountDAO.getMinistryAccountList();
									ministryAccounts.sort(MinistryAccount.ministryAccountAscendingComparator);
								
									ministryAccountsFilter.removeAll(ministryAccountsFilter);
									ministryAccountsFilter.addAll(ministryAccounts);
							        
									revalidate();
							        repaint();
									
									showMessageDialog(null, accountToReset.getUsername() + " has been reseted to '1111'!");
								}
								
							}
				        }
				        
				    }
				};
		    	
				tbl_ministryAccountList.getColumnModel().getColumn(i-1).setCellRenderer(new MinistryAccountActionCellRenderer(tbl_ministryAccountList, actionMinistryAccount));
				tbl_ministryAccountList.getColumnModel().getColumn(i-1).setCellEditor(new MinistryAccountActionCellRenderer(tbl_ministryAccountList, actionMinistryAccount));
		    }
		    else {
		    	tbl_ministryAccountList.getColumnModel().getColumn(i-1).setCellRenderer(new RowSessionListRenderer());
		    }
		    
		    
		}
		
		
		
		scrollView = new JScrollPane(tbl_ministryAccountList);

		
		
		firstRow.add(lbl_title);
		searchRow.add(lbl_search);
		searchRow.add(txt_search);
		secondRow.add(btn_createAccount);
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
        
		
        layoutSecondRow.putConstraint(SpringLayout.NORTH, btn_createAccount, 10, SpringLayout.NORTH, secondRow);
        layoutSecondRow.putConstraint(SpringLayout.SOUTH, btn_createAccount, -10, SpringLayout.SOUTH, secondRow);
        layoutSecondRow.putConstraint(SpringLayout.EAST, btn_createAccount, -15, SpringLayout.EAST, secondRow);
        
       
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
				        
						ministryAccounts.removeAll(ministryAccounts);
						ministryAccountsFilter.removeAll(ministryAccountsFilter);
						
						
						ministryAccounts = MinistryAccountDAO.getMinistryAccountList();
						ministryAccounts.sort(MinistryAccount.ministryAccountAscendingComparator);
					
						ministryAccountsFilter.removeAll(ministryAccountsFilter);
						ministryAccountsFilter.addAll(ministryAccounts);
						
						tbl_ministryAccountList.revalidate();
						tbl_ministryAccountList.repaint();
				        
						revalidate();
				        repaint();
				    }
				};
				
				JComponent createMinistryAccountForm;
				createMinistryAccountForm = new CreateMinistryAccountForm(actionRefresh);
				createMinistryAccountForm.setOpaque(true);
				createMinistryAccountForm.setVisible(true);
				
			} catch (IOException | URISyntaxException e1) {
				showMessageDialog(null, "Error!");
			}
	    }

	}

}

class RowMinistryAccountListRenderer extends JLabel implements  TableCellRenderer
{

  
  private static final long serialVersionUID = 1L;

  public RowMinistryAccountListRenderer() {
   
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

class MinistryAccountActionCellRenderer extends AbstractCellEditor implements  TableCellEditor, TableCellRenderer, ActionListener
{
	static final long serialVersionUID = 1L;
	private JTable table;
	private Action action;
	
	private Object editorValue;
	
	public MinistryAccountActionCellRenderer(JTable table, Action action) {

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
		view_button.setLayout(new GridLayout(1,3));
		view_button.setBackground(Color.white);
		
		view_button.add(btn_edit);
		view_button.add(btn_passwordReset);
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
		
		JButton btn_passwordReset = new JButton();
		Border emptyBorder3 = BorderFactory.createEmptyBorder();
		btn_passwordReset.setBorder(emptyBorder3);
		btn_passwordReset.setPreferredSize(new Dimension(30, 30));
		btn_passwordReset.setBackground(Color.white);
		btn_passwordReset.setForeground(Color.white);
		btn_passwordReset.setActionCommand("passwordReset");
		  
		ImageIcon icon3 = new ImageIcon("img/passwordReset.png");
		Image scaleImage3 = icon3.getImage().getScaledInstance(25, 25,Image.SCALE_SMOOTH);
		btn_passwordReset.setIcon(new ImageIcon(scaleImage3));
		btn_passwordReset.addActionListener(this);
		btn_passwordReset.setMnemonic(KeyEvent.VK_D);

		
		JPanel view_button = new JPanel();
		view_button.setLayout(new GridLayout(1,3));
		view_button.setBackground(Color.white);
		
		view_button.add(btn_edit);
		view_button.add(btn_passwordReset);
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






