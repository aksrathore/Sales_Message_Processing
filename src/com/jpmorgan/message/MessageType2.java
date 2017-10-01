package com.jpmorgan.message;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Implementation of MessageType2
/*messagetype2="20 sales of apples at 10p each"*/

public class MessageType2 implements Message {

	@Override
	public Map<String, String> parse(String message, String regex) {
		Map<String, String> parsedMessage = new HashMap<String, String>();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(message);
		if (matcher.matches()) {
			parsedMessage.put("productType", matcher.group(2).toLowerCase());
			parsedMessage.put("productQuantity", matcher.group(1));
			parsedMessage.put("productPrice", matcher.group(3));
			parsedMessage.put("operationType", "");
			parsedMessage.put("adjustPrice", "");
		}
		return parsedMessage;
	}

}
