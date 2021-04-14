package Contacts.appClasses;

import java.util.Locale;
import java.util.logging.Logger;

import Contacts.ServiceLocator;
import Contacts.abstractClasses.View;
import Contacts.commonClasses.Translator;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.CheckBoxListCell;
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
	
	protected Button newButton, groupButton, saveAndUpdateButton, 
			deleteButton, editButtton, homeButton, homeButtonGroup, searchButton, updateButton;
	
	protected Label lbVName, lbNName, lbNumber, lbEmail, lbBirthDate, lbNotizen, lbGroup;
	
	//Images für Buttons
	
	
	protected static Image ICONGROUP = new Image("/group.png");
	protected static Image ICONADD = new Image("/add_contact.png");
	protected static Image ICONSAVE = new Image("/tick.png");
	protected static Image ICONHOME = new Image("/home.png");
	protected static Image ICONHOMEGROUP = new Image("/home.png");
	protected static Image ICONEDIT = new Image("/edit.png");
	protected static Image ICONDELETE = new Image("/delete_red.png");
	protected static Image ICONSEARCH = new Image("/search.png");
	protected static Image ICONUPDATE = new Image("/updateButton.png");
	
	
	protected ImageView iconGroup, iconAdd, iconSave, iconHome, iconHomeGroup,
			iconEdit, iconDelete, iconSearch, iconUpdateButton;
   
	protected ComboBox<String> cbGroup;
	
	protected MenuBar menuBar;
	
	protected  Menu menuFile, menuFileLanguage, menuHelp;
	
	protected TextField txtVName, txtNName, txtNumber, txtEmail, txtSearch, txtID;
	
	protected TextArea txtaNotizen;
	
	protected DatePicker birthDate;
	
	protected ListView<Contact> contactList;
	
	protected BorderPane root, contactView, groupView;
	
	protected HBox buttonBar, searchBar, bottomBar, saveBar, homeBar;
	
	protected VBox center, centerContact, centerGroup;
	
	protected Scene scene1, scene2, scene3;

	public App_View(Stage stage, App_Model model) {
        super(stage, model);
        ServiceLocator.getServiceLocator().getLogger().info("Application view initialized");
    }

	@Override
	protected Scene create_GUI() {
	    ServiceLocator sl = ServiceLocator.getServiceLocator();  
	    Logger logger = sl.getLogger();
	    
	    
	    //Menubar für Übersetzungsfunktion
	    this.menuBar = new MenuBar();
	    this.menuFile = new Menu();
	    this.menuFileLanguage = new Menu();
	    this.menuFile.getItems().add(menuFileLanguage);
	    
	    
	  //HomeView

	    this.root = new BorderPane();
	    
	  
	    this.contactList = new ListView<Contact>();
	    this.contactList.getStyleClass().add("contactList");
	    

	   //Daten für die Kontaktliste werden geladen 
	   for(Contact c : super.model.treeContactList) {
		   
		   
	   	this.contactList.getItems().add(c);
	    }
	   
		  
		 //Sprachauswahl
	     for (Locale locale : sl.getLocales()) {
	    	 MenuItem language = new MenuItem(locale.getLanguage());
	           menuFileLanguage.getItems().add(language);
	           language.setOnAction( event -> {
					sl.getConfiguration().setLocalOption("Language", locale.getLanguage());
	                sl.setTranslator(new Translator(locale.getLanguage()));
	                updateTexts();
	            });
	        }
	     
	     	this.menuBar.getMenus().addAll(menuFile);
	     	
  	   
		   
		   	this.newButton = new Button();
		    this.newButton.getStyleClass().add("newButton");
		    
		    
		     //Button wird mit Bild ergänzt
		    this.iconAdd = new ImageView(ICONADD);
		    this.newButton.setGraphic(this.iconAdd);
		    this.iconAdd.setFitHeight(30);
		    this.iconAdd.setFitWidth(30);
		   	   
		    
		    this.groupButton = new Button();
		    this.groupButton.getStyleClass().add("groupButton");
		    
		    
		    //Button wird mit Bild ergänzt
		    this.iconGroup = new ImageView(ICONGROUP);
		    this.groupButton.setGraphic(this.iconGroup);
		    this.iconGroup.setFitHeight(30);
		    this.iconGroup.setFitWidth(30);
		    
		    
		   //ButtonBar wird definiert 
		   this.buttonBar = new HBox();
		   this.buttonBar.getStyleClass().add("buttonBar");
		   
		   this.buttonBar.getChildren().addAll(this.groupButton, this.newButton);
		   
	
		   //Searchbar wird definiert
		   this.txtSearch = new TextField();
		   this.txtSearch.getStyleClass().add("txtSearch");
		   
		   
		   this.searchButton = new Button();
		   this.searchButton.getStyleClass().add("searchButton");
		   
		   //Button wird mit Bild ergänzt
		   this.iconSearch = new ImageView(ICONSEARCH);
		   this.searchButton.setGraphic(this.iconSearch);
		   this.iconSearch.setFitHeight(25);
		   this.iconSearch.setFitWidth(25);
		   
		   
		   this.searchBar = new HBox();
		   this.searchBar.getStyleClass().add("searchBar");
		   
		   this.searchBar.getChildren().addAll(this.txtSearch, this.searchButton);
		   HBox.setHgrow(this.txtSearch, Priority.ALWAYS);
		   
		   
		   //VBox Center wird gefüllt
		   this.center = new VBox();
		   this.center.getStyleClass().add("center");
		   this.center.getChildren().addAll(this.buttonBar, this.searchBar, this.contactList);
		   VBox.setVgrow(this.contactList, Priority.ALWAYS);
		   
		   this.root.setTop(this.menuBar);
		   this.root.setCenter(this.center);
		   
		   
	   
	   //View für neuen Kontakt oder Kontakt anzeigen
	   
		   this.contactView = new BorderPane();
		   
		   GridPane listCenter = new GridPane();
		   listCenter.getStyleClass().add("listCenter");
		   
		   
		   this.homeButton = new Button();
		   this.homeButton.getStyleClass().add("homeButton");
		   
		   //Button wird mit Bild ergänzt
		   this.iconHome = new ImageView(ICONHOME);
		   this.homeButton.setGraphic(this.iconHome);
		   this.iconHome.setFitHeight(30);
		   this.iconHome.setFitWidth(30);
		   
		   		   
		   this.saveAndUpdateButton = new Button();
		   this.saveAndUpdateButton.getStyleClass().add("saveButton");
		   
		   //Button wird mit Bild ergänzt
		   this.iconSave = new ImageView(ICONSAVE);
		   this.saveAndUpdateButton.setGraphic(this.iconSave);
		   this.iconSave.setFitHeight(30);
		   this.iconSave.setFitWidth(30);
		   
		   this.updateButton = new Button();
		   this.updateButton.getStyleClass().add("updateButton");
		   
		   //Button wird mit Bild ergänzt
		   this.iconUpdateButton = new ImageView(ICONUPDATE);
		   this.updateButton.setGraphic(this.iconUpdateButton);
		   this.iconUpdateButton.setFitHeight(30);
		   this.iconUpdateButton.setFitWidth(30);
		   
		   
		   this.editButtton = new Button();
		   this.editButtton.getStyleClass().add("editButton");
		   
		   //Button wird mit Bild ergänzt
		   this.iconEdit = new ImageView(ICONEDIT);
		   this.editButtton.setGraphic(this.iconEdit);
		   this.iconEdit.setFitHeight(30);
		   this.iconEdit.setFitWidth(30);
		   
		   
		   this.deleteButton = new Button();
		   this.deleteButton.getStyleClass().add("deleteButton");
		   
		   
		   //Button wird mit Bild ergänzt
		   this.iconDelete = new ImageView(ICONDELETE);
		   this.deleteButton.setGraphic(this.iconDelete);
		   this.iconDelete.setFitHeight(30);
		   this.iconDelete.setFitWidth(30);
		   
		   
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
		   this.txtID = new TextField();
		   
		   this.cbGroup = new ComboBox<String>();
		   this.cbGroup.setEditable(true);
		  
		   
		   this.birthDate = new DatePicker();
		   
		   
		   //CSS Verküpfung TextFields
		   this.txtaNotizen.getStyleClass().add("txtNotizen");
		   this.txtEmail.getStyleClass().add("txtContactForm");
		   this.txtNName.getStyleClass().add("txtContactForm");
		   this.txtNumber.getStyleClass().add("txtContactForm");
		   this.txtVName.getStyleClass().add("txtContactForm");
		   this.cbGroup.getStyleClass().add("txtContactForm");
		   this.birthDate.getStyleClass().add("txtBirthDate");
		
		   
		   
		   listCenter.add(this.lbVName, 0, 0);
		   listCenter.add(this.txtVName, 1, 0);
		   listCenter.add(this.lbNName, 0, 1);
		   listCenter.add(this.txtNName, 1, 1);
		   listCenter.add(this.lbNumber, 0, 2);
		   listCenter.add(this.txtNumber, 1, 2);
		   listCenter.add(this.lbBirthDate, 0, 3);
		   listCenter.add(this.birthDate, 1, 3);
		   listCenter.add(this.lbEmail, 0, 4);
		   listCenter.add(this.txtEmail, 1, 4);
		   listCenter.add(this.lbGroup, 0, 5);
		   listCenter.add(this.cbGroup, 1, 5);
		   listCenter.add(this.lbNotizen, 0, 6);
		   listCenter.add(this.txtaNotizen, 1, 6);
		   
		   
		   this.saveBar = new HBox();
		   this.saveBar.getStyleClass().add("saveBar");
		   
		   HBox spacer = new HBox();
		   HBox.setHgrow(spacer, Priority.ALWAYS);
		   
		   this.saveBar.getChildren().addAll(this.homeButton, spacer, this.updateButton, this.saveAndUpdateButton);
		   
		   
		   this.bottomBar = new HBox();
		   this.bottomBar.getChildren().addAll(this.deleteButton, this.txtID, this.editButtton);
		   // ID unsichtbar machen
		   this.txtID.setVisible(false);
		   this.bottomBar.getStyleClass().add("bottomBar");
		   
		   this.centerContact = new VBox();
		   this.centerContact.getStyleClass().add("centerContact");
		   VBox.setVgrow(listCenter, Priority.ALWAYS);
		   
		   
		   this.centerContact.getChildren().addAll(this.saveBar, listCenter);

		   //this.contactView.setTop(this.menuBar);
		   this.contactView.setCenter(this.centerContact);
		   this.contactView.setBottom(this.bottomBar);
		   
		   
	   //View Gruppe
		   
		   this.groupView = new BorderPane();

		   this.centerGroup = new VBox(); 
		   this.centerGroup.getStyleClass().add("centerGroup");
		   
		   this.homeButtonGroup = new Button();
		   this.homeButtonGroup.getStyleClass().add("homeButton");
		   
		   //Button wird mit Bild ergänzt
		   this.iconHomeGroup = new ImageView(ICONHOMEGROUP);
		   this.homeButtonGroup.setGraphic(this.iconHomeGroup);
		   this.iconHomeGroup.setFitHeight(30);
		   this.iconHomeGroup.setFitWidth(30);
		   
		   this.homeBar = new HBox();
		   this.homeBar.getStyleClass().add("homeBar");
		   this.homeBar.getChildren().add(this.homeButtonGroup);
		   
		  
		  	
		   
		  	/*	   
		  
		   ListView<String> listFam = new ListView<String>();
		   
		   listFam.getItems().add("items");
		   listFam.getItems().add("items");
		   listFam.getItems().add("items");
		   listFam.getItems().add("items");
		   
		   	listFam.setCellFactory(CheckBoxListCell.forListView(null));
		   
		   	Accordion accordion = new Accordion();
	
	        TitledPane pane1 = new TitledPane("Familie", listFam);
	        TitledPane pane2 = new TitledPane("Cars", listFam);
	        TitledPane pane3 = new TitledPane("Planes", listFam);
	        TitledPane pane4 = new TitledPane("XXX", listFam);
	
	
	        accordion.getPanes().add(pane1);
	        accordion.getPanes().add(pane2);
	        accordion.getPanes().add(pane3);
	        accordion.getPanes().add(pane4);
	
	        */
		   
	        this.centerGroup.getChildren().addAll(this.homeButtonGroup);
			   
	        this.groupView.setCenter(this.centerGroup);
	        
	        //this.groupView.setCenter(accordion);
		   

        
        updateTexts();
		
        scene1 = new Scene(root, 450, 750);
        scene2 = new Scene(contactView, 450, 750);
        scene3 = new Scene(groupView, 450, 750);
        scene1.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
        scene2.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
        scene3.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
        return scene1;
        
        
	}
	
	
	
	   protected void updateTexts() {
	       Translator t = ServiceLocator.getServiceLocator().getTranslator();
	       
	     //Text für die Elemente
	       this.menuFile.setText(t.getString("program.menu.file"));
	       this.menuFileLanguage.setText(t.getString("program.menu.file.language"));
	       this.deleteButton.setText(t.getString("program.center.button.delete"));
	       this.editButtton.setText(t.getString("program.center.button.edit"));
	       this.lbNName.setText(t.getString("program.label.contact.nname"));
	       this.lbVName.setText(t.getString("program.label.contact.vname"));
	       this.lbNumber.setText(t.getString("program.label.contact.number"));
	       this.lbBirthDate.setText(t.getString("program.label.contact.birthdate"));
	       this.lbEmail.setText(t.getString("program.label.contact.email"));
	       this.lbNotizen.setText(t.getString("program.label.contact.notizen"));
	       this.lbGroup.setText(t.getString("program.label.contact.group"));
	       this.cbGroup.getItems().addAll(t.getString("program.label.contact.comboBox.value.1"),
	    		   t.getString("program.label.contact.comboBox.value.2"), 
	    		   t.getString("program.label.contact.comboBox.value.3"));

           
           stage.setTitle(t.getString("program.name"));
	    }
	   
	   public void changeContactView() {
			stage.setScene(scene2);
			stage.show();
		}
	   
	   public void backHome() {
			stage.setScene(scene1);
			stage.show();
		}

	public void disableTextField() {
		this.txtVName.setDisable(true);
		this.txtNName.setDisable(true);
		this.txtaNotizen.setDisable(true);
		this.txtNumber.setDisable(true);
		this.birthDate.setDisable(true);
		this.cbGroup.setDisable(true);
		this.txtEmail.setDisable(true);
		this.txtID.setDisable(true);
	}

	public void enableTextField() {
		this.txtVName.setDisable(false);
		this.txtNName.setDisable(false);
		this.txtaNotizen.setDisable(false);
		this.txtNumber.setDisable(false);
		this.birthDate.setDisable(false);
		this.cbGroup.setDisable(false);
		this.txtEmail.setDisable(false);
		
	}
	
}