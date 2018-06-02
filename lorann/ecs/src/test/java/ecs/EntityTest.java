package ecs;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

class C1 implements Component {
	public int test;
}
class C2 implements Component {}
class C3 implements Component {}
class C4 implements Component {}

public class EntityTest {

	private Entity entity;
	
	@Before
	public void setUp() throws Exception {
		this.entity = new Entity();
	}

	@After
	public void tearDown() throws Exception {
		this.entity = null;
	}

	@Test
	public void testAttach() {
		this.entity.attach(new C1());
		if (!this.entity.has(C1.class)) {
			fail("Failed to attach a component.");
		}
	}

	@Test
	public void testDetach() {
		this.entity.attach(new C1());
		this.entity.detach(C1.class);
		if (this.entity.has(C1.class)) {
			fail("Failed to detach a component.");
		}
	}

	@Test
	public void testAllComponents() {
		this.entity.attach(new C1());
		this.entity.attach(new C2());
		this.entity.attach(new C3());
		this.entity.attach(new C4());
		assertEquals(4, this.entity.allComponents().size());
	}

}
