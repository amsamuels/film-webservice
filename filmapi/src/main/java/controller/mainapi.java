package controller;

import java.io.IOException;


import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ApiUtils.AppUtils;
import model.FilmDAO;
import model.Film;

/**
 * Servlet implementation class mainapi
 */
@WebServlet("/mainapi")
public class mainapi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public mainapi() {
		super();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String format = request.getHeader("Content-Type");
		String data = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
		Film f = new Film();
		AppUtils utils = new AppUtils();
		//  It first checks the content type of the request.
		if ("application/xml".equals(format)) {
			//If it’s XML, it uses the deconstructXmlArrayList method to parse the XML data
			f = utils.deconstructXmlArrayList(data);
		} else if ("application/json".equals(format)) {
			// If it’s JSON, it uses the deconstructJsonArrayList method to parse the JSON data.
			f = utils.deconstructJsonArrayList(data);
		} else {
			// If the content type is neither XML nor JSON, it uses
			// the split method to split the data into an array of strings.
			String[] parts = data.split("#");
			f.setTitle(parts[0]);
			f.setYear(Integer.parseInt(parts[1]));
			f.setDirector(parts[2]);
			f.setStars(parts[3]);
			f.setReview(parts[4]);
		}
		// setting the "Film" record to insert into the database
		FilmDAO filmdao = new FilmDAO();
		// insert Film
		try {
			response.setContentType("text/html");
			filmdao.insertFilm(f);
			PrintWriter out = response.getWriter();
			out.println("<h3> Film Inserted</h3>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String format = request.getHeader("Content-Type");
		String data = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
		Film f = new Film();
		AppUtils utils = new AppUtils();
		// The format of the request is checked and the appropriate data is extracted.
		if ("application/xml".equals(format)) {
		//If it’s XML, it uses the deconstructXmlArrayList method to parse the XML data

			f = utils.deconstructXmlArrayList(data);
		} else if ("application/json".equals(format)) {
		// If it’s JSON, it uses the deconstructJsonArrayList method to parse the JSON data.
			f = utils.deconstructJsonArrayList(data);
		} else {
			// If the content type is neither XML nor JSON, it uses
			// the split method to split the data into an array of strings.
			String[] parts = data.split("#");
			f.setId(Integer.parseInt(parts[0]));
			f.setTitle(parts[1]);
			f.setYear(Integer.parseInt(parts[2]));
			f.setDirector(parts[3]);
			f.setStars(parts[4]);
			f.setReview(parts[5]);
		}
		FilmDAO filmdao = new FilmDAO();
		// update film
		try {
			response.setContentType("text/html");
			filmdao.updateFilm(f);
			PrintWriter out = response.getWriter();
			out.println("<h3> Film Updated</h3>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String format = request.getHeader("Content-Type");
		String data = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
		Film f = new Film();
		AppUtils utils = new AppUtils();
		// The format of the request is checked and the appropriate data is extracted.
		if ("application/xml".equals(format)) {
			//If it’s XML, it uses the deconstructXmlArrayList method to parse the XML data
			f = utils.deconstructXmlArrayList(data);
		} else if ("application/json".equals(format)) {
			// If it’s JSON, it uses the deconstructJsonArrayList method to parse the JSON data.
			f = utils.deconstructJsonArrayList(data);
		} else {
			// If the content type is neither XML nor JSON, it uses
			// the split method to split the data into an array of strings.
			String[] parts = data.split("#");
			f.setId(Integer.parseInt(parts[0]));
		}
		FilmDAO filmdao = new FilmDAO();
		try {
			filmdao.deleteFilm(f);
			PrintWriter out = response.getWriter();
			out.println("<h3> Film Deleted</h3>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("text/html");
	}
}
