package de.unima.sempoi.server.settings;

import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class Settings {
	
	private static final String FREEBASE_API_KEY = "freebase.api.key";
	
	private static final ResourceBundle SETTINGS_BUNDLE;
//			ResourceBundle.getBundle("src.main.resources.settings", Locale.ENGLISH);
	
	static {
		ResourceBundle bundle = null;
		try {
			bundle = new PropertyResourceBundle(
					Thread.currentThread()
					.getContextClassLoader()
					.getResourceAsStream("settings.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		SETTINGS_BUNDLE = bundle;
	}
	
	public String getFreebaseApiKey() {
		return SETTINGS_BUNDLE.getString(FREEBASE_API_KEY);
	}

}
