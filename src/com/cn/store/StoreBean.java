package com.cn.store;

public class StoreBean {
	private String store_ID;
	private String store_name;
	private String taste;
	private String lowest_consume;
	private int order_number;
	private String state;
	private String store_password;
	private double balance;
	private String store_telephone;
	private double store_score;
	private String address;
	private double longitude;
	private double latitude;
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getStore_score() {
		return store_score;
	}
	public void setStore_score(double store_score) {
		this.store_score = store_score;
	}
	public String getstore_ID(){
		return store_ID;
	}
	public void setstore_ID(String store_ID){
		this.store_ID=store_ID;
	}
	
	public String getStore_telephone(){
		return store_telephone;
	}
	public void setStore_telephone(String store_telephone){
		this.store_telephone=store_telephone;
	}
	
	public void setstore_password(String store_password){
		this.store_password=store_password;
	}
	public String getstore_password(){
		return store_password;
	}
	
	
	public String getstore_name(){
		return store_name;
	}
	public void setstore_name(String store_name){
		this.store_name=store_name;
	}
	public String gettaste(){
		return taste;
	}
	public void settaste(String taste){
		this.taste=taste;
	}
	
	public String getlowest_consume(){
		return lowest_consume;
	}
	public void setlowest_consume(String lowest_consume){
		this.lowest_consume=lowest_consume;
	}
	
	public String getstate(){
		return state;
	}
	public void setstate(String state){
		this.state=state;
	}
	
	public int getorder_number(){
		return order_number;
	}
	public void setorder_number(int order_number){
		this.order_number=order_number;
	}
	
	public Double getBalance(){
		return balance;
	}
	public void setBalance(Double balance){
		this.balance=balance;
	}
	
}
