package de.unima.sempoi.server.adapter;

import java.util.ArrayList;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * code snippet um die freebase api zu testen und zu schauen wie die query aussehen muss
 * @author Christian
 *
 */
public class Freebase {

	private String key = "AIzaSyBFyuWLHzt1BoPQ6yqaMSBj4HrnWVFsSh4";
	

	public ArrayList<String> readSightsOfCity(String city) {
		city = city.trim();
		city = city.replaceAll("\"", "").replaceAll("\\\\", "");
		String query = "[{ \"id\": null, \"name\": \""+city+"\", \"type\": \"/travel/travel_destination\", \"tourist_attractions\": [{   \"id\": null,   \"name\": null }]}]";
		
		try {
			HttpTransport httpTransport = new NetHttpTransport();
			HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
			JsonParser parser = new JsonParser();
			GenericUrl url = new GenericUrl("https://www.googleapis.com/freebase/v1/mqlread");
			url.put("query", query);
			url.put("key", key);
			HttpRequest request = requestFactory.buildGetRequest(url);
			HttpResponse httpResponse = request.execute();
			JsonObject response = (JsonObject) parser.parse(httpResponse.parseAsString());
			JsonArray results = (JsonArray) response.get("result");
			for (Object result : results) {
				System.out.println(result);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Freebase f = new Freebase();
		f.readSightsOfCity("Berlin");

	}

}
