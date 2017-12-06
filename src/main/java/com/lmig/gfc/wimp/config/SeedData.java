package com.lmig.gfc.wimp.config;

import java.util.Date;

import org.springframework.context.annotation.Configuration;

import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.models.Movie;
import com.lmig.gfc.wimp.repositories.ActorRepository;
import com.lmig.gfc.wimp.repositories.MovieRepository;

@Configuration
public class SeedData {

	public SeedData(ActorRepository actorRepo, MovieRepository movieRepo) {
		actorRepo.save(new Actor("Brad", "Pitt", 1984L, new Date(1975, 3, 2)));
		actorRepo.save(new Actor("Julia", "Roberts", 1984L, null));
		movieRepo.save(new Movie("Pretty Woman", new Date(2009, 12, 12), 12000000L, "MGM"));
		movieRepo.save(new Movie("Benjamin Button", new Date(2003, 8, 20), 27000000L, "Lions Gate"));
	}
}
