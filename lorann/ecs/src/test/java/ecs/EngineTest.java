package ecs;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

class Sys1 extends System {
	{
		targets.add(C1.class);
	}
}
class Sys2 extends System {
	{
		targets.add(C1.class);
		targets.add(C2.class);
	}
}
class Sys3 extends System {
	{
		targets.add(C1.class);
		excluded.add(C2.class);
	}
}
class Sys4 extends System {
	{
		targets.add(C2.class);
		excluded.add(C1.class);
	}
}

public class EngineTest {

	private Engine engine;
	
	@Before
	public void setUp() throws Exception {
		this.engine = new Engine();
		this.engine.addEntity(new Entity(new C1()));			// Entity #1
		this.engine.addEntity(new Entity(new C2()));			// Entity #2
		this.engine.addEntity(new Entity(new C1(), new C2()));	// Entity #3
		this.engine.addSystem(new Sys1());
		this.engine.addSystem(new Sys2());
		this.engine.addSystem(new Sys3());
	}

	@After
	public void tearDown() throws Exception {
		this.engine = null;
	}

	@Test
	public void testGetEntitiesWithComponent() {
		assertEquals(2, this.engine.getEntitiesWithComponent(C1.class).size());
		assertEquals(2, this.engine.getEntitiesWithComponent(C2.class).size());
	}

	@Test
	public void testGetEntitiesWithComponents() {
		assertEquals(1, this.engine.getEntitiesWithComponents(C1.class, C2.class).size());
	}

	@Test
	public void testAddEntity() {
		Entity e = new Entity(new C1(), new C2());
		this.engine.addEntity(e);
		assertEquals(4, e.getID());
		assertEquals(true, this.engine.getSystem(Sys1.class).hasEntity(e));
		assertEquals(true, this.engine.getSystem(Sys2.class).hasEntity(e));
		assertEquals(false, this.engine.getSystem(Sys3.class).hasEntity(e));
	}

	@Test
	public void testRemoveEntity() {
		int id = 1;
		Entity e = this.engine.getEntity(id);
		this.engine.removeEntity(id);
		assertEquals(false, this.engine.hasEntity(e));
	}

	@Test
	public void testAddSystem() {
		System system = new Sys4();
		this.engine.addSystem(system);
		assertEquals(true, this.engine.hasSystem(Sys4.class));
	}

	@Test
	public void testRemoveSystem() {
		this.engine.removeSystem(Sys1.class);
		assertEquals(false, this.engine.hasSystem(Sys1.class));
	}

}
