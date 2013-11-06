package de.unima.sempoi.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.unima.sempoi.server.adapter.dbpedia.Dbpedia;
import de.unima.sempoi.server.adapter.dbpedia.DbpediaAdapter;
import de.unima.sempoi.server.adapter.exception.AccessNotConfiguredException;
import de.unima.sempoi.server.adapter.exception.ParameterException;
import de.unima.sempoi.server.adapter.freebase.FreebaseAdapter;
import de.unima.sempoi.server.model.freebase.City;

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
			Set<City> cities = new FreebaseAdapter().readSightsOfCity(request.getParameter("city"));
			System.out.println(cities);
			DbpediaAdapter dbpediaAdapter = new DbpediaAdapter();
			for(City city : cities) {
				System.out.println(Dbpedia.getQuery(city.getAttractionNames()));
				dbpediaAdapter.query(city.getAttractionNames());
			}
//			writer.write(new Gson().toJson(sightNames));
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
