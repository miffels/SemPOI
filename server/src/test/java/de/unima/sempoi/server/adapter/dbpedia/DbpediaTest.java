package de.unima.sempoi.server.adapter.dbpedia;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import de.unima.sempoi.server.adapter.dbpedia.Dbpedia;

public class DbpediaTest {
	
	@Test
	public void testQuery() {
		Set<String> sightNames = new HashSet<String>(Arrays.asList("A", "B"));
		
		assertEquals("it should assemble the predicates properly",
				"PREFIX p: <http://dbpedia.org/property/>\n" +
				"PREFIX dbpedia: <http://dbpedia.org/resource/>\n" +
				"PREFIX category: <http://dbpedia.org/resource/Category:>\n" +
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
				"PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" +
				"PREFIX geo: <http://www.georss.org/georss/>\n\n" +

				"SELECT *\n" +
				"WHERE {\n" +
				"{\n" +
						"?s rdfs:label ?l.\n" +
						"?l <bif:contains> \"'A'\".\n" +
						"FILTER regex(?l, \"A\").\n" +
					"} UNION {\n" +
						"?s rdfs:label ?l.\n" +
						"?l <bif:contains> \"'B'\".\n" +
						"FILTER regex(?l, \"B\").\n" +
					"}\n}",
				Dbpedia.getQuery(sightNames));
	}
	
	@Test
	public void testPredicate() {
		String sightName = "Neue Kirche, Berlin";
		
		assertEquals("it should assemble the predicates properly",
				"{\n" +
						"?s rdfs:label ?l.\n" +
						"?l <bif:contains> \"'Neue'\".\n" +
						"FILTER regex(?l, \"Neue Kirche, Berlin\").\n" +
					"}",
				Dbpedia.getPredicate(sightName));
	}
	
	@Test
	public void testWhereClause() {
		Set<String> sightNames = new HashSet<String>(Arrays.asList("A", "B"));
		
		assertEquals("it should assemble the predicates properly",
				"{\n" +
						"?s rdfs:label ?l.\n" +
						"?l <bif:contains> \"'A'\".\n" +
						"FILTER regex(?l, \"A\").\n" +
					"} UNION {\n" +
						"?s rdfs:label ?l.\n" +
						"?l <bif:contains> \"'B'\".\n" +
						"FILTER regex(?l, \"B\").\n" +
					"}",
				Dbpedia.getWhereClause(sightNames));
	}

}
