package com.cn.comment;

public class CommentBean {
	private int ID;
	private String username;
	private String store_ID;
	private String comment_time;
	private String content;
	private double score;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getStore_ID() {
		return store_ID;
	}
	public void setStore_ID(String store_ID) {
		this.store_ID = store_ID;
	}
	public String getComment_time() {
		return comment_time;
	}
	public void setComment_time(String comment_time) {
		this.comment_time = comment_time;
	}
	
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}

}
