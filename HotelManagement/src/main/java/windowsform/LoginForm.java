package windowsform;

import javax.swing.*;

import core.business.Employee;
import core.formcontroller.MDIDesktopPane;

import java.awt.*;

import java.awt.event.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class LoginForm extends JInternalFrame {
  /**
	 *
	 */
	private static final long serialVersionUID = 1L;
JPanel jPanel1 = new JPanel();
  GridLayout gridLayout1 = new GridLayout();
  JLabel jLabel1 = new JLabel();
  JTextField tfUsername = new JTextField();
  JLabel jLabel2 = new JLabel();
  JPasswordField tfPassword = new JPasswordField();
  JButton btnLogin = new JButton();
  JButton btnCancel = new JButton();
  MDIDesktopPane desktop;
  JFrame owner;
  public LoginForm(MDIDesktopPane desktop, JFrame owner) {
    try {
    	this.desktop = desktop;
      this.owner = owner;
    	jbInit();
      this.setClosable(true);
      this.setVisible(true);
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    btnLogin.addActionListener(new LoginForm_btnLogin_actionAdapter(this));
    btnCancel.addActionListener(new LoginForm_btnCancel_actionAdapter(this));
    btnLogin.setBounds(new Rectangle(111, 98, 66, 26));
    btnCancel.setBounds(new Rectangle(188, 97, 75, 27));
    jLabel2.setBounds(new Rectangle(28, 69, 80, 23));
    tfPassword.setBounds(new Rectangle(112, 62, 151, 25));
    jLabel1.setBounds(new Rectangle(28, 36, 66, 24));
    tfUsername.setBounds(new Rectangle(112, 35, 150, 21));
    jPanel1.add(tfUsername, null);
    jPanel1.add(jLabel1, null);
    jPanel1.add(tfPassword, null);
    jPanel1.add(jLabel2, null);
    jPanel1.add(btnLogin, null);
    jPanel1.add(btnCancel, null);
    this.getContentPane().add(jPanel1, null);
    this.getContentPane().setLayout(gridLayout1);
    jPanel1.setLayout(null);
    jLabel1.setText("User Name");
    jLabel2.setText("Password");
    btnLogin.setText("Login");
    btnCancel.setText("Cancel");
    tfUsername.setText("");
    tfPassword.setText("");
    this.setSize(new Dimension(311, 174));
    this.getRootPane().setDefaultButton(btnLogin);
  }

  @SuppressWarnings("deprecation")
void btnLogin_actionPerformed(ActionEvent e) {
	  if(Employee.login(this.tfUsername.getText(), this.tfPassword.getText()))
	  {
		  ((MainForm)owner).setMenuStatus(true);
		  ((MainForm)owner).callRoomStatus();
		  ((MainForm)owner).miLogin.setText("Logout");
		  this.setVisible(false);
		  
		  desktop.remove(this);
	  }
  }

  void btnCancel_actionPerformed(ActionEvent e) {
	  this.setVisible(false);
	  desktop.remove(this);
  }

}

class LoginForm_btnLogin_actionAdapter implements java.awt.event.ActionListener {
  LoginForm adaptee;

  LoginForm_btnLogin_actionAdapter(LoginForm adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnLogin_actionPerformed(e);
  }
}

class LoginForm_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  LoginForm adaptee;

  LoginForm_btnCancel_actionAdapter(LoginForm adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}