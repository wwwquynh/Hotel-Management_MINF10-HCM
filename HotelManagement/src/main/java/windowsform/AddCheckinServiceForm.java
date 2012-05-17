package windowsform;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Rectangle;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import core.business.Customer;
import core.business.Reservation;
import core.business.ReservationDetailService;
import core.business.Room;
import core.formcontroller.MDIDesktopPane;



public class AddCheckinServiceForm extends JInternalFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MDIDesktopPane desktop;
	DefaultTableModel tblModelService;
	int resID;
	int roomID;
	int custID;
	int resDetailID;
	int oldRowNumber;
	public AddCheckinServiceForm(MDIDesktopPane desktop, int resID) {
        try {
        	this.desktop = desktop;
        	this.resID = resID;
        	this.setSize(370, 320);
            this.setClosable(true);
            this.setMaximizable(true);
            this.setVisible(true);
            jbInit();
            loadReserService();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

	public void loadReserService(){
		  
			  
			  ResultSet rs = Reservation.getReservationInfo(resID);
			  try {
				  while(rs.next()){
					  this.custID = rs.getInt("customerID"); 
					  roomID = rs.getInt("roomID");
					  txtTotalAmt.setText(rs.getDouble("preTotalCost") + "");
					  resDetailID = rs.getInt("resDetailID");
				  }
				  Room room = new Room(roomID);
				  this.txtRoomName.setText(room.getRoomName());
				  
				  Customer cust = new Customer(custID);
				  this.txtCustomer.setText(cust.getCustName());

			  //load reservation detail room, service
				 
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
		  while(rs.next()){
			  tblModelService.insertRow(i++, new Object[]{rs.getInt("serviceID"), rs.getString("serviceName"), rs.getDouble("serviceAmount")});
		  }
		  oldRowNumber = i;
	  }
	  catch(Exception ex){
		System.out.println(ex.getMessage());  
	  }
}
    private void jbInit() throws Exception {
    	String col[] = {"Service ID", "Service Name", "Service Amt"};
    	tblModelService = new DefaultTableModel(null, col);
    	tblExtraService = new JTable(tblModelService){
    		private static final long serialVersionUID = 1L;
    		public boolean isCellEditable(int rowIndex, int colIndex) {
    		  return false; //Disallow the editing of any cell
    	}};
        this.setSize(367,300);
        this.getContentPane().setLayout(gridLayout1);
        jScrollPane1.setBounds(new Rectangle(29, 156, 305, 133));
        txtRoomName.setBounds(new Rectangle(87, 24, 182, 22));
        btnAddMore.setBounds(new Rectangle(87, 123, 97, 23));
        btnAddMore.setText("Add more...");
        btnAddMore.addActionListener(new
        		AddCheckinServiceForm_btnAddMore_actionAdapter(this));
        btnSave.setBounds(new Rectangle(195, 123, 74, 23));
        btnSave.setText("Save");
        btnSave.addActionListener(new AddCheckinServiceForm_btnSave_actionAdapter(this));
        jLabel2.setText("Customer");
        jLabel2.setBounds(new Rectangle(29, 63, 57, 18));
        txtCustomer.setBounds(new Rectangle(87, 58, 182, 22));
        jLabel3.setText("Total Amt");
        jLabel3.setBounds(new Rectangle(28, 97, 116, 24));
        txtTotalAmt.setBounds(new Rectangle(178, 90, 90, 23));
        jPanel1.add(jLabel1);
        jPanel1.add(txtRoomName);
        jPanel1.add(txtCustomer);
        jPanel1.add(jLabel2);
        jPanel1.add(jScrollPane1);
        jPanel1.add(btnAddMore);
        jPanel1.add(btnSave);
        jPanel1.add(jLabel3);
        jPanel1.add(txtTotalAmt);
        jScrollPane1.getViewport().add(tblExtraService);
        
        this.getContentPane().add(jPanel1, null);
        jLabel1.setText("Room:");
        jLabel1.setBounds(new Rectangle(29, 26, 63, 24));
        jPanel1.setLayout(null);
    }

    JPanel jPanel1 = new JPanel();
    GridLayout gridLayout1 = new GridLayout();
    JLabel jLabel1 = new JLabel();
    JTextField txtRoomName = new JTextField();
    JScrollPane jScrollPane1 = new JScrollPane();
    JButton btnAddMore = new JButton();
    JButton btnSave = new JButton();
    JTable tblExtraService;
    JLabel jLabel2 = new JLabel();
    JTextField txtCustomer = new JTextField();
    JLabel jLabel3 = new JLabel();
    JTextField txtTotalAmt = new JTextField();
    
    public void btnAddMore_actionPerformed(ActionEvent e) {
    	ServiceChooseForAddMore sc = new ServiceChooseForAddMore(this);
	  	try{
	  		desktop.add(sc);
	  	}catch(Exception ex){
	  		System.out.println(ex.getMessage());
	  	}
    }

    public void btnSave_actionPerformed(ActionEvent e) {
    	int rowcount = tblExtraService.getModel().getRowCount();
    	for(int i=0; i<rowcount-oldRowNumber; i++){
    		
    		int sevID = (Integer) tblExtraService.getModel().getValueAt(i, 0);
    		ReservationDetailService resDet = new ReservationDetailService(resDetailID, sevID, 1);
    		resDet.addReservationServiceDetail();
    		
    		//System.out.println(sevID + ", " + resDetailID);
    	}
    	Reservation.updateAmount(resID, Double.parseDouble(txtTotalAmt.getText()));
    	this.setVisible(false);
    	desktop.remove(this);
    }
}


class AddCheckinServiceForm_btnSave_actionAdapter implements ActionListener {
    private AddCheckinServiceForm adaptee;
    AddCheckinServiceForm_btnSave_actionAdapter(AddCheckinServiceForm adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btnSave_actionPerformed(e);
    }
}


class AddCheckinServiceForm_btnAddMore_actionAdapter implements ActionListener {
    private AddCheckinServiceForm adaptee;
    AddCheckinServiceForm_btnAddMore_actionAdapter(AddCheckinServiceForm adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btnAddMore_actionPerformed(e);
    }
}

