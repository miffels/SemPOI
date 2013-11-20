package de.unima.sempoi.server.adapter.dbpedia;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class DbpediaTest {
	
	@Test
	public void testQuery() {
		Set<String> sightNames = new HashSet<String>(Arrays.asList("A", "B"));
		
		assertEquals("it should assemble the predicates properly",
				"PREFIX dbpedia2: <http://dbpedia.org/property/>\n" +
				"PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
				"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n\n" +

				"SELECT *\n" +
				"WHERE {\n" +
					"{\n" +
						"	?sight rdfs:label \"A\"@en.\n" +
						"	?sight rdfs:label ?label.\n" +
						"	?sight rdfs:comment ?comment.\n" +
						"	?sight rdf:type ?type.\n" +
						"	OPTIONAL {?sight dbpedia2:hasPhotoCollection ?photos}\n" +
						"	OPTIONAL {?sight foaf:isPrimaryTopicOf ?wikiLink}\n" +
						"	FILTER(STRSTARTS(STR(?sight), \"http://dbpedia.org/resource\"))\n" +
						"	FILTER(LANGMATCHES(LANG(?label), \"en\"))\n" +
						"	FILTER(LANGMATCHES(LANG(?comment), \"en\"))\n" +
					"} UNION {\n" +
						"	?sight rdfs:label \"B\"@en.\n" +
						"	?sight rdfs:label ?label.\n" +
						"	?sight rdfs:comment ?comment.\n" +
						"	?sight rdf:type ?type.\n" +
						"	OPTIONAL {?sight dbpedia2:hasPhotoCollection ?photos}\n" +
						"	OPTIONAL {?sight foaf:isPrimaryTopicOf ?wikiLink}\n" +
						"	FILTER(STRSTARTS(STR(?sight), \"http://dbpedia.org/resource\"))\n" +
						"	FILTER(LANGMATCHES(LANG(?label), \"en\"))\n" +
						"	FILTER(LANGMATCHES(LANG(?comment), \"en\"))\n" +
				"}\n}",
				Dbpedia.getQuery(sightNames));
	}
	
	@Test
	public void testPredicate() {
		String sightName = "A";
		
		assertEquals("it should assemble the predicates properly",
				"{\n" +
					"	?sight rdfs:label \"A\"@en.\n" +
					"	?sight rdfs:label ?label.\n" +
					"	?sight rdfs:comment ?comment.\n" +
					"	?sight rdf:type ?type.\n" +
					"	OPTIONAL {?sight dbpedia2:hasPhotoCollection ?photos}\n" +
					"	OPTIONAL {?sight foaf:isPrimaryTopicOf ?wikiLink}\n" +
					"	FILTER(STRSTARTS(STR(?sight), \"http://dbpedia.org/resource\"))\n" +
					"	FILTER(LANGMATCHES(LANG(?label), \"en\"))\n" +
					"	FILTER(LANGMATCHES(LANG(?comment), \"en\"))\n" +
				"}",
				Dbpedia.getPredicate(sightName));
	}
	
	@Test
	public void testWhereClause() {
		Set<String> sightNames = new HashSet<String>(Arrays.asList("A", "B"));
		
		assertEquals("it should assemble the predicates properly",
				"{\n" +
						"	?sight rdfs:label \"A\"@en.\n" +
						"	?sight rdfs:label ?label.\n" +
						"	?sight rdfs:comment ?comment.\n" +
						"	?sight rdf:type ?type.\n" +
						"	OPTIONAL {?sight dbpedia2:hasPhotoCollection ?photos}\n" +
						"	OPTIONAL {?sight foaf:isPrimaryTopicOf ?wikiLink}\n" +
						"	FILTER(STRSTARTS(STR(?sight), \"http://dbpedia.org/resource\"))\n" +
						"	FILTER(LANGMATCHES(LANG(?label), \"en\"))\n" +
						"	FILTER(LANGMATCHES(LANG(?comment), \"en\"))\n" +
					"} UNION {\n" +
						"	?sight rdfs:label \"B\"@en.\n" +
						"	?sight rdfs:label ?label.\n" +
						"	?sight rdfs:comment ?comment.\n" +
						"	?sight rdf:type ?type.\n" +
						"	OPTIONAL {?sight dbpedia2:hasPhotoCollection ?photos}\n" +
						"	OPTIONAL {?sight foaf:isPrimaryTopicOf ?wikiLink}\n" +
						"	FILTER(STRSTARTS(STR(?sight), \"http://dbpedia.org/resource\"))\n" +
						"	FILTER(LANGMATCHES(LANG(?label), \"en\"))\n" +
						"	FILTER(LANGMATCHES(LANG(?comment), \"en\"))\n" +
				"}",
				Dbpedia.getWhereClause(sightNames));
	}

}
