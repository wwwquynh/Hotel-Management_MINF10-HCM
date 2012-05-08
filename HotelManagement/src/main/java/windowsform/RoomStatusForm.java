package windowsform;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import formcontroller.MDIDesktopPane;

import business.Room;

public class RoomStatusForm extends JInternalFrame {

	/**
	 *
	 */
	private int selectedRoomID;
	private int selectedResID;
	MDIDesktopPane desktop;
	private static final long serialVersionUID = 1L;
  GridLayout gridLayout1 = new GridLayout();
  JPanel jPanel1 = new JPanel();
  JPanel pnFloor1 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JPanel pnFloor2 = new JPanel();
  JLabel jLabel3 = new JLabel();
  JPanel pnFloor3 = new JPanel();
  JLabel jLabel4 = new JLabel();
  JPanel pnFloor4 = new JPanel();
  JLabel jLabel5 = new JLabel();
  JPanel pnFloor5 = new JPanel();


  JPopupMenu popAvailable = new JPopupMenu();
  JPopupMenu popResv = new JPopupMenu();
  JPopupMenu popOcc = new JPopupMenu();

  JMenuItem miRes = new JMenuItem("Reserv");
  JMenuItem miCheckIn = new JMenuItem("Check in");
  JMenuItem miCheckOut = new JMenuItem("Check out");
  JMenuItem miUpdateAvaiServ = new JMenuItem("Update Available Service");
  JMenuItem miCancelResv = new JMenuItem("Cancel Reservation");
  JMenuItem miCheckInWithResv = new JMenuItem("Check in");
  JMenuItem miAddMoreServ = new JMenuItem("Add Services");
  JPanel pnAvailable = new JPanel();
  JPanel pnBooked = new JPanel();
  JPanel pnOccupied = new JPanel();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JLabel jLabel8 = new JLabel();
  JInternalFrame owner;
  
