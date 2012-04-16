package business;

public class KeyValue {
	String key, value;

	public KeyValue(String key, String value){
	this.key=key;
	this.value=value;
}
	
	public String getKey(){
		return key;
	}
	public String getValue() {
		return value;
	}
	public String toString(){ return value;}
	
}
