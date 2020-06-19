package com.revature.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.Launcher;
import com.revature.models.Actor;
import com.revature.models.Movie;

public class ActorDao {

	SessionFactory sf;

	public ActorDao() {
		super();
		sf = Launcher.getSessionFactory();
	}
	
	public void insertActor(Actor actor) {
		try(Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();
			session.persist(actor);
			tx.commit();
		}
	}
	
	/**
	 * Reading data from database
	 * get() - EAGER retrieval from the database - provides persistent object or null 
	 */
		public Actor selectActor(int id) {
		try(Session session = sf.openSession()) {
			return (session.get(Actor.class, id));
		}
	}
	
	/**
	 * Creates a proxy for an object.
	 * The proxy will initialize lazily when it is needed.
	 * The proxy can only initialize while the session that loaded is still ongoing.
	 * Will throw LazyInitializationException if we attempt to load the proxy outside of a
	 * session.
	 * Will throw ObjectNotFoundException upon initialization if an object with that ID does
	 * not exist.
	 * @param id
	 * @return
	 */
	public Actor loadActor(int id) {
		try(Session session = sf.openSession()) {
			Actor actor = session.load(Actor.class, id);
			
			// Explicitly load in a proxy
			Hibernate.initialize(actor);
			return actor;
		}
	}
	
	/*
	 * Updates a Actor
	 * update could throw NonUniqueObjectException if there is a persistent reference tracked
	 * 	by the session
	 * 
	 */
	public void updateActor(Actor actor) {
		try(Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();
			Actor sameActor = session.get(Actor.class, actor.getId());
//			session.update(actor);
			actor = (Actor) session.merge(actor);
			tx.commit();
		}
	}
	
	public void deleteActor(Actor actor) {
		try(Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();
			session.delete(actor);
			tx.commit();
		}
	}
	
	public List<Actor> getActorsByMovieId(int movieId) {
		try(Session session = sf.openSession()) {
			// Retrieve associated instance
			Movie movie = session.get(Movie.class, movieId);
			if (movie == null) return null;
			
			// Extract the list of actors
			List<Actor> actors = movie.getActors();
			
			// Initialize this collection
			Hibernate.initialize(actors);
			
			// Return
			return actors;
		}
	}
}
	
//	public void addCubs(Actor actor, Actor... cubs) {
//		try(Session session = sf.openSession()) {
//			actor = session.get(Actor.class, actor.getId());
//			Transaction tx = session.beginTransaction();
//			
//			if(actor.getCubs() == null) {
//				actor.setCubs(new ArrayList<Actor>());
//			}
//			
//			for(Actor cub : cubs) {
//				actor.getCubs().add(cub);
//			}
//			
//			session.merge(actor);
//			tx.commit();
//		}
//	}
//
//}
