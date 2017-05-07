package com.entities;

public class Issue {

	public Issue(int id, String title, String content, int assignUserId, IssueStatus statusId) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.assignUserId = assignUserId;
		this.statusId = statusId;
	}

	// Issue Id
	private int id;
	
	public int GetId(){
		return this.id;
	}
	
	// Issue Title
	private String title;
	
	public void SetTitle(String title){
		this.title = title;
	}
	
	public String GetTitle(){
		return this.title;
	}
	
	// Issue Content
	private String content;
	
	public void SetContent(String content){
		this.content = content;
	}
	
	public String GetContent(){
		return this.content;
	}
	
	// Issue Assign User Id
	private int assignUserId;
	
	public void SetAssingUserId(int userId){
		this.assignUserId = userId;
	}
	
	// Issue Status
	private IssueStatus statusId;
	
	public void SetStatusId(IssueStatus statusId){
		this.statusId = statusId;
	}
	
	public IssueStatus GetStatusId(){
		return this.statusId;
	}
}
