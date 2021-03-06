package com.jpmorgan.message;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*messagetype1 = "apple at 10p"*/

public class MessageType1 implements Message {

	@Override
	public Map<String, String> parse(String message, String regex) {
		Map<String, String> parsedMessage = new HashMap<String, String>();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(message);
		if (matcher.matches()) {
			parsedMessage.put("productType", matcher.group(1).toLowerCase());
			parsedMessage.put("productQuantity", "1");
			parsedMessage.put("productPrice", matcher.group(2));
			parsedMessage.put("operationType", "");
			parsedMessage.put("adjustPrice", "");
		}
		return parsedMessage;
	}

}
