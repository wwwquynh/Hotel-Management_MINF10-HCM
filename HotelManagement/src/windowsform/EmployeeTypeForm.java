package windowsform;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;



import business.IEmployeeType;
import connect.sqlite.ConnectData;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmployeeTypeForm extends JFrame implements IEmployeeType {

	private JPanel contentPane;
	private JTextField txt_ID;
	private JTextField txt_Name;
	private static JTable table;

	/**
	 * Launch the application.
	 */
	
	public static void showTable() {
		Vector<String> rowHeader = new Vector (); 				
		rowHeader.add ("empTypeID"); 
		rowHeader.add ("empTypeName"); 
		 		
		DefaultTableModel model = new DefaultTableModel(rowHeader,0);				
		table.setModel(model); 				
		ConnectData ds=new ConnectData();
		ds.connect();			
		String newSQL="SELECT * FROM EmployeeType"; 
		try { 
			ResultSet rs =ds.ExcuteQuery(newSQL); 
			Vector rowData; 				
			if (rs != null) while (rs.next()){ 
				rowData = new Vector() ; 
				rowData.add (rs.getString("empTypeID")); 
				//rowData.add (String.valueOf(rs.getInt("roomName"))); 
				rowData.add (rs.getString("empTypeName"));				 
				model.addRow(rowData) ; 
			} 

			rs.close(); ds.dispose(); 
		} catch(Exception ex){System.out.println("Error : "+ex);}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeTypeForm frame = new EmployeeTypeForm();
					frame.setVisible(true);
					showTable();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EmployeeTypeForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(10, 74, 46, 14);
		contentPane.add(lblNewLabel);
		
		txt_ID = new JTextField();
		txt_ID.setBounds(83, 71, 162, 20);
		contentPane.add(txt_ID);
		txt_ID.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 105, 46, 14);
		contentPane.add(lblName);
		
		txt_Name = new JTextField();
		txt_Name.setBounds(83, 102, 349, 20);
		contentPane.add(txt_Name);
		txt_Name.setColumns(10);
		
		JLabel lblEmployeeType = new JLabel("EMPLOYEE TYPE");
		lblEmployeeType.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEmployeeType.setBounds(146, 11, 171, 30);
		contentPane.add(lblEmployeeType);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 138, 422, 87);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table.getSelectedRow();
				txt_ID.setText((String) table.getValueAt(row, 0));
				txt_Name.setText((String) table.getValueAt(row, 1));
			}
		});
		scrollPane.setColumnHeaderView(table);
		
		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txt_ID.setText("");
				txt_Name.setText("");
			}
		});
		btnNew.setBounds(10, 247, 89, 23);
		contentPane.add(btnNew);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addEmp();
			showTable();
			
			}
		});
		btnAdd.setBounds(124, 247, 89, 23);
		contentPane.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				update();
				showTable();
			}
		});
		btnUpdate.setBounds(235, 247, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				delete();
				showTable();
			}
		});
		btnDelete.setBounds(343, 247, 89, 23);
		contentPane.add(btnDelete);
	}
	@Override
	public boolean addEmp() {
		// TODO Auto-generated method stub
		
		ConnectData ds=new ConnectData();
		ds.connect();						
		String name=txt_Name.getText();
		
		String sql_insert="insert into EmployeeType values("+ null +",'"+name+ "')";
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
				

		String sql_insert="Update Customer Set empTypeID="+id+",empTypeName='"+name+"' where empTypeID=" + id ;
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
		String sql_insert="delete from EmployeeType where empTypeID='"+ID+"'";
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
