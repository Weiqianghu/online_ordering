package com.cn.apriorialgorithm;

import java.sql.Timestamp;
public class AprioriRecommendBean {
	private String username;
	private Timestamp order_time;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Timestamp getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Timestamp order_time) {
		this.order_time = order_time;
	}
	
}
