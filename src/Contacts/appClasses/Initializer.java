package Contacts.appClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Initializer {
	
	private final static String pathName = ("src/Contacts/contacts.txt");
	
	public static ArrayList<Contact> readplayerFile() throws FileNotFoundException{
		ArrayList<Contact> contactList = new ArrayList<Contact>();
		Scanner fileReader = new Scanner (new File(pathName));
		while(fileReader.hasNext()) {
			String line = fileReader.nextLine();
			Contact contact = readLine(line);
			contactList.add(contact);
		}
		return contactList;
		
	}

	private static Contact readLine(String line) {
		Scanner lineScanner = new Scanner(line);
		lineScanner.useDelimiter(";");
		String vName = lineScanner.next();
		String nName = lineScanner.next();
		String eMail = lineScanner.next();
		int phoneNumber = lineScanner.nextInt();
		return new Contact(vName, nName, eMail, phoneNumber);
	}

}
