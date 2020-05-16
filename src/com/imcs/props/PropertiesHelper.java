package com.imcs.props;

import java.util.ResourceBundle;

public class PropertiesHelper {
	private static PropertiesHelper instance = null;
	ResourceBundle bundle = null;

	public PropertiesHelper() {
		bundle = ResourceBundle.getBundle("appconfig");
	}

	public static PropertiesHelper getInstance() {
		if (instance == null) {
			instance = new PropertiesHelper();
		}
		return instance;

	}

	public String getProperty(String key) {
		return bundle.getString(key);

	}
}
