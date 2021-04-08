package Contacts.appClasses;

public class Contact implements Comparable <Contact>{
	
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
	
	public int getID() {
		return this.ID;
	}

	@Override
	public String toString() {
		return "Contact [vName=" + vName + ", nName=" + nName + ", eMail=" + eMail + ", phoneNumber=" + phoneNumber
				+ ", ID=" + ID + "]";
	}

	@Override
	public int compareTo(Contact o) {
		if(this.ID == o.getID())
		return 0;
		else 
			if(this.ID < o.ID)
				return -1;
			else
				return 1;
	}
	public boolean equals(Contact o) {
		if(this.ID == o.getID())
		return true;
		else
			return false;
	}

	

}
