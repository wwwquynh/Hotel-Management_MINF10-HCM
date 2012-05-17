import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;


import javax.swing.JCheckBoxMenuItem;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import core.formcontroller.*;

import windowsform.AssignTaskForm;
import windowsform.CustomerFrom;
import windowsform.EmployeeForm;
import windowsform.LoginForm;
import windowsform.ReservationForm;
import windowsform.RoomFrom;
import windowsform.RoomStatusForm;

public class MainForm extends JFrame {


	
	private static final long serialVersionUID = 1L;

public MDIDesktopPane desktop;

  private JMenuBar menuBar = new JMenuBar();
  
  private JMenu mnuFile = new JMenu("File");
  private JMenu mnuMasterData = new JMenu("Masterdata");
  private JMenu mnuManagement = new JMenu("Management");
  private JMenu mnuReport = new JMenu("Report");
  
  //private JMenuItem newMenu = new JMenuItem("New");
  private JMenuItem miLogin = new JMenuItem("Login");
  private JMenuItem miExit = new JMenuItem("Exit");
  
  JMenuItem miHotel = new JMenuItem("Hotel");
  JMenuItem miRoom = new JMenuItem("Room");
  JMenuItem miEmployee = new JMenuItem("Employee");
  JMenuItem miTask = new JMenuItem("Task");
  
  JMenuItem miCustomer = new JMenuItem("Customer");
  JMenuItem miReservation = new JMenuItem("Reservation");
  JMenuItem miRoomStatus = new JMenuItem("Room Status");
  JMenuItem miAssignTask = new JMenuItem("Assign Task");
  
  private JMenuItem testForm = new JMenuItem("testForm");
  
  //private JMenuItem 
  private JScrollPane scrollPane = new JScrollPane();

  public MainForm() {
	  desktop = new MDIDesktopPane();
    menuBar.add(mnuFile);
    menuBar.add(mnuMasterData);
    menuBar.add(mnuManagement);
    menuBar.add(mnuReport);
    menuBar.add(new WindowMenu(desktop));
    
    mnuFile.add(miLogin);
    mnuFile.add(miExit);
    
    mnuMasterData.add(miHotel);
    mnuMasterData.add(miRoom);
    mnuMasterData.add(miEmployee);
    mnuMasterData.add(miTask);
    
    mnuManagement.add(miCustomer);
    mnuManagement.add(miReservation);
    mnuManagement.add(miRoomStatus);
    mnuManagement.add(miAssignTask);
    
    setJMenuBar(menuBar);
    setTitle("PUF Hotel");
    scrollPane.getViewport().add(desktop);
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(scrollPane, BorderLayout.CENTER);

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    testForm.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
        	TestFormCuaTui fct = new TestFormCuaTui();
          desktop.add(fct);
        }
      });
    
    miLogin.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
        	LoginForm fct = new LoginForm();
          desktop.add(fct); 
        }
      });
    
    miReservation.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
        	ReservationForm fct = new ReservationForm(null, desktop, true, 0);
          desktop.add(fct);
          
        }
      });
    
    miRoomStatus.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
        	RoomStatusForm rsf = new RoomStatusForm(desktop, null);
        	
          desktop.add(rsf);
          
        }
      });
    
    miCustomer.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
        	CustomerFrom rsf = new CustomerFrom(desktop, null);
        	
        	desktop.add(rsf);
          
        }
      });
    
    miRoom.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
        	//RoomFrom rsf = new RoomFrom(desktop, null);
        	RoomFrom rsf = new RoomFrom();
        	
        	desktop.add(rsf);
          
        }
      });
    /*
    newMenu.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
    	System.out.println("" + ae.toString());
        desktop.add(new TextFrame());
      }
    });*/
    
    miAssignTask.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
        	//RoomFrom rsf = new RoomFrom(desktop, null);
        	AssignTaskForm rsf = new AssignTaskForm();
        	
        	desktop.add(rsf);
          
        }
      });
    
    miEmployee.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
        	//RoomFrom rsf = new RoomFrom(desktop, null);
        	EmployeeForm rsf = new EmployeeForm();
        	
        	desktop.add(rsf);
          
        }
      });

  }

  public static void main(String[] args) {
	  MainForm main = new MainForm();
    main.setSize(1024, 768);
    main.setVisible(true);
  }

}

class TextFrame extends JInternalFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private JTextArea textArea = new JTextArea();

  private JScrollPane scrollPane = new JScrollPane();

  public TextFrame() {
    setSize(200, 300);
    setTitle("Edit Text");
    setMaximizable(true);
    setIconifiable(true);
    setClosable(true);
    setResizable(true);
    scrollPane.getViewport().add(textArea);
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(scrollPane, BorderLayout.CENTER);
  }
}

/**
 * An extension of WDesktopPane that supports often used MDI functionality. This
 * class also handles setting scroll bars for when windows move too far to the
 * left or bottom, providing the MDIDesktopPane is in a ScrollPane.
 */



/**
 * Menu component that handles the functionality expected of a standard
 * "Windows" menu for MDI applications.
 */
class WindowMenu extends JMenu {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private MDIDesktopPane desktop;

  private JMenuItem cascade = new JMenuItem("Cascade");

  private JMenuItem tile = new JMenuItem("Tile");

  public WindowMenu(MDIDesktopPane desktop) {
    this.desktop = desktop;
    setText("Window");
    cascade.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        WindowMenu.this.desktop.cascadeFrames();
      }
    });
    tile.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        WindowMenu.this.desktop.tileFrames();
      }
    });
    addMenuListener(new MenuListener() {
      public void menuCanceled(MenuEvent e) {
      }

      public void menuDeselected(MenuEvent e) {
        removeAll();
      }

      public void menuSelected(MenuEvent e) {
        buildChildMenus();
      }
    });
  }

  /* Sets up the children menus depending on the current desktop state */
  private void buildChildMenus() {
    int i;
    ChildMenuItem menu;
    JInternalFrame[] array = desktop.getAllFrames();

    add(cascade);
    add(tile);
    if (array.length > 0)
      addSeparator();
    cascade.setEnabled(array.length > 0);
    tile.setEnabled(array.length > 0);

    for (i = 0; i < array.length; i++) {
      menu = new ChildMenuItem(array[i]);
      menu.setState(i == 0);
      menu.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
          JInternalFrame frame = ((ChildMenuItem) ae.getSource()).getFrame();
          frame.moveToFront();
          try {
            frame.setSelected(true);
          } catch (PropertyVetoException e) {
            e.printStackTrace();
          }
        }
      });
      menu.setIcon(array[i].getFrameIcon());
      add(menu);
    }
  }

  /*
   * This JCheckBoxMenuItem descendant is used to track the child frame that
   * corresponds to a give menu.
   */
  class ChildMenuItem extends JCheckBoxMenuItem {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JInternalFrame frame;

    public ChildMenuItem(JInternalFrame frame) {
      super(frame.getTitle());
      this.frame = frame;
    }

    public JInternalFrame getFrame() {
      return frame;
    }
  }
}