  public RoomStatusForm(MDIDesktopPane desktop, JInternalFrame owner) {
    try {
  	 this.owner = owner;
      jbInit();
      this.desktop = desktop;
      this.setClosable(true);
      this.setMaximizable(true);
      this.setVisible(true);
    
      this.setResizable(true);
      layoutRoom();
      this.setSize(550, 350);
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  public void updateRoomLayout(){
	 
	  try {
		  this.jPanel1.removeAll();
		  jPanel1.add(pnFloor2, null);
		    pnFloor2.add(jLabel2, null);
		    jPanel1.add(pnFloor3, null);
		    pnFloor3.add(jLabel3, null);
		    jPanel1.add(pnFloor5, null);
		    pnFloor5.add(jLabel5, null);
		    jPanel1.add(pnFloor4, null);
		    pnFloor4.add(jLabel4, null);
		    jPanel1.add(pnFloor1, null);
		    pnFloor1.add(jLabel1, null);
		layoutRoom();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  private void layoutRoom() throws SQLException {
	  ResultSet rs = Room.getAllRoom();
	  while(rs.next()){
		  Room room = new Room(rs);
		  final RoomControl rCont = new RoomControl(room);
		  switch(room.getRoomFloor()){
		  case 1:

			  rCont.setBounds(new Rectangle(pnFloor1.getX() + 70*room.getRoomOrder() +room.getRoomOrder()*10, pnFloor1.getY(), 70, 36));
			  break;
		  case 2:
			  rCont.setBounds(pnFloor2.getX()+ 70*room.getRoomOrder() +room.getRoomOrder()*10, pnFloor2.getY(), 70, 36);
			  break;
		  case 3:
			  rCont.setBounds(pnFloor3.getX() + 70*room.getRoomOrder() +room.getRoomOrder()*10, pnFloor3.getY(), 70, 36);
			  break;
		  case 4:
			  rCont.setBounds(pnFloor4.getX() + 70*room.getRoomOrder() +room.getRoomOrder()*10, pnFloor4.getY(), 70, 36);
			  break;
		  case 5:
			  rCont.setBounds(pnFloor5.getX() + 70*room.getRoomOrder() +room.getRoomOrder()*10, pnFloor5.getY(), 70, 36);
			  break;
		  }
		  this.jPanel1.add(rCont, null);

		  rCont.addMouseListener(new MouseListener() {


			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				selectedRoomID = rCont.room.getRoomID();
				selectedResID = rCont.room.getResID();
				if(e.isPopupTrigger()){
					RoomControl rc = (RoomControl)e.getComponent();
					switch(rc.room.getRoomStatusID()){
					case 1://available
						popAvailable.show(e.getComponent(),e.getX(), e.getY());
						break;
					case 2://reserv
						popResv.show(e.getComponent(),e.getX(), e.getY());
						break;
					case 3: //occupied
						popOcc.show(e.getComponent(),e.getX(), e.getY());
						break;
					}


				}
			}


			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}


			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}


			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}


			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				selectedRoomID = rCont.room.getRoomID();
				selectedResID = rCont.room.getResID();
				//System.out.println();
				if(e.getClickCount()==2 && owner!=null){
					selectedRoomID = rCont.room.getRoomID();
					closingForm();
				}
			}
		});

	  }
  }
  public void closingForm(){
	  this.setVisible(false);
	  ((ReservationForm)owner).updateRoomInfo(selectedRoomID);
	  desktop.remove(this);
  }
  
  private void callResForm(boolean isReservation, int resrvID){
	  ReservationForm res = new ReservationForm(this, desktop, selectedRoomID, isReservation, resrvID);//true: res. 0: new
		desktop.add(res);
  }
  
  private void jbInit() throws Exception {
	  this.setMinimumSize(new Dimension(100, 100));

    this.getContentPane().setLayout(gridLayout1);
    jPanel1.setLayout(null);
    pnFloor1.setBackground(Color.gray);
    pnFloor1.setBorder(BorderFactory.createLineBorder(Color.black));
    pnFloor1.setToolTipText("");
    pnFloor1.setBounds(new Rectangle(27, 214, 70, 36));
    jLabel1.setFont(new java.awt.Font("Dialog", 1, 15));
    jLabel1.setText("Floor 1");
    jLabel2.setText("Floor 2");
    jLabel2.setFont(new java.awt.Font("Dialog", 1, 15));
    pnFloor2.setBounds(new Rectangle(27, 170, 70, 36));
    pnFloor2.setToolTipText("");
    pnFloor2.setBorder(BorderFactory.createLineBorder(Color.black));
    pnFloor2.setBackground(Color.gray);
    jLabel3.setText("Floor 3");
    jLabel3.setFont(new java.awt.Font("Dialog", 1, 15));
    pnFloor3.setBounds(new Rectangle(27, 126, 70, 36));
    pnFloor3.setToolTipText("");
    pnFloor3.setBorder(BorderFactory.createLineBorder(Color.black));
    pnFloor3.setBackground(Color.gray);
    jLabel4.setText("Floor 4");
    jLabel4.setFont(new java.awt.Font("Dialog", 1, 15));
    pnFloor4.setBounds(new Rectangle(27, 79, 70, 36));
    pnFloor4.setToolTipText("");
    pnFloor4.setBorder(BorderFactory.createLineBorder(Color.black));
    pnFloor4.setBackground(Color.gray);
    jLabel5.setText("Floor 5");
    jLabel5.setFont(new java.awt.Font("Dialog", 1, 15));
    pnFloor5.setBounds(new Rectangle(27, 35, 70, 36));
    pnFloor5.setToolTipText("");
    pnFloor5.setBorder(BorderFactory.createLineBorder(Color.black));
    pnFloor5.setBackground(Color.gray);
    pnAvailable.setBorder(BorderFactory.createLineBorder(Color.black));
    pnAvailable.setBounds(new Rectangle(27, 270, 20, 19));
    pnAvailable.setBackground(Color.cyan);
    pnBooked.setBounds(new Rectangle(167, 269, 20, 19));
    pnBooked.setBorder(BorderFactory.createLineBorder(Color.black));
    pnBooked.setBackground(Color.yellow);
    pnOccupied.setBounds(new Rectangle(308, 269, 20, 19));
    pnOccupied.setBorder(BorderFactory.createLineBorder(Color.black));
    pnOccupied.setBackground(Color.red);
    jLabel6.setText("Available");
    jLabel6.setBounds(new Rectangle(64, 271, 71, 17));
    jLabel7.setText("Booked");
    jLabel7.setBounds(new Rectangle(193, 272, 71, 16));
    jLabel8.setBounds(new Rectangle(339, 271, 71, 16));
    jLabel8.setText("Occupied");
    jPanel1.add(pnFloor2, null);
    pnFloor2.add(jLabel2, null);
    jPanel1.add(pnFloor3, null);
    pnFloor3.add(jLabel3, null);
    jPanel1.add(pnFloor5, null);
    pnFloor5.add(jLabel5, null);
    jPanel1.add(pnFloor4, null);
    pnFloor4.add(jLabel4, null);
    jPanel1.add(pnFloor1, null);
    pnFloor1.add(jLabel1, null);
    jPanel1.add(pnAvailable, null);
    jPanel1.add(pnBooked, null);
    jPanel1.add(pnOccupied, null);
    jPanel1.add(jLabel6, null);
    jPanel1.add(jLabel7, null);
    jPanel1.add(jLabel8, null);
    this.getContentPane().add(jPanel1, null);

    popAvailable.add(miRes);//ok
    popAvailable.add(miCheckIn);//ok
    popAvailable.add(miUpdateAvaiServ);

    popResv.add(miCheckInWithResv);
    popResv.add(miCancelResv);

    popOcc.add(miCheckOut);
    popOcc.add(miAddMoreServ);
    
    miRes.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent arg0) {
			callResForm(true, 0);
			//ReservationForm res = new ReservationForm(this, desktop, selectedRoomID, true, 0);//true: res. 0: new
			//desktop.add(res);
		}
	});
    
    miCheckIn.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent arg0) {
			callResForm(false, 0);
			//ReservationForm res = new ReservationForm(desktop, selectedRoomID, false, 0);//checkin, 0: new also
			//desktop.add(res);
		}
	});
    
    miCheckInWithResv.addActionListener(new ActionListener() {
    	//checkin with available reservation
    	//reservation ID where?
    	public void actionPerformed(ActionEvent arg0) {
    		callResForm(false, selectedResID);
    		//ReservationForm res = new ReservationForm(desktop, selectedRoomID, false, selectedResID);//checkin, load reservation
			//desktop.add(res);
		}
	});
    
  }

}
