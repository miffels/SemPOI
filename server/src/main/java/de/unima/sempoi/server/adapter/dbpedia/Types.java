package de.unima.sempoi.server.adapter.dbpedia;

import java.net.URLDecoder;

public class Types {
	
	private static final String URL_PREFIX = "http://.*(/|#)";

	public static String cleanTypeName(String rawTypeName) {
		return decode(rawTypeName.replaceAll(URL_PREFIX, ""))
				.replaceAll("[0-9]+$", "")
				.replaceAll("([a-z])([A-Z])", "$1 $2")
				.replace("_", " ")
				.trim();
	}
	
	private static String decode(String s) {
		try {
			return URLDecoder.decode(s.replaceAll(URL_PREFIX, ""), "UTF-8");
		} catch (Exception e) {
			return "";
		}
	}

}
