package com.revature.daos;

import java.util.ArrayList;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.Launcher;
import com.revature.models.Movie;
import com.revature.models.Director;

public class DirectorDao {
	
	SessionFactory sf;
	Logger logger = Logger.getRootLogger();
	
	public DirectorDao() {
		super();
		sf = Launcher.getSessionFactory();
	}

	public void insertDirector(Director director) {
		try(Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();
			session.persist(director);
			tx.commit();
		}
	}
	
	public void addMovie(Director director, Movie movie) {
		if(director.getMovies() == null) {
			director.setMovies(new ArrayList<Movie>());
		}
		
		if(director.getMovies().contains(movie)) {
			logger.warn("Movie already in director!");
			return;
		}
		
		try(Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();
			director.getMovies().add(movie);
			director = (Director) session.merge(director);
			tx.commit();
		}
	}
	
	public Optional<Director> getDirector(int id) {
		try(Session session = sf.openSession()) {
			Director director = session.get(Director.class, id);
			
			if (director == null) return Optional.empty();
			
			Hibernate.initialize(director.getMovies());
			return Optional.of(director);
		}
	}
	
	public Director updateDirector(Director director) {
		try(Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();
			director = (Director) session.merge(director);
			tx.commit();
			return director;
		}
	}
	
	public void deleteDirector(Director director) {
		try(Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();
			session.delete(director);
			tx.commit();
		}
	}

}
