package Contacts.appClasses;

import java.util.Locale;
import java.util.logging.Logger;

import Contacts.ServiceLocator;
import Contacts.abstractClasses.View;
import Contacts.commonClasses.Translator;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This code
 * is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * @author Brad Richards
 */
public class App_View extends View<App_Model> {
	
	protected Button newButton, groupButton, saveAndUpdateButton, deleteButton, editButtton, homeButton;
	
	protected Label lbHeadingContact, lbHeadingNewContact, lbHeadingGroup,
			lbVName, lbNName, lbNumber, lbEmail, lbBirthDate, lbNotizen, lbGroup;
	
	//Images für Buttons
	protected final Image ICONGROUP = new Image("/Contacts/appClasses/group.png");
	protected final Image ICONADD = new Image("/Contacts/appClasses/add_contact.png");
	protected final Image ICONSAVE = new Image("/Contacts/appClasses/tick.png");
	protected final Image ICONHOME = new Image("/Contacts/appClasses/back.png");
	
	protected ImageView iconGroup, iconAdd, iconSave, iconHome;
   
	protected ChoiceBox cbGroup;
	
	//Choicebox für die Sprachauswahl
	//protected  ChoiceBox<String> cbLanguage = new ChoiceBox();
	
	
	protected TextField txtVName, txtNName, txtNumber, txtEmail;
	
	protected TextArea txtaNotizen;
	
	protected DatePicker birthDate;
	
	protected ListView contactList;
	
	protected BorderPane root, contactView, groupView;
	
	protected ToolBar toolbarMain, toolbarContact, toolbarGroup;
	
	//Menu für Sprachauswahl
	//protected Menu menuFile, menuFileLanguage, menuHelp;


	public App_View(Stage stage, App_Model model) {
        super(stage, model);
        ServiceLocator.getServiceLocator().getLogger().info("Application view initialized");
    }

