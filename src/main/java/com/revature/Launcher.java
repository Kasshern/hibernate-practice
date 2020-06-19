package com.revature;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.revature.daos.ActorDao;
import com.revature.daos.MovieDao;
import com.revature.daos.DirectorDao;
import com.revature.models.Actor;
import com.revature.models.Movie;
import com.revature.models.Director;


public class Launcher {
private static SessionFactory sf;
	
//	private static ActorDao actorDao;
//	private static MovieDao movieDao;
//	private static DirectorDao directorDao;

	
	public static SessionFactory getSessionFactory() {
		return sf;
	}
	
	static Logger logger = Logger.getRootLogger();
	
	
	static SessionFactory configureHibernate() {
		// Create jdbc url from database URL
		String jdbcUrl = String.format("jdbc:postgresql://%s:5432/postgres", 
				System.getenv("NODE_APP_URL"));

		// Create configuration instance
		Configuration configuration = new Configuration()
				.configure()
				.setProperty("hibernate.connection.username", System.getenv("NODE_APP_ROLE"))
				.setProperty("hibernate.connection.url", jdbcUrl)
				.setProperty("hibernate.connection.password", System.getenv("NODE_APP_PASS"))
				.addAnnotatedClass(Actor.class)
				.addAnnotatedClass(Movie.class)
				.addAnnotatedClass(Director.class);
		
		// Use configuration to create serviceRegistry
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
		
		// Use configuration to create sessionFactory, passing in serviceRegistry
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}
	

	static void runCrudDemo() {
		ActorDao actorDao = new ActorDao();

//		Optional<Actor> actor = actorDao.selectActor(2);
//		if(actor.isPresent()) {
//			System.out.println(actor);
//		} else {
//			System.out.println("No actor with the provided id");
//		}
		
		Actor brandNewActor = new Actor(0, "male", "Grizzly Bear", "Christian Bale",  LocalDate.now(), 1.85);
		actorDao.insertActor(brandNewActor);
		
		
		Actor loadedActor = actorDao.selectActor(1);
		System.out.println(loadedActor);
		
//		LocalDate birthDate = loadedActor.getDateOfBirth();
//		LocalDate modifiedDate = birthDate.minus(1, ChronoUnit.DAYS);
//		loadedBear.setDateOfBirth(modifiedDate);
//		bearDao.updateBear(loadedBear);
//		
//		Bear brandNewBear = new Bear(0, "male", "Grizzly Bear", LocalDate.now(), 100.0);
//		bearDao.insertBear(brandNewBear);
//		bearDao.deleteBear(brandNewBear);
	}
////	
//	public static void relationalDemo() {
//		ActorDao actorDao = new ActorDao();
//		MovieDao movieDao = new MovieDao();
//		DirectorDao directorDao = new DirectorDao();

		
//		Cave cave = new Cave(0, "Bear Cave", "Underground", 500);
//		caveDao.insertCave(cave);
//		Bear bear = bearDao.loadBear(1);
//		caveDao.addBear(cave, bear);
		
//		Optional<Cave> cave = caveDao.getCave(4);
//		logger.warn(cave.get());
		
//		List<Bear> bears = bearDao.getBearsByCaveId(4);
//		System.out.println(bears);
		
//		Bear bear = bearDao.selectBear(1).get();
//		logger.warn(bear);
//		logger.warn(bear.getCave());

		// This will work because we have configured Cave to cascade persist operations
		// to their bears
//		List<Bear> bears = new ArrayList<>();
//		
//		bears.add(new Bear(0, "female", "Panda", LocalDate.now(), 1000));
//		bears.add(new Bear(0, "female", "Brown Bear", LocalDate.now(), 1000));
//		
//		Cave cave = new Cave(0, "Luminous Cave", "Canada", 10000, bears);
//		caveDao.insertCave(cave);
		
		
//		Bear bear = bearDao.loadBear(1);
//		Bear cubA = bearDao.loadBear(5);
//		Bear cubB = bearDao.loadBear(6);
//		
//		bearDao.addCubs(bear, cubA, cubB);		
//	}
	
	public static void main(String[] args) {
		sf = configureHibernate();

		try {
			runCrudDemo();
		} catch(PersistenceException e) {
			e.printStackTrace();
			sf.close();
		}
		
	}
}

