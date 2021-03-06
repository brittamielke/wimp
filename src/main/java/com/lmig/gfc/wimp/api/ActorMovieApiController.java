package com.lmig.gfc.wimp.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.models.Movie;
import com.lmig.gfc.wimp.repositories.ActorRepository;
import com.lmig.gfc.wimp.repositories.MovieRepository;

@RestController
@RequestMapping("/api/movies/{movieId}/actors")
public class ActorMovieApiController {

	private ActorRepository actorRepo;
	private MovieRepository movieRepo;

	public ActorMovieApiController(ActorRepository actorRepo, MovieRepository movieRepo) {
		this.actorRepo = actorRepo;
		this.movieRepo = movieRepo;
	}

	@PostMapping("")
	public Movie create(@PathVariable Long movieId, @RequestBody Long actorId) {
		Movie movie = movieRepo.findOne(movieId);
		Actor actor = actorRepo.findOne(actorId);
		movie.getActors().add(actor);
		movieRepo.save(movie);
		return movie;
	}
}
