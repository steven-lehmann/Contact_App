package Contacts.appClasses;

import java.util.ArrayList;
import java.util.Date;

public class Contact implements Comparable <Contact>{
	

	
	private static int IDNr = 0; 
	private String vName;
	private String nName; 
	private ArrayList<String> eMails;
	private Date birthday;
	private int groupIndex;
	private ArrayList<Integer> phoneNumbers;
	private final int ID;
	private String notes;
	
	private static int getNextID() {
		return ++IDNr;
	}
	
	public Contact(String vName, String nName, ArrayList<String> eMails, int groupIndex, Date birthday, 
			ArrayList<Integer> phoneNumbers, String notes) {
		this.ID = getNextID();
		this.vName = vName;
		this.nName = nName;
		this.eMails = eMails;
		this.groupIndex = groupIndex;
		this.birthday = birthday;
		this.phoneNumbers = phoneNumbers;
		this.notes = notes;
		
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

	public int getGroup() {
		return groupIndex;
	}

	public void setGroup(int groupIndex) {
		this.groupIndex = groupIndex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}
