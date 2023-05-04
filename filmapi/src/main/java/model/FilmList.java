package model;

import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "films")
public class FilmList {
	@XmlElement(name = "film")
	private ArrayList<Film> filmList;
	
	public FilmList() {}
	
	public FilmList(ArrayList<Film> filmList) {
		this.filmList = filmList;
	}
	
	public FilmList(Film film) {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Film> getFilmList() {
		return filmList;
	}
	
	public void setFilmList(ArrayList<Film> filmList) {
		this.filmList = filmList;
	}

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setFilmList(Film f) {
		// TODO Auto-generated method stub
		
	}

}