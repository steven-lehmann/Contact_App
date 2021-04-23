package Contacts.appClasses;



import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import Contacts.ServiceLocator;
import Contacts.abstractClasses.Controller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;

/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * @author Brad Richards
 */
public class App_Controller extends Controller<App_Model, App_View> {
    ServiceLocator serviceLocator;
    
    private static int INDEX = 0;

    public App_Controller(App_Model model, App_View view) {
        super(model, view);
     // register ourselves to listen for button clicks
      /*  view.btnClick.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                buttonClick();
            }
        });*/

        // register ourselves to handle window-closing event
        view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
            }
        });
        
        serviceLocator = ServiceLocator.getServiceLocator();        
        serviceLocator.getLogger().info("Application controller initialized");
        
        view.validateMailButton.setOnAction(this::validateMail);
        
        view.contactList.setOnMouseClicked(this::updateContact);
        
        view.homeButton.setOnAction(this::updateHome);
        
        view.newButton.setOnAction(this::newContact);
        
        view.saveAndUpdateButton.setOnAction(arg0 -> {
			try {
				saveNewContact(arg0);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
        
        view.deleteButton.setOnAction(this::delete);
        
        view.searchButton.setOnAction(this::search);
        
        view.updateButton.setOnAction(arg0 -> {
			try {
				refreshContact(arg0);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
        
        view.editButtton.setOnAction(this::editContact);
        
        view.groupButton.setOnAction(this::showGroup);
        
        view.searchGroupButton.setOnAction(this::searchGroup);
        
        view.groupList.setOnMouseClicked(this::updateContactGroupVie);
        
        view.addTfNumButton.setOnAction(this::addTfNum);
        
        view.addTfMailButton.setOnAction(this::addTfMail);
     
    }
    
    private void validateMail(Event e) {
    	for(int i = 0; i < App_View.INDEXE; i++) {
			String arrayMail = view.tfMailArray[i].getText();
			INDEX = i;
			this.validateEMail(arrayMail);
		}
    }
  
    protected void validateEMail(String newValue) {
    	boolean valid = false;

		// Split on '@': must give us two not-empty parts
		String[] addressParts = newValue.split("@");
		if (addressParts.length == 2 && !addressParts[0].isEmpty() && !addressParts[1].isEmpty()) {
			// We want to split the domain on '.', but split does not give us an empty
			// string, if the split-character is the last character in the string. So we
			// first ensure that the string does not end with '.'
			if (addressParts[1].charAt(addressParts[1].length() - 1) != '.') {
				// Split domain on '.': must give us at least two parts.
				// Each part must be at least two characters long
				String[] domainParts = addressParts[1].split("\\.");
				if (domainParts.length >= 2) {
					valid = true;
					for (String s : domainParts) {
						if (s.length() < 2) valid = false;
					}
				}
			}
		}
		
		
		view.tfMailArray[INDEX].getStyleClass().remove("emailNotOk");
		view.tfMailArray[INDEX].getStyleClass().remove("emailOk");
		if (valid) {
			view.tfMailArray[INDEX].getStyleClass().add("emailOk");
		} else {
			view.tfMailArray[INDEX].getStyleClass().add("emailNotOk");
		}
		
	}
    
    private void showGroup(Event e) {
    	view.changeGroupView();
    }
    

	private void updateContact(MouseEvent mouseevent1) {
		view.changeContactView();
		view.disableTextField();
		
		view.saveAndUpdateButton.setDisable(true);
		view.saveAndUpdateButton.setVisible(false);
		view.saveAndUpdateButton.setManaged(false);
		view.updateButton.setDisable(true);
		view.editButtton.setDisable(false);
		view.deleteButton.setDisable(false);
		view.deleteButton.setVisible(true);
		view.editButtton.setVisible(true);
		view.updateButton.setVisible(true);
		Contact contact = view.contactList.getSelectionModel().getSelectedItem();
		this.updateView(contact);
	}

	private void updateContactGroupVie(MouseEvent mouseevent1) {
		view.changeContactView();
		view.disableTextField();
		view.saveAndUpdateButton.setDisable(true);
		view.saveAndUpdateButton.setVisible(false);
		view.saveAndUpdateButton.setManaged(false);
		view.updateButton.setDisable(true);
		view.editButtton.setDisable(false);
		view.deleteButton.setDisable(false);
		view.deleteButton.setVisible(true);
		view.editButtton.setVisible(true);
		view.updateButton.setVisible(true);
		Contact contact = view.groupList.getSelectionModel().getSelectedItem();
		this.updateView(contact);
	}


	private void updateView(Contact contact) {
		if (contact != null) {
			view.txtVName.setText(contact.getvName());
			view.txtNName.setText(contact.getnName());
			
			ArrayList<String> mails = contact.geteMails();
			for(String e : mails) {
				view.addTfMailView2(e);
			}
			
			ArrayList<Integer> numbers = contact.getPhoneNumbers();
			for(int n : numbers) {
				view.addTfNumView2(n);
			}

			view.cbGroup.setValue(contact.getGroup().name());
			String birthday = model.formatter.format(contact.getBirthday());
			LocalDate date = LocalDate.parse(birthday, model.LocalFormatter);
			view.birthDate.setValue(date);
			view.txtID.setText(Integer.toString(contact.getID()));
		} else {
			view.txtVName.setText("");
			view.txtNName.setText("");
			view.txtNumber.setText("");
			view.txtEmail.setText("");
			view.cbGroup.setValue(null);
			view.birthDate.setValue(LocalDate.now());
			view.txtID.setText("");
		}
		
	}



	private void updateHome(Event e) {
		view.backHome();
		view.updateTfNum();
		view.updateTfMail();
		this.updateListView();
		view.txtSearch.clear();
	}
	
	private void newContact(Event e) {
		view.changeContactView();
		view.enableTextField();
		view.saveAndUpdateButton.setDisable(false);
		view.saveAndUpdateButton.setVisible(true);
		view.saveAndUpdateButton.setManaged(true);
		view.deleteButton.setVisible(false);
		view.editButtton.setVisible(false);
		view.updateButton.setVisible(false);
		view.txtID.setDisable(true);
		this.updateView(null);
	}
	
	private void saveNewContact(Event e) throws ParseException {
		String nName = view.txtNName.getText();
		String vName = view.txtVName.getText();
		
		ArrayList<String> eMails = new ArrayList<String>();
		
		for(int i = 0; i < App_View.INDEXE; i++) {
			String arrayMail = view.tfMailArray[i].getText();
			eMails.add(arrayMail);
		}
	
		String stringGroup = view.cbGroup.getSelectionModel().getSelectedItem();
		Group group = Group.valueOf(stringGroup);
		
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		
		for(int i = 0; i < App_View.INDEXN; i++) {
			String arrayNumber = view.tfNumArray[i].getText();
			numbers.add(Integer.parseInt(arrayNumber));
		}
	
		
		LocalDate date = view.birthDate.getValue();
		String dateString = date.format(model.LocalFormatter);
		Date birthday = model.formatter.parse(dateString);
		
		Contact contact = model.creatContact(vName, nName, eMails, group, birthday, numbers);
		view.contactList.getItems().add(contact);
		view.backHome();
		view.updateTfNum();
		view.updateTfMail();
		model.saveContact();
	}
	
	private void delete(Event e) {
		String name = view.txtNName.getText();
		Contact contact = model.getSelectedContact(name);
		model.treeContactList.remove(contact);
		model.saveContact();
		this.updateListView();
		this.updateView(null);
		view.backHome();
	}
	
	private void updateListView() {
		view.contactList.getItems().clear();
		for(Contact c : model.treeContactList) {
		   	view.contactList.getItems().add(c);
		    }
	}
	
	private void search(Event e) {
		String name = view.txtSearch.getText();
		Contact contact = model.getSelectedContact(name);
		view.contactList.getItems().clear();
		view.contactList.getItems().add(contact);
	}
	
	public void searchGroup(Event e) {
		Group group = view.cbGroup2.getValue();
		ArrayList<Contact> arrayGroup = model.getSelectedGroup(group);
		view.groupList.getItems().clear();
		for(Contact c : arrayGroup) {
			view.groupList.getItems().add(c);
		}
	}
	
	
	
	private void refreshContact(Event e) throws ParseException {
		String ID = view.txtID.getText();
		int contactID = Integer.parseInt(ID);
		Contact contact = model.getSelectedContacdID(contactID);
		contact.setvName(view.txtVName.getText());
		contact.setnName(view.txtNName.getText());
		contact.setGroup(Group.valueOf(view.cbGroup.getValue()));
		
		ArrayList<String> eMails = new ArrayList<String>();
		
			for(int i = 0; i < App_View.INDEXE; i++) {
				String arrayMail = view.tfMailArray[i].getText();
				eMails.add(arrayMail);
			}
			
		contact.seteMails(eMails);
		
		ArrayList<Integer> numbers = new ArrayList<Integer>();
			
			for(int i = 0; i < App_View.INDEXN; i++) {
				String arrayNumber = view.tfNumArray[i].getText();
				numbers.add(Integer.parseInt(arrayNumber));
			}
			
		contact.setPhoneNumbers(numbers);
		
		LocalDate date = view.birthDate.getValue();
		String dateString = date.format(model.LocalFormatter);
		Date birthday = model.formatter.parse(dateString);
		contact.setBirthday(birthday);
		view.disableTextField();
		view.updateButton.setDisable(true);
		model.saveContact();
	}
	
	private void editContact(Event e) {
		view.updateButton.setDisable(false);
		view.enableTextField();
	}
	
	private void addTfNum(Event e) {
		view.addTfNumView();
	}
	
	private void addTfMail(Event e) {
		view.addTfMailView();
	}
	
	
}
