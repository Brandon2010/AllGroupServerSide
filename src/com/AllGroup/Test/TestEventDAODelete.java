package com.AllGroup.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.AllGroup.Bean.Event;
import com.AllGroup.DAO.EventDAO;

public class TestEventDAODelete {

	@Test
	public void testDeleteEvent() {
		EventDAO ed = new EventDAO();
		int update = ed.deleteEvent(4);
		assertEquals("Delete failure", 1, update);
		Event search = ed.getEventById(4);
		assertEquals("Search failure", null, search);
	}

}
