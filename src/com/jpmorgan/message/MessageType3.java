package com.jpmorgan.message;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Implementation of MessageType3
/*message3="Add 20p apples"*/

public class MessageType3 implements Message {

	@Override
	public Map<String, String> parse(String message, String regex) {
		Map<String, String> parsedMessage = new HashMap<String, String>();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(message);
		if (matcher.matches()) {
			parsedMessage.put("productType", matcher.group(3).toLowerCase());
			parsedMessage.put("productQuantity", "");
			parsedMessage.put("productPrice", "");
			parsedMessage.put("operationType", matcher.group(1));
			parsedMessage.put("adjustPrice", matcher.group(2));
		}
		return parsedMessage;
	}

}
