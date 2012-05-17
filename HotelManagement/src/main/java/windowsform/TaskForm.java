package windowsform;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

import core.business.ITask;

import connect.sqlite.ConnectData;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class TaskForm extends JFrame implements ITask{

	private JPanel contentPane;
	private static JTextField txt_ID;
	private static JTextField txt_Name;
	private static JTable table;
	int taskID;
	String taskName;
	/**
	 * Launch the application.
	 */
	
	public static void showTable() {
		Vector<String> rowHeader = new Vector<String> (); 				
		rowHeader.add ("taskID"); 
		rowHeader.add ("taskName"); 		
		DefaultTableModel model = new DefaultTableModel(rowHeader,0);				
		table.setModel(model); 				
		ConnectData ds=new ConnectData();
		ds.connect();			
		String newSQL="SELECT * FROM Task"; 
		try { 
			ResultSet rs =ds.ExcuteQuery(newSQL); 
			Vector<String> rowData; 				
			if (rs != null) while (rs.next()){ 
				rowData = new Vector<String>() ; 
				rowData.add (rs.getString("taskID")); 
				//rowData.add (String.valueOf(rs.getInt("roomName"))); 
				rowData.add (rs.getString("taskName"));				 		
				model.addRow(rowData); 
			} 

			rs.close(); ds.dispose(); 
		} catch(Exception ex){System.out.println("Error : "+ex);}
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaskForm frame = new TaskForm();
					frame.setVisible(true);
					showTable();
					txt_ID.setText((String) table.getValueAt(0, 0));
					txt_Name.setText((String) table.getValueAt(0, 1));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TaskForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTaskId = new JLabel("Task ID");
		lblTaskId.setBounds(10, 57, 46, 14);
		contentPane.add(lblTaskId);
		
		JLabel lblTaskName = new JLabel("Task Name");
		lblTaskName.setBounds(10, 103, 77, 14);
		contentPane.add(lblTaskName);
		
		txt_ID = new JTextField();
		txt_ID.setEditable(false);
		txt_ID.setBounds(103, 54, 129, 20);
		contentPane.add(txt_ID);
		txt_ID.setColumns(10);
		
		txt_Name = new JTextField();
		txt_Name.setBounds(103, 100, 329, 20);
		contentPane.add(txt_Name);
		txt_Name.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 154, 422, 98);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				updateField();
			}
		});
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				updateField();
			}
		});
		
		JButton button = new JButton("New");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txt_ID.setText("");
				txt_Name.setText("");
			}
		});
		button.setBounds(10, 263, 89, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Add");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addTask();
				//showTable();
			}
		});
		button_1.setBounds(118, 263, 89, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Update");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateRecord();
				//showTable();
			}
		});
		button_2.setBounds(234, 263, 89, 23);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("Delete");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteRecord();
				//showTable();
			}
		});
		button_3.setBounds(343, 263, 89, 23);
		contentPane.add(button_3);
		
		JLabel lblTaskManager = new JLabel("TASK MANAGEMENT");
		lblTaskManager.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTaskManager.setBounds(143, 11, 180, 32);
		contentPane.add(lblTaskManager);
	}
	
	// modify
	public boolean addRecord() {
		ConnectData ds=new ConnectData();
		ds.connect();					
					
		//int id= Integer.parseInt(txt_ID.getText());
		String name=txt_Name.getText();
		
		String sql_insert="insert into Task values("+ null +",'"+name+"')";
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

	public boolean updateRecord() {
		ConnectData ds=new ConnectData();
		ds.connect();					
		int id= Integer.parseInt(txt_ID.getText());
		String name=txt_Name.getText();			

		String sql_insert="Update Task Set taskID="+id+",taskName='"+name+ "' where taskID=" + id ;
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
	

	public int getRowCount() {
		// TODO Auto-generated method stub
		ConnectData ds=new ConnectData();
		ds.connect();			
		String newSQL="SELECT * FROM Task"; 
		try { 
			ResultSet rs =ds.ExcuteQuery(newSQL); 
			rs.last();
			return rs.getRow();
		} catch (SQLException ex) {
			System.out.print(ex);
			return 0;
			}		
		
	}
	//new AssignTaskForm().setVisible(true);
	public Object getValueAt(int row, int column) {
		ConnectData ds=new ConnectData();
		ds.connect();			
		String newSQL="SELECT * FROM Task"; 
		try {
			ResultSet rs =ds.ExcuteQuery(newSQL); 
			rs.absolute(row + 1);
			return rs.getObject(column + 1);
		} catch (SQLException ex) {
			System.out.print(ex);
			return null;
		}
	}

	public boolean deleteRecord() {
		ConnectData ds=new ConnectData();
		ds.connect();
		String ID=txt_ID.getText();
		String sql_insert="delete from Task where taskID='"+ID+"'";
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

	public void updateField() {
		int row = table.getSelectedRow();
		txt_ID.setText((String) table.getValueAt(row, 0));
		txt_Name.setText((String) table.getValueAt(row, 1));		
	}


	@Override
	public int getTaskID() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public String getTaskName() {
		// TODO Auto-generated method stub
		return null;
	}



	public boolean addTask() {
		// TODO Auto-generated method stub
		ConnectData ds=new ConnectData();
		ds.connect();				
		//int id= Integer.parseInt(txt_ID.getText());
		String name=txt_Name.getText();
		
		String sql_insert="insert into Task values("+ null +",'"+name+"')";
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
	public ITask getTask(int taskID) {
		return null;
		// TODO Auto-generated method stub
		
	}
}
