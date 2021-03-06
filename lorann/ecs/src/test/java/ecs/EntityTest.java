package ecs;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
	public void hasComponent() {
		final Entity e = new Entity();
		assertEquals(false, e.has(Component.class));
		e.attach(new C1());
		assertEquals(true, e.has(Component.class));
	}

	@Test
	public void testAttach() {
		this.entity.attach(new C1());
		if (!this.entity.has(C1.class)) {
			fail("Failed to attach a component.");
		}
	}
	
	@Test
	public void testAttachCollection() {
		Collection<Component> c = new ArrayList<>();
		c.add(new C1());
		c.add(new C2());
		this.entity.attach(c);
		if (!this.entity.has(C1.class) && !this.entity.has(C2.class)) {
			fail("Failed to attach a collection of component.");
		}
	}
	
	@Test
	public void testAttachArray() {
		Component[] c = {new C1(), new C2()};
		this.entity.attach(c);
		if (!this.entity.has(C1.class) && !this.entity.has(C2.class)) {
			fail("Failed to attach an array of component.");
		}
	}
	
	@Test
	public void testAttachMultiple() {
		this.entity.attach(new C1(), new C2());
		if (!this.entity.has(C1.class) && !this.entity.has(C2.class)) {
			fail("Failed to attach an array of component.");
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
