package com.jpmorgan.message;

import java.util.Map;

/*Strategy Pattern is implemented to decide the message type at runtime and 
 respective Class Implementation is called. It's open for extension but not for 
 modification*/

public interface Message {

	Map<String, String> parse(String message, String regex);

}
