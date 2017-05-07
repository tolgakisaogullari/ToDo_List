package com.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User{

	public User(int id, String firstName, String lastName, String email, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
    
	// User id
	@XmlElement public int id;
	
	public int GetUserId(){
		return this.id;
	}
	
	// User First Name
	@XmlElement public String firstName;
	
	public void SetFirstName (String firstName){
		this.firstName = firstName;
	}
	
	public String GetFirstName(){
		return this.firstName;
	}
	
	// User Last Name
	@XmlElement public String lastName;
	
	public void SetLastName (String lastName){
		this.lastName = lastName;
	}
	
	public String GetLastName(){
		return lastName;
	}
	
	// User Email
	@XmlElement public String email;
	
	public void SetEmail (String email){
		this.email = email;
	}
	
	public String GetEmail(){
		return this.email;
	}
	
	// User Password
	@XmlElement public String password;
	
	public void SetPassword (String password){
		this.password = password;
	}
	
	public String GetPassword(){
		return this.password;
	}
}
