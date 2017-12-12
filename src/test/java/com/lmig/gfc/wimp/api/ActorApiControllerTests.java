package com.lmig.gfc.wimp.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.lmig.gfc.wimp.models.Actor;
import com.lmig.gfc.wimp.repositories.ActorRepository;

public class ActorApiControllerTests {

	private ActorApiController controller;
	@Mock
	private ActorRepository actorRepo;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new ActorApiController(actorRepo);
	}

	@Test
	public void getAll_returns_list_of_actors() {
		ArrayList<Actor> actorList = new ArrayList<Actor>();
		when(actorRepo.findAll()).thenReturn(actorList);

		// act
		List<ActorView> actual = controller.getAll();
		// assert
		assertThat(actual.size()).isEqualTo(actorList.size());
		verify(actorRepo).findAll();
	}

	// @GetMapping("")
	// public List<ActorView> getAll() {
	// List<Actor> actors = actorRepo.findAll();
	// ArrayList<ActorView> actorViews = new ArrayList<ActorView>();
	// for (Actor actor : actors) {
	// actorViews.add(new ActorView(actor));
	// }
	// return actorViews;
	// }

	@Test
	public void getOne_returns_an_actor_for_a_valid_id() {
		// arrange
		Actor actor = new Actor();
		when(actorRepo.findOne(6L)).thenReturn(actor);

		// act
		ActorView actual = controller.getOne(6L);

		// assert
		assertThat(actual.getId()).isEqualTo(actor.getId());
		verify(actorRepo).findOne(6L);
	}

	@Test
	public void getOne_returns_an_actor_for_an_invalid_id() {
		// arrange
		when(actorRepo.findOne(6L)).thenReturn(null);

		// act
		ActorView actual = controller.getOne(6L);
		// assert
		assertThat(actual).isNull();
		verify(actorRepo).findOne(6L);
	}
	// @GetMapping("{id}")
	// public ActorView getOne(@PathVariable Long id) {
	// Actor actor = actorRepo.findOne(id);
	// ActorView actorView = new ActorView(actor);
	// return actorView;
	// }

	@Test
	public void create_saves_actor_and_returns_it() {
		// arrange
		Actor actor = new Actor();
		when(actorRepo.save(actor)).thenReturn(actor);
		// act
		Actor actual = controller.create(actor);
		// assert
		assertThat(actual).isSameAs(actor);
		verify(actorRepo).save(actor);
	}
	// @PostMapping("")
	// @ResponseStatus(code = HttpStatus.CREATED)
	// public Actor create(@RequestBody Actor actor) {
	// return actorRepo.save(actor);
	// }

	@Test
	public void update_sets_id_of_actor_and_saves_and_returns_it() {
		// arrange
		Actor actor = new Actor();
		when(actorRepo.save(actor)).thenReturn(actor);
		// act
		Actor actual = controller.update(actor, 45L);
		// assert
		assertThat(actual).isSameAs(actor);
		verify(actorRepo).save(actor);
		assertThat(actor.getId()).isEqualTo(45L);
	}
	// @PutMapping("{id}")
	// public Actor update(@RequestBody Actor actor, @PathVariable Long id) {
	// actor.setId(id);
	// return actorRepo.save(actor);
	// }

	@Test
	public void delete_gets_the_dog_and_deletes_it_from_the_repo_and_returns_it() {
		// arrange
		Actor actor = new Actor();
		when(actorRepo.findOne(23L)).thenReturn(actor);
		// act
		Actor actual = controller.delete(23L);
		// assert
		assertThat(actual).isSameAs(actor);
		verify(actorRepo).findOne(23L);
		verify(actorRepo).delete(23L);
	}
	// @DeleteMapping("{id}")
	// public Actor delete(@PathVariable Long id) {
	// Actor actor = actorRepo.findOne(id);
	// actorRepo.delete(id);
	// return actor;
	// }

}
