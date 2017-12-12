package com.lmig.gfc.wimp.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.models.Award;
import com.lmig.gfc.wimp.repositories.ActorRepository;
import com.lmig.gfc.wimp.repositories.AwardRepository;

public class ActorAwardApiControllerTests {

	private ActorAwardApiController controller;
	@Mock
	private ActorRepository actorRepo;
	@Mock
	private AwardRepository awardRepo;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new ActorAwardApiController(actorRepo, awardRepo);
	}

	@Test
	public void create_saves_award_to_actor_and_returns_it() {
		// arrange
		Actor actor = new Actor();
		Award award = new Award();
		award.setActor(actor);
		when(actorRepo.findOne(4L)).thenReturn(actor);

		// act
		Actor actual = controller.create(4L, award);

		// assert
		assertThat(actual).isSameAs(actor);
		verify(actorRepo).findOne(4L);
		verify(awardRepo).save(award);
		assertThat(award.getActor().equals(actor));

	}
	// @PostMapping("")
	// public Actor create(@PathVariable Long actorId, @RequestBody Award award) {
	// Actor actor = actorRepo.findOne(actorId);
	// award.setActor(actor);
	// awardRepo.save(award);
	// return actor;
	// }

}
