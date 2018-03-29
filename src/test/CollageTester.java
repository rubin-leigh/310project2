package test;

import static org.junit.Assert.*;

import org.junit.Test;
import server.Collage;

public class CollageTester {

	//tests the getter and setter methods of collage class by setting the them and then getting them
	@Test
	public void setterAndGetter() {
		Collage c = new Collage();
		c.setImage("testImage");
		c.setTopic("testTopic");
		c.setFilter("testFilter");
		c.setBorder(true);
		c.setRotation(true);
		
		assertEquals("testImage", c.getImage());
		assertEquals("testTopic", c.getTopic());
		assertEquals("testFilter", c.getFilter());
		assertEquals(true, c.getBorder());
		assertEquals(true, c.getRotation());
	}

}
