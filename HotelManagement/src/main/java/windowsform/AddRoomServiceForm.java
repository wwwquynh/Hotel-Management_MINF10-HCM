package windowsform;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2012</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */

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
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import core.business.AvailableService;
import core.business.Room;
import core.formcontroller.MDIDesktopPane;



public class AddRoomServiceForm extends JInternalFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JInternalFrame owner;
	MDIDesktopPane desktop;
	DefaultTableModel tblModelAvailable;
	int roomID;
	public ArrayList<Integer> avail;
	public ArrayList<Integer> newAdd;
	public AddRoomServiceForm(JInternalFrame owner, MDIDesktopPane desktop, int roomID) {
        try {
        	
            jbInit();
           
            this.owner = owner;
            this.desktop = desktop;
            this.roomID = roomID;
            this.setSize(370, 320);
            this.setClosable(true);
            this.setMaximizable(true);
            this.setVisible(true);
            
            loadRoomDetailOfRes();
          
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

	private void loadRoomDetailOfRes(){
		avail =  new ArrayList<Integer>();
		newAdd  = new ArrayList<Integer>();
		  Room room = new Room(roomID);
		 
		  this.txtRoomName.setText(room.getRoomName());
		  
		  ResultSet rs = AvailableService.getAvailableServiceByRoom(roomID);
		  try {
			  int i=0;
			  while(rs.next()){
				  	int svcID = rs.getInt("serviceID");
				  	if(!avail.contains(svcID)){
				  		tblModelAvailable.insertRow(i++, new Object[]{svcID, rs.getString("serviceName"), rs.getDouble("serviceAmount")});
				  		avail.add(svcID);
				  	}
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	
    private void jbInit() throws Exception {
    	 //String dataEmpty[][] = {{}};
     	  String col[] = {"Service ID", "Service Name", "Service Amt"};
     	  
           tblModelAvailable = new DefaultTableModel(null, col);
        this.getContentPane().setLayout(gridLayout1);
        tblAvailableService = new JTable(tblModelAvailable){
    		private static final long serialVersionUID = 1L;
    		public boolean isCellEditable(int rowIndex, int colIndex) {
    		  return false; //Disallow the editing of any cell
    	}};
    
        txtRoomName.setBounds(new Rectangle(81, 24, 182, 22));
        btnAddMore.setBounds(new Rectangle(81, 54, 97, 23));
        btnAddMore.setText("Add more...");
        btnAddMore.addActionListener(new
                AddRoomServiceForm_btnAddMore_actionAdapter(this));
        btnSave.setBounds(new Rectangle(190, 54, 74, 23));
        btnSave.setText("Save");
        btnSave.addActionListener(new AddRoomServiceForm_btnSave_actionAdapter(this));
        jPanel1.add(jLabel1);
        jPanel1.add(txtRoomName);
        jScrollPane1= new JScrollPane(tblAvailableService);
        jScrollPane1.setBounds(new Rectangle(29, 87, 305, 133));
        //jScrollPane1.getViewport().add(tblAvailableService);
        jPanel1.add(jScrollPane1);
       
        jPanel1.add(btnAddMore);
        jPanel1.add(btnSave);
        this.getContentPane().add(jPanel1, null);
        jLabel1.setText("Room:");
        jLabel1.setBounds(new Rectangle(29, 26, 63, 24));
        jPanel1.setLayout(null);
    }

    JPanel jPanel1 = new JPanel();
    GridLayout gridLayout1 = new GridLayout();
    JLabel jLabel1 = new JLabel();
    JTextField txtRoomName = new JTextField();
    JScrollPane jScrollPane1;
    JButton btnAddMore = new JButton();
    JButton btnSave = new JButton();
    JTable tblAvailableService;
    public void btnAddMore_actionPerformed(ActionEvent e) {
    	ServiceChoose sc = new ServiceChoose(this, false);
	  	try{
	  		desktop.add(sc);
	  	}catch(Exception ex){
	  		System.out.println(ex.getMessage());
	  	}
    }

    public void btnSave_actionPerformed(ActionEvent e) {
    	 int rowcount = tblAvailableService.getModel().getRowCount();
   	  for(int i=0; i<rowcount; i++){
   		  int svcID = (Integer) tblAvailableService.getModel().getValueAt(i, 0);
   		  if(newAdd.contains(svcID)){
   			  avail.add(AvailableService.addNew(roomID, svcID));
   		  }
   	  }
   	newAdd.clear();
    }
}


class AddRoomServiceForm_btnSave_actionAdapter implements ActionListener {
    private AddRoomServiceForm adaptee;
    AddRoomServiceForm_btnSave_actionAdapter(AddRoomServiceForm adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btnSave_actionPerformed(e);
    }
}


class AddRoomServiceForm_btnAddMore_actionAdapter implements ActionListener {
    private AddRoomServiceForm adaptee;
    AddRoomServiceForm_btnAddMore_actionAdapter(AddRoomServiceForm adaptee) {
        this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
        adaptee.btnAddMore_actionPerformed(e);
    }
}

