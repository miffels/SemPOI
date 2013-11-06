package de.unima.sempoi.server.adapter.dbpedia;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import de.unima.sempoi.server.adapter.dbpedia.Dbpedia;

public class DbpediaTest {
	
//	@Test
//	public void testQuery() {
//		Set<String> sightNames = new HashSet<String>(Arrays.asList("A", "B"));
//		
//		assertEquals("it should assemble the predicates properly",
//				"PREFIX dbpedia2: <http://dbpedia.org/resource/>\n" +
//				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n\n" +
//
//				"SELECT *\n" +
//				"WHERE {\n" +
//				"{\n" +
//						"	?s rdfs:label \"A\"@en.\n" +
//						"	?s rdfs:label ?l.\n" +
//						"	?s rdfs:comment ?c.\n" +
//						"	OPTIONAL {?s dbpedia2:hasPhotoCollection ?p}\n" +
//						"	FILTER(STRSTARTS(STR(?s), \"http://dbpedia.org/resource\"))\n" +
//						"	FILTER(LANGMATCHES(LANG(?l), \"en\"))\n" +
//						"	FILTER(LANGMATCHES(LANG(?c), \"en\"))\n" +
//					"} UNION {\n" +
//						"	?s rdfs:label \"B\"@en.\n" +
//						"	?s rdfs:label ?l.\n" +
//						"	?s rdfs:comment ?c.\n" +
//						"	OPTIONAL {?s dbpedia2:hasPhotoCollection ?p}\n" +
//						"	FILTER(STRSTARTS(STR(?s), \"http://dbpedia.org/resource\"))\n" +
//						"	FILTER(LANGMATCHES(LANG(?l), \"en\"))\n" +
//						"	FILTER(LANGMATCHES(LANG(?c), \"en\"))\n" +
//				"}\n}",
//				Dbpedia.getQuery(sightNames));
//	}
//	
//	@Test
//	public void testPredicate() {
//		String sightName = "A";
//		
//		assertEquals("it should assemble the predicates properly",
//				"{\n" +
//					"	?s rdfs:label \"A\"@en.\n" +
//					"	?s rdfs:label ?l.\n" +
//					"	?s rdfs:comment ?c.\n" +
//					"	OPTIONAL {?s dbpedia2:hasPhotoCollection ?p}\n" +
//					"	FILTER(STRSTARTS(STR(?s), \"http://dbpedia.org/resource\"))\n" +
//					"	FILTER(LANGMATCHES(LANG(?l), \"en\"))\n" +
//					"	FILTER(LANGMATCHES(LANG(?c), \"en\"))\n" +
//				"}",
//				Dbpedia.getPredicate(sightName));
//	}
//	
//	@Test
//	public void testWhereClause() {
//		Set<String> sightNames = new HashSet<String>(Arrays.asList("A", "B"));
//		
//		assertEquals("it should assemble the predicates properly",
//				"{\n" +
//					"	?s rdfs:label \"A\"@en.\n" +
//					"	?s rdfs:label ?l.\n" +
//					"	?s rdfs:comment ?c.\n" +
//					"	OPTIONAL {?s dbpedia2:hasPhotoCollection ?p}\n" +
//					"	FILTER(STRSTARTS(STR(?s), \"http://dbpedia.org/resource\"))\n" +
//					"	FILTER(LANGMATCHES(LANG(?l), \"en\"))\n" +
//					"	FILTER(LANGMATCHES(LANG(?c), \"en\"))\n" +
//				"} UNION {\n" +
//					"	?s rdfs:label \"B\"@en.\n" +
//					"	?s rdfs:label ?l.\n" +
//					"	?s rdfs:comment ?c.\n" +
//					"	OPTIONAL {?s dbpedia2:hasPhotoCollection ?p}\n" +
//					"	FILTER(STRSTARTS(STR(?s), \"http://dbpedia.org/resource\"))\n" +
//					"	FILTER(LANGMATCHES(LANG(?l), \"en\"))\n" +
//					"	FILTER(LANGMATCHES(LANG(?c), \"en\"))\n" +
//				"}",
//				Dbpedia.getWhereClause(sightNames));
//	}

}
