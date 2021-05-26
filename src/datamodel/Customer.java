package datamodel;

//import java.util.ArrayList;

public class Customer {
	
	private final String id;
	private String firstName;
	private String lastName;
	private String contact;
	
	protected Customer (String id, String name, String contact) {
		this.id = id;
		this.lastName = name;
		this.firstName = "";
		this.contact = contact;
	}
	
	public String getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

//	public String getName() {
//		return lastName + ", " + firstName;
//	}
	
//	public void setName(String name) {
//		 boolean komma  = false;
//	        String eingabe = name;
//	        for (int i = 0; i < eingabe.length(); i++) {
//	            if (eingabe.charAt(i) == ',') {
//	                komma = true;
//	            } 
//	        }
//	        if (komma == true){
//	            String [] nAngabe = eingabe.trim().split(", ");
//	            this.lastName = nAngabe[0];
//	            this.firstName = nAngabe[1];
//	        } else {
//	            String [] nAngabe = eingabe.trim().split(" ");
//
//	                this.lastName = nAngabe[nAngabe.length - 1]; //wegen der Indizierung
//	                this.firstName = "";
//	                for (int i = 0; i < nAngabe.length - 1; i++) {
//	                    if(i > 0) {
//	                        this.firstName += " ";
//	                    }
//	                    this.firstName += nAngabe[i];
//	                }
//	        }
//	}
	
	public String getContact() {
		return contact;
	}
	
	public void setContact(String contact) {
		this.contact = contact;
	}
		
}