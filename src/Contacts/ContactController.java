package Contacts;

public class ContactController {
	private ContactMainView mainView;
	private ContactModel model;

	public ContactController(ContactModel model, ContactMainView mainView) {
		this.model = model;
		this.mainView = mainView;
	}

}
