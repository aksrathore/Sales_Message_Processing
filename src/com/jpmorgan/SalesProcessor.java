package com.jpmorgan;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.jpmorgan.core.Sales;

// Main Driver class of the application
public class SalesProcessor {

	public static void main(String[] args) {

		try (BufferedReader br = new BufferedReader(new FileReader("messageStream.txt"))) {
			String message = br.readLine();
			while (message != null) {
				Sales.processMessage(message);
				message = br.readLine();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
