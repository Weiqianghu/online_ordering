package com.cn.salesfood;
import java.util.List;

import com.cn.food.foodBean;

public class UserOrderListBean {
	private String username;
	private String name;
	private String telephone;
	private String address;
	private String order_time;
	private String order_state;
	private Double total_price;
	private List<foodBean> foodlist;
	public String getTelephone(){
		return telephone;
	}
	public  void setTelephone(String telephone){
		this.telephone=telephone;
	}
	
	public String getAddress(){
		return address;
	}
	public  void setAddress(String address){
		this.address=address;
	}
	
	public String getOrder_time(){
		return order_time;
	}
	public  void setOrder_time(String order_time){
		this.order_time=order_time;
	}
	
	public String getOrder_state(){
		return order_state;
	}
	public  void setOrder_state(String order_state){
		this.order_state=order_state;
	}
	
	public Double getTotal_price(){
		return total_price;
	}
	public  void setTotal_price(Double total_price){
		this.total_price=total_price;
	}
	
	public String getUsername(){
		return username;
	}
	public  void setUsername(String username){
		this.username=username;
	}
	
	public String getName(){
		return name;
	}
	public  void setName(String name){
		this.name=name;
	}
	
	public List<foodBean> getFoodlist(){
		return foodlist;
	}
	public void setFoodlist(List<foodBean> foodlist){
		this.foodlist=foodlist;
	}

}
