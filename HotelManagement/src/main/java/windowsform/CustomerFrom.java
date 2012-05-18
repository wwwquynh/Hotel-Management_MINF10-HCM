package windowsform;

//import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;


import connect.sqlite.ConnectData;
import core.business.ICustomer;
import core.formcontroller.MDIDesktopPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class CustomerFrom extends JInternalFrame implements ICustomer {

	MDIDesktopPane desktop;
	private JPanel contentPane;
	private static JTextField txt_ID;
	private static JTextField txt_Name;
	private static JTextField txt_Phone;
	private static JTextField txt_Address;
	private static JTextField txt_email;
	private static JTextField txt_Passport;
	private static JTable table;
	JInternalFrame owner;
	//
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					CustomerFrom frame = new CustomerFrom();
					frame.setVisible(true);
					showTable();
										
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
	}

	/**
	 * Create the frame.
	 */
	public CustomerFrom(final MDIDesktopPane desktop, JInternalFrame owner) {
		this.desktop = desktop;
		this.owner = owner;
		this.setClosable(true);
		this.setMaximizable(true);
		this.setVisible(true);
		 this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		    
		setBounds(100, 100, 450, 497);
		contentPane = new JPanel();
		contentPane.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {
			}
			public void ancestorMoved(AncestorEvent arg0) {
			}
			public void ancestorRemoved(AncestorEvent arg0) {
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {
			}
			public void ancestorMoved(AncestorEvent arg0) {
			}
			public void ancestorRemoved(AncestorEvent arg0) {
			}
		});
		lblNewLabel.setBounds(27, 63, 67, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(26, 98, 82, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblCust = new JLabel("Phone");
		lblCust.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {
			}
			public void ancestorMoved(AncestorEvent arg0) {
			}
			public void ancestorRemoved(AncestorEvent arg0) {
			}
		});
		lblCust.setBounds(26, 134, 46, 14);
		contentPane.add(lblCust);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {
			}
			public void ancestorMoved(AncestorEvent arg0) {
			}
			public void ancestorRemoved(AncestorEvent arg0) {
			}
		});
		lblAddress.setBounds(26, 170, 67, 14);
		contentPane.add(lblAddress);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {
			}
			public void ancestorMoved(AncestorEvent arg0) {
			}
			public void ancestorRemoved(AncestorEvent arg0) {
			}
		});
		lblEmail.setBounds(26, 209, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblCustomerManagement = new JLabel("Customer Management");
		lblCustomerManagement.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCustomerManagement.setBounds(132, 11, 202, 20);
		contentPane.add(lblCustomerManagement);
		
		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txt_ID.setText("");
				txt_Name.setText("");
				txt_Phone.setText("");
				txt_Address.setText("");
				txt_email.setText("");
				txt_Passport.setText("");
			}
		});
		btnNew.setBounds(10, 401, 69, 23);
		contentPane.add(btnNew);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCustomer();
				showTable("SELECT * FROM Customer");
			}
		});
		btnAdd.setBounds(88, 401, 69, 23);
		contentPane.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateCustomer();
				showTable("SELECT * FROM Customer");
			}
		});
		btnUpdate.setBounds(170, 401, 79, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteCustomer();
				showTable("SELECT * FROM Customer");
			}
		});
		btnDelete.setBounds(265, 401, 79, 23);
		contentPane.add(btnDelete);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
