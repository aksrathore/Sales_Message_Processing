package com.jpmorgan.operation;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;

import com.jpmorgan.product.Product;

//It handles the adjustment operations and generate each adjustment log
public class AdjustOperation {

	private Product product;
	private String timeStamp;
	private LocalDateTime localDateTime = LocalDateTime.now();

	public AdjustOperation(Product product) {
		this.product = product;
	}

	// Operates different operation types
	public Product processAdjustmentOperation() {
		switch (product.getOperationType().toLowerCase()) {
		case "add":
			addOperation();
			break;
		case "subtract":
			subtractOperation();
			break;
		case "multiply":
			multiplyOperation();
			break;
		default:
			break;
		}
		timeStamp = localDateTime.toString();
		return this.product;
	}

	private void addOperation() {
		double totalPrice = this.product.getTotalPrice()
				+ this.product.getTotalQuantity()
				* this.product.getAdjustAmount();
		this.product.setTotalPrice(totalPrice);
	}

	private void subtractOperation() {
		double totalPrice = this.product.getTotalPrice()
				- this.product.getTotalQuantity()
				* this.product.getAdjustAmount();
		this.product.setTotalPrice(totalPrice);
	}

	private void multiplyOperation() {
		double totalPrice = this.product.getTotalPrice()
				+ (this.product.getTotalQuantity()
						* this.product.getProductPrice() * this.product
							.getAdjustAmount());
		this.product.setTotalPrice(totalPrice);
	}

	// Generate adjustment log
	public String adjustmentLog() {
		StringBuilder adjustmentLog = new StringBuilder();
		NumberFormat formatter = new DecimalFormat("#0.00");
		adjustmentLog.append(timeStamp).append(" : Performed - ")
				.append(this.product.getOperationType().toUpperCase())
				.append(" Operation ~ ").append(this.product.getAdjustAmount())
				.append("GBP : Product Type - ")
				.append(this.product.getProductType().toUpperCase())
				.append(" : New Total Price - ")
				.append(formatter.format(this.product.getTotalPrice()));

		return adjustmentLog.toString();
	}
}
