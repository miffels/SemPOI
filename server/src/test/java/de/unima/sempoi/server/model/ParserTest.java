package de.unima.sempoi.server.model;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

import com.google.gson.Gson;

import de.unima.sempoi.server.model.freebase.Attraction;
import de.unima.sempoi.server.model.freebase.Response;

public class ParserTest {
	
	@Test
	public void test() {
		InputStream berlin = Thread.currentThread()
				.getContextClassLoader()
				.getResourceAsStream("berlin.json");
		
		Response response = new Gson().fromJson(new InputStreamReader(berlin), Response.class);
		
		assertEquals("It should parse 2 results",
				2, response.getResults().size());
		
		assertEquals("It should parse 25 attractions",
				25, response.getAttractions().size());
		
		Attraction attraction = response.getResults().get(0).getAttractions().get(0);
		assertEquals("The first attraction should be Neue Kirche, Berlin",
				"Neue Kirche, Berlin", attraction.getName());
		assertEquals("The first attraction should have the id /en/deutscher_dom ", "/en/deutscher_dom", attraction.getId());
	}

}
