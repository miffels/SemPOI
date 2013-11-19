package de.unima.sempoi.server.adapter.dbpedia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.jena.atlas.web.HttpException;

import de.unima.sempoi.server.model.dbpedia.DbpediaSight;
import de.unima.sempoi.server.model.freebase.FreebaseCity;

public class ParallelDbpediaAdapter {
	
	public Map<FreebaseCity, Map<String, DbpediaSight>> getSightDetailsFor(Set<FreebaseCity> cities) {
		return this.getSightDetailsFor(new ArrayList<FreebaseCity>(cities));
	}
	
	public Map<FreebaseCity, Map<String, DbpediaSight>> getSightDetailsFor(List<FreebaseCity> cities) {
		ExecutorService executor = Executors.newFixedThreadPool(cities.size() < 5 ? cities.size() : 5);
		
		List<Future<Map<String, DbpediaSight>>> futures =
				new ArrayList<Future<Map<String, DbpediaSight>>>();
		
		Map<FreebaseCity, Map<String, DbpediaSight>> sights = new HashMap<FreebaseCity, Map<String, DbpediaSight>>();
		
		for (FreebaseCity city : cities) {
			futures.add(executor.submit(new DbpediaAdapter(city.getAttractionNames())));
		}
		
		for (Future<Map<String, DbpediaSight>> future : futures) {
			FreebaseCity city = cities.get(futures.indexOf(future));
			try {
				sights.put(city, future.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				System.out.println("Querying "  + city + " failed.");
				sights.put(city, new HashMap<String, DbpediaSight>());
			}
		}
		executor.shutdown();
		
		return sights;
	}

}
