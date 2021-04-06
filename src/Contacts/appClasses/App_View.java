package Contacts.appClasses;

import java.util.Locale;
import java.util.logging.Logger;

import Contacts.ServiceLocator;
import Contacts.abstractClasses.View;
import Contacts.commonClasses.Translator;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
			lbVName, lbNName, lbNumber, lbEmail, lbBirthDate, lbNotizen, lbGroupe;
	
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
	
	protected ListView contactList;
	
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

	    BorderPane root = new BorderPane();
	    
	    ToolBar toolbar = new ToolBar();
	    
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
     
	    
	   HBox spacer = new HBox();                 // verbraucht den Platz vor dem rechten Element
	   HBox.setHgrow(spacer, Priority.ALWAYS);
	    
	   toolbar.getItems().addAll(this.lbHeadingContact, spacer, this.groupButton, this.newButton);

	   root.setTop(toolbar);


	   root.setCenter(this.contactList);




        
        updateTexts();
		
        Scene scene = new Scene(root, 450, 750);
        scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
        return scene;
        
        
	}
	
	
	
	   protected void updateTexts() {
	       Translator t = ServiceLocator.getServiceLocator().getTranslator();
	       
	     //toolbar Text
	       this.lbHeadingContact.setText(t.getString("program.toolbar.label.heading"));
	       this.groupButton.setText(t.getString("program.toolbar.button.group"));
	       this.newButton.setText(t.getString("program.toolbar.button.new"));
	      // this.cbLanguage.setValue(t.getString("program.toolbar.choicebox.language"));
	      // this.menuFile.setText(t.getString("program.menu.file"));
	      // this.menuFileLanguage.setText(t.getString("program.menu.file.language"));
          // this.menuHelp.setText(t.getString("program.menu.help"));
	        

           
           stage.setTitle(t.getString("program.name"));
	    }
}