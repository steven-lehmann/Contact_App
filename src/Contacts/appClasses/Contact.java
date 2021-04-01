package Contacts.appClasses;

public class Contact {
	
	private static int IDNr = 0; 
	private String vName;
	private String nName; 
	private String eMail;
	private int phoneNumber;
	private final int ID;
	
	
	private static int getNextID() {
		return ++IDNr;
	}
	
	public Contact(String vName, String nName, String eMail, int phoneNumber) {
		this.ID = getNextID();
		this.vName = vName;
		this.nName = nName;
		this.eMail = eMail;
		this.phoneNumber = phoneNumber;
	}

	public String getvName() {
		return vName;
	}

	public void setvName(String vName) {
		this.vName = vName;
	}

	public String getnName() {
		return nName;
	}

	public void setnName(String nName) {
		this.nName = nName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Contact [vName=" + vName + ", nName=" + nName + ", eMail=" + eMail + ", phoneNumber=" + phoneNumber
				+ ", ID=" + ID + "]";
	}
	

	

}
