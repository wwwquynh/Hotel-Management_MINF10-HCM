package windowsform;

import datechooser.*;

import formcontroller.MDIDesktopPane;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import business.AvailableService;
import business.Customer;
import business.Reservation;
import business.ReservationDetail;
import business.ReservationDetailService;
import business.Room;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ReservationForm extends JInternalFrame{

	/**
	 *
	 */
	private int roomID = 0;
	private int custID = 0;
	private int resrvID = 0;
	private int resDetailID = 0;
	MDIDesktopPane desktop;
	 DefaultTableModel tblModelAvailable;
	 DefaultTableModel tblModelService;
	private static final long serialVersionUID = 1L;
	
	
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JTextField txtCustomerName = new JTextField();
  JButton btnCustomerChoose = new JButton();
  JTextField txtPhone = new JTextField();
  JTextField txtAddress = new JTextField();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JLabel jLabel8 = new JLabel();
  JSpinner spNumOfAdult = new JSpinner();
  JSpinner spNumOfChild = new JSpinner();
  JDateChooser dateCheckin = new JDateChooser();
  JDateChooser dateCheckout = new JDateChooser();
  JButton btnRoomChoose = new JButton();
  JLabel jLabel9 = new JLabel();
  //JDateChooser dateCheckin = new JDateChooser();
  JTable tblAvailableService;
  JTable tblExtraService = new JTable();
  JTextField txtRoom = new JTextField();
  JTextField txtTotalCost = new JTextField();
  JLabel jLabel10 = new JLabel();
  JLabel jLabel11 = new JLabel();
  JButton btnAddMoreService = new JButton();
  JTable tblReservation = new JTable();
  GridLayout gridLayout1 = new GridLayout();
  JButton btnSearch = new JButton();
  JButton btnSave = new JButton();
  JButton btnNew = new JButton();
  JLabel jLabel12 = new JLabel();
  JTextField txtIDCardNumber = new JTextField();

  public ReservationForm() {
    try {
      jbInit();
      this.setClosable(true);
      this.setMaximizable(true);
      this.setVisible(true);
      this.setSize(800, 600);
      this.setResizable(true);
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  
  public ReservationForm(MDIDesktopPane desktop, int roomID){
	  try {
		  this.desktop = desktop;
	      jbInit();
	      this.setClosable(true);
	      this.setMaximizable(true);
	      this.setVisible(true);
	      this.setSize(800, 600);
	      this.setResizable(true);
	      
	      loadRoomDetail(roomID);
	    }
	    catch(Exception e) {
	      e.printStackTrace();
	    }
	  
  }
  
  private void loadRoomDetail(int roomID){
	  //load room detail
	  Room room = new Room(roomID);
	  this.roomID = roomID;
	  this.txtRoom.setText(room.getRoomName());
	 
	  
	  //load available service
	  ResultSet rs = AvailableService.getAvailableServiceByRoom(roomID);
	  try {
		  int i=0;
		  double totalService = 0;
		  while(rs.next()){
			totalService += rs.getDouble("serviceAmount");
			tblModelAvailable.insertRow(++i, new Object[]{rs.getInt("serviceID"), rs.getString("serviceName"), rs.getDouble("serviceAmount")});
		  }
		  totalService += room.getRoomFee();
		this.txtTotalCost.setText(totalService+"");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
 

  private void jbInit() throws Exception {
	  String dataEmpty[][] = {{}};
	  String col[] = {"Service ID", "Service Name", "Service Amt"};

	  tblModelAvailable = new DefaultTableModel(dataEmpty, col);
	  tblModelService = new DefaultTableModel(dataEmpty, col);
	  
	  
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    this.setTitle("Reservation");
    this.setPreferredSize(new Dimension(800, 600));
    this.getContentPane().setLayout(borderLayout1);
    jPanel1.setBorder(BorderFactory.createLineBorder(Color.black));
    jPanel1.setPreferredSize(new Dimension(10, 450));
    jPanel1.setLayout(null);
    jPanel2.setBorder(BorderFactory.createLineBorder(Color.black));
    jPanel2.setLayout(gridLayout1);
    jLabel1.setText("Customer");
    jLabel1.setBounds(new Rectangle(32, 31, 65, 24));
    txtCustomerName.setText("");
    txtCustomerName.setBounds(new Rectangle(171, 32, 233, 21));
    btnCustomerChoose.setBounds(new Rectangle(410, 32, 45, 21));
    btnCustomerChoose.setText("...");
    btnCustomerChoose.addActionListener(new ReservationForm_btnCustomerChoose_actionAdapter(this));
    txtPhone.setBounds(new Rectangle(171, 84, 233, 21));
    txtPhone.setText("");
    txtAddress.setBounds(new Rectangle(170, 110, 233, 21));
    txtAddress.setText("");
    jLabel2.setText("Phone");
    jLabel2.setBounds(new Rectangle(31, 82, 68, 22));
    jLabel3.setBounds(new Rectangle(30, 108, 77, 22));
    jLabel3.setText("Address");
    jLabel4.setRequestFocusEnabled(true);
    jLabel4.setText("Check-in Date");
    jLabel4.setBounds(new Rectangle(30, 128, 119, 35));
    jLabel5.setText("Number of Adult");
    jLabel5.setBounds(new Rectangle(458, 56, 98, 22));
    jLabel6.setText("Number of Children");
    jLabel6.setBounds(new Rectangle(455, 83, 99, 22));
    jLabel7.setText("Leave Date");
    jLabel7.setBounds(new Rectangle(29, 162, 109, 22));
    jLabel8.setText("Pre-Total Cost");
    jLabel8.setBounds(new Rectangle(29, 225, 106, 22));
    spNumOfAdult.setBounds(new Rectangle(566, 56, 59, 20));
    spNumOfChild.setBounds(new Rectangle(566, 79, 59, 20));
    //dateCheckin.setBounds(new Rectangle(171, 110, 233, 21));
    //dateCheckin.setText("");
    dateCheckout.setBounds(new Rectangle(170, 167, 233, 21));
    //dateCheckout.setText("");
    btnRoomChoose.setText("...");
    btnRoomChoose.addActionListener(new ReservationForm_btnRoomChoose_actionAdapter(this));
    btnRoomChoose.setBounds(new Rectangle(409, 195, 45, 21));
    jLabel9.setBounds(new Rectangle(30, 192, 65, 24));
    jLabel9.setText("Room");
    //dateCheckin.setText("");
    dateCheckin.setBounds(new Rectangle(169, 137, 233, 21));
    
    tblAvailableService = new JTable(tblModelAvailable){
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int rowIndex, int colIndex) {
		  return false; //Disallow the editing of any cell
	}};

    tblAvailableService.setBounds(new Rectangle(168, 278, 234, 103));
    
    tblExtraService = new JTable(tblModelService){
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int rowIndex, int colIndex) {
		  return false; //Disallow the editing of any cell
	}};
    tblExtraService.setBounds(new Rectangle(433, 278, 234, 103));
    txtRoom.setBounds(new Rectangle(170, 194, 233, 21));
    txtRoom.setText("");
    txtTotalCost.setText("");
    txtTotalCost.setBounds(new Rectangle(170, 225, 233, 21));
    jLabel10.setBounds(new Rectangle(169, 250, 114, 24));
    jLabel10.setText("Available Services");
    jLabel11.setText("Extra Services");
    jLabel11.setBounds(new Rectangle(432, 250, 114, 24));
    btnAddMoreService.setBounds(new Rectangle(568, 251, 98, 21));
    btnAddMoreService.setText("Add More...");
    btnAddMoreService.addActionListener(new ReservationForm_btnAddMoreService_actionAdapter(this));
    btnSearch.setBounds(new Rectangle(168, 389, 82, 21));
    btnSearch.setToolTipText("");
    btnSearch.setText("Search");
    btnSearch.addActionListener(new ReservationForm_btnSearch_actionAdapter(this));
    btnSave.setBounds(new Rectangle(336, 388, 66, 21));
    btnSave.setText("Save");
    btnSave.addActionListener(new ReservationForm_btnSave_actionAdapter(this));
    btnNew.setBounds(new Rectangle(263, 390, 62, 21));
    btnNew.setText("New");
    btnNew.addActionListener(new ReservationForm_btnNew_actionAdapter(this));
    jLabel12.setBounds(new Rectangle(32, 57, 68, 22));
    jLabel12.setText("ID/Passport");
    txtIDCardNumber.setBounds(new Rectangle(170, 58, 233, 21));
    txtIDCardNumber.setText("");
    this.getContentPane().add(jPanel2,  BorderLayout.CENTER);
    jPanel2.add(tblReservation, null);
    this.getContentPane().add(jPanel1, BorderLayout.NORTH);
    jPanel1.add(jLabel1, null);
    jPanel1.add(txtCustomerName, null);
    jPanel1.add(btnCustomerChoose, null);
    jPanel1.add(jLabel5, null);
    jPanel1.add(jLabel6, null);
    jPanel1.add(spNumOfAdult, null);
    jPanel1.add(spNumOfChild, null);
    jPanel1.add(txtAddress, null);
    jPanel1.add(jLabel3, null);
    jPanel1.add(jLabel4, null);
    jPanel1.add(dateCheckin, null);
    jPanel1.add(dateCheckout, null);
    jPanel1.add(jLabel7, null);
    jPanel1.add(jLabel9, null);
    jPanel1.add(jLabel8, null);
    jPanel1.add(txtRoom, null);
    jPanel1.add(txtTotalCost, null);
    jPanel1.add(btnRoomChoose, null);
    jPanel1.add(tblAvailableService, null);
    jPanel1.add(jLabel10, null);
    jPanel1.add(jLabel11, null);
    jPanel1.add(tblExtraService, null);
    jPanel1.add(btnAddMoreService, null);
    jPanel1.add(btnSearch, null);
    jPanel1.add(txtPhone, null);
    jPanel1.add(jLabel2, null);
    jPanel1.add(jLabel12, null);
    jPanel1.add(txtIDCardNumber, null);
    jPanel1.add(btnSave, null);
    jPanel1.add(btnNew, null);
    jPanel1.add(dateCheckin, null);
  }
  //private void formatTable(){
	  //tblAvailableService.getColumn("Amount").setCellRenderer(new RightTableCellRenderer());

  //}
  void btnCustomerChoose_actionPerformed(ActionEvent e) {
//Customer Choose
  }

  void btnRoomChoose_actionPerformed(ActionEvent e) {
//Room choose
  }

  void btnAddMoreService_actionPerformed(ActionEvent e) {
	  	ServiceChoose sc = new ServiceChoose(this);
	  	desktop.add(sc);
  }

  void btnSearch_actionPerformed(ActionEvent e) {
  //serach reservation
  }

  void btnNew_actionPerformed(ActionEvent e) {
//new- clear all
  }

  void btnSave_actionPerformed(ActionEvent e) {
	  try{

		//save reservation
			  //create new customer if code = 0
			  
			  if(custID == 0){
				  Customer cus = new Customer(0, txtCustomerName.getText(), txtAddress.getText(), txtPhone.getText(), "", txtIDCardNumber.getText());
				  custID = cus.addNewCustomer();
			  }
			//add reservation -> code
			  //public Reservation(int resID, int customerID, Date resDate, Date resLeaveDate, double preTotalCost, int numberOfAdult, int numberOfChild, int resStatus)
			  
			  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 
		      String checkin = dateFormat.format(dateCheckin.getDate());
			  String checkout = dateFormat.format(dateCheckout.getDate());

			  
			  resrvID =  Reservation.addReservationStat(custID,  checkin, checkout, Double.parseDouble(txtTotalCost.getText()), Integer.parseInt(spNumOfAdult.getValue().toString()), Integer.parseInt(spNumOfChild.getValue().toString()), 1);
			

			  //add reservation detail-> code
			  //public ReservationDetail(int resID, int roomID)
			  ReservationDetail resDet = new ReservationDetail(resrvID, roomID);
			  resDetailID = resDet.addReservationDetail();
			  //add resevation detail service
			  int rowCount = tblExtraService.getModel().getRowCount();
			  for(int i=1; i<rowCount; i++){
				  
				  ReservationDetailService reDetSer = new ReservationDetailService(resDetailID, Integer.parseInt(tblExtraService.getModel().getValueAt(i, 0).toString()), 1);
				  reDetSer.addReservationServiceDetail();
			  }
			  
			  //update room status
			  Room.updateStatus(roomID, 2);
			  System.out.println("ok las");
	  }
	  catch(Exception ex){
		  System.out.println(ex.getMessage());
	  }
  }

}

class ReservationForm_btnCustomerChoose_actionAdapter implements java.awt.event.ActionListener {
  ReservationForm adaptee;

  ReservationForm_btnCustomerChoose_actionAdapter(ReservationForm adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCustomerChoose_actionPerformed(e);
  }
}

class ReservationForm_btnRoomChoose_actionAdapter implements java.awt.event.ActionListener {
  ReservationForm adaptee;

  ReservationForm_btnRoomChoose_actionAdapter(ReservationForm adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnRoomChoose_actionPerformed(e);
  }
}

class ReservationForm_btnAddMoreService_actionAdapter implements java.awt.event.ActionListener {
  ReservationForm adaptee;

  ReservationForm_btnAddMoreService_actionAdapter(ReservationForm adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnAddMoreService_actionPerformed(e);
  }
}

class ReservationForm_btnSearch_actionAdapter implements java.awt.event.ActionListener {
  ReservationForm adaptee;

  ReservationForm_btnSearch_actionAdapter(ReservationForm adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSearch_actionPerformed(e);
  }
}

class ReservationForm_btnNew_actionAdapter implements java.awt.event.ActionListener {
  ReservationForm adaptee;

  ReservationForm_btnNew_actionAdapter(ReservationForm adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnNew_actionPerformed(e);
  }
}

class ReservationForm_btnSave_actionAdapter implements java.awt.event.ActionListener {
  ReservationForm adaptee;

  ReservationForm_btnSave_actionAdapter(ReservationForm adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnSave_actionPerformed(e);
  }
}
