package Contacts.appClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;

import Contacts.ServiceLocator;
import Contacts.abstractClasses.Model;

/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * @author Brad Richards
 */
public class App_Model extends Model {
	private static String CONTACT_FILE = "contacts.txt";
	private static String SEPARATOR = ";"; // Separator for "split"
	private static String SEPARATOR2 = ",";
	protected SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
	protected DateTimeFormatter LocalFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");


	ServiceLocator serviceLocator;
	protected TreeSet<Contact> treeContactList = new TreeSet<Contact>();


	private int value;

	public App_Model() {
		value = 0;
		serviceLocator = ServiceLocator.getServiceLocator();        
		serviceLocator.getLogger().info("Application model initialized");

	}

	public void readFile() {
		File contactFile = new File(CONTACT_FILE);
		try(Reader inReader = new FileReader(contactFile)) {
			BufferedReader fileIn = new BufferedReader(inReader);
			String line = fileIn.readLine(); // Zeile lesen
			while(line != null) {
				Contact contact = readContact(line);
				treeContactList.add(contact);
				line = fileIn.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Contact readContact(String line) throws ParseException {
		String [] attributes = line.split(SEPARATOR);
		String vName = attributes[0];
		String nName = attributes[1];
		String eMail = attributes[2];
		String[] eMails = eMail.split(SEPARATOR2);
		ArrayList<String> alEmails = this.readMailsArray(eMails);
		int groupIndex = Integer.parseInt(attributes[3]);
		String date = attributes[4];
		Date birthday = formatter.parse(date);
		String number = attributes[5];
		String[] numbers = number.split(SEPARATOR2);
		ArrayList<Integer> alNumbers = this.readArrayNum(numbers);
		String notes;
		if(attributes[6].isEmpty() != true ) {
		notes = attributes[6];
		}else
			notes = "notes...";
		Contact contact = new Contact(vName, nName, alEmails, groupIndex, birthday, alNumbers, notes);
		return contact;
	}

	private ArrayList<Integer> readArrayNum(String[] numbers) {
		ArrayList<Integer> alNumbers = new ArrayList<Integer>();
		try {
			alNumbers = new ArrayList<Integer>();
			for(int i = 0; i < numbers.length; i++) {
				alNumbers.add(Integer.parseInt(numbers[i]));
			}

		}catch(Exception e) {
		}

		return alNumbers;
	}

	private ArrayList<String> readMailsArray(String[] eMails) {
		ArrayList<String> alEmails = new ArrayList<String>();
		for(int i = 0; i < eMails.length; i++) {
			alEmails.add(eMails[i]);
		}
		return alEmails;
	}

	public void saveContact() {
		File contactFile = new File(CONTACT_FILE);
		try(Writer out = new FileWriter(contactFile)) {
			for(Contact contact : treeContactList) {
				String line = writeContact(contact);
				out.write(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String writeContact(Contact contact) {
		ArrayList<String> eMails = contact.geteMails();
		String concatEmails = "";
		for(String e : eMails) {
			concatEmails += e + ",";
		}

		ArrayList<Integer> numbers = contact.getPhoneNumbers();
		String concatPhoneNumbers = "";
		for(int n : numbers) {
			String tempString = Integer.toString(n);
			concatPhoneNumbers += tempString + ",";
		}

		String line = contact.getvName() + SEPARATOR + contact.getnName() + SEPARATOR
				+ concatEmails + SEPARATOR + contact.getGroup() + SEPARATOR 
				+ formatter.format(contact.getBirthday()) + SEPARATOR + concatPhoneNumbers + SEPARATOR + 
				contact.getNotes() + SEPARATOR + "\n";
		return line;
	}


	public Contact creatContact(String vName, String nName, ArrayList<String> eMails, int groupIndex, Date birthday, 
			ArrayList<Integer> numbers, String notes) {
		Contact contact = new Contact(vName, nName, eMails, groupIndex, birthday, numbers, notes);
		serviceLocator.getLogger().info("Create new Contact: " + contact);
		treeContactList.add(contact);
		return contact;

	}

	public Contact getSelectedContact(String name) {
		Contact contact = null;
		for(Contact c : treeContactList)
			if(c.getnName().contains(name)) {
				contact = c;
			} else {
				if(c.getvName().contains(name))
					contact = c;
			}
		return contact;
	}

	public Contact getSelectedContacdID(int id) {
		Contact contact = null;
		for(Contact c : treeContactList) {
			if(c.getID() == id) {
				contact = c;
			} 
		}
		return contact;
	}

	public ArrayList<Contact> getSelectedGroup(int group) {
		ArrayList<Contact> groupArrayList = new ArrayList<Contact>();
		for(Contact c : treeContactList)
			if(c.getGroup() == group) {
				groupArrayList.add(c);
			}
		return groupArrayList;
	}




}
