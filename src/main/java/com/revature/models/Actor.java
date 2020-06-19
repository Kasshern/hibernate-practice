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
@Table(name = "actors")
public class Actor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String gender;
	private String bio;
	private String name;
	
	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;
	
	/** height in meters */
	private double height;
		
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

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getBio() {
			return bio;
		}

		public void setBio(String bio) {
			this.bio = bio;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public LocalDate getDateOfBirth() {
			return dateOfBirth;
		}

		public void setDateOfBirth(LocalDate dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}

		public double getHeight() {
			return height;
		}

		public void setHeight(double height) {
			this.height = height;
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
			result = prime * result + ((bio == null) ? 0 : bio.hashCode());
			result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
			result = prime * result + ((gender == null) ? 0 : gender.hashCode());
			long temp;
			temp = Double.doubleToLongBits(height);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			result = prime * result + id;
			result = prime * result + ((movies == null) ? 0 : movies.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
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
			Actor other = (Actor) obj;
			if (actors == null) {
				if (other.actors != null)
					return false;
			} else if (!actors.equals(other.actors))
				return false;
			if (bio == null) {
				if (other.bio != null)
					return false;
			} else if (!bio.equals(other.bio))
				return false;
			if (dateOfBirth == null) {
				if (other.dateOfBirth != null)
					return false;
			} else if (!dateOfBirth.equals(other.dateOfBirth))
				return false;
			if (gender == null) {
				if (other.gender != null)
					return false;
			} else if (!gender.equals(other.gender))
				return false;
			if (Double.doubleToLongBits(height) != Double.doubleToLongBits(other.height))
				return false;
			if (id != other.id)
				return false;
			if (movies == null) {
				if (other.movies != null)
					return false;
			} else if (!movies.equals(other.movies))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

	public Actor(int id, String gender, String bio, String name, LocalDate dateOfBirth, double height) {
		super();
		this.id = id;
		this.gender = gender;
		this.bio = bio;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.height = height;
		
	}

	
	@Override
	public String toString() {
		return "Actor [id=" + id + ", gender=" + gender + ", bio=" + bio + ", name=" + name + ", dateOfBirth="
				+ dateOfBirth + ", height=" + height + "]";
	}

	public Actor() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}