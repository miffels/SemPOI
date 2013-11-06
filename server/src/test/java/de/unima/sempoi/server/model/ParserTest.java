package de.unima.sempoi.server.model;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.junit.Test;

import com.google.gson.Gson;

import de.unima.sempoi.server.model.freebase.Attraction;
import de.unima.sempoi.server.model.freebase.FreebaseCity;
import de.unima.sempoi.server.model.freebase.Response;

public class ParserTest {
	
	@Test
	public void test() {
		InputStream berlin = Thread.currentThread()
				.getContextClassLoader()
				.getResourceAsStream("berlin.json");
		
		Response response = new Gson().fromJson(new InputStreamReader(berlin), Response.class);
		
		assertEquals("It should parse 2 results",
				2, response.getCities().size());
		
		Iterator<FreebaseCity> it = response.getCities().iterator();
		
		FreebaseCity city1 = it.next();
		FreebaseCity city2 = it.next();
		
		assertEquals("It should the right latitude for the first city",
				52.516667, city1.getLocation().getLatitude(), 1e-10);
		assertEquals("It should the right longitude for the first city",
				13.383333, city1.getLocation().getLongitude(), 1e-10);
		
		assertEquals("It should parse the right number of containedBys for the first city",
				1, city1.getContainedBy().size());
		assertEquals("It should parse the right number of containedBys for the second city",
				2, city2.getContainedBy().size());
		
		assertEquals("It should parse 24 attractions in the first city",
				24, city1.getAttractions().size());
		assertEquals("It should parse 1 attraction in the second city",
				1, city2.getAttractions().size());
		
		Attraction neueKirche = city1.getAttractions().get(0);
		
		assertEquals("It should parse the right name for the first attraction",
				"Neue Kirche, Berlin", neueKirche.getName());
		
		assertEquals("It should the right latitude for the first attraction",
				52.512756, neueKirche.getLocation().getLatitude(), 1e-10);
		assertEquals("It should the right longitude for the first attraction",
				13.392506, neueKirche.getLocation().getLongitude(), 1e-10);
		
		Attraction statePark = city2.getAttractions().get(0);
		
		assertNull("It should return a null location",
				statePark.getLocation());
		
		assertEquals("It parse the right name for the containedBy locations",
				"Germany", city1.getContainedBy().get(0).getName());
		
	}

}
