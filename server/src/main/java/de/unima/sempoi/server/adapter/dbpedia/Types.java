package de.unima.sempoi.server.adapter.dbpedia;

public class Types {

	public static String cleanTypeName(String rawTypeName) {
		return rawTypeName
				.replace("http://dbpedia.org/class/yago/", "")
				.replace("http://www.opengis.net/gml/", "")
				.replaceAll("[0-9]+$", "")
				.replaceAll("([a-z])([A-Z])", "$1 $2")
				.replace("_", " ")
				.trim();
	}

}
