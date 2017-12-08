package com.lmig.gfc.wimp.api;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.models.Award;
import com.lmig.gfc.wimp.models.Movie;

public class ActorView {

	private Actor actor;

	public ActorView(Actor actor) {
		this.actor = actor;
	}

	public Long getId() {
		return actor.getId();
	}

	public String getFirstName() {
		return actor.getFirstName();
	}

	public String getLastName() {
		return actor.getLastName();
	}

	public Long getActiveSinceYear() {
		return actor.getActiveSinceYear();
	}

	public Date getBirthDate() {
		return actor.getBirthDate();
	}

	public Set<MovieView> getMovies() {
		HashSet<MovieView> views = new HashSet<MovieView>();
		for (Movie movie : actor.getMovies()) {
			views.add(new MovieView(movie));
		}
		return views;
	}

	public Set<Award> getAwards() {
		return actor.getAwards();
	}

}
