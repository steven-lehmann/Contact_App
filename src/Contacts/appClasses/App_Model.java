package Contacts.appClasses;

import java.util.ArrayList;

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
	
	ServiceLocator serviceLocator;
	private static ArrayList<Contact> myContacts;
    private int value;
    
    public App_Model() {
        value = 0;
        
        serviceLocator = ServiceLocator.getServiceLocator();        
        serviceLocator.getLogger().info("Application model initialized");
    }
    
    public int getValue() {
        return value;
    }
    
    public int incrementValue() {
        value++;
        serviceLocator.getLogger().info("Application model: value incremented to " + value);
        return value;
    }
}
