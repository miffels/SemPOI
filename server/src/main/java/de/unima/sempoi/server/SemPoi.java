package de.unima.sempoi.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import de.unima.sempoi.server.adapter.dbpedia.ParallelDbpediaAdapter;
import de.unima.sempoi.server.adapter.exception.AccessNotConfiguredException;
import de.unima.sempoi.server.adapter.exception.ParameterException;
import de.unima.sempoi.server.adapter.flickr.ParallelFlickrAdapter;
import de.unima.sempoi.server.adapter.freebase.FreebaseAdapter;
import de.unima.sempoi.server.adapter.processing.Merger;
import de.unima.sempoi.server.model.dbpedia.DbpediaSight;
import de.unima.sempoi.server.model.freebase.FreebaseCity;

/**
 * Servlet implementation class SemPOI
 */
public class SemPoi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SemPoi() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		try {
			Set<FreebaseCity> cities = new FreebaseAdapter().readSightsOfCity(request.getParameter("city"));
			ParallelDbpediaAdapter dbpediaAdapter = new ParallelDbpediaAdapter();
			Map<FreebaseCity, Map<String, DbpediaSight>> sights = dbpediaAdapter.getSightDetailsFor(cities);
			new ParallelFlickrAdapter().loadImagesFor(sights);
			writer.write(new Gson().toJson(new Merger().merge(sights)));
		} catch (ParameterException e) {
			response.setStatus(400);
			writer.write(e.getMessage());
		} catch (AccessNotConfiguredException e) {
			response.setStatus(500);
			writer.write(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
