/**
 * 
 */
package com.AllGroup.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.AllGroup.Bean.PostItem;
import com.AllGroup.Bean.User;
import com.AllGroup.Util.DataAccess;

/**
 * @author wangxi
 *
 */
public class PostDAO {
	public List<PostItem> getPostsByEvent(long eventId) {
		Connection dbCon = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		List<PostItem> results = new ArrayList<PostItem>();
		
		try {
			dbCon = DataAccess.getConnection();
			sql = "select * from post_item where event_id = ?";
			ps = dbCon.prepareStatement(sql);
			ps.setLong(1, eventId);
			rs = ps.executeQuery();
			while (rs.next()) {
				PostItem item = new PostItem();
				item.setEventId(eventId);
				item.setPostId(rs.getLong("post_id"));
				item.setContent(rs.getString("content"));
				item.setTime(rs.getString("time"));
				long user_id = rs.getLong("user_id");
				item.setUserId(user_id);
				UserDAO ud = new UserDAO();
				User user = ud.searchUser(user_id);
				item.setUser(user);
				results.add(item);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(ps, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return results;
	}
	
	public PostItem getPostById(long postId) {
		Connection dbCon = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		PostItem item = null;
		
		try {
			dbCon = DataAccess.getConnection();
			sql = "select * from post_item where post_id = ?";
			ps = dbCon.prepareStatement(sql);
			ps.setLong(1, postId);
			rs = ps.executeQuery();
			if (rs.next()) {
				item = new PostItem();
				item.setEventId(rs.getLong("event_id"));
				item.setPostId(rs.getLong("post_id"));
				item.setContent(rs.getString("content"));
				item.setTime(rs.getString("time"));
				long user_id = rs.getLong("user_id");
				item.setUserId(user_id);
				UserDAO ud = new UserDAO();
				User user = ud.searchUser(user_id);
				item.setUser(user);
				return item;
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(ps, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return item;
	}
	
	public int addPost(long userId, long eventId, String content, String time) {
		Connection dbCon = null;
		PreparedStatement ps = null;
		int updateRows = 0;
		String sql = null;
		try {
			dbCon = DataAccess.getConnection();
			dbCon.setAutoCommit(false);
			sql = "insert into post_item (user_id, event_id, time, content) values (?, ?, ?, ?)";
			ps = dbCon.prepareStatement(sql);
			ps.setLong(1, userId);
			ps.setLong(2, eventId);
			ps.setString(3, time);
			ps.setString(4, content);
			updateRows = ps.executeUpdate();
			dbCon.commit();
		} catch (Exception e) {
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(ps, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return updateRows;
	}
	
	public int deletePost(long postId) {
		Connection dbCon = null;
		PreparedStatement ps = null;
		int updateRows = 0;
		String sql = null;
		try {
			dbCon = DataAccess.getConnection();
			dbCon.setAutoCommit(false);
			sql = "delete from post_item where post_id = ?";
			ps = dbCon.prepareStatement(sql);
			ps.setLong(1, postId);
			updateRows = ps.executeUpdate();
			dbCon.commit();
		} catch (Exception e) {
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(ps, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return updateRows;
	}
	
}
