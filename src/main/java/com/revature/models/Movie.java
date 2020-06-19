package com.revature.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Movie {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String title;
	private String genre;
	private double duration; 
	
	@Column(name = "release_date")
	private LocalDate releaseDate;
	
	@Column(name = "gross_us")
	private double  grossUs;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "director_id")
	private Director director;
	
	@ManyToMany
	@JoinTable(name="actor_movies", joinColumns = { @JoinColumn(name="actor_id") },
						inverseJoinColumns = { @JoinColumn(name="movie_id")})
	private List<Movie> movies;

	@ManyToMany
	@JoinTable(name="actor_movies", joinColumns = { @JoinColumn(name="movie_id") },
						inverseJoinColumns = { @JoinColumn(name="actor_id")})
	private List<Actor> actors;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public double getGrossUs() {
		return grossUs;
	}

	public void setGrossUs(double grossUs) {
		this.grossUs = grossUs;
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actors == null) ? 0 : actors.hashCode());
		result = prime * result + ((director == null) ? 0 : director.hashCode());
		long temp;
		temp = Double.doubleToLongBits(duration);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		temp = Double.doubleToLongBits(grossUs);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((movies == null) ? 0 : movies.hashCode());
		result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (actors == null) {
			if (other.actors != null)
				return false;
		} else if (!actors.equals(other.actors))
			return false;
		if (director == null) {
			if (other.director != null)
				return false;
		} else if (!director.equals(other.director))
			return false;
		if (Double.doubleToLongBits(duration) != Double.doubleToLongBits(other.duration))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (Double.doubleToLongBits(grossUs) != Double.doubleToLongBits(other.grossUs))
			return false;
		if (id != other.id)
			return false;
		if (movies == null) {
			if (other.movies != null)
				return false;
		} else if (!movies.equals(other.movies))
			return false;
		if (releaseDate == null) {
			if (other.releaseDate != null)
				return false;
		} else if (!releaseDate.equals(other.releaseDate))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public Movie(int id, String title, String genre, double duration, LocalDate releaseDate, double grossUs,
			Director director, List<Movie> movies, List<Actor> actors) {
		super();
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.duration = duration;
		this.releaseDate = releaseDate;
		this.grossUs = grossUs;
		this.director = director;
		this.movies = movies;
		this.actors = actors;
	}


	
}