	@Override
	protected Scene create_GUI() {
	    ServiceLocator sl = ServiceLocator.getServiceLocator();  
	    Logger logger = sl.getLogger();
	    
	    
	  //HomeView

	    this.root = new BorderPane();
	    
	   this.toolbarMain = new ToolBar();
	    
	    /*
	    MenuBar menuBar = new MenuBar();
	    menuFile = new Menu();
	    menuFileLanguage = new Menu();
	    menuFile.getItems().add(menuFileLanguage);
	    */   

	    this.lbHeadingContact = new Label();
	    this.lbHeadingContact.getStyleClass().add("lbHeadingContact");
	    
	   
	    this.newButton = new Button();
	    this.newButton.getStyleClass().add("newButton");
	    
	    /*
	     * Button wird mit Bild ergänzt
	    this.iconAdd = new ImageView(ICONADD);
	    this.newButton.setGraphic(this.iconAdd);
	   	 */   
	    
	    this.groupButton = new Button();
	    this.groupButton.getStyleClass().add("groupButton");
	    
	    /*
	     * Button wird mit Bild ergänzt
	    this.iconGroup = new ImageView(this.ICONGROUP);
	    this.groupButton.setGraphic(this.iconGroup);
	    */
	    
	    
	    this.contactList = new ListView();
	    this.contactList.getStyleClass().add("center");
	    
	    //Muss durch Arraylist ersetzt werden
	    this.contactList.getItems().add("item");
	    this.contactList.getItems().add("item");
	    this.contactList.getItems().add("item");
	    this.contactList.getItems().add("item");
	    this.contactList.getItems().add("item");
	    this.contactList.getItems().add("item");

	   
		 /*   
		 //Sprachauswahl
	     for (Locale locale : sl.getLocales()) {
	    	 	MenuItem language = new MenuItem(locale.getLanguage());
	    	 	menuFileLanguage.getItems().add(language);
	    	 	language.setOnAction( event -> {
					sl.getConfiguration().setLocalOption("Language", locale.getLanguage());
	                sl.setTranslator(new Translator(locale.getLanguage()));
	                updateTexts();
	            });
	        }*/
	     
		   // verbraucht den Platz vor dem rechten Element 
		   HBox spacer = new HBox();                
		   HBox.setHgrow(spacer, Priority.ALWAYS);
		    
		   this.toolbarMain.getItems().addAll(this.lbHeadingContact, spacer, this.groupButton, this.newButton);
	
		   this.root.setTop(this.toolbarMain);
	
		   this.root.setCenter(this.contactList);


	   
	   //View für neuen Kontakt oder Kontakt anzeigen
	   
		   this.contactView = new BorderPane();
		   
		   GridPane contactCenter = new GridPane();
		   contactCenter.getStyleClass().add("contactForm");
		   
		   this.toolbarContact = new ToolBar();
		   
		   this.homeButton = new Button();
		   this.homeButton.getStyleClass().add("homeButton");
		   this.saveAndUpdateButton = new Button();
		   this.saveAndUpdateButton.getStyleClass().add("saveButton");
		   
		   this.lbHeadingNewContact = new Label();
		   this.lbHeadingNewContact.getStyleClass().add("lbHeadingContact");
		   
		   
		   //Labels für das Formular
		   this.lbNName = new Label();
		   this.lbVName = new Label();
		   this.lbBirthDate = new Label();
		   this.lbEmail = new Label();
		   this.lbGroup = new Label();
		   this.lbNumber = new Label();
		   this.lbNotizen = new Label();
		   
		   //CSS Verküpfung Label
		   
		   this.lbNName.getStyleClass().add("lbContactForm");
		   this.lbVName.getStyleClass().add("lbContactForm");
		   this.lbBirthDate.getStyleClass().add("lbContactForm");
		   this.lbEmail.getStyleClass().add("lbContactForm");
		   this.lbGroup.getStyleClass().add("lbContactForm");
		   this.lbNumber.getStyleClass().add("lbContactForm");
		   this.lbNotizen.getStyleClass().add("lbContactForm");
		   
		   //Textfelder für das Formular
		   this.txtaNotizen = new TextArea();
		   this.txtEmail = new TextField();
		   this.txtNName = new TextField();
		   this.txtNumber = new TextField();
		   this.txtVName = new TextField();
		   this.cbGroup = new ChoiceBox();
		   this.birthDate = new DatePicker();
		   
		   
		   //CSS Verküpfung TextFields
		   this.txtaNotizen.getStyleClass().add("txtNotizen");
		   this.txtEmail.getStyleClass().add("txtContactForm");
		   this.txtNName.getStyleClass().add("txtContactForm");
		   this.txtNumber.getStyleClass().add("txtContactForm");
		   this.txtVName.getStyleClass().add("txtContactForm");
		   this.cbGroup.getStyleClass().add("txtContactForm");
		   this.birthDate.getStyleClass().add("txtBirthDate");
		   
		   
		   HBox spacer2 = new HBox();                
		   HBox.setHgrow(spacer2, Priority.ALWAYS);
		   
		   
		   this.toolbarContact.getItems().addAll(this.homeButton, spacer, 
				   this.lbHeadingNewContact, spacer2, this.saveAndUpdateButton);
		   
		   contactCenter.add(this.lbVName, 0, 0);
		   contactCenter.add(this.txtVName, 1, 0);
		   contactCenter.add(this.lbNName, 0, 1);
		   contactCenter.add(this.txtNName, 1, 1);
		   contactCenter.add(this.lbNumber, 0, 2);
		   contactCenter.add(this.txtNumber, 1, 2);
		   contactCenter.add(this.lbBirthDate, 0, 3);
		   contactCenter.add(this.birthDate, 1, 3);
		   contactCenter.add(this.lbEmail, 0, 4);
		   contactCenter.add(this.txtEmail, 1, 4);
		   contactCenter.add(this.lbGroup, 0, 5);
		   contactCenter.add(this.cbGroup, 1, 5);
		   contactCenter.add(this.lbNotizen, 0, 6);
		   contactCenter.add(this.txtaNotizen, 1, 6);
		   
		   
		   this.contactView.setTop(this.toolbarContact);
		   this.contactView.setCenter(contactCenter);

        
        updateTexts();
		
        Scene scene = new Scene(contactView, 450, 750);
        scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
        return scene;
        
        
	}
	
	
	
	   protected void updateTexts() {
	       Translator t = ServiceLocator.getServiceLocator().getTranslator();
	       
	     //toolbar Text
	       this.lbHeadingContact.setText(t.getString("program.toolbar.label.heading"));
	       this.groupButton.setText(t.getString("program.toolbar.button.group"));
	       this.newButton.setText(t.getString("program.toolbar.button.new"));
	       
	      //this.cbLanguage.setValue(t.getString("program.toolbar.choicebox.language"));
	      //this.menuFile.setText(t.getString("program.menu.file"));
	      //this.menuFileLanguage.setText(t.getString("program.menu.file.language"));
          //this.menuHelp.setText(t.getString("program.menu.help"));
	       
	       this.lbNName.setText(t.getString("programm.label.contact.nname"));
	       this.lbVName.setText(t.getString("program.label.contact.vname"));
	       this.lbNumber.setText(t.getString("program.label.contact.number"));
	       this.lbBirthDate.setText(t.getString("program.label.contact.birthdate"));
	       this.lbEmail.setText(t.getString("program.label.contact.email"));
	       this.lbNotizen.setText(t.getString("program.label.contact.notizen"));
	       this.lbGroup.setText(t.getString("program.label.contact.group"));
	       this.lbHeadingNewContact.setText(t.getString("program.label.contact.heading"));
	       this.cbGroup.setValue(t.getString("program.label.contact.choiceBox"));
	       
           
           stage.setTitle(t.getString("program.name"));
	    }
}