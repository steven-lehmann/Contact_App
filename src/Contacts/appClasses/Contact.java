package Contacts.appClasses;

import java.util.ArrayList;
import java.util.Date;

public class Contact implements Comparable <Contact>{
	

	
	private static int IDNr = 0; 
	private String vName;
	private String nName; 
	private ArrayList<String> eMails;
	private Date birthday;
	private Group group;
	private ArrayList<Integer> phoneNumbers;
	private final int ID;
	
	private static int getNextID() {
		return ++IDNr;
	}
	
	public Contact(String vName, String nName, ArrayList<String> eMails, Group group, Date birthday, ArrayList<Integer> phoneNumbers) {
		this.ID = getNextID();
		this.vName = vName;
		this.nName = nName;
		this.eMails = eMails;
		this.group = group;
		this.birthday = birthday;
		this.phoneNumbers = phoneNumbers;
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
	
	public ArrayList<String> geteMails() {
		return eMails;
	}

	public void seteMails(ArrayList<String> eMails) {
		this.eMails = eMails;
	}

	public ArrayList<Integer> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(ArrayList<Integer> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public int getID() {
		return this.ID;
	}

	@Override
	public String toString() {
		return nName + " " + vName;
	}

	@Override
	public int compareTo(Contact o) {
		int compValue = this.getnName().compareTo(o.getnName());
		if(compValue  == 0)
		return 0;
		else
			if (compValue < 0)
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

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}
