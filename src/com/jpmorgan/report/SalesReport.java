package com.jpmorgan.report;

import java.util.List;
import java.util.Map;

import com.jpmorgan.product.Product;

public class SalesReport {

	// It generates reports
	public static void generatePeriodicReport(Map<String, Product> productsMap,
			List<String> parsingErrorMessageList) {
		System.out.println("--------------Sales Report----------------");
		System.out.println(String.format("%-10s : %-10s : %-10s",
				"Product Type", "Total Units", "Total Sales"));
		System.out.println("------------------------------------------");
		for (String productType : productsMap.keySet()) {
			formatLog(productType, productsMap.get(productType));
		}
		System.out.println("------------------------------------------");
		System.out.println("10 Messages Processed");
		generateMessageParsingErrorReport(parsingErrorMessageList);
		System.out.println("\n\n");
	}

	private static void formatLog(String productType, Product product) {
		String log = String.format("%-12s : %11d : %10.2f",
				product.getProductType(), product.getTotalQuantity(),
				product.getTotalPrice());
		System.out.println(log);

	}

	private static void generateMessageParsingErrorReport(List<String> parsingErrorMessageList) {
		if (!parsingErrorMessageList.isEmpty()) {
			System.err.println("INVALID Message Format Detected");
			for (String error : parsingErrorMessageList) {
				System.err.println("INVALID FORMAT : " + error);
			}
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void generateAdjustmentReport(
			List<String> adjustedOperationLogList) {
		System.out
				.println("------------------Adjustment Records-----------------");
		for (String log : adjustedOperationLogList) {
			System.out.println(log);
		}
		System.out.println("\n50 Messages Processed");
	}

}
