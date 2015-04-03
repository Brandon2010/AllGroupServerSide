/**
 * 
 */
package com.AllGroup.Test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.AllGroup.Bean.User;
import com.AllGroup.DAO.EventDAO;

/**
 * @author wangxi
 *
 */
public class TestEventDAOParticipant {

	/**
	 * Test method for {@link com.AllGroup.DAO.EventDAO#getParticipantsByEvent(long)}.
	 */
	@Test
	public void testGetParticipantsByEvent() {
		EventDAO ed = new EventDAO();
		List<User> part = ed.getParticipantsByEvent(2);
		assertEquals("Get Participant wrong", 1, part.size());
	}

	/**
	 * Test method for {@link com.AllGroup.DAO.EventDAO#addParticipant(long, long)}.
	 */
	@Test
	public void testAddParticipant() {
		EventDAO ed = new EventDAO();
		List<User> part = ed.getParticipantsByEvent(4);
		assertEquals("Get participant wrong 2", 0, part.size());
		ed.addParticipant(5, 4);
		part = ed.getParticipantsByEvent(4);
		assertEquals("Add particpant wrong", 1, part.size());
	}
	
	@Test
	public void testUpdateCategory() {
		EventDAO ed = new EventDAO();
		List<User> part = ed.getParticipantsByEvent(4);
		User user1 = part.get(0);
		assertEquals("Get user wrong", "ella", user1.getName());
		int update = ed.updateCategory(4, 4);
		assertEquals("Update wrong", 1, update);
		part = ed.getParticipantsByEvent(4);
		User user2 = part.get(0);
		assertEquals("Get user wrong 2", "charlie", user2.getName());
	}

}
