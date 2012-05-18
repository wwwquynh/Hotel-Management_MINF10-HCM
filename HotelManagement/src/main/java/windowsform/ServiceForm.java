package windowsform;

import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.WindowConstants;

import connect.sqlite.ConnectData;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ServiceForm extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField txt_ID;
	private static JTextField txt_Name;
	private static JTextField txt_Amount;
	private static JTable table;


	public static void showTable() {
		Vector<String> rowHeader = new Vector<String> (); 				
		rowHeader.add ("serviceID"); 
		rowHeader.add ("serviceName"); 
		rowHeader.add ("serviceAmount");
		DefaultTableModel model = new DefaultTableModel(rowHeader,0);				
		table.setModel(model); 				
		ConnectData ds=new ConnectData();
		ds.connect();			
		String newSQL="SELECT * FROM Service"; 
		try { 
			ResultSet rs =ds.ExcuteQuery(newSQL); 
			Vector<String> rowData; 				
			if (rs != null) while (rs.next()){ 
				rowData = new Vector<String>() ; 
				rowData.add (rs.getString("serviceID")); 
				rowData.add (rs.getString("serviceName"));
				rowData.add (rs.getString("serviceAmount"));
				model.addRow(rowData); 
			} 

			rs.close(); ds.dispose(); 
		} catch(Exception ex){System.out.println("Error : "+ex);}
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServiceForm frame = new ServiceForm();
					frame.setVisible(true);
					showTable();
					table.selectAll();
					updateField();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ServiceForm() {
		this.setClosable(true);
		  this.setMaximizable(true);
		  this.setVisible(true);
		  this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		  this.setResizable(true);

		setBounds(100, 100, 450, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(10, 53, 46, 14);
		contentPane.add(lblNewLabel);

		txt_ID = new JTextField();
		txt_ID.setEditable(false);
		txt_ID.setBounds(112, 50, 86, 20);
		contentPane.add(txt_ID);
		txt_ID.setColumns(10);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 97, 46, 14);
		contentPane.add(lblName);

		txt_Name = new JTextField();
		txt_Name.setBounds(112, 91, 211, 20);
		contentPane.add(txt_Name);
		txt_Name.setColumns(10);

		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(10, 142, 46, 14);
		contentPane.add(lblAmount);

		txt_Amount = new JTextField();
		txt_Amount.setBounds(112, 139, 86, 20);
		contentPane.add(txt_Amount);
		txt_Amount.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 184, 422, 127);
		contentPane.add(scrollPane);
		
				table = new JTable();
				scrollPane.setViewportView(table);
				table.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent arg0) {
						updateField();
					}
				});
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						updateField();
					}
				});

		JButton button = new JButton("New");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txt_ID.setText("");
				txt_Name.setText("");
				txt_Amount.setText("");
			}
		});
		button.setBounds(10, 339, 89, 23);
		contentPane.add(button);

		JButton button_1 = new JButton("Add");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addRecord();
				showTable();
			}
		});
		button_1.setBounds(118, 339, 89, 23);
		contentPane.add(button_1);

		JButton button_2 = new JButton("Update");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateRecord();
				showTable();
				updateField();
			}
		});
		button_2.setBounds(234, 339, 89, 23);
		contentPane.add(button_2);

		JButton button_3 = new JButton("Delete");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteRecord();
				showTable();
				updateField();
			}
		});
		button_3.setBounds(343, 339, 89, 23);
		contentPane.add(button_3);

		JLabel lblServiceManagement = new JLabel("SERVICE MANAGEMENT");
		lblServiceManagement.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblServiceManagement.setBounds(120, 11, 249, 28);
		contentPane.add(lblServiceManagement);
		
		showTable();
		table.selectAll();
		updateField();
	}

	public boolean deleteRecord() {
		ConnectData ds=new ConnectData();
		ds.connect();
		String ID=txt_ID.getText();
		String sql_insert="delete from Service where serviceID='"+ID+"'";
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

	public static void updateField() {
		int row = table.getSelectedRow();
		txt_ID.setText((String) table.getValueAt(row, 0));
		txt_Name.setText((String) table.getValueAt(row, 1));
		txt_Amount.setText((String) table.getValueAt(row, 2));
	}

	public boolean updateRecord() {
		ConnectData ds=new ConnectData();
		ds.connect();					
		int id= Integer.parseInt(txt_ID.getText());
		String name=txt_Name.getText();	
		String amount=txt_Amount.getText();	

		String sql_insert="Update Service Set serviceID="+id+",serviceName='"+name+"',serviceAmount='"+amount+ "' where serviceID=" + id ;
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

	public boolean addRecord() {
		// TODO Auto-generated method stub
		ConnectData ds=new ConnectData();
		ds.connect();				
		//int id= Integer.parseInt(txt_ID.getText());
		String name=txt_Name.getText();
		String amount=txt_Amount.getText();

		String sql_insert="insert into Service values("+ null +",'"+name+"','"+amount+"')";
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


}
