package de.unima.sempoi.server.adapter.freebase;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;

import de.unima.sempoi.server.adapter.exception.AccessNotConfiguredException;
import de.unima.sempoi.server.adapter.exception.ParameterException;
import de.unima.sempoi.server.model.freebase.FreebaseCity;
import de.unima.sempoi.server.model.freebase.Response;
import de.unima.sempoi.server.settings.Settings;

/**
 * code snippet um die freebase api zu testen und zu schauen wie die query aussehen muss
 * @author Christian
 *
 */
public class FreebaseAdapter {

	private String key = new Settings().getFreebaseApiKey();
	private static final String QUERY_TEMPLATE = "[{" +
			"	\"name\": \"%s\"," +
			"	\"type\": \"/travel/travel_destination\"," +
			"	\"/location/location/geolocation\": [{" +
			"		\"/location/geocode/latitude\": null," +
			"		\"/location/geocode/longitude\": null" +
			"	}]," +
			"	\"/location/location/containedby\": [{" +
			"		\"name\": null" +
			"	}]," +
			"	\"tourist_attractions\": [{" +
			"		\"/location/location/geolocation\": [{" +
			"			\"/location/geocode/latitude\": null," +
			"			\"/location/geocode/longitude\": null," +
			"			\"optional\": true" +
			"		}]," +
			"		\"name\": null" +
			"	}]" +
			"}]";

	public Set<FreebaseCity> readSightsOfCity(String city) throws AccessNotConfiguredException, ParameterException {
		if(city == null || "".equals(city)) {
			throw new ParameterException("Expected parameter 'city'");
		}
		city = city.trim();
		city = city.replaceAll("\"", "").replaceAll("\\\\", "");
		String query = String.format(QUERY_TEMPLATE, city);
		
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
			return new HashSet<FreebaseCity>();
		}
		
		Response response = new Gson()
		.fromJson(new InputStreamReader(stream), Response.class);
		
		return response.getCities();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FreebaseAdapter f = new FreebaseAdapter();
		try {
			f.readSightsOfCity("Berlin");
		} catch (ParameterException e) {
			e.printStackTrace();
		} catch (AccessNotConfiguredException e) {
			e.printStackTrace();
		}

	}

}
