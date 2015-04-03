package com.AllGroup.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.AllGroup.Bean.PostItem;
import com.AllGroup.DAO.PostDAO;

public class TestPostDAODelete {

	@Test
	public void testDeletePost() {
		PostDAO pd = new PostDAO();
		int update = pd.deletePost(3);
		assertEquals("Delete Failure", 1, update);
		PostItem pi = pd.getPostById(3);
		assertNull(pi);
	}

}
