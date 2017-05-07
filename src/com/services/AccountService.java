package com.services;
import java.sql.*;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jettison.json.JSONArray;

import com.entities.User;
import com.google.gson.Gson;

@Path("/account")
public class AccountService {
	
	// Mysql config
	private final String USER_NAME= "your username";
	private final String PASSWORD = "your password";
	private final String CONNECTION_URL = "jdbc:mysql://localhost:3306/ToDoList";
	
	@GET
	@Path("/getuserlist")
	@Produces({ MediaType.APPLICATION_JSON })
	public String GetUserList(){

		List<User> userList = new  ArrayList<User>();
		
		try {
			Class.forName ("com.mysql.jdbc.Driver").newInstance ();
			
			Connection conn = DriverManager.getConnection (CONNECTION_URL, USER_NAME, PASSWORD);
			Statement pst = conn.createStatement();
			ResultSet result = pst.executeQuery("Select * from Users");
			
			while(result.next()){
				userList.add(
						new User(
								result.getInt("Id"), 
								result.getString("FirstName"), 
								result.getString("LastName"),
								result.getString("Email"),
								result.getString("Password"))
						);
			}
			
			Gson gson = new Gson();
			return gson.toJson(userList);
			
		} catch (Exception e) {
			//TODO: Log here
			return null;
		}
	}

	@POST
	@Path("/createuser")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	public String CreateUser(@FormParam(value = "firstName") String firstName,
			@FormParam(value = "lastName") String lastName,
			@FormParam(value = "email") String email,
			@FormParam(value = "password") String password){
		
		try {
			Class.forName ("com.mysql.jdbc.Driver").newInstance ();
			
			Connection conn = DriverManager.getConnection (CONNECTION_URL, USER_NAME, PASSWORD);
			
			Statement pst = conn.createStatement();
			
			String query = "INSERT INTO Users(FirstName, LastName, Email, Password) VALUES ('"+firstName+"','"+lastName+"','"+email+"', '"+password+"')";
			
			int effectedRow = pst.executeUpdate(query);
			
			conn.close();
			
			Gson gson = new Gson();
			
			String result = gson.toJson(effectedRow);
			
			return result;
			
		} catch (Exception e) {
			//TODO: Log here
			return null;
		}
	}
}

