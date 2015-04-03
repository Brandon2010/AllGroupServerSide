/**
 * 
 */
package com.AllGroup.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.AllGroup.Bean.Event;
import com.AllGroup.DAO.EventDAO;

/**
 * @author wangxi
 *
 */
public class TestEventDAOCreate {

	/**
	 * Test method for {@link com.AllGroup.DAO.EventDAO#createEvent(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testCreateEvent() {
		EventDAO ed = new EventDAO();
		int update = ed.createEvent("Town Hall", "2015-04-03 15:00:00", "INI", "CMU");
		assertEquals("Add Failure", 1, update);
		Event e = ed.getEventById(4);
		assertEquals("Search Failure", "CMU", e.getLocation());
	}


}