////////////////////////////////////////////////////////////////////////	
				searchCustomer();
			}
		});
		btnSearch.setBounds(350, 401, 79, 23);
		contentPane.add(btnSearch);
		
		JButton btnChoose = new JButton("Choose");
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
////////////////////////////////////////////////////////////////////////	
				closingWindow();
			}
		});
		btnChoose.setBounds(350, 430, 79, 23);
		contentPane.add(btnChoose);
		
		
		
		txt_ID = new JTextField();
		txt_ID.setEditable(false);
		txt_ID.setBounds(97, 60, 155, 20);
		contentPane.add(txt_ID);
		txt_ID.setColumns(10);
		
		txt_Name = new JTextField();
		txt_Name.setBounds(97, 95, 335, 20);
		contentPane.add(txt_Name);
		txt_Name.setColumns(10);
		
		txt_Phone = new JTextField();
		txt_Phone.setBounds(97, 131, 335, 20);
		contentPane.add(txt_Phone);
		txt_Phone.setColumns(10);
		
		txt_Address = new JTextField();
		txt_Address.setBounds(97, 167, 335, 20);
		contentPane.add(txt_Address);
		txt_Address.setColumns(10);
		
		txt_email = new JTextField();
		txt_email.setBounds(97, 206, 335, 20);
		contentPane.add(txt_email);
		txt_email.setColumns(10);
		
		txt_Passport = new JTextField();
		txt_Passport.setBounds(97, 245, 335, 20);
		contentPane.add(txt_Passport);
		txt_Passport.setColumns(10);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 270, 422, 124);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				updateField();
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				updateField();
			}
		});
		scrollPane.setViewportView(table);
		
		showTable("SELECT * FROM Customer");
	}
	public void closingWindow(){
		
		int row = table.getSelectedRow();
		if(row>-1){
			int idc = Integer.parseInt((String) table.getValueAt(row, 0));
			String txt_Name = (String) table.getValueAt(row, 1);
			String txt_Phone = (String) table.getValueAt(row, 2);
			String txt_Address = (String) table.getValueAt(row, 3);
			String txt_email = (String) table.getValueAt(row, 4);
			String txt_Passport = (String) table.getValueAt(row, 5);
			((ReservationForm)owner).updateCustomerInfo(idc, txt_Name, txt_Address, txt_Phone, txt_Passport, txt_email);
			this.setVisible(false);
			desktop.remove(this);
		}
		
		
		
	}
	@Override
	public boolean addCustomer() {
		ConnectData ds=new ConnectData();
		ds.connect();					
					
		//int id= Integer.parseInt(txt_ID.getText());
		String name=txt_Name.getText();
		String phone=txt_Phone.getText();
		String address=txt_Address.getText();
		String email=txt_email.getText();	
		String passport = txt_Passport.getText();
		String sql_insert="insert into Customer values("+ null +",'"+name+ "','"+ phone+ "','" +address+"','"+email+"','"+passport+"')";
		if(ds.queryExcuteUpdate(sql_insert))
		{
			JOptionPane.showMessageDialog(null,"Successfull");
			showTable("SELECT * FROM Customer");
		}
		else

			JOptionPane.showMessageDialog(null,"fail");

		try {
			ds.dispose();
			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateCustomer() {
		ConnectData ds=new ConnectData();
		ds.connect();					
		int id= Integer.parseInt(txt_ID.getText());
		String name=txt_Name.getText();
		String phone=txt_Phone.getText();
		String address=txt_Address.getText();
		String email=txt_email.getText();		
		String passport = txt_Passport.getText(); 
		String sql_insert="Update Customer Set custID="+id+",custName='"+name+ "',custAddress='"+ address+ "',custPhone='" +phone+"',custEmail='"+email+ "', custPassport = '"+passport+ "' where custID=" + id ;
		if(ds.queryExcuteUpdate(sql_insert))					
		{
			JOptionPane.showMessageDialog(null,"Successfull");
			showTable("SELECT * FROM Customer");
			
		}
		else					
			JOptionPane.showMessageDialog(null,"fail");		

		try {
			ds.dispose();
			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;	
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void showTable(String newSQL) {
		// TODO Auto-generated method stub
		Vector<String> rowHeader = new Vector(); 				
		rowHeader.add ("ID"); 
		rowHeader.add ("Name"); 
		rowHeader.add ("Phone"); 
		rowHeader.add ("Address"); 
		rowHeader.add ("Email"); 
		rowHeader.add ("Passport"); 
		 		
		DefaultTableModel model = new DefaultTableModel(rowHeader,0);				
		table.setModel(model); 				
		ConnectData ds=new ConnectData();
		ds.connect();			
		
		try { 
			ResultSet rs =ds.ExcuteQuery(newSQL); 
			Vector rowData; 				
			if (rs != null) while (rs.next()){ 
				rowData = new Vector() ; 
				rowData.add (rs.getString("custID")); 
				//rowData.add (String.valueOf(rs.getInt("roomName"))); 
				rowData.add (rs.getString("custName"));
				rowData.add (rs.getString("custPhone")); 
				rowData.add (rs.getString("custAddress")); 
				rowData.add (rs.getString("custEmail"));
				rowData.add(rs.getString("custPassport"));
				model.addRow(rowData) ; 
			} 

			rs.close(); ds.dispose(); 
		} catch(Exception ex){System.out.println("Error : "+ex);}
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		ConnectData ds=new ConnectData();
		ds.connect();			
		String newSQL="SELECT * FROM Customer"; 
		try { 
			ResultSet rs =ds.ExcuteQuery(newSQL); 
			rs.last();
			return rs.getRow();
		} catch (SQLException ex) {
			System.out.print(ex);
			return 0;
			}		
		
	}

	@Override
	public Object getValueAt(int row, int column) {
		ConnectData ds=new ConnectData();
		ds.connect();			
		String newSQL="SELECT * FROM Customer"; 
		try {
			ResultSet rs =ds.ExcuteQuery(newSQL); 
			rs.absolute(row + 1);
			return rs.getObject(column + 1);
		} catch (SQLException ex) {
			System.out.print(ex);
			return null;
		}
	}

	@Override
	public boolean deleteCustomer() {
		ConnectData ds=new ConnectData();
		ds.connect();
		String ID=txt_ID.getText();
		String sql_insert="delete from Customer where custID='"+ID+"'";
		if(ds.queryExcuteUpdate(sql_insert)){
			JOptionPane.showMessageDialog(null,"delete Successfull");
			showTable("SELECT * FROM Customer");}
		else
			JOptionPane.showMessageDialog(null,"delete fail");

		try {
			ds.dispose();
			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

	public void searchCustomer(){
		ConnectData ds=new ConnectData();
		ds.connect();
		String cname=txt_Name.getText();
		String sql_search="select * from Customer where custName like '%"+cname+"%'";
		
		showTable(sql_search);
	}
		
	
	
	public static void updateField() {
		int row = table.getSelectedRow();
		txt_ID.setText((String) table.getValueAt(row, 0));
		txt_Name.setText((String) table.getValueAt(row, 1));
		txt_Phone.setText((String) table.getValueAt(row, 2));
		txt_Address.setText((String) table.getValueAt(row, 3));
		txt_email.setText((String) table.getValueAt(row, 4));
		txt_Passport.setText((String) table.getValueAt(row, 5));
	}

	
	
}
