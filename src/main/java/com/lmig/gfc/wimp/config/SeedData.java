package com.lmig.gfc.wimp.config;

import java.util.Date;
import java.util.HashSet;

import org.springframework.context.annotation.Configuration;

import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.models.Movie;
import com.lmig.gfc.wimp.repositories.ActorRepository;
import com.lmig.gfc.wimp.repositories.MovieRepository;

@Configuration
public class SeedData {

	public SeedData(ActorRepository actorRepo, MovieRepository movieRepo) {

		Actor actorOne = actorRepo.save(new Actor("Julia", "Roberts", 1984L, null));
		Actor actorTwo = actorRepo.save(new Actor("Bradley", "Cooper", 1995L, null));
		Actor actorThree = actorRepo.save(new Actor("Brad", "Pitt", 1984L, new Date(1975, 3, 2)));

		Movie movieOne = movieRepo.save(new Movie("Pretty Woman", new Date(2009, 12, 12), 12000000L, "MGM"));
		movieRepo.save(new Movie("Pitch Perfect", new Date(2010, 9, 25), 36000000L, "DreamWorks"));
		Movie movieTwo = movieRepo.save(new Movie("Benjamin Button", new Date(2003, 8, 20), 27000000L, "Lions Gate"));

		HashSet<Actor> actorsListOne = new HashSet<Actor>();
		actorsListOne.add(actorOne);
		actorsListOne.add(actorTwo);
		movieOne.setActors(actorsListOne);
		movieRepo.save(movieOne);

		HashSet<Actor> actorsListTwo = new HashSet<Actor>();
		actorsListTwo.add(actorThree);
		actorsListTwo.add(actorTwo);
		movieTwo.setActors(actorsListTwo);
		movieRepo.save(movieTwo);

	}
}
