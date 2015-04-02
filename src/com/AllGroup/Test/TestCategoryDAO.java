package com.AllGroup.Test;

import java.math.BigInteger;
import java.util.ArrayList;

import com.AllGroup.Bean.*;
import com.AllGroup.DAO.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestCategoryDAO {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreate() {
		CategoryDAO test = new CategoryDAO();
		test.createCategory(3, "game");
		
		Category cate = test.getCategory(3, "game");	
		assertEquals("Wrong Category!", 6, cate.getCateId());
	}
	
	@Test
	public void testGetByUserIdNameSuccess() {
		CategoryDAO test = new CategoryDAO();
		Category cate = test.getCategory(1, "ceremony");
		
		assertEquals("Wrong Category!", 3, cate.getCateId());
	}
	
	@Test
	public void testGetByUserId() {
		CategoryDAO test = new CategoryDAO();
		ArrayList<Category> list = (ArrayList<Category>) test.getCategory(1);
		
		assertEquals("Wrong Caegories!", 3, list.size());
	}
	
	@Test
	public void testDelete() {
		CategoryDAO test = new CategoryDAO();
		test.deleteCategory(4);
		
		Category cate = test.getCategory(3, "ceremony");
		
		assertNull("Wrong Category!", cate);
		
	}
	
	
}
