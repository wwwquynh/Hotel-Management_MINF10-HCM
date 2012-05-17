package core.datechooser;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;

import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.util.Date;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class JCalTestEclipse extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JDateChooser jCal = null;
	private JLabel introLabel = null;
	private JLabel dateLabel = null;
	private JLabel dayLabel = null;
	private JLabel MonthLabel = null;
	private JLabel yearLabel = null;
	private JLabel dayOfYearLabel = null;
	private JLabel sqlLabel = null;
	private JButton dateButton = null;
	private JTextField dayTextField = null;
	private JTextField monthTextField = null;
	private JTextField yearTextField = null;
	private JTextField dayOfYearTextField = null;
	private JTextField sqlTextField = null;
	private JTextField dateTextField = null;
	private JButton exitButton = null;

	private JDateChooser getJCal() {
		if (jCal == null) {
			jCal = new JDateChooser();
			jCal.setBounds(new Rectangle(60, 60, 211, 31));
			jCal.setFont(new Font("Dialog", Font.PLAIN, 14));
		}
		return jCal;
	}

	private JButton getDateButton() {
		if (dateButton == null) {
			dateButton = new JButton();
			dateButton.setBounds(new Rectangle(30, 420, 136, 31));
			dateButton.setFont(new Font("Dialog", Font.BOLD, 14));
			dateButton.setForeground(Color.blue);
			dateButton.setText("Get Date Info");
			dateButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// System.out.println("actionPerformed()"); 
					displayDateInfo();
				}
			});
		}
		return dateButton;
	}
	
	public void displayDateInfo() {
		Date d = jCal.getDate();
		dateTextField.setText(d.toString());
		DateBean jcb = new DateBean(d);
		yearTextField.setText(Integer.toString(jcb.getYear()));
		monthTextField.setText(jcb.getMonth());
		dayTextField.setText(jcb.getDay());
		dayOfYearTextField.setText(Integer.toString(jcb.getDayOfYear()));
		java.sql.Date sqlDate = new java.sql.Date(d.getTime());
		sqlTextField.setText(jcb.getSQLDate().toString());
	}

	private JTextField getDayTextField() {
		if (dayTextField == null) {
			dayTextField = new JTextField();
			dayTextField.setBounds(new Rectangle(135, 150, 226, 31));
			dayTextField.setForeground(Color.blue);
			dayTextField.setFont(new Font("Dialog", Font.PLAIN, 14));
		}
		return dayTextField;
	}

	private JTextField getMonthTextField() {
		if (monthTextField == null) {
			monthTextField = new JTextField();
			monthTextField.setBounds(new Rectangle(135, 195, 226, 31));
			monthTextField.setForeground(Color.blue);
			monthTextField.setFont(new Font("Dialog", Font.PLAIN, 14));
		}
		return monthTextField;
	}

	private JTextField getYearTextField() {
		if (yearTextField == null) {
			yearTextField = new JTextField();
			yearTextField.setBounds(new Rectangle(135, 240, 226, 31));
			yearTextField.setForeground(Color.blue);
			yearTextField.setFont(new Font("Dialog", Font.PLAIN, 14));
		}
		return yearTextField;
	}

	private JTextField getDayOfYearTextField() {
		if (dayOfYearTextField == null) {
			dayOfYearTextField = new JTextField();
			dayOfYearTextField.setBounds(new Rectangle(135, 285, 226, 31));
			dayOfYearTextField.setForeground(Color.blue);
			dayOfYearTextField.setFont(new Font("Dialog", Font.PLAIN, 14));
		}
		return dayOfYearTextField;
	}

	private JTextField getSqlTextField() {
		if (sqlTextField == null) {
			sqlTextField = new JTextField();
			sqlTextField.setBounds(new Rectangle(135, 330, 226, 31));
			sqlTextField.setForeground(Color.blue);
			sqlTextField.setFont(new Font("Dialog", Font.PLAIN, 14));
		}
		return sqlTextField;
	}

	private JTextField getDateTextField() {
		if (dateTextField == null) {
			dateTextField = new JTextField();
			dateTextField.setBounds(new Rectangle(135, 105, 226, 31));
			dateTextField.setForeground(Color.blue);
			dateTextField.setFont(new Font("Dialog", Font.PLAIN, 14));
		}
		return dateTextField;
	}

	private JButton getExitButton() {
		if (exitButton == null) {
			exitButton = new JButton();
			exitButton.setBounds(new Rectangle(195, 420, 121, 31));
			exitButton.setFont(new Font("Dialog", Font.BOLD, 14));
			exitButton.setForeground(Color.red);
			exitButton.setText("Exit");
			exitButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return exitButton;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JCalTestEclipse thisClass = new JCalTestEclipse();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	public JCalTestEclipse() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(382, 503);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			sqlLabel = new JLabel();
			sqlLabel.setBounds(new Rectangle(15, 330, 91, 31));
			sqlLabel.setFont(new Font("Dialog", Font.BOLD, 14));
			sqlLabel.setText("SQL Date:");
			dayOfYearLabel = new JLabel();
			dayOfYearLabel.setBounds(new Rectangle(15, 285, 91, 31));
			dayOfYearLabel.setFont(new Font("Dialog", Font.BOLD, 14));
			dayOfYearLabel.setText("Day of Year:");
			yearLabel = new JLabel();
			yearLabel.setBounds(new Rectangle(15, 240, 91, 31));
			yearLabel.setFont(new Font("Dialog", Font.BOLD, 14));
			yearLabel.setText("Year:");
			MonthLabel = new JLabel();
			MonthLabel.setBounds(new Rectangle(15, 195, 91, 31));
			MonthLabel.setFont(new Font("Dialog", Font.BOLD, 14));
			MonthLabel.setText("Month:");
			dayLabel = new JLabel();
			dayLabel.setBounds(new Rectangle(15, 150, 91, 31));
			dayLabel.setFont(new Font("Dialog", Font.BOLD, 14));
			dayLabel.setText("Day:");
			dateLabel = new JLabel();
			dateLabel.setBounds(new Rectangle(15, 105, 91, 31));
			dateLabel.setFont(new Font("Dialog", Font.BOLD, 14));
			dateLabel.setText("Date:");
			introLabel = new JLabel();
			introLabel.setBounds(new Rectangle(15, 15, 301, 31));
			introLabel.setFont(new Font("Dialog", Font.BOLD, 18));
			introLabel.setText("JCalendar - JDateChooser Bean");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJCal(), null);
			jContentPane.add(introLabel, null);
			jContentPane.add(dateLabel, null);
			jContentPane.add(dayLabel, null);
			jContentPane.add(MonthLabel, null);
			jContentPane.add(yearLabel, null);
			jContentPane.add(dayOfYearLabel, null);
			jContentPane.add(sqlLabel, null);
			jContentPane.add(getDateButton(), null);
			jContentPane.add(getDayTextField(), null);
			jContentPane.add(getMonthTextField(), null);
			jContentPane.add(getYearTextField(), null);
			jContentPane.add(getDayOfYearTextField(), null);
			jContentPane.add(getSqlTextField(), null);
			jContentPane.add(getDateTextField(), null);
			jContentPane.add(getExitButton(), null);
		}
		return jContentPane;
	}
}