package de.unima.sempoi.server.adapter.flickr;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import de.unima.sempoi.server.model.dbpedia.DbpediaSight;
import de.unima.sempoi.server.model.freebase.FreebaseCity;

public class ParallelFlickrAdapter {
	
	public void loadImagesFor(Map<FreebaseCity, Map<String, DbpediaSight>> sights) {
		ExecutorService executor = Executors.newFixedThreadPool(Math.max(sights.size() < 5 ? sights.size() : 5, 1));
		
		List<Future<Void>> futures =
				new ArrayList<Future<Void>>();
		
		for (Entry<FreebaseCity, Map<String, DbpediaSight>> entry : sights.entrySet()) {
			for(DbpediaSight sight : entry.getValue().values()) {
				futures.add(executor.submit(new FlickrAdapter(sight)));
			}
		}
		
		for (Future<Void> future : futures) {
			try {
				future.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		executor.shutdown();
	}

}
