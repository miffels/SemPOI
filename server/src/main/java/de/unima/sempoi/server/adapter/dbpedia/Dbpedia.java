package de.unima.sempoi.server.adapter.dbpedia;

import java.util.Set;

public class Dbpedia {
	
	private static final String QUERY_BASE =
			"PREFIX p: <http://dbpedia.org/property/>\n" +
			"PREFIX dbpedia: <http://dbpedia.org/resource/>\n" +
			"PREFIX category: <http://dbpedia.org/resource/Category:>\n" +
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
			"PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" +
			"PREFIX geo: <http://www.georss.org/georss/>\n\n" +

			"SELECT *\n" +
			"WHERE {\n%s\n}";
	
	private static final String PREDICATE = "{\n" +
				"?s rdfs:label ?l.\n" +
				"?l <bif:contains> \"'%s'\".\n" +
				"FILTER regex(?l, \"%s\").\n" +
			"}";
	
	
	public static String getPredicate(String sightName) {
		String bifHint = sightName.split(" ")[0];
		return String.format(PREDICATE, bifHint, sightName);
	}
	
	public static String getWhereClause(Set<String> sightNames) {
		String delimiter = "";
		StringBuffer whereClause = new StringBuffer("");
		
		for(String sightName: sightNames) {
			whereClause.append(delimiter);
			whereClause.append(getPredicate(sightName));
			delimiter = " UNION ";
		}
		
		return whereClause.toString();
	}
	
	public static String getQuery(Set<String> sightNames) {
		return String.format(QUERY_BASE, getWhereClause(sightNames));
	}

}
