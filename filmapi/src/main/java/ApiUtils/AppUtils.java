package ApiUtils;


import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import model.Film;
import model.FilmList;

public class AppUtils {

	public String arrayListToXmlFormat(ArrayList<Film> data) {

		FilmList films = new FilmList();
		films.setFilmList(data);

		JAXBContext context;

		StringWriter sw = new StringWriter();
		try {
			context = JAXBContext.newInstance(FilmList.class);
			Marshaller mar = (Marshaller) context.createMarshaller();
			mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			mar.marshal(films, sw);

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sw.toString();

	}

	public String arrayListToJsonFormat(ArrayList<Film> data) {

		FilmList films = new FilmList();
		films.setFilmList(data);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(films);
		return json;

	}

	public String arrayListToTextFormat(ArrayList<Film> data) {

		String filmString = "";

		for (Film film1 : data) {
			filmString = filmString + film1.toString();
		}

		return filmString;

	}

	public Film deconstructXmlArrayList(String data) {
		Film returnedFilm = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Film.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			returnedFilm = (Film) jaxbUnmarshaller.unmarshal(new StringReader(data));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnedFilm;

	}

	public Film deconstructJsonArrayList(String data) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.fromJson(data, Film.class);

	}

	public String[] deconstructTextArrayList(String data) {
		String[] parts = data.split("#");
		return parts;

	}

	public String objectToXmlFormat(Film data) {
		ArrayList<Film> filmData = new ArrayList<>();
		filmData.add(data);
		System.out.println(data.getStars());
		FilmList films = new FilmList();
		films.setFilmList(filmData);
		StringWriter sw = new StringWriter();
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(FilmList.class);
			Marshaller mar = (Marshaller) context.createMarshaller();
			mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			mar.marshal(films, sw);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sw.toString();

	}

	public String objectToJsonFormat(Film data) {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(data);
		return json;

	}
	

}
