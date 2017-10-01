package com.jpmorgan.message;

import java.util.Map;

// Its the context class to handle the different MessageTypes
public class MessageParser {

	private String notification;
	private Message message;
	private String regex;
	private String productType;
	private double productPrice = 0.0;
	private int productQuantity = 0;
	private String operationType;
	private double adjustPrice = 0.0;

	public MessageParser(Message message, String notification, String regex) {
		this.notification = notification;
		this.message = message;
		this.regex = regex;
	}

	public MessageParser() {
	}

	public void doMessageParsing() {
		Map<String, String> parsedMessage = message.parse(notification, regex);

		this.productType = formatProductType(parsedMessage.get("productType"));
		if (!"".equals(parsedMessage.get("productPrice"))) {
			this.productPrice = formatPrice(parsedMessage.get("productPrice"));
		}
		if (!"".equals(parsedMessage.get("productQuantity"))) {
			this.productQuantity = Integer.parseInt(parsedMessage
					.get("productQuantity"));
		}

		// For Type 3 Messages
		if (!"".equals(parsedMessage.get("adjustPrice"))) {
			this.adjustPrice = formatPrice(parsedMessage.get("adjustPrice"));
		}
		this.operationType = parsedMessage.get("operationType").toLowerCase();

	}

	// handle the plural cases of the product types
	private String formatProductType(String productType) {
		String parsedProductType = "";
		if (productType.endsWith("ies")) {
			parsedProductType = productType.replace("ies", "y");
		} else if (productType.endsWith("oes")) {
			parsedProductType = productType.replace("oes", "o");
		} else if (productType.endsWith("ches")) {
			parsedProductType = productType.replace("ches", "ch");
		} else if (productType.endsWith("s")) {
			parsedProductType = productType.replace("s", "");
		} else {
			parsedProductType = productType;
		}
		return parsedProductType.toLowerCase();
	}

	// format the string price value to double
	private double formatPrice(String price) {
		double formatedPrice = Double.parseDouble(price.replaceAll("p", "")) / 100.0;
		return formatedPrice;
	}

	public String getProductType() {
		return productType;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public String getOperationType() {
		return operationType;
	}

	public double getAdjustPrice() {
		return adjustPrice;
	}

}
