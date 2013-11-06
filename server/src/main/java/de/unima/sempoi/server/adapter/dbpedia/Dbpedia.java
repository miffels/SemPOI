package de.unima.sempoi.server.adapter.dbpedia;

import java.util.Set;

public class Dbpedia {
	
	private static final String QUERY_BASE =
			"PREFIX dbpedia2: <http://dbpedia.org/property/>\n" +
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n\n" +

			"SELECT *\n" +
			"WHERE {\n%s\n}";
	
	private static final String PREDICATE = "{\n" +
			"	?s rdfs:label \"%s\"@en.\n" +
			"	?s rdfs:label ?l.\n" +
			"	?s rdfs:comment ?c.\n" +
			"	OPTIONAL {?s dbpedia2:hasPhotoCollection ?p}\n" +
			"	FILTER(STRSTARTS(STR(?s), \"http://dbpedia.org/resource\"))\n" +
			"	FILTER(LANGMATCHES(LANG(?l), \"en\"))\n" +
			"	FILTER(LANGMATCHES(LANG(?c), \"en\"))\n" +
			"}";
	
	
	public static String getPredicate(String sightName) {
		return String.format(PREDICATE, sightName);
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
