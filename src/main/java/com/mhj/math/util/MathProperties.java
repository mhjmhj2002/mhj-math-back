package com.mhj.math.util;

import java.util.ResourceBundle;

public class MathProperties {

	private static ResourceBundle bundle = ResourceBundle.getBundle("messages");

	private MathProperties() {
		super();
	}
	
	public static String getPropertyString(final String propertyName){
		return bundle.getString(propertyName);
	}

}
