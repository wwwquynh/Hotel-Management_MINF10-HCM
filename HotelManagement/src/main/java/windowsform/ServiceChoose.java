package windowsform;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import core.business.Service;


import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceChoose extends JInternalFrame{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JButton btnOk = new JButton();
  JButton btnCancel = new JButton();
  BorderLayout borderLayout2 = new BorderLayout();
  
  Object[] columnNames = {"Select", "Service ID", "Service Name", "Amount"};
  Object[][] dataEmpty = {{false, 0, "",0}};
  DefaultTableModel model;
  JTable tblService;
  JInternalFrame rf;
  boolean isRes;
  public ServiceChoose(JInternalFrame rf, boolean isRes) {
    try {
    	this.rf = rf;
    	this.isRes = isRes;
      jbInit();
      this.setClosable(true);
      this.setMaximizable(true);
      this.setVisible(true);
      this.setSize(350, 200);
      this.setResizable(true);
      loadAllService();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

private void loadAllService(){
	try {
		ResultSet rs= Service.getAllService();
		int i = 0;
		while(rs.next()){
			//model.insertRow(++i, new Object[]{false, 8, "serviceName", 8.9});
			model.insertRow(i++, new Object[]{false, rs.getInt("serviceID"), rs.getString("serviceName"), rs.getDouble("serviceAmount")});
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
  private void jbInit() throws Exception {
    this.getContentPane().setLayout(borderLayout1);
    jPanel1.setPreferredSize(new Dimension(10, 50));
    jPanel1.setLayout(null);
    btnOk.setBounds(new Rectangle(108, 13, 77, 27));
    btnOk.setText("Ok");
    btnCancel.setText("Cancel");
    btnCancel.setBounds(new Rectangle(197, 13, 77, 27));
    jPanel2.setDebugGraphicsOptions(0);
    jPanel2.setLayout(borderLayout2);
    this.getContentPane().add(jPanel1,  BorderLayout.SOUTH);
    jPanel1.add(btnCancel, null);
    jPanel1.add(btnOk, null);
    this.getContentPane().add(jPanel2, BorderLayout.CENTER);

    model = new DefaultTableModel(null, columnNames) {

		private static final long serialVersionUID = 1L;

		@Override public Class<?> getColumnClass(int column) {
            return getValueAt(0, column).getClass();
        }
    };


    tblService = new JTable(model) {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		@Override public void updateUI() {
            super.updateUI();
            //XXX: Nimbus
            TableCellRenderer r = getDefaultRenderer(Boolean.class);
            if(r instanceof JComponent) {
                ((JComponent)r).updateUI();
            }
        }
        @Override public Component prepareEditor(TableCellEditor editor, int row, int column) {
            Component c = super.prepareEditor(editor, row, column);
            if(c instanceof JCheckBox) {
                JCheckBox b = (JCheckBox)c;
                b.setBackground(getSelectionBackground());
                b.setBorderPainted(true);
            }
            return c;
        }
    };



    jPanel2.add(tblService, BorderLayout.CENTER);
    btnCancel.addActionListener(new ServiceChoose_btnCancel_actionAdapter(this));
    btnOk.addActionListener(new ServiceChoose_btnOk_actionAdapter(this));
  }

  void btnOk_actionPerformed(ActionEvent e) {
	  int rowcount = tblService.getModel().getRowCount();
	  int j=0;
	  double total = 0;
	  for(int i=0; i<rowcount; i++){
		  if(tblService.getModel().getValueAt(i, 0).toString() == "true"){
			  int sevID = (Integer) tblService.getModel().getValueAt(i, 1);
			  if(isRes){
				  ((ReservationForm)rf).tblModelService.insertRow(j++, new Object[]{sevID, tblService.getModel().getValueAt(i, 2), tblService.getModel().getValueAt(i, 3)});
				  total += Double.parseDouble(tblService.getModel().getValueAt(i, 3)+ "");
		  
			  }else{
				  if(!((AddRoomServiceForm)rf).avail.contains(sevID) && !((AddRoomServiceForm)rf).newAdd.contains(sevID)){
					  ((AddRoomServiceForm)rf).tblModelAvailable.insertRow(j++, new Object[]{sevID, tblService.getModel().getValueAt(i, 2), tblService.getModel().getValueAt(i, 3)});
					  ((AddRoomServiceForm)rf).newAdd.add(sevID);
				  }
			  }
		  }
		  
	  }
	  if(isRes){
		  double totalcost = Double.parseDouble(((ReservationForm)rf).txtTotalCost.getText())+ total;
		  ((ReservationForm)rf).txtTotalCost.setText(totalcost + "");
	  }
	  this.setVisible(false);
  }

  void btnCancel_actionPerformed(ActionEvent e) {
	  this.setVisible(false);
  }

}

class ServiceChoose_btnOk_actionAdapter implements java.awt.event.ActionListener {
  ServiceChoose adaptee;

  ServiceChoose_btnOk_actionAdapter(ServiceChoose adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnOk_actionPerformed(e);
  }
}


class ServiceChoose_btnCancel_actionAdapter implements java.awt.event.ActionListener {
  ServiceChoose adaptee;

  ServiceChoose_btnCancel_actionAdapter(ServiceChoose adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancel_actionPerformed(e);
  }
}

class HeaderRenderer extends JCheckBox implements TableCellRenderer {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JLabel label = new JLabel("Check All");
    private int targetColumnIndex;
    public HeaderRenderer(JTableHeader header, int index) {
        super((String)null);
        this.targetColumnIndex = index;
        setOpaque(false);
        setFont(header.getFont());
        header.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                JTableHeader header = (JTableHeader)e.getSource();
                JTable table = header.getTable();
                TableColumnModel columnModel = table.getColumnModel();
                int vci = columnModel.getColumnIndexAtX(e.getX());
                int mci = table.convertColumnIndexToModel(vci);
                if(mci == targetColumnIndex) {
                    TableColumn column = columnModel.getColumn(vci);
                    Object v = column.getHeaderValue();
                    boolean b = Status.DESELECTED.equals(v)?true:false;
                    TableModel m = table.getModel();
                    for(int i=0; i<m.getRowCount(); i++) m.setValueAt(b, i, mci);
                    column.setHeaderValue(b?Status.SELECTED:Status.DESELECTED);
                    //header.repaint();
                }
            }
        });
    }
    @Override public Component getTableCellRendererComponent(JTable tbl, Object val, boolean isS, boolean hasF, int row, int col) {
        TableCellRenderer r = tbl.getTableHeader().getDefaultRenderer();
        JLabel l =(JLabel)r.getTableCellRendererComponent(tbl, val, isS, hasF, row, col);
        if(targetColumnIndex==tbl.convertColumnIndexToModel(col)) {
            if(val instanceof Status) {
                switch((Status)val) {
                  case SELECTED:      setSelected(true);  setEnabled(true);  break;
                  case DESELECTED:    setSelected(false); setEnabled(true);  break;
                  case INDETERMINATE: setSelected(true);  setEnabled(false); break;
                }
            }else{
                setSelected(true); setEnabled(false);
            }
            label.setIcon(new ComponentIcon(this));
            l.setIcon(new ComponentIcon(label));
            l.setText(null); //XXX: Nimbus???
        //    l.setHorizontalAlignment(SwingConstants.CENTER);
        //}else{
        //    l.setHorizontalAlignment(SwingConstants.LEFT);
        }

//         System.out.println("getHeaderRect: " + tbl.getTableHeader().getHeaderRect(col));
//         System.out.println("getPreferredSize: " + l.getPreferredSize());
//         System.out.println("getMaximunSize: " + l.getMaximumSize());
//         System.out.println("----");
//         if(l.getPreferredSize().height>1000) { //XXX: Nimbus???
//             System.out.println(l.getPreferredSize().height);
//             Rectangle rect = tbl.getTableHeader().getHeaderRect(col);
//             l.setPreferredSize(new Dimension(0, rect.height));
//         }
        return l;
    }
//     @Override public void updateUI() {
//         setText(null); //XXX: Nimbus???
//         super.updateUI();
//     }
}

class HeaderCheckBoxHandler implements TableModelListener{
    private final JTable table;
    private final int targetColumnIndex;
    public HeaderCheckBoxHandler(JTable table, int index) {
        this.table = table;
        this.targetColumnIndex = index;
    }
    @Override public void tableChanged(TableModelEvent e) {
        if(e.getType()==TableModelEvent.UPDATE && e.getColumn()==targetColumnIndex) {
            int vci = table.convertColumnIndexToView(targetColumnIndex);
            TableColumn column = table.getColumnModel().getColumn(vci);
            if(!Status.INDETERMINATE.equals(column.getHeaderValue())) {
                column.setHeaderValue(Status.INDETERMINATE);
            }else{
                boolean selected = true, deselected = true;
                TableModel m = table.getModel();
                for(int i=0; i<m.getRowCount(); i++) {
                    Boolean b = (Boolean)m.getValueAt(i, targetColumnIndex);
                    selected &= b; deselected &= !b;
                    if(selected==deselected) return;
                }
                if(selected) {
                    column.setHeaderValue(Status.SELECTED);
                }else if(deselected) {
                    column.setHeaderValue(Status.DESELECTED);
                }else{
                    return;
                }
            }
            JTableHeader h = table.getTableHeader();
            h.repaint(h.getHeaderRect(vci));
        }
    }
}
enum Status { SELECTED, DESELECTED, INDETERMINATE }

class ComponentIcon implements Icon{
    private final JComponent cmp;
    public ComponentIcon(JComponent cmp) {
        this.cmp = cmp;
    }
    @Override public int getIconWidth() {
        return cmp.getPreferredSize().width;
    }
    @Override public int getIconHeight() {
        return cmp.getPreferredSize().height;
    }
    @Override public void paintIcon(Component c, Graphics g, int x, int y) {
        SwingUtilities.paintComponent(g, cmp, (Container)c, x, y, getIconWidth(), getIconHeight());
    }
}
