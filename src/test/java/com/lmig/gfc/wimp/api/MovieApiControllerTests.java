package com.lmig.gfc.wimp.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lmig.gfc.wimp.models.Movie;
import com.lmig.gfc.wimp.repositories.MovieRepository;

public class MovieApiControllerTests {

	private MovieApiController controller;
	@Mock
	private MovieRepository movieRepo;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new MovieApiController(movieRepo);
	}

	@Test
	public void getAll_returns_list_of_movies() {
		// arrange
		ArrayList<Movie> movies = new ArrayList<Movie>();
		when(movieRepo.findAll()).thenReturn(movies);
		// act
		List<Movie> actual = controller.getAll();
		// assert
		assertThat(actual).isSameAs(movies);
		verify(movieRepo).findAll();
	}
	// @GetMapping("")
	// public List<Movie> getAll() {
	// return movieRepo.findAll();
	// }

	@Test
	public void getOne_returns_a_movie_for_a_valid_id() {
		// arrange
		Movie movie = new Movie();
		when(movieRepo.findOne(10L)).thenReturn(movie);
		// act
		Movie actual = controller.getOne(10L);
		// assert
		assertThat(actual).isSameAs(movie);
		verify(movieRepo, atLeastOnce()).findOne(10L);
	}

	@Test
	public void getOne_returns_a_movie_for_an_invalid_id() {
		// arrange
		when(movieRepo.findOne(10L)).thenReturn(null);
		// act
		Movie actual = controller.getOne(10L);
		// assert
		assertThat(actual).isNull();
		verify(movieRepo, atLeastOnce()).findOne(10L);
	}
	// @GetMapping("{id}")
	// public Movie getOne(@PathVariable Long id) {
	// return movieRepo.findOne(id);
	// }

	@Test
	public void create_saves_movie_and_returns_it() {
		// arrange
		Movie movie = new Movie();
		when(movieRepo.save(movie)).thenReturn(movie);
		// act
		Movie actual = controller.create(movie);
		// assert
		assertThat(actual).isSameAs(movie);
		verify(movieRepo).save(movie);
	}
	// @PostMapping("")
	// public Movie create(@RequestBody Movie movie) {
	// return movieRepo.save(movie);
	// }

	@Test
	public void update_sets_id_of_movie_saves_and_returns_movie() {
		// arrange
		Movie movie = new Movie();
		when(movieRepo.save(movie)).thenReturn(movie);
		// act
		Movie actual = controller.update(movie, 93L);
		// assert
		assertThat(actual).isSameAs(movie);
		verify(movieRepo).save(movie);
		assertThat(movie.getId()).isEqualTo(93L);
	}
	// @PutMapping("{id}")
	// public Movie update(@RequestBody Movie movie, @PathVariable Long id) {
	// movie.setId(id);
	// return movieRepo.save(movie);
	// }

	@Test
	public void delete_gets_movie_and_deletes_it_from_repo_and_returns_it() {
		// arrange
		Movie movie = new Movie();
		when(movieRepo.findOne(85L)).thenReturn(movie);
		// act
		Movie actual = controller.delete(85L);
		// assert
		assertThat(actual).isSameAs(movie);
		verify(movieRepo).findOne(85L);
		verify(movieRepo).delete(85L);
	}
	// @DeleteMapping("{id}")
	// public Movie delete(@PathVariable Long id) {
	// Movie movie = movieRepo.findOne(id);
	// movieRepo.delete(id);
	// return movie;
	// }

}
