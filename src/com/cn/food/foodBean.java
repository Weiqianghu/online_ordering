package com.cn.food;

public class foodBean {
	private int food_ID;
	private int num;
	private String name;
	private String price;
	private int sales_volume;
	private String store_ID;
	private String store_name;
	private String store_telephone;
	private String food_taste;
	private String introduction;
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getFood_taste() {
		return food_taste;
	}
	public void setFood_taste(String food_taste) {
		this.food_taste = food_taste;
	}
	public int getFood_ID(){
		return food_ID;
	}
	public void setFood_ID(int food_ID){
		this.food_ID=food_ID;
	}

	public int getNum(){
		return num;
	}
	public void setNum(int num){
		this.num=num;
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
	
	public int getSales_volume(){
		return sales_volume;
	}
	public void setSales_volume(int sales_volume){
		this.sales_volume=sales_volume;
	}
	
	public String getStore_ID(){
		return store_ID;
	}
	public void setStore_ID(String store_ID){
		this.store_ID=store_ID;
	}
	
	public String getStore_name(){
		return store_name;
	}
	public void setStore_name(String store_name){
		this.store_name=store_name;
	}
	
	public String getStore_telephone(){
		return store_telephone;
	}
	public void setStore_telephone(String store_telephone){
		this.store_telephone=store_telephone;
	}

}
