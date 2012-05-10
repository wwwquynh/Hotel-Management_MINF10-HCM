package windowsform;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import business.KeyValue;
import connect.sqlite.ConnectData;
import java.awt.TextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.Label;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


@SuppressWarnings("serial")
public class RoomFrom extends JInternalFrame {

	private JPanel contentPane;
	private static JTable table;
	private static JComboBox cb_roomStatusID;
	/**
	 * Launch the application.
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void showTable() {
		Vector<String> rowHeader = new Vector (); 				
		rowHeader.add ("roomID"); 
		rowHeader.add ("roomName"); 
		rowHeader.add ("roomFloor"); 
		rowHeader.add ("roomStatusID"); 
		rowHeader.add ("roomNoOfAdult"); 
		rowHeader.add ("roomNoOfChild"); 
		rowHeader.add ("roomFee"); 		
		DefaultTableModel model = new DefaultTableModel(rowHeader,0);				
		table.setModel(model); 				
		ConnectData ds=new ConnectData();
		ds.connect();			
		String newSQL="SELECT * FROM Room"; 
		try { 
			ResultSet rs =ds.ExcuteQuery(newSQL); 
			Vector rowData; 				
			if (rs != null) while (rs.next()){ 
				rowData = new Vector() ; 
				rowData.add (rs.getString("roomID")); 
				//rowData.add (String.valueOf(rs.getInt("roomName"))); 
				rowData.add (rs.getString("roomName"));
				rowData.add (rs.getString("roomFloor")); 
				rowData.add (rs.getString("roomStatusID")); 
				rowData.add (rs.getString("roomNoOfAdult")); 
				rowData.add (rs.getString("roomNoOfChild")); 
				rowData.add (rs.getString("roomFee")); 
				model.addRow(rowData) ; 
			} 

			rs.close(); ds.dispose(); 
		} catch(Exception ex){System.out.println("Error : "+ex);}
	}

	public static void showCombo(){
		ConnectData ds=new ConnectData();
		ds.connect();			
		String newSQL="SELECT roomStatusID,roomStatusName FROM RoomStatus"; 

		try { 
			ResultSet rs =ds.ExcuteQuery(newSQL); 						
			if (rs != null) while (rs.next()){ 						
				//cb_roomStatusID.addItem((String)rs.getString("roomStatusName"));
				cb_roomStatusID.addItem(new KeyValue(rs.getString("roomStatusID"),rs.getString("roomStatusName")));
			} 

			rs.close(); ds.dispose(); 
		} catch(Exception ex){System.out.println("Error : " + ex);}
	}


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoomFrom frame = new RoomFrom();
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
	public RoomFrom() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 122, 528, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);


		final TextField txt_idroom = new TextField();
		txt_idroom.setBounds(219, 58, 148, 22);

		final TextField txt_Floor = new TextField();
		txt_Floor.setBounds(219, 114, 148, 22);

		final TextField txt_roomName = new TextField();
		txt_roomName.setBounds(219, 86, 148, 22);

		Label label = new Label("RoomID");
		label.setBounds(56, 58, 84, 22);

		Label label_1 = new Label("Floor");
		label_1.setBounds(56, 114, 84, 22);

		Label label_2 = new Label("roomName");
		label_2.setBounds(56, 86, 84, 22);

		final TextField txt_roomStatusID = new TextField();		
		txt_roomStatusID.setEditable(false);

		txt_roomStatusID.setBounds(219, 142, 32, 22);

		Label label_3 = new Label("RoomStatusID");
		label_3.setBounds(56, 142, 127, 22);

		Label label_4 = new Label("roomNoOfAdult");
		label_4.setBounds(56, 170, 115, 22);

		final TextField txt_roomNoOfAdult = new TextField();
		txt_roomNoOfAdult.setBounds(219, 170, 148, 22);

		final TextField txt_roomNoOfChild = new TextField();
		txt_roomNoOfChild.setBounds(219, 198, 148, 22);

		Label label_5 = new Label("roomNoOfChild");
		label_5.setBounds(56, 198, 127, 22);

		final TextField txt_roomFee = new TextField();
		txt_roomFee.setBounds(219, 226, 148, 22);

		cb_roomStatusID = new JComboBox();
		cb_roomStatusID.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		cb_roomStatusID.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				String statusID = ((KeyValue) cb_roomStatusID.getSelectedItem()).getKey();
				txt_roomStatusID.setText(statusID.toString());
			}
		});
		cb_roomStatusID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String statusID = ((KeyValue) cb_roomStatusID.getSelectedItem()).getKey();
				txt_roomStatusID.setText(statusID.toString());
			}
		});
		cb_roomStatusID.setBounds(258, 144, 109, 20);
		contentPane.add(cb_roomStatusID);


		Button button = new Button("Add");
		button.setBounds(166, 427, 70, 22);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConnectData ds=new ConnectData();
				ds.connect();					
				int floor=Integer.parseInt(txt_Floor.getText());
				String roomName=txt_roomName.getText();
				int roomNoOfAdult=Integer.parseInt(txt_roomNoOfAdult.getText());
				int roomNoOfChild=Integer.parseInt(txt_roomNoOfChild.getText());
				int roomStatusID=Integer.parseInt(txt_roomStatusID.getText());
				int roomFee=Integer.parseInt(txt_roomFee.getText());
				//String sql_insert="insert into Room values('"+idroom+"','"+roomName+ "','"+ floor+ "','" +roomStatusID+"','"+roomNoOfAdult+"','"+roomNoOfChild+"','"+ roomFee +"')";
				String sql_insert="insert into Room values("+ null +",'"+roomName+ "','"+ floor+ "','" +roomStatusID+"','"+roomNoOfAdult+"','"+roomNoOfChild+"','"+ roomFee +"')";
				if(ds.queryExcuteUpdate(sql_insert))
				{
					JOptionPane.showMessageDialog(null,"Successfull");
					showTable();
				}
				else

					JOptionPane.showMessageDialog(null,"fail");

				try {
					ds.dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		Button button_1 = new Button("Delete");
		button_1.setBounds(297, 427, 70, 22);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnectData ds=new ConnectData();
				ds.connect();
				String roomID=txt_idroom.getText();
				String sql_insert="delete from Room where roomID='"+roomID+"'";
				if(ds.queryExcuteUpdate(sql_insert)){
					JOptionPane.showMessageDialog(null,"delete Successfull");
					showTable();}
				else
					JOptionPane.showMessageDialog(null,"delete fail");

				try {
					ds.dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		Button button_2 = new Button("Update");
		button_2.setBounds(414, 427, 70, 22);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnectData ds=new ConnectData();
				ds.connect();					
				int idroom= Integer.parseInt(txt_idroom.getText());
				int floor=Integer.parseInt(txt_Floor.getText());
				String roomName=txt_roomName.getText();
				int roomNoOfAdult=Integer.parseInt(txt_roomNoOfAdult.getText());
				int roomNoOfChild=Integer.parseInt(txt_roomNoOfChild.getText());
				int roomStatusID=Integer.parseInt(txt_roomStatusID.getText());
				float roomFee= Float.parseFloat(txt_roomFee.getText());

				String sql_insert="Update Room Set roomID="+idroom+",roomName='"+roomName+ "',roomFloor="+ floor+ ",roomStatusID=" +roomStatusID+",roomNoOfAdult="+roomNoOfAdult+",roomNoOfChild="+roomNoOfChild+",roomFee="+ roomFee +" where roomID=" + idroom ;
				if(ds.queryExcuteUpdate(sql_insert))					
				{
					JOptionPane.showMessageDialog(null,"Successfull");
					showTable();
				}
				else					
					JOptionPane.showMessageDialog(null,"fail");

				try {
					ds.dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		});
		contentPane.setLayout(null);
		contentPane.add(label);
		contentPane.add(txt_idroom);
		contentPane.add(label_1);
		contentPane.add(txt_Floor);
		contentPane.add(label_2);
		contentPane.add(txt_roomName);
		contentPane.add(label_3);
		contentPane.add(txt_roomStatusID);
		contentPane.add(label_4);
		contentPane.add(txt_roomNoOfAdult);
		contentPane.add(label_5);
		contentPane.add(txt_roomNoOfChild);
		contentPane.add(txt_roomFee);
		contentPane.add(button);
		contentPane.add(button_1);
		contentPane.add(button_2);

		Label label_6 = new Label("roomFee");
		label_6.setBounds(56, 226, 62, 22);
		contentPane.add(label_6);

		JLabel lblNewLabel = new JLabel("Room Management");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setForeground(SystemColor.desktop);
		lblNewLabel.setBounds(166, 11, 230, 29);
		contentPane.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();		
		scrollPane.setBounds(10, 264, 500, 136);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				comboUpdate(txt_idroom, txt_Floor, txt_roomName,
						txt_roomStatusID, txt_roomNoOfAdult, txt_roomNoOfChild,
						txt_roomFee);		
			}
		});
		table.setBackground(new Color(255, 255, 153));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				comboUpdate(txt_idroom, txt_Floor, txt_roomName,
						txt_roomStatusID, txt_roomNoOfAdult, txt_roomNoOfChild,
						txt_roomFee);			       			
			}
		});

		scrollPane.setViewportView(table);
		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				txt_idroom.setText("");
				txt_roomName.setText("");
				txt_Floor.setText("");
				txt_roomStatusID.setText("");
				txt_roomNoOfAdult.setText("");
				txt_roomNoOfChild.setText("");
				txt_roomFee.setText("");
			}
		});
		btnNew.setBounds(40, 427, 78, 23);
		contentPane.add(btnNew);	
	}


	public JTable getTable() {	
		return table;	
	}


	//

	public int getRowCount() {		
		ConnectData ds=new ConnectData();
		ds.connect();			
		String newSQL="SELECT * FROM Room"; 
		try { 
			ResultSet rs =ds.ExcuteQuery(newSQL); 
			rs.last();
			return rs.getRow();
		} catch (SQLException ex) {
			System.out.print(ex);
			return 0;		
		}		
	}


	public Object getValueAt(int row, int column) {
		ConnectData ds=new ConnectData();
		ds.connect();			
		String newSQL="SELECT * FROM Room"; 
		try {
			ResultSet rs =ds.ExcuteQuery(newSQL); 
			rs.absolute(row + 1);
			return rs.getObject(column + 1);
		} catch (SQLException ex) {
			System.out.print(ex);
			return null;
		}

	}

	public void comboUpdate(final TextField txt_idroom,
			final TextField txt_Floor, final TextField txt_roomName,
			final TextField txt_roomStatusID,
			final TextField txt_roomNoOfAdult,
			final TextField txt_roomNoOfChild, final TextField txt_roomFee) {
		int row = table.getSelectedRow();
		txt_idroom.setText((String) table.getValueAt(row, 0));
		txt_roomName.setText((String) table.getValueAt(row, 1));
		txt_Floor.setText((String) table.getValueAt(row, 2));				
		txt_roomStatusID.setText((String) table.getValueAt(row, 3));
		txt_roomNoOfAdult.setText((String) table.getValueAt(row, 4));
		txt_roomNoOfChild.setText((String) table.getValueAt(row, 5));
		txt_roomFee.setText((String) table.getValueAt(row, 6));				

		ConnectData ds=new ConnectData();
		ds.connect();			
		String value = null;
		String statusID = (String) table.getValueAt(row, 3);
		String newSQL="SELECT roomStatusName FROM RoomStatus where roomStatusID='" + statusID +"'"; 

		try { 
			ResultSet rs =ds.ExcuteQuery(newSQL); 						
			if (rs != null) {
				value = (String)rs.getString("roomStatusName");
				//System.out.println(value);
			} 
			rs.close(); ds.dispose(); 
		} catch(Exception ex){System.out.println("Error : " + ex);}


		int a=0;
		for (int i=0; i<cb_roomStatusID.getItemCount(); i++){
			if(((KeyValue)cb_roomStatusID.getItemAt(i)).getValue().equals(value))
			{
				a=i;
				break;
			}
		}
		cb_roomStatusID.setSelectedIndex(a);
	}
}
