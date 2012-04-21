package windowsform;



import business.Room;
import java.awt.*;

import javax.swing.*;

public class RoomControl extends JPanel{

	protected Room room;
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
  GridLayout gridLayout1 = new GridLayout();
  JLabel jLabel1 = new JLabel();
	public RoomControl(Room room){
		super();
		try{
			
		jbInit();
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	      this.setSize(70, 36);
		this.room = room;
		this.setToolTipText(room.getRoomName() + "\n Adult: " + room.getRoomNoOfAdult() +"\n Children: "+room.getRoomNoOfChild() + "\n Fee: "+ room.getRoomFee());
		this.jLabel1.setText(room.getRoomName());
		switch(room.getRoomStatusID()){
			case 1:
				this.setBackground(Color.cyan);//available
				break;
			case 2:
				this.setBackground(Color.yellow);// reservated
				break;
			case 3:
				this.setBackground(Color.red);
				break;

		}

		}
		catch(Exception ex)
		{
			
		}
		
	}

  public RoomControl() {
    try {
      jbInit();
      this.setSize(70, 36);
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    this.setLayout(gridLayout1);
    jLabel1.setFont(new java.awt.Font("Dialog", 1, 16));
    jLabel1.setText("[]");
    this.add(jLabel1, null);
  }

}
