package core.business;

import connect.sqlite.IConnectData;

public class InvoiceManager {
	private IConnectData conn;
	
	public InvoiceManager(IConnectData conn){
		this.conn = conn;
		
	}
	public boolean addNewInvoice(Customer customer) throws NullPointerException{
		if (customer == null){	
			conn.ExcuteQuery("zx");
			throw new NullPointerException();
		}
		return false;
	}
}
