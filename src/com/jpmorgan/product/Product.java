package com.jpmorgan.product;

// Pojo Class to handle the Product Information
public class Product {

	private double productPrice;
	private int productQuantity;
	private String productType;
	private int totalQuantity;
	private double totalPrice;
	private String operationType;
	private double adjustAmount;

	public Product(String type) {
		this.productType = type;
		this.productPrice = 0.0;
		this.totalQuantity = 0;
		this.totalPrice = 0.0;
		this.productQuantity = 0;
	}

	public double getAdjustAmount() {
		return adjustAmount;
	}

	public void setAdjustAmount(double adjustAmount) {
		this.adjustAmount = adjustAmount;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setTotalQuantity(int quantity) {
		this.totalQuantity += quantity;
	}

	public int getTotalQuantity() {
		return this.totalQuantity;
	}

	public double getTotalPrice() {
		return this.totalPrice;
	}

	public String getProductType() {
		return this.productType;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

}
