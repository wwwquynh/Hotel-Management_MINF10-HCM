package windowsform;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Rectangle;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import core.business.AvailableService;
import core.business.Customer;
import core.business.Reservation;
import core.business.ReservationDetailService;
import core.business.Room;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckoutForm extends JInternalFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int resID;
	int custID;
	int roomID;
	JInternalFrame owner;
	DefaultTableModel tblModelAvailable;
	 DefaultTableModel tblModelService;
	public CheckoutForm(JInternalFrame owner, int resID) {
        try {
        	this.resID = resID;
        	this.owner = owner;
            jbInit();
            this.setClosable(true);
            this.setMaximizable(true);
            this.setVisible(true);
            //this.setResizable(true);

            this.setSize(700, 550);
            loadReservation();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

	public void loadReservation(){

			  ResultSet rs = Reservation.getReservationInfo(resID);
			  try {
				  
				  while(rs.next()){
					  this.custID = rs.getInt("customerID");
					  this.txt_NumOfAdult.setText(rs.getInt("numberOfAdult") +"");
					  this.txt_NumOFfChild.setText(rs.getInt("numberOfChild")+"");
					  this.txt_CheckinDate.setText(rs.getString("resDate"));
					  this.txt_LeaveDate.setText(rs.getString("resLeaveDate"));
					  
					  this.txt_Total.setText(rs.getDouble("preTotalCost") + "");  
					  roomID = rs.getInt("roomID");
				  }
			
				  
				  //load customer
				  loadCustomer(custID);
				  
			  //load reservation detail room, service
				  loadRoomDetailOfRes();
				  loadExtraService(resID);
			  //load 
			  
			  } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
	  }
	
	private void loadExtraService(int resID){
		  try{
			  ResultSet rs = ReservationDetailService.getExtraServices(resID);
			  int i = 0;
			  double total = 0;
			  while(rs.next()){
				  tblModelService.insertRow(i++, new Object[]{rs.getInt("serviceID"), rs.getString("serviceName"), rs.getDouble("serviceAmount")});
				  total += rs.getDouble("serviceAmount");
			  }
			  txt_ServiceFee.setText(total + "");
		  }
		  catch(Exception ex){
			System.out.println(ex.getMessage());  
		  }
	  }
	private void loadCustomer(int custID){
		  Customer cust = new Customer(custID);
		  this.txt_CustName.setText(cust.getCustName());
		  this.txt_CustAddress.setText(cust.getCustAddress());
		  this.txt_CustPassport.setText(cust.getCustPassport());
		  this.txt_CustPhone.setText(cust.getCustPhone());
		  
	  }
	
	private void loadRoomDetailOfRes(){
		  Room room = new Room(roomID);

		  this.txt_Room.setText(room.getRoomName());
		  this.txt_RoomFee.setText(room.getRoomFee()+"");
		  ResultSet rs = AvailableService.getAvailableServiceByRoom(roomID);
		  try {
			  int i=0;
			  
			  while(rs.next()){
				tblModelAvailable.insertRow(i++, new Object[]{rs.getInt("serviceID"), rs.getString("serviceName"), rs.getDouble("serviceAmount")});
				
			  }
			  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	
    private void jbInit() throws Exception {
    	String col[] = {"Service ID", "Service Name", "Service Amt"};
    	tblModelAvailable = new DefaultTableModel(null, col);
  	  tblModelService = new DefaultTableModel(null, col);
	  	tblExtraService = new JTable(tblModelService){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int rowIndex, int colIndex) {
			  return false; //Disallow the editing of any cell
		}};
		
		tblAvailableService = new JTable(tblModelAvailable){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int rowIndex, int colIndex) {
			  return false; //Disallow the editing of any cell
		}};
        this.getContentPane().setLayout(gridLayout1);
        jLabel2.setFont(new java.awt.Font("Tahoma", Font.BOLD, 15));
        jLabel2.setText("Check Out");
        jLabel2.setBounds(new Rectangle(309, 2, 84, 20));
        jLabel3.setText("Address");
        jLabel3.setBounds(new Rectangle(26, 93, 64, 18));
        jLabel4.setText("ID/Passport");
        jLabel4.setBounds(new Rectangle(26, 69, 78, 14));
        jLabel5.setText("Room");
        jLabel5.setBounds(new Rectangle(26, 152, 34, 14));
        jLabel6.setText("Number Of Adult");
        jLabel6.setBounds(new Rectangle(352, 42, 99, 15));
        jLabel7.setText("Number Of Children");
        jLabel7.setBounds(new Rectangle(352, 62, 98, 21));
        jLabel8.setText("Phone");
        jLabel8.setBounds(new Rectangle(26, 116, 70, 24));
        jLabel9.setText("Checkin Date");
        jLabel9.setBounds(new Rectangle(26, 176, 92, 23));
        jLabel10.setText("Leave Date");
        jLabel10.setBounds(new Rectangle(26, 201, 86, 24));
        jLabel11.setText("Room Fee");
        jLabel11.setBounds(new Rectangle(25, 384, 87, 21));
        jLabel12.setText("Total Service Fee");
        jLabel12.setBounds(new Rectangle(25, 406, 108, 26));
        jLabel13.setText("Total");
        jLabel13.setBounds(new Rectangle(25, 431, 84, 31));
        jScrollPane1 = new JScrollPane(tblExtraService);
        jScrollPane1.setBounds(new Rectangle(348, 233, 305, 133));
        
        jScrollPane2 = new JScrollPane( tblAvailableService);
        jScrollPane2.setBounds(new Rectangle(26, 234, 305, 133));
        jLabel14.setText("Extra Services");
        jLabel14.setBounds(new Rectangle(354, 203, 101, 22));
        txt_CustName.setText(":");
        txt_CustName.setBounds(new Rectangle(146, 41, 138, 16));
        txt_CustPassport.setText(":");
        txt_CustPassport.setBounds(new Rectangle(146, 67, 138, 16));
        txt_CheckinDate.setText(":");
        txt_CheckinDate.setBounds(new Rectangle(147, 176, 138, 16));
        txt_CustPhone.setText(":");
        txt_CustPhone.setBounds(new Rectangle(147, 118, 138, 16));
        txt_Room.setText(":");
        txt_Room.setBounds(new Rectangle(147, 150, 138, 16));
        txt_CustAddress.setText(":");
        txt_CustAddress.setBounds(new Rectangle(146, 95, 351, 16));
        txt_NumOfAdult.setText(":");
        txt_NumOfAdult.setBounds(new Rectangle(486, 41, 138, 16));
        txt_NumOFfChild.setText(":");
        txt_NumOFfChild.setBounds(new Rectangle(486, 62, 138, 16));
        txt_LeaveDate.setText(":");
        txt_LeaveDate.setBounds(new Rectangle(147, 205, 138, 16));
        txt_RoomFee.setText(":");
        txt_RoomFee.setBounds(new Rectangle(146, 388, 138, 16));
        txt_ServiceFee.setText(":");
        txt_ServiceFee.setBounds(new Rectangle(146, 410, 138, 16));
        txt_Total.setText(":");
        txt_Total.setBounds(new Rectangle(146, 437, 138, 16));
        btn_Print.setBounds(new Rectangle(303, 468, 73, 23));
        btn_Print.setText("Print");
        btn_Print.addActionListener(new CheckoutForm_btn_Print_actionAdapter(this));
        btn_Ok.setBounds(new Rectangle(217, 468, 73, 23));
        btn_Ok.setText("Ok");
        btn_Ok.addActionListener(new CheckoutForm_btn_Ok_actionAdapter(this));
        btn_Cancel.setBounds(new Rectangle(388, 468, 73, 23));
        btn_Cancel.setText("Cancel");
        btn_Cancel.addActionListener(new CheckoutForm_btn_Cancel_actionAdapter(this));
        jPanel1.add(jLabel1);
        jPanel1.add(jLabel4);
        jPanel1.add(jLabel3);
        jPanel1.add(jLabel5);
        jPanel1.add(jLabel8);
        jPanel1.add(jLabel10);
        jPanel1.add(jLabel9);
        jPanel1.add(jLabel11);
        jPanel1.add(jLabel13);
        jPanel1.add(jLabel12);
        jPanel1.add(jScrollPane2);
        jPanel1.add(jScrollPane1);
        jPanel1.add(jLabel14);
        jPanel1.add(jLabel2);
        jPanel1.add(txt_CustName);
        jPanel1.add(txt_CustPassport);
        jPanel1.add(txt_CustAddress);
        jPanel1.add(txt_CustPhone);
        jPanel1.add(txt_Room);
        jPanel1.add(txt_CheckinDate);
        jPanel1.add(txt_LeaveDate);
        jPanel1.add(txt_RoomFee);
        jPanel1.add(txt_ServiceFee);
        jPanel1.add(txt_Total);
        jPanel1.add(jLabel7);
        jPanel1.add(jLabel6);
        jPanel1.add(txt_NumOfAdult);
        jPanel1.add(txt_NumOFfChild);
        jPanel1.add(btn_Print);
        jPanel1.add(btn_Cancel);
        jPanel1.add(btn_Ok);
        //jScrollPane1.getViewport().add(tblExtraService);
        //jScrollPane2.getViewport().add(jTable3);
        
        this.getContentPane().add(jPanel1);
        jLabel1.setText("Customer");
        jLabel1.setBounds(new Rectangle(26, 43, 58, 14));
        jPanel1.setLayout(null);
    }

    GridLayout gridLayout1 = new GridLayout();
    JPanel jPanel1 = new JPanel();
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JLabel jLabel5 = new JLabel();
    JLabel jLabel6 = new JLabel();
    JLabel jLabel7 = new JLabel();
    JLabel jLabel8 = new JLabel();
    JLabel jLabel9 = new JLabel();
    JLabel jLabel10 = new JLabel();
    JLabel jLabel11 = new JLabel();
    JLabel jLabel12 = new JLabel();
    JLabel jLabel13 = new JLabel();
    JTable tblAvailableService;
    JScrollPane jScrollPane1;
    JTable tblExtraService;
    JScrollPane jScrollPane2;

    JLabel jLabel14 = new JLabel();
    JLabel txt_CustName = new JLabel();
    JLabel txt_CustPassport = new JLabel();
    JLabel txt_CheckinDate = new JLabel();
    JLabel txt_CustPhone = new JLabel();
    JLabel txt_Room = new JLabel();
    JLabel txt_CustAddress = new JLabel();
    JLabel txt_NumOfAdult = new JLabel();
    JLabel txt_NumOFfChild = new JLabel();
    JLabel txt_LeaveDate = new JLabel();
    JLabel txt_RoomFee = new JLabel();
    JLabel txt_ServiceFee = new JLabel();
    JLabel txt_Total = new JLabel();
    JButton btn_Print = new JButton();
    JButton btn_Ok = new JButton();
    JButton btn_Cancel = new JButton();
    public void btn_Ok_actionPerformed(ActionEvent e) {
    	//update res Status to complete
    	Reservation.makeComplete(resID);
    	//update room Status to free
    	Room.updateStatus(roomID, 1);
    	JOptionPane.showMessageDialog(null, "Check-out completed.");
    	((RoomStatusForm)owner).updateRoomLayout();
    }

    public void btn_Print_actionPerformed(ActionEvent e) {

    }

    public void btn_Cancel_actionPerformed(ActionEvent e) {

    }
}


class CheckoutForm_btn_Cancel_actionAdapter implements ActionListener {
    private CheckoutForm adaptee;
    CheckoutForm_btn_Cancel_actionAdapter(CheckoutForm adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btn_Cancel_actionPerformed(e);
    }
}


class CheckoutForm_btn_Print_actionAdapter implements ActionListener {
    private CheckoutForm adaptee;
    CheckoutForm_btn_Print_actionAdapter(CheckoutForm adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btn_Print_actionPerformed(e);
    }
}


class CheckoutForm_btn_Ok_actionAdapter implements ActionListener {
    private CheckoutForm adaptee;
    CheckoutForm_btn_Ok_actionAdapter(CheckoutForm adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btn_Ok_actionPerformed(e);
    }
}
