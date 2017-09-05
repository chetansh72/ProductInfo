package com.niit.cloud;

public class ItemInvoice {
  private String name;
  private int  qty;
  private int price;
  private String id;
public ItemInvoice(String name, int qty, int price, String id) {
	super();
	this.name = name;
	this.qty = qty;
	this.price = price;
	this.id = id;
}
  
public ItemInvoice(){
	super();
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getQty() {
	return qty;
}

public void setQty(int qty) {
	this.qty = qty;
}

public int getPrice() {
	return price;
}

public void setPrice(int price) {
	this.price = price;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

@Override
public String toString() {
	return "ItemInvoice [name=" + name + ", qty=" + qty + ", price=" + price + ", id=" + id + "]";
}

}
