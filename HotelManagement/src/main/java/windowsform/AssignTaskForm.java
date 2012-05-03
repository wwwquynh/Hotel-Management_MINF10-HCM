package windowsform;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

import business.KeyValue;
import connect.sqlite.ConnectData;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AssignTaskForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_ID;
	private JTextField txt_taskID;
	private JTextField txt_emp;
	private static JTable table;
	private JTextField txt_Date;
	private static JComboBox cb_task;
	private static JComboBox cb_emp;
	
	
	/**
	 * Launch the application.
	 */
	

	public static void showCombo(){
		ConnectData ds=new ConnectData();
		ds.connect();			
		String newSQL="SELECT taskID,taskName FROM Task"; 
		String newSQL1="SELECT empID,empName FROM Employee";  
		try { 
			ResultSet rs =ds.ExcuteQuery(newSQL); 						
			if (rs != null) while (rs.next()){ 						
				//cb_roomStatusID.addItem((String)rs.getString("roomStatusName"));
				cb_task.addItem(new KeyValue(rs.getString("taskID"),rs.getString("taskName")));
			} 
			rs.close(); 
			ResultSet rs1 =ds.ExcuteQuery(newSQL1); 						
			if (rs1 != null) while (rs1.next()){ 						
				//cb_roomStatusID.addItem((String)rs.getString("roomStatusName"));
				cb_emp.addItem(new KeyValue(rs1.getString("empID"),rs1.getString("empName")));
			} 
			rs1.close(); 			
			ds.dispose(); 
		} catch(Exception ex){System.out.println("Error : " + ex);}
	}

	
	//
	public static void showTable() {
		Vector<String> rowHeader = new Vector (); 				
		rowHeader.add ("assignTaskID"); 
		rowHeader.add ("taskID"); 
		rowHeader.add ("empID");	 
		rowHeader.add ("assignTaskDate");
		DefaultTableModel model = new DefaultTableModel(rowHeader,0);				
		table.setModel(model); 				
		ConnectData ds=new ConnectData();
		ds.connect();			
		String newSQL="SELECT * FROM AssignTask"; 
		try { 
			ResultSet rs =ds.ExcuteQuery(newSQL); 
			Vector rowData; 				
			if (rs != null) while (rs.next()){ 
				rowData = new Vector() ; 
				rowData.add (rs.getString("assignTaskID")); 
				rowData.add (rs.getString("taskID"));
				rowData.add (rs.getString("empID")); 
				rowData.add (rs.getString("assignTaskDate"));				
				model.addRow(rowData); 
			} 

			rs.close(); ds.dispose(); 
		} catch(Exception ex){System.out.println("Error : "+ex);}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AssignTaskForm frame = new AssignTaskForm();
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
	public AssignTaskForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAssignTaskManagement = new JLabel("Assign Task Management");
		lblAssignTaskManagement.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAssignTaskManagement.setBackground(Color.DARK_GRAY);
		lblAssignTaskManagement.setBounds(127, 0, 234, 29);
		contentPane.add(lblAssignTaskManagement);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(28, 43, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblMamager = new JLabel("Task");
		lblMamager.setBounds(28, 81, 74, 14);
		contentPane.add(lblMamager);
		
		JLabel lblAssign = new JLabel("Employeer");
		lblAssign.setBounds(28, 117, 74, 14);
		contentPane.add(lblAssign);
		
		txt_ID = new JTextField();
		txt_ID.setBounds(112, 40, 89, 20);
		contentPane.add(txt_ID);
		txt_ID.setColumns(10);
		
		txt_taskID = new JTextField();
		txt_taskID.setEditable(false);
		txt_taskID.setText("");
		txt_taskID.setBounds(112, 78, 89, 20);
		contentPane.add(txt_taskID);
		txt_taskID.setColumns(10);
		
		txt_emp = new JTextField();
		txt_emp.setEditable(false);
		txt_emp.setBounds(112, 114, 89, 20);
		contentPane.add(txt_emp);
		txt_emp.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();		
		scrollPane.setBounds(23, 181, 409, 161);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				txt_ID.setText((String) table.getValueAt(row, 0));
				txt_taskID.setText((String) table.getValueAt(row, 1));
				txt_emp.setText((String) table.getValueAt(row, 2));
				txt_Date.setText((String) table.getValueAt(row, 3));
								
				ConnectData ds=new ConnectData();
				ds.connect();			
				String value = null;				
				String ID = (String) table.getValueAt(row,1);				
				String newSQL="SELECT taskName FROM Task where taskID='" + ID +"'"; 

				String value1 = null;				
				String empID = (String) table.getValueAt(row,2);				
				String newSQL1="SELECT empName FROM Employee where empID='" + empID +"'"; 
				
				try { 
					ResultSet rs1 =ds.ExcuteQuery(newSQL1); 						
					if (rs1 != null) {
						value1 = (String)rs1.getString("empName");						
					} 
					rs1.close(); 
					//ds.dispose(); 
				} catch(Exception ex){System.out.println("Error : " + ex);}

				int b=0;
				for (int i=0; i<cb_emp.getItemCount(); i++){
					if(((KeyValue)cb_emp.getItemAt(i)).getValue().equals(value1))
					{
						b=i;
						break;
					}
				}
				cb_emp.setSelectedIndex(b);	
				
				
				try { 
					ResultSet rs =ds.ExcuteQuery(newSQL); 						
					if (rs != null) {
						value = (String)rs.getString("taskName");						
					} 
					rs.close(); ds.dispose(); 
				} catch(Exception ex){System.out.println("Error : " + ex);}

				int a=0;
				for (int i=0; i<cb_task.getItemCount(); i++){
					if(((KeyValue)cb_task.getItemAt(i)).getValue().equals(value))
					{
						a=i;
						break;
					}
				}
				cb_task.setSelectedIndex(a);
				 
				// cb_emp
				
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txt_ID.setText("");
				txt_taskID.setText("");
				txt_emp.setText("");
				txt_Date.setText("");
			}
		});
		btnNew.setBounds(22, 364, 89, 23);
		contentPane.add(btnNew);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addEmp();
				showTable();
			}
		});
		btnAdd.setBounds(127, 364, 89, 23);
		contentPane.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				update();
				showTable();
			}
		});
		btnUpdate.setBounds(239, 364, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				delete();
				showTable();
			}
		});
		btnDelete.setBounds(343, 364, 89, 23);
		contentPane.add(btnDelete);
		
		cb_task = new JComboBox();
		cb_task.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String statusID = ((KeyValue) cb_task.getSelectedItem()).getKey();
				txt_taskID.setText(statusID.toString());
			}
		});
		cb_task.setBounds(211, 78, 221, 20);
		contentPane.add(cb_task);
		
		cb_emp = new JComboBox();
		cb_emp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String statusID = ((KeyValue) cb_emp.getSelectedItem()).getKey();
				txt_emp.setText(statusID.toString());
			}
		});
		cb_emp.setBounds(211, 114, 221, 20);
		contentPane.add(cb_emp);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(28, 156, 46, 14);
		contentPane.add(lblDate);
		
		txt_Date = new JTextField();
		txt_Date.setBounds(112, 153, 89, 20);
		contentPane.add(txt_Date);
		txt_Date.setColumns(10);
	}
	
	public boolean addEmp() {
		// TODO Auto-generated method stub

		ConnectData ds=new ConnectData();
		ds.connect();						
		String task=txt_taskID.getText();
		String emp = txt_emp.getText();
		String date = txt_Date.getText();				

		String sql_insert="insert into AssignTask values("+ null +",'"+task+ "','"+emp+"','"+date+"')";
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
	
	public boolean update() {
		// TODO Auto-generated method stub
		ConnectData ds=new ConnectData();
		ds.connect();					
		int id= Integer.parseInt(txt_ID.getText());
		String task=txt_taskID.getText();
		String emp = txt_emp.getText();
		String date = txt_Date.getText();	
		String sql_insert="Update AssignTask Set assignTaskID="+id+",taskID='"+task+"',empID='"+emp+"',assignTaskDate='"+date+ "' where assignTaskID='" + id +"'" ;
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
	public ResultSet find(int ID) {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean delete() {
		ConnectData ds=new ConnectData();
		ds.connect();
		String ID=txt_ID.getText();
		String sql_insert="delete from AssignTask where assignTaskID='"+ID+"'";
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
