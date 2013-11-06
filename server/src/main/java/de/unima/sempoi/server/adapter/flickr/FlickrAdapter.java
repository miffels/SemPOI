package de.unima.sempoi.server.adapter.flickr;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import de.unima.sempoi.server.model.dbpedia.DbpediaSight;

public class FlickrAdapter implements Callable<Void> {

	private DbpediaSight sight;
	
	public FlickrAdapter(DbpediaSight sight) {
		this.sight = sight;
	}

	@Override
	public Void call() throws Exception {
		try {
			Document doc = Jsoup.connect(this.sight.getPhotoUrl()).get();
			Elements imageTags = doc.select("html body p a img");
			List<String> images = new ArrayList<String>();
			for(Element image : imageTags) {
				images.add(image.attr("src"));
			}
			this.sight.setImages(images);
		} catch(HttpStatusException e) {
			System.err.println("No images found for " + this.sight);
		}
		return null;
	}

}
