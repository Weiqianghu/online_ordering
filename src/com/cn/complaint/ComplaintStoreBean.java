package com.cn.complaint;

import java.util.Date;

public class ComplaintStoreBean {
	private int id;
	private String username;
	private String state;
	private String store_ID;
	private String order_time;
    private String complaint_reason;
    private String store_telephone;
    private String user_telephone;
    private String complaint_time;
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getOrder_time() {
		return order_time;
	}
	public void setOrder_time(String order_time) {
		this.order_time = order_time;
	}
	public String getComplaint_reason() {
		return complaint_reason;
	}
	public void setComplaint_reason(String complaint_reason) {
		this.complaint_reason = complaint_reason;
	}
	public String getStore_telephone() {
		return store_telephone;
	}
	public void setStore_telephone(String store_telephone) {
		this.store_telephone = store_telephone;
	}
	public String getUser_telephone() {
		return user_telephone;
	}
	public void setUser_telephone(String user_telephone) {
		this.user_telephone = user_telephone;
	}
	public String getComplaint_time() {
		return complaint_time;
	}
	public void setComplaint_time(String complaint_time) {
		this.complaint_time = complaint_time;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
