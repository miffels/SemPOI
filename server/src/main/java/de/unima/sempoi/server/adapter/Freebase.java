package de.unima.sempoi.server.adapter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;

import de.unima.sempoi.server.model.freebase.Attraction;
import de.unima.sempoi.server.model.freebase.Response;
import de.unima.sempoi.server.settings.Settings;

/**
 * code snippet um die freebase api zu testen und zu schauen wie die query aussehen muss
 * @author Christian
 *
 */
public class Freebase {

	private String key = new Settings().getFreebaseApiKey();
	

	public List<Attraction> readSightsOfCity(String city) throws AccessNotConfiguredException, ParameterException {
		if(city == null || "".equals(city)) {
			throw new ParameterException("Expected parameter 'city'");
		}
		city = city.trim();
		city = city.replaceAll("\"", "").replaceAll("\\\\", "");
		String query = "[{ \"id\": null, \"name\": \""+city+"\", \"type\": \"/travel/travel_destination\", \"tourist_attractions\": [{   \"id\": null,   \"name\": null }]}]";
		
		HttpTransport httpTransport = new NetHttpTransport();
		HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
		GenericUrl url = new GenericUrl("https://www.googleapis.com/freebase/v1/mqlread");
		url.put("query", query);
		url.put("key", key);
		
		InputStream stream= null;
		try {
			stream = requestFactory
					.buildGetRequest(url)
					.execute()
					.getContent();
		} catch(HttpResponseException e) {
			throw new AccessNotConfiguredException("Freebase access not configured");
		} catch(IOException e) {
			return new ArrayList<Attraction>();
		}
		
		return new Gson()
				.fromJson(new InputStreamReader(stream), Response.class)
				.getAttractions();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Freebase f = new Freebase();
		try {
			f.readSightsOfCity("Berlin");
		} catch (ParameterException e) {
			e.printStackTrace();
		} catch (AccessNotConfiguredException e) {
			e.printStackTrace();
		}

	}

}
