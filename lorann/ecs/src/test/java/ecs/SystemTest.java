package ecs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

class TestSystem extends ecs.System {
	
	{
		targets.add(C1.class);
		targets.add(C2.class);
		excluded.add(C3.class);
		excluded.add(C4.class);
	}
	
	@Override
	public void onEntityAdded(final Entity e) {
		e.get(C1.class).test = 28;
	}
	
	@Override
	public void update(final Engine engine, final double dt) {
		for (final Entity e : this) {
			e.get(C1.class).test++;
		}
	}
	
	@Override
	public void onEntityRemoved(final Entity e) {
		e.get(C1.class).test = -4;
	}
	
}

public class SystemTest {

	private System system;
	
	@Before
	public void setUp() throws Exception {
		this.system = new TestSystem();
	}

	@After
	public void tearDown() throws Exception {
		this.system = null;
	}

	@Test
	public void testForEach() {
		try {
			for (final Entity e : system) { e.getID(); }
		}
		catch (Exception ex) {
			fail("Unable to use system in a for-each.");
		}
	}
	
	@Test
	public void testOnEntityAdded() {
		Entity e = new Entity();
		e.attach(new C1());
		e.attach(new C2());
		e.get(C1.class).test = -31;
		system.addEntity(e);
		assertEquals(28, e.get(C1.class).test);
	}

	@Test
	public void testPriority() {
		Entity e1 = new Entity();
		e1.attach(new C1());
		e1.attach(new C2());
		e1.setID(104);
		
		Entity e2 = new Entity();
		e2.attach(new C1());
		e2.attach(new C2());
		e2.setID(16);
		
		system.addEntity(e1);
		system.addEntity(e2);
		
		for (final Entity e : system) {
			assertEquals(e2, e);
			break;
		}
	}
	
	@Test
	public void testHasEntity() {
		final Entity e1 = new Entity();
		e1.attach(new C1());
		e1.attach(new C2());
		e1.setID(1);
		
		final Entity e2 = new Entity();
		e2.attach(new C1());
		e2.attach(new C2());
		e2.attach(new C3());
		e2.setID(2);
		
		system.addEntity(e1);
		if (!system.hasEntity(e1)) {
			fail("Unable to add a conform Entity: " + e1);
		}
		system.addEntity(e2);
		if (system.hasEntity(e2)) {
			fail("Added a non-conform Entity: " + e2);
		}
	}

	@Test
	public void testOnEntityRemoved() {
		Entity e = new Entity();
		e.attach(new C1());
		e.attach(new C2());
		system.addEntity(e);
		e.get(C1.class).test = -31;
		system.removeEntity(e);
		assertEquals(-4, e.get(C1.class).test);
	}

	@Test
	public void testUpdate() {
		Entity e = new Entity();
		e.attach(new C1());
		e.attach(new C2());
		system.addEntity(e);
		e.get(C1.class).test = 0;
		system.update(null, 0);
		assertEquals(1, e.get(C1.class).test);
	}

}
