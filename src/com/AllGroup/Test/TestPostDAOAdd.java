package com.AllGroup.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.AllGroup.Bean.PostItem;
import com.AllGroup.DAO.PostDAO;

public class TestPostDAOAdd {

	@Test
	public void testAddPost() {
		PostDAO pd = new PostDAO();
		String content = "happy";
		int update = pd.addPost(3, 2, content, "2015-04-03 22:59:52");
		assertEquals("Add Post Failure", 1, update);
		PostItem pi = pd.getPostById(3);
		assertEquals("Get Failure", content, pi.getContent());
	}

}
