package controller;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ApiUtils.AppUtils;
import model.FilmDAO;
import model.Film;

@WebServlet("/getApi")
public class getApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public getApi() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		String format = request.getHeader("Accept");
		ArrayList<Film> returnAllfilm;
		Film film;
		FilmDAO filmdao = new FilmDAO();
		AppUtils utils = new AppUtils();
		PrintWriter out = response.getWriter();
		if (request.getParameterMap().containsKey("id")) {
			String filmid = request.getParameter("id");
			film = filmdao.getFilmByID(Integer.parseInt(filmid));
			if ("application/xml".equals(format)) {
				response.setContentType("application/xml");
				System.out.println(utils.objectToXmlFormat(film));
				out.write(utils.objectToXmlFormat(film));
			} else if ("application/json".equals(format)) {
				response.setContentType("application/json");
				out.write(utils.objectToJsonFormat(film));
			} else {
				response.setContentType("application/text");
				out.write(film.toString());
			}
			out.close();

		} else if (request.getParameterMap().containsKey("name")) {
			String filmName = request.getParameter("name");
			returnAllfilm = filmdao.getFilmByName(filmName);
			if ("application/xml".equals(format)) {
				response.setContentType("application/xml");
				out.write(utils.arrayListToXmlFormat(returnAllfilm));
			} else if ("application/json".equals(format)) {
				response.setContentType("application/json");
				out.write(utils.arrayListToJsonFormat(returnAllfilm));
			} else {
				response.setContentType("application/text");
				out.write(utils.arrayListToTextFormat(returnAllfilm));
			}
			out.close();
		}else if (request.getParameterMap().containsKey("year")) {
			String filmYear = request.getParameter("year");
			returnAllfilm = filmdao.getFilmByYear(Integer.parseInt(filmYear));
			System.out.println(returnAllfilm);
			if ("application/xml".equals(format)) {
				response.setContentType("application/xml");
				out.write(utils.arrayListToXmlFormat(returnAllfilm));
			} else if ("application/json".equals(format)) {
				response.setContentType("application/json");
				out.write(utils.arrayListToJsonFormat(returnAllfilm));
			} else {
				response.setContentType("application/text");
				out.write(utils.arrayListToTextFormat(returnAllfilm));
			}
			out.close();
		}
		else {
			returnAllfilm = filmdao.getAllFilms();
			if ("application/xml".equals(format)) {
				response.setContentType("application/xml");
				out.write(utils.arrayListToXmlFormat(returnAllfilm));
			} else if ("application/json".equals(format)) {
				response.setContentType("application/json");
				out.write(utils.arrayListToJsonFormat(returnAllfilm));
			} else {
				response.setContentType("application/text");
				out.write(utils.arrayListToTextFormat(returnAllfilm));
			}
			out.close();
		}
	}

}
