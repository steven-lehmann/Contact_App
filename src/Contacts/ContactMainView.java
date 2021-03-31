package Contacts;

import javafx.stage.Stage;

public class ContactMainView {
	
	final private ContactModel model;
	private Stage stage;

	public ContactMainView(Stage stage, ContactModel model) {
		this.model = model;
		this.stage = stage;
		
	}

	public void start() {
		stage.show();
		
	}

}
