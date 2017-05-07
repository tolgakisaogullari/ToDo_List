package com.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.entities.Issue;
import com.entities.IssueStatus;
import com.google.gson.Gson;

@Path("/task")
public class TaskService {

	private final String USER_NAME = "your username";
	private final String PASSWORD = "your password";
	private final String CONNECTION_URL = "jdbc:mysql://localhost:3306/ToDoList";

	//TODO: Should add authentiction for this service
	// Returns Task list as a json string
	@Path("gettasklist")
	@GET
	public String GetTaskList() {
		List<Issue> issueList = new ArrayList<Issue>();

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			Connection conn = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
			Statement pst = conn.createStatement();

			ResultSet result = pst.executeQuery("Select * from Issues");

			while (result.next()) {
				issueList.add(new Issue(result.getInt("Id"), result.getString("Title"), result.getString("Content"),
				        result.getInt("AssignUserId"), IssueStatus.values()[result.getInt("StatusId")]));
			}

			//Was added to convert list to json string
			Gson gson = new Gson();
			return gson.toJson(issueList);

		} catch (Exception e) {
			
			//TODO: Add log here
			return null;
		}
	}

	@POST
	@Path("/createtask")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	public String CreateTask(@FormParam(value = "title") String title, @FormParam(value = "content") String content,
	        @FormParam(value = "assignUserId") String assignUserId, @FormParam(value = "statusId") String statusId) {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			Connection conn = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
			Statement stmt = conn.createStatement();
			String query = "INSERT INTO Issues(Title, Content, AssignUserId, StatusId) VALUES ('" + title + "','"
			        + content + "','" + assignUserId + "', '" + statusId + "')";

			
			int effectedRowCount = stmt.executeUpdate(query);

			conn.close();

			return Integer.toString(effectedRowCount);

		} catch (Exception e) {
			//TODO: Log here
			return null;
		}
	}

	@POST
	@Path("/deletetask")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	public String DeleteTask(@FormParam(value = "id") String id) {

		Gson gson = new Gson();

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			Connection conn = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);

			String query = "Delete from issues where Id = ?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, id);
			pst.execute();

			conn.close();
			return gson.toJson("Success");

		} catch (Exception e) {
			return gson.toJson("Fail");
		}
	}

	@POST
	@Path("/updatetask")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	public String UpdateTask(@FormParam(value = "id") String id, @FormParam(value = "title") String title,
	        @FormParam(value = "content") String content, @FormParam(value = "assignUserId") String assignUserId,
	        @FormParam(value = "statusId") String statusId) {

		String conUrl = "jdbc:mysql://localhost:3306/ToDoList";
		Gson gson = new Gson();

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			Connection conn = DriverManager.getConnection(conUrl, USER_NAME, PASSWORD);

			String query = "Update issues Set Title = ?, Content = ?, AssignUserId = ?, StatusId = ? Where Id = ?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, title);
			pst.setString(2, content);
			pst.setString(3, assignUserId);
			pst.setString(4, statusId);
			pst.setString(5, id);

			String result = pst.executeUpdate() > 0 ? "Success" : "Fail";

			conn.close();
			return gson.toJson(result);

		} catch (Exception e) {
			return gson.toJson("Fail");
		}
	}
}
