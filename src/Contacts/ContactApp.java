package Contacts;

import javafx.application.Application;
import javafx.stage.Stage;

public class ContactApp extends Application{
	
	private ContactModel model;
	private ContactMainView mainView;
	private ContactController controller;

	public static void main(String[] args) {
		launch();

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		model = new ContactModel();
		mainView = new ContactMainView(primaryStage, model);
		controller = new ContactController(model, mainView);
		
		mainView.start();

		
		
	}

}
