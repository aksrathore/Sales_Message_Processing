package com.jpmorgan.message;

import com.jpmorgan.utility.Config;

// To hold different message regex
public enum MessageType {
	
	MESSAGETYPE1(Config.getString("messagetype1_regex")),
	MESSAGETYPE2(Config.getString("messagetype2_regex")),
	MESSAGETYPE3(Config.getString("messagetype3_regex"));
	
	private final String regex;
	
	private MessageType(final String regex) {
        this.regex = regex;
    }
	
	@Override
    public String toString() {
        return regex;
    }

}
