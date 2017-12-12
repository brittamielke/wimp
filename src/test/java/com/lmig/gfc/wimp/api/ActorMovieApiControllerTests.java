package com.lmig.gfc.wimp.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.models.Movie;
import com.lmig.gfc.wimp.repositories.ActorRepository;
import com.lmig.gfc.wimp.repositories.MovieRepository;

public class ActorMovieApiControllerTests {

	private ActorMovieApiController controller;
	@Mock
	private MovieRepository movieRepo;
	@Mock
	private ActorRepository actorRepo;

	@Before
	public void SetUp() {
		MockitoAnnotations.initMocks(this);
		controller = new ActorMovieApiController(actorRepo, movieRepo);
	}

	@Test
	public void create_saves_movie_and_returns_it() {
		// arrange
		Movie movie = new Movie();
		Actor actor = new Actor();
		movie.setActors(new HashSet<Actor>());
		when(movieRepo.findOne(21L)).thenReturn(movie);
		when(actorRepo.findOne(75L)).thenReturn(actor);
		// act
		Movie actual = controller.create(21L, 75L);
		// assert
		assertThat(actual).isSameAs(movie);
		verify(movieRepo).save(movie);
		assertThat(movie.getActors().contains(actor));
		verify(movieRepo).findOne(21L);
		verify(actorRepo).findOne(75L);
	}

	// @PostMapping("")
	// public Movie create(@PathVariable Long movieId, @RequestBody Long actorId) {
	// Movie movie = movieRepo.findOne(movieId);
	// Actor actor = actorRepo.findOne(actorId);
	// movie.getActors().add(actor);
	// movieRepo.save(movie);
	// return movie;
	// }
}
