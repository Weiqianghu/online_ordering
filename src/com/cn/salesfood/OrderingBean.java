package com.cn.salesfood;

public class OrderingBean {
	private String username;
	private String telephone;
	private String address;
	private int num;
	private String store_ID;
	private int food_ID;
	private String lowest_consume;
	private String order_state;
	private int basket_ID;
	private int sales_ID;
	private String name;
	private String price;
	public String getUsername(){
		return username;
	}
	public void setUsername(String username){
		this.username=username;
	}
	
	public String getTelephone(){
		return telephone;
	}
	public void setTelephone(String telephone){
		this.telephone=telephone;
	}
	
	public String getAddress(){
		return address;
	}
	public void setAddress(String address){
		this.address=address;
	}
	
	public String getStore_ID(){
		return store_ID;
	}
	public void setStore_ID(String store_ID){
		this.store_ID=store_ID;
	}
	
	public String getOrder_state(){
		return order_state;
	}
	public void setOrder_state(String order_state){
		this.order_state=order_state;
	}
	
	public int getNum(){
		return num;
	}
	public void setNum(int num){
		this.num=num;
	}
	
	public int getFood_ID(){
		return food_ID;
	}
	public void setFood_ID(int food_ID){
		this.food_ID=food_ID;
	}
	
	public int getBasket_ID(){
		return basket_ID;
	}
	public void setBasket_ID(int basket_ID){
		this.basket_ID=basket_ID;
	}
	
	public int getSales_ID(){
		return sales_ID;
	}
	public void setSales_ID(int sales_ID){
		this.sales_ID=sales_ID;
	}
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	
	public String getPrice(){
		return price;
	}
	public void setPrice(String price){
		this.price=price;
	}
	public String getLowest_consume() {
		return lowest_consume;
	}
	public void setLowest_consume(String lowest_consume) {
		this.lowest_consume = lowest_consume;
	}

}
