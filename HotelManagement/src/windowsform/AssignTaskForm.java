package windowsform;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class AssignTaskForm extends JFrame {

	private JPanel contentPane;
	private JTextField txt_ID;
	private JTextField txt_empManager;
	private JTextField txt_empAssign;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AssignTaskForm frame = new AssignTaskForm();
					frame.setVisible(true);
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
		setBounds(100, 100, 450, 355);
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
		
		JLabel lblMamager = new JLabel("Mamager");
		lblMamager.setBounds(28, 81, 46, 14);
		contentPane.add(lblMamager);
		
		JLabel lblAssign = new JLabel("Assign");
		lblAssign.setBounds(28, 117, 46, 14);
		contentPane.add(lblAssign);
		
		txt_ID = new JTextField();
		txt_ID.setBounds(99, 40, 100, 20);
		contentPane.add(txt_ID);
		txt_ID.setColumns(10);
		
		txt_empManager = new JTextField();
		txt_empManager.setText("");
		txt_empManager.setBounds(99, 78, 216, 20);
		contentPane.add(txt_empManager);
		txt_empManager.setColumns(10);
		
		txt_empAssign = new JTextField();
		txt_empAssign.setBounds(99, 114, 333, 20);
		contentPane.add(txt_empAssign);
		txt_empAssign.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 153, 409, 102);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNew = new JButton("New");
		btnNew.setBounds(22, 287, 89, 23);
		contentPane.add(btnNew);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(127, 287, 89, 23);
		contentPane.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(239, 287, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(343, 287, 89, 23);
		contentPane.add(btnDelete);
	}
}
