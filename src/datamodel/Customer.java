package datamodel;

public class Customer {
	private final String id;
	private String firstName;
	private String lastName;
	private String contact;
	
	protected Customer(String id, String name, String contact) {
		firstName = "";
			this.id = id;
		if(name == null) {
			this.lastName = "";
		}else {
			this.lastName = name;
		}
		if(contact == null) {
			this.contact = "";
		}else {
			this.contact = contact;
		}
		
	}

	public void setFirstName(String firstName) {
		if(firstName == null) {
			this.firstName = "";
		}else {
			this.firstName = firstName;
		}
	}
	
	public void setLastName(String lastName) {
		if(lastName == null) {
			this.lastName = "";
		}else {
			this.lastName = lastName;
		}
	}
	
	public void setContact(String contact) {
		if(contact == null) {
			this.contact = "";
		}else {
			this.contact = contact;
		}
	}
	
	public String getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
		
	public String getContact() {
		return contact;
	}
	
	
}
