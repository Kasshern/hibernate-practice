package com.revature.daos;

import java.util.ArrayList;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.Launcher;
import com.revature.models.Actor;
import com.revature.models.Movie;

public class MovieDao {
	
	SessionFactory sf;
	Logger logger = Logger.getRootLogger();
	
	public MovieDao() {
		super();
		sf = Launcher.getSessionFactory();
	}

	public void insertMovie(Movie movie) {
		try(Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();
			session.persist(movie);
			tx.commit();
		}
	}
	
	public void addActor(Movie movie, Actor actor) {
		if(movie.getActors() == null) {
			movie.setActors(new ArrayList<Actor>());
		}
		
		if(movie.getActors().contains(actor)) {
			logger.warn("Actor already in movie!");
			return;
		}
		
		try(Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();
			movie.getActors().add(actor);
			movie = (Movie) session.merge(movie);
			tx.commit();
		}
	}
	
	public Optional<Movie> getMovie(int id) {
		try(Session session = sf.openSession()) {
			Movie movie = session.get(Movie.class, id);
			
			if (movie == null) return Optional.empty();
			
			Hibernate.initialize(movie.getActors());
			return Optional.of(movie);
		}
	}
	
	public Movie updateMovie(Movie movie) {
		try(Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();
			movie = (Movie) session.merge(movie);
			tx.commit();
			return movie;
		}
	}
	
	public void deleteMovie(Movie movie) {
		try(Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();
			session.delete(movie);
			tx.commit();
		}
	}

}
