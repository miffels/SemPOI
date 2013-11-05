package de.unima.sempoi.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import de.unima.sempoi.server.adapter.Freebase;

/**
 * Servlet implementation class SemPOI
 */
public class SemPOI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SemPOI() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		try {
			writer.write(new Gson().toJson(new Freebase().readSightsOfCity(request.getParameter("city"))));
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
