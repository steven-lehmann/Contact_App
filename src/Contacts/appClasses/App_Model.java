package Contacts.appClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private Contact readContact(String line) { //File import
		String [] attributes = line.split(SEPARATOR);
		String vName = attributes[0];
		String nName = attributes[1];
		String eMail = attributes[2];
		int phoneNumber = Integer.parseInt(attributes[3]);
		Contact contact = new Contact(vName, nName, eMail, phoneNumber);
		return contact;
	}
    
    public void saveContact() { //save Contacts in File
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
		String line = contact.getvName() + SEPARATOR + contact.getnName() + SEPARATOR
				+ contact.geteMail() + SEPARATOR + contact.getPhoneNumber() + "\n";
		return line;
	}

	public int getValue() {
        return value;
    }
    
    public int incrementValue() {
        value++;
        serviceLocator.getLogger().info("Application model: value incremented to " + value);
        return value;
    }

	public Contact creatContact(String vName, String nName, String eMail, int phoneNumber) {
		Contact contact = new Contact(vName, nName, eMail, phoneNumber);
		treeContactList.add(contact);
		return contact;
	}

	/*public Contact getSelectedContact(String name) {
		Contact contact = new Contact();
		for(Contact c : treeContactList) {
			if(c.getnName().equals(name)) {
				contact = c;
			} 
		}
		return contact;
	}*/
}
