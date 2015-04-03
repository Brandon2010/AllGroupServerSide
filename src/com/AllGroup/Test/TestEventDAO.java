package com.AllGroup.Test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.AllGroup.Bean.Event;
import com.AllGroup.DAO.EventDAO;

public class TestEventDAO {

	@Test
	public void testFindEvents() {
		EventDAO ed = new EventDAO();
		List<Event> find = ed.findEvents("name", "party");
		assertEquals("Find failure", 1, find.size());
		find = ed.findEvents("time", "2015-03-03 22:59:52");
		assertEquals("Find time failure", 1, find.size());
	}

	@Test
	public void testGetEventById() {
		EventDAO ed = new EventDAO();
		Event find = ed.getEventById(1);
		assertEquals("Get Id failure", "For May", find.getDescription());
	}

	@Test
	public void testGetEventsByCate() {
		EventDAO ed = new EventDAO();
		List<Event> find = ed.getEventsByCate(1);
		assertEquals("Get by cate failure 1", 0, find.size());
		find = ed.getEventsByCate(2);
		assertEquals("Get by cate failure 2", 1, find.size());
	}

}
