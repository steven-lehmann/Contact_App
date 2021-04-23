package Contacts.appClasses;

public enum Group {
	
	Familie, Family, Freunde, Friends, Arbeit, Work, Favoriten, Favorites;
	
	public boolean contains(String searchString) {
		return (this.name().contains(searchString));
	}
	
	public boolean equals(String searchString) {
		return (this.name().equals(searchString));
	}

}
