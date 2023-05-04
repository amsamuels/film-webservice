package model;

import java.util.ArrayList;


import java.sql.*;

public class FilmDAO {

	Film oneFilm = null;
	Connection conn = null;
	Statement stmt = null;
	String user = "abiolaal";
	String password = "bretswAv6";
	// Note none default port used, 6306 not 3306
	String url = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:6306/" + user;
	
	public FilmDAO() {
	}

	@SuppressWarnings("deprecation")
	private void openConnection() {
		// loading jdbc driver for mysql
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.out.println(e);
		}

		// connecting to database
		try {
			// connection string for demos database, username demos, password demos
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
		} catch (SQLException se) {
			System.out.println(se);
		}
	}

	private void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Film getNextFilm(ResultSet rs) {
		Film thisFilm = null;
		try {
			thisFilm = new Film(rs.getInt("id"), rs.getString("title"), rs.getInt("year"), rs.getString("director"),
					rs.getString("stars"), rs.getString("review"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return thisFilm;
	}

	public ArrayList<Film> getAllFilms() {

		ArrayList<Film> allFilms = new ArrayList<Film>();
		openConnection();

		// Create select statement and execute it
		try {
			String selectSQL = "select * from films";
			ResultSet rs1 = stmt.executeQuery(selectSQL);
			System.out.println(rs1);
			// Retrieve the results
			while (rs1.next()) {
				oneFilm = getNextFilm(rs1);
				allFilms.add(oneFilm);
			}

			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}

		return allFilms;
	}
	
	public ArrayList<Film> getFilmByName(String name) {
	   	ArrayList<Film> filmname = new ArrayList<Film>();

		openConnection();
		oneFilm = null;
		// Create select statement and execute it
		try {
			String selectSQL = "select * from films where title like '%" + name +"%'";
			ResultSet rs1 = stmt.executeQuery(selectSQL);
			// Retrieve the results
			while (rs1.next()) {
				oneFilm = getNextFilm(rs1);
				filmname.add(oneFilm);
			}

			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}

		return filmname;
	}
	
	public ArrayList<Film> getFilmByYear(int year) {
	   	ArrayList<Film> filmYear = new ArrayList<Film>();

		openConnection();
		oneFilm = null;
		// Create select statement and execute it
		try {
			String selectSQL = "select * from films where year like '%" + year +"%'";
			ResultSet rs1 = stmt.executeQuery(selectSQL);
			// Retrieve the results
			while (rs1.next()) {
				oneFilm = getNextFilm(rs1);
				filmYear.add(oneFilm);
			}

			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}

		return filmYear;
	}
	
	

	public Film getFilmByID(int id) {

		openConnection();
		oneFilm = null;
		// Create select statement and execute it
		try {
			String selectSQL = "select * from films where id=" + id;
			ResultSet rs1 = stmt.executeQuery(selectSQL);
			// Retrieve the results
			while (rs1.next()) {
				oneFilm = getNextFilm(rs1);
			}

			stmt.close();
			closeConnection();
		} catch (SQLException se) {
			System.out.println(se);
		}

		return oneFilm;
	}

	/**
	 * Insert Film into database
	 * 
	 * @param f Film Object
	 * @return True if inserted
	 * @throws SQLException Any error message thrown
	 */
	public boolean insertFilm(Film f) throws SQLException {
		
		openConnection();
		boolean b = false;
		try {
			String sql = "insert into films (Title, Year, Director, Stars, Review) values ('" + f.getTitle() + "','" + f.getYear() + "','"
					+ f.getDirector() + "','" + f.getStars() + "','" + f.getReview() + "');";
			System.out.println(sql);
			b = stmt.execute(sql);
			b = true;
		} catch (SQLException s) {
			throw new SQLException("FILM Not Added");
		}
		closeConnection();
		return b;
	}

	public boolean updateFilm(Film f) throws SQLException {
		openConnection();
		boolean b = false;
		try {
			String updateSQL = "UPDATE  films "
		    		+ "SET title = '" + f.getTitle().toUpperCase() +"', "
		    		+ "year = '" + f.getYear() +"', "
		    		+ "director = '" + f.getDirector().toUpperCase() +"', "
		    		+ "stars = '" + f.getStars().toUpperCase() +"', "
		    		+ "review = '" + f.getReview().toUpperCase() +"'"
		    		+ "WHERE id = '" + f.getId() + "';"
		    		;
			System.out.println(updateSQL);
			b = stmt.execute(updateSQL);
			b = true;
		} catch (SQLException s) {
			throw new SQLException("FILM Not Updated");
		}
		closeConnection();
		return b;

	}
	public boolean deleteFilm(Film f) throws SQLException {
		openConnection();
		boolean b = false;
		try {	  
			 String deleteSQL = "DELETE FROM films WHERE id='" + f.getId() + "';";			  System.out.println(deleteSQL);
			    b = stmt.execute(deleteSQL);
			    closeConnection();
		} catch (SQLException s) {
			throw new SQLException("Film Not Deleted");
		}
		closeConnection();
		return b;
	}

}
