package de.unima.sempoi.server.adapter.dbpedia;

import java.util.Set;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

public class DbpediaAdapter {
	
	public void query(Set<String> sightNames) {
		Query query = QueryFactory.create(Dbpedia.getQuery(sightNames));
		QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);
		try {
		    ResultSet results = qexec.execSelect();
		    if(results.hasNext()) {
		    	System.out.println("Resource\t\t\t\t\tlabel\tcomment\tphotos\t");
		    } else {
		    	System.out.println("No results.");
		    }
		    for (; results.hasNext();) {
		    	QuerySolution result = results.next();
		    	System.out.print(
		    			result.get("s") + "\t"
		    			+ result.get("l") + "\t"
		    			+ result.get("c") + "\t"
		    			+ result.get("p"));
		    	System.out.println();
		    }
		}
		finally {
		   qexec.close();
		}
	}
	
}
