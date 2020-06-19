package com.revature.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Director {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String gender;
	private String bio;
	private String name;
	
	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	
	private int nominations;
	private int wins;
	
	// The many side loads lazily by default
		@OneToMany(cascade = CascadeType.PERSIST)
		@JoinColumn(name = "director_id")
		private List<Movie> movies;

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

		public int getNominations() {
			return nominations;
		}

		public void setNominations(int nominations) {
			this.nominations = nominations;
		}

		public int getWins() {
			return wins;
		}

		public void setWins(int wins) {
			this.wins = wins;
		}

		public List<Movie> getMovies() {
			return movies;
		}

		public void setMovies(List<Movie> movies) {
			this.movies = movies;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((bio == null) ? 0 : bio.hashCode());
			result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
			result = prime * result + ((gender == null) ? 0 : gender.hashCode());
			result = prime * result + id;
			result = prime * result + ((movies == null) ? 0 : movies.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + nominations;
			result = prime * result + wins;
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
			Director other = (Director) obj;
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
			if (nominations != other.nominations)
				return false;
			if (wins != other.wins)
				return false;
			return true;
		}

		public Director(int id, String gender, String bio, String name, LocalDate dateOfBirth, int nominations,
				int wins, List<Movie> movies) {
			super();
			this.id = id;
			this.gender = gender;
			this.bio = bio;
			this.name = name;
			this.dateOfBirth = dateOfBirth;
			this.nominations = nominations;
			this.wins = wins;
			this.movies = movies;
		}
		
	


}

	