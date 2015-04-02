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
		User user = test.searchUser(new BigInteger("117"));		
		assertNull("Wrong User!", user);
	}
	
	
	
	

}
