package com.AllGroup.Test;

import com.AllGroup.Bean.*;
import com.AllGroup.DAO.*;

import java.math.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestUserDAO {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testSearchFBIdSuccess() {
		UserDAO test = new UserDAO();
		User user = test.searchUser(new BigInteger("112"));
		
		assertEquals("Wrong User!", 2, user.getUserId());
		assertEquals("Wrong User!", "bob", user.getName());
	}
	
	@Test
	public void testSearchFBIdFailure() {
		UserDAO test = new UserDAO();
		User user = test.searchUser(new BigInteger("200"));		
		assertNull("Wrong User!", user);
	}
	
	@Test
	public void testSearchNameSuccess() {
		UserDAO test = new UserDAO();
		User user = test.searchUser("alice");
		
		assertEquals("Wrong User!", 1, user.getUserId());
		assertEquals("Wrong User!", (long)111, user.getFacebookId().longValue());
	}
	
	@Test
	public void testSearchNameFailure() {
		UserDAO test = new UserDAO();
		User user = test.searchUser("zack");		
		assertNull("Wrong User!", user);
	}
	
	@Test
	public void testCreateUser() {
		UserDAO test = new UserDAO();
		test.createUser("helen", new BigInteger("117"));
		
		User user = test.searchUser("helen");		
		assertEquals("Wrong User!", 7, user.getUserId());
		assertEquals("Wrong User!", (long)117, user.getFacebookId().longValue());
	}

}
