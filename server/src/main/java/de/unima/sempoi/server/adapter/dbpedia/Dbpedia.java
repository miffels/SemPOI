package de.unima.sempoi.server.adapter.dbpedia;

import java.util.Set;

public class Dbpedia {
	
	private static final String QUERY_BASE =
			"PREFIX dbpedia2: <http://dbpedia.org/property/>\n" +
			"PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
			"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n\n" +

			"SELECT *\n" +
			"WHERE {\n%s\n}";
	
	private static final String PREDICATE = "{\n" +
			"	?sight rdfs:label \"%s\"@en.\n" +
			"	?sight rdfs:label ?label.\n" +
			"	?sight rdfs:comment ?comment.\n" +
			"	?sight rdf:type ?type.\n" +
			"	OPTIONAL {?sight dbpedia2:hasPhotoCollection ?photos}\n" +
			"	OPTIONAL {?sight foaf:isPrimaryTopicOf ?wikiLink}\n" +
			"	FILTER(STRSTARTS(STR(?sight), \"http://dbpedia.org/resource\"))\n" +
			"	FILTER(LANGMATCHES(LANG(?label), \"en\"))\n" +
			"	FILTER(LANGMATCHES(LANG(?comment), \"en\"))\n" +
			"	MINUS{[] rdfs:subClassOf ?type}\n" +
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
