package windowsform;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;

import connect.sqlite.ConnectData;
import core.business.IEmployeeType;
import core.business.KeyValue;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;

public class EmployeeForm extends JInternalFrame implements IEmployeeType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_ID;
	private JTextField txt_Name;
	private JTextField txt_Address;
	private JTextField txt_Phone;
	private JTextField txt_Date;
	private JTextField txt_Type;
	private JTextField txt_UserName;
	private JTextField txt_PassWord;
	private JScrollPane scrollPane;
	private JButton btnNew;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private static JComboBox comboBox;
	private static JTable table;



	/**
	 * Launch the application.
	 */
	@SuppressWarnings("unchecked")
	public static void showTable() {
		Vector<String> rowHeader = new Vector<String> (); 				
		rowHeader.add ("empID"); 
		rowHeader.add ("empName");
		rowHeader.add ("empAddress"); 
		rowHeader.add ("empPhone"); 
		rowHeader.add ("empEmployedDate"); 
		rowHeader.add ("empTypeID"); 
		rowHeader.add ("empUserName"); 
		rowHeader.add ("empPassword"); 

		DefaultTableModel model = new DefaultTableModel(rowHeader,0);				
		table.setModel(model); 				
		ConnectData ds=new ConnectData();
		ds.connect();			
		String newSQL="SELECT * FROM Employee"; 
		try { 
			ResultSet rs =ds.ExcuteQuery(newSQL); 
			Vector rowData; 				
			if (rs != null) while (rs.next()){ 
				rowData = new Vector() ; 
				rowData.add (rs.getString("empID")); 
				//rowData.add (String.valueOf(rs.getInt("roomName"))); 
				rowData.add (rs.getString("empName"));	
				rowData.add (rs.getString("empAddress"));
				rowData.add (rs.getString("empPhone"));
				rowData.add (rs.getString("empEmployedDate"));
				rowData.add (rs.getString("empTypeID"));
				rowData.add (rs.getString("empUserName"));
				rowData.add (rs.getString("empPassword"));				
				model.addRow(rowData) ; 
			} 

			rs.close(); ds.dispose(); 
		} catch(Exception ex){System.out.println("Error : "+ex);}
	}
	
	public static void showCombo(){
		ConnectData ds=new ConnectData();
		ds.connect();			
		String newSQL="SELECT empTypeID,empTypeName FROM EmployeeType"; 

		try { 
			ResultSet rs =ds.ExcuteQuery(newSQL); 						
			if (rs != null) while (rs.next()){ 						
				//cb_roomStatusID.addItem((String)rs.getString("roomStatusName"));
				comboBox.addItem(new KeyValue((String)rs.getString("empTypeID"),(String)rs.getString("empTypeName")));
			} 

			rs.close(); ds.dispose(); 
		} catch(Exception ex){System.out.println("Error : " + ex);}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeForm frame = new EmployeeForm();
					frame.setVisible(true);
					showTable();
					showCombo();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EmployeeForm() {
		getContentPane().setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 553, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblEmployeeManagement = new JLabel("EMPLOYEE MANAGEMENT");
		lblEmployeeManagement.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEmployeeManagement.setBounds(188, 11, 222, 36);
		contentPane.add(lblEmployeeManagement);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(10, 69, 46, 14);
		contentPane.add(lblId);

		txt_ID = new JTextField();
		txt_ID.setBounds(112, 66, 86, 20);
		contentPane.add(txt_ID);
		txt_ID.setColumns(10);

		txt_Name = new JTextField();
		txt_Name.setBounds(112, 97, 235, 20);
		contentPane.add(txt_Name);
		txt_Name.setColumns(10);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 103, 46, 14);
		contentPane.add(lblName);

		txt_Address = new JTextField();
		txt_Address.setBounds(112, 130, 418, 20);
		contentPane.add(txt_Address);
		txt_Address.setColumns(10);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(10, 136, 92, 14);
		contentPane.add(lblAddress);

		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(10, 164, 46, 14);
		contentPane.add(lblPhone);

		txt_Phone = new JTextField();
		txt_Phone.setBounds(112, 161, 137, 20);
		contentPane.add(txt_Phone);
		txt_Phone.setColumns(10);

		txt_Date = new JTextField();
		txt_Date.setBounds(112, 192, 86, 20);
		contentPane.add(txt_Date);
		txt_Date.setColumns(10);

		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(10, 195, 46, 14);
		contentPane.add(lblDate);

		JLabel lblType = new JLabel("Type");
		lblType.setBounds(10, 226, 46, 14);
		contentPane.add(lblType);

		txt_Type = new JTextField();
		txt_Type.setEditable(false);
		txt_Type.setBounds(112, 223, 46, 20);
		contentPane.add(txt_Type);
		txt_Type.setColumns(10);

		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setBounds(10, 257, 67, 14);
		contentPane.add(lblUsername);

		txt_UserName = new JTextField();
		txt_UserName.setBounds(112, 254, 137, 20);
		contentPane.add(txt_UserName);
		txt_UserName.setColumns(10);

		JLabel lblPassword = new JLabel("PassWord");
		lblPassword.setBounds(10, 288, 67, 14);
		contentPane.add(lblPassword);

		txt_PassWord = new JTextField();
		txt_PassWord.setBounds(112, 285, 137, 20);
		contentPane.add(txt_PassWord);
		txt_PassWord.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 313, 530, 118);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table.getSelectedRow();
				txt_ID.setText((String) table.getValueAt(row, 0));
				txt_Name.setText((String) table.getValueAt(row, 1));
				txt_Address.setText((String) table.getValueAt(row, 2));
				txt_Phone.setText((String) table.getValueAt(row, 3));
				txt_Date.setText((String) table.getValueAt(row, 4));
				txt_Type.setText((String) table.getValueAt(row, 5));
				txt_UserName.setText((String) table.getValueAt(row, 6));
				txt_PassWord.setText((String) table.getValueAt(row, 7));				
				ConnectData ds=new ConnectData();
				ds.connect();			
				String value = null;
				
				String ID = (String) table.getValueAt(row, 5);
				String newSQL="SELECT empTypeName FROM EmployeeType where empTypeID='" + ID +"'"; 

				try { 
					ResultSet rs =ds.ExcuteQuery(newSQL); 						
					if (rs != null) {
						value = (String)rs.getString("empTypeName");						
					} 
					rs.close(); ds.dispose(); 
				} catch(Exception ex){System.out.println("Error : " + ex);}


				int a=0;
				for (int i=0; i<comboBox.getItemCount(); i++){
					if(((KeyValue)comboBox.getItemAt(i)).getValue().equals(value))
					{
						a=i;
						break;
					}
				}
				comboBox.setSelectedIndex(a);				
			}
		});
		scrollPane.setColumnHeaderView(table);

		btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txt_ID.setText("");
				txt_Name.setText("");
				txt_Address.setText("");
				txt_Phone.setText("");
				txt_Date.setText("");
				txt_Type.setText("");
				txt_UserName.setText("");
				txt_PassWord.setText("");
			}
		});
		btnNew.setBounds(10, 455, 89, 23);
		contentPane.add(btnNew);

		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addEmp();
				showTable();
			}
		});
		btnAdd.setBounds(160, 455, 89, 23);
		contentPane.add(btnAdd);

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				update();
				showTable();
			}
		});
		btnUpdate.setBounds(298, 455, 89, 23);
		contentPane.add(btnUpdate);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				delete();
				showTable();
			}
		});
		btnDelete.setBounds(438, 455, 89, 23);
		contentPane.add(btnDelete);
		// asign click b
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ID = ((KeyValue) comboBox.getSelectedItem()).getKey();
				txt_Type.setText(ID.toString());
			}
		});
		comboBox.setBounds(168, 223, 171, 20);
		contentPane.add(comboBox);
	}
	@Override
	public boolean addEmp() {
		// TODO Auto-generated method stub

		ConnectData ds=new ConnectData();
		ds.connect();						
		String name=txt_Name.getText();
		String address = txt_Address.getText();
		String phone = txt_Phone.getText();
		String date = txt_Date.getText();
		String type = txt_Type.getText();
		String user = txt_UserName.getText();
		String pass = txt_PassWord.getText();		

		String sql_insert="insert into Employee values("+ null +",'"+name+ "','"+address+"','"+phone+"','"+date+"','"+type+"','"+user+"','"+pass+"')";
		if(ds.queryExcuteUpdate(sql_insert))
		{
			JOptionPane.showMessageDialog(null,"Successfull");
			showTable();
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
	public boolean update() {
		// TODO Auto-generated method stub
		ConnectData ds=new ConnectData();
		ds.connect();					
		int id= Integer.parseInt(txt_ID.getText());
		String name=txt_Name.getText();
		String address = txt_Address.getText();
		String phone = txt_Phone.getText();
		String date = txt_Date.getText();
		String type = txt_Type.getText();
		String user = txt_UserName.getText();
		String pass = txt_PassWord.getText();
		String sql_insert="Update Employee Set empID="+id+",empName='"+name+"',empAddress='"+address+"',empPhone='"+phone+"',empEmployedDate='"+date+"',empTypeID='"+type+"',empUserName='"+user+"',empPassword='"+pass+"' where empID='" + id +"'" ;
		if(ds.queryExcuteUpdate(sql_insert))					
		{
			JOptionPane.showMessageDialog(null,"Successfull");
			showTable();			
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
	public ResultSet find(int ID) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean delete() {
		ConnectData ds=new ConnectData();
		ds.connect();
		String ID=txt_ID.getText();
		String sql_insert="delete from Employee where empID='"+ID+"'";
		if(ds.queryExcuteUpdate(sql_insert)){
			JOptionPane.showMessageDialog(null,"delete Successfull");
			showTable();}
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
}
