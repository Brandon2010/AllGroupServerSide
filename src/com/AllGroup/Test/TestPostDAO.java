package com.AllGroup.Test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.AllGroup.Bean.PostItem;
import com.AllGroup.DAO.PostDAO;

public class TestPostDAO {

	@Test
	public void testGetPostsByEvent() {
		PostDAO pd = new PostDAO();
		List<PostItem> pi =  pd.getPostsByEvent(1);
		assertEquals("Get post by event incorrect", 1, pi.size());
	}

	@Test
	public void testGetPostById() {
		PostDAO pd = new PostDAO();
		PostItem p = pd.getPostById(1);
		assertEquals("Get post by id incorrect", "hello", p.getContent());
	}

}
