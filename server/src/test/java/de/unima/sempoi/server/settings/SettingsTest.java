package de.unima.sempoi.server.settings;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SettingsTest {
	
	@Test
	public void test() {
		Settings settings = new Settings();
		assertEquals("It should return the API key configured in the settings.properties",
				"AIzaSyBFyuWLHzt1BoPQ6yqaMSBj4HrnWVFsSh4", settings.getFreebaseApiKey());
	}

}
