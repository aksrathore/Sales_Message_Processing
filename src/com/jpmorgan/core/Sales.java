package com.jpmorgan.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jpmorgan.message.MessageParser;
import com.jpmorgan.message.MessageType;
import com.jpmorgan.message.MessageType1;
import com.jpmorgan.message.MessageType2;
import com.jpmorgan.message.MessageType3;
import com.jpmorgan.operation.AdjustOperation;
import com.jpmorgan.product.Product;
import com.jpmorgan.report.SalesReport;

/*It is the core service class of the application. It interacts with the MessageParser.java 
 to get the parsed message and store the information in the Product Pojo Class with is then 
 store in the HashMap. It checks if the 10th message is reached then the Sales report is generated
 with the help of SalesReport Class. Also, Price Adjustment Operation is handled by the AdjustOperation Class.
 */

public class Sales {

	private static Product product;
	// To hold the count of the number of messages processed
	private static int messageCount = 0;
	// Holds the sales information of all the products
	private static HashMap<String, Product> productsMap = new HashMap<String, Product>();
	// Holds unparsed messages
	private static List<String> parsingErrorMessageList = new ArrayList<String>();
	// holds the information about the adjustment operations done
	private static List<String> adjustedOperationLogList = new ArrayList<String>();

	/**
	 * It acts as the service provider and deals with the different classes like
	 * SalesReport for generating report, AdjustOperation for making adjustment
	 * operations and MessageParser to parse the input notification messages
	 * 
	 * @param message input notification message to be parsed
	 * @return void
	 * 
	 */
	public static void processMessage(String message) {

		MessageParser parser = process(message);
		messageCount++;
		if (parser == null) {
			parsingErrorMessageList.add(message);
		} else {
			product = productsMap.getOrDefault(parser.getProductType(),
					new Product(parser.getProductType()));
			if (!"".equals(parser.getOperationType())) {
				product.setOperationType(parser.getOperationType());
				product.setAdjustAmount(parser.getAdjustPrice());
				AdjustOperation adustOperation = new AdjustOperation(product);
				product = adustOperation.processAdjustmentOperation();
				adjustedOperationLogList.add(adustOperation.adjustmentLog());

			} else {
				product.setProductPrice(parser.getProductPrice());
				product.setProductQuantity(parser.getProductQuantity());
				product.setTotalQuantity(parser.getProductQuantity());
				product.setTotalPrice(product.getTotalPrice()
						+ (parser.getProductQuantity() * product
								.getProductPrice()));
			}
			productsMap.put(product.getProductType(), product);
		}
		if (messageCount % 10 == 0) {
			SalesReport.generatePeriodicReport(productsMap,
					parsingErrorMessageList);
			// To avoid intermingling of reports on the console
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			parsingErrorMessageList = new ArrayList<String>();
		}
		if (messageCount % 50 == 0) {
			SalesReport.generateAdjustmentReport(adjustedOperationLogList);
			String userInput = "";
			System.out.print("Do you want to continue?(YES/NO) : ");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			try {
				userInput = in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (!"YES".equalsIgnoreCase(userInput)) {
				System.out.println("Exiting...");
				System.exit(1);

			}
		}
	}

	/**
	 * Calls the respective MessageType and parses message string This method is
	 * called from processMessage()
	 * 
	 * @param message input notification message to be parsed
	 * @return parser MessageParser Object
	 * 
	 */
	private static MessageParser process(String message) {
		MessageParser parser = null;
		loop: for (MessageType msgType : MessageType.values()) {
			if (message.matches(msgType.toString())) {
				switch (msgType) {
				case MESSAGETYPE1:
					parser = new MessageParser(new MessageType1(), message,
							MessageType.MESSAGETYPE1.toString());
					parser.doMessageParsing();
					break loop;
				case MESSAGETYPE2:
					parser = new MessageParser(new MessageType2(), message,
							MessageType.MESSAGETYPE2.toString());
					parser.doMessageParsing();
					break loop;
				case MESSAGETYPE3:
					parser = new MessageParser(new MessageType3(), message,
							MessageType.MESSAGETYPE3.toString());
					parser.doMessageParsing();
					break loop;
				default:
					break loop;
				}
			}
		}
		return parser;
	}
}
