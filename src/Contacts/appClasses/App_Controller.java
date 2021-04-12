package Contacts.appClasses;

import Contacts.ServiceLocator;
import Contacts.abstractClasses.Controller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;

/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * @author Brad Richards
 */
public class App_Controller extends Controller<App_Model, App_View> {
    ServiceLocator serviceLocator;

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
        
        /*view.contactList.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				view.changeContactView();
				Contact contact = view.contactList.getSelectionModel().getSelectedItem();
        		this.updateView(contact);
				
            	System.out.println("clicked on " + view.contactList.getSelectionModel().getSelectedItem());
				
			}
			 private void updateView(Contact contact) {
					// Auto-generated method stub
					
				}

        });*/
        
        view.contactList.setOnMouseClicked(this::updateContact);
        
        view.homeButton.setOnAction(this::updateHome);
        
        view.newButton.setOnAction(this::newContact);
        
        view.saveAndUpdateButton.setOnAction(this::saveNewContact);
        
        view.deleteButton.setOnAction(this::delete);
     
    }
    
    
    
    public void buttonClick() {
        model.incrementValue();
        String newText = Integer.toString(model.getValue());        

       // view.lblNumber.setText(newText);        
    }
    

	private void updateContact(MouseEvent mouseevent1) {
		view.changeContactView();
		Contact contact = view.contactList.getSelectionModel().getSelectedItem();
		this.updateView(contact);
	}


	private void updateView(Contact contact) {
		if (contact != null) {
			view.txtVName.setText(contact.getvName());
			view.txtNName.setText(contact.getnName());
			view.txtNumber.setText(Integer.toString(contact.getPhoneNumber()));
			view.txtEmail.setText(contact.geteMail());
		} else {
			view.txtVName.setText("");
			view.txtNName.setText("");
			view.txtNumber.setText("");
			view.txtEmail.setText("");
		}
		
	}



	private void updateHome(Event e) {
		view.backHome();
	}
	
	private void newContact(Event e) {
		view.changeContactView();
		this.updateView(null);
	}
	
	private void saveNewContact(Event e) {
		String nName = view.txtNName.getText();
		String vName = view.txtVName.getText();
		String eMail = view.txtEmail.getText();
		String phoneNum = view.txtNumber.getText();
		int phoneNumber = Integer.parseInt(phoneNum);
		Contact contact = model.creatContact(vName, nName, eMail, phoneNumber);
		view.contactList.getItems().add(contact);
		model.saveContact();
	}
	
	private void delete(Event e) {
		String name = view.txtNName.getText();
		Contact contact = model.getSelectedContact(name);
		model.treeContactList.remove(contact);
		model.saveContact();
		this.updateListView();
		this.updateView(null);
		
	}
	
	private void updateListView() {
		view.contactList.getItems().clear();
		for(Contact c : model.treeContactList) {
		   	view.contactList.getItems().add(c);
		    }
	}

}
