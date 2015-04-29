/**
 * 
 */
package com.AllGroup.DAO;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.AllGroup.Util.DataAccess;
import com.AllGroup.Bean.Event;
import com.AllGroup.Bean.User;

/**
 * @author wangxi
 * 
 */
public class EventDAO {

	public int createEvent(String name, String time, String description,
			String location) {
		Connection dbCon = null;
		PreparedStatement ps = null;
		int updateRows = 0;
		String sql = null;
		try {
			dbCon = DataAccess.getConnection();
			dbCon.setAutoCommit(false);
			sql = "insert into event (name, description, time, location) values (?, ?, ?, ?)";
			ps = dbCon.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, description);
			ps.setString(3, time);
			ps.setString(4, location);
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

	public List<Event> findEvents(String searchKey, String searchValue) {
		Connection dbCon = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		List<Event> results = new ArrayList<Event>();
		try {
			dbCon = DataAccess.getConnection();
			sql = "SELECT * FROM event e where e." + searchKey + " like '%%"
					+ searchValue + "%'";
			ps = dbCon.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Event event = new Event();
				event.setEventId(rs.getLong("event_id"));
				event.setName(rs.getString("name"));
				event.setTime(rs.getString("time"));
				event.setLocation(rs.getString("location"));
				event.setDescription(rs.getString("description"));
				event.setImage_url(rs.getString("image_url"));
				results.add(event);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(rs, ps, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return results;
	}

	public Event getEventById(long eventId) {
		Connection dbCon = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		Event event = null;
		try {
			dbCon = DataAccess.getConnection();
			sql = "SELECT * FROM event e where e.event_id = ?";
			ps = dbCon.prepareStatement(sql);
			ps.setLong(1, eventId);
			rs = ps.executeQuery();
			if (rs.next()) {
				event = new Event();
				event.setEventId(rs.getLong("event_id"));
				event.setName(rs.getString("name"));
				event.setTime(rs.getString("time"));
				event.setLocation(rs.getString("location"));
				event.setDescription(rs.getString("description"));
				event.setImage_url(rs.getString("image_url"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(rs, ps, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return event;
	}

	public long getEventId(String name, long cateId) {
		Connection dbCon = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		try {
			dbCon = DataAccess.getConnection();
			sql = "SELECT * FROM event e where e.name = ?";
			ps = dbCon.prepareStatement(sql);
//			ps.setLong(1, cateId);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getLong("event_id");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(rs, ps, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public List<Event> getEventsByCate(long cateId) {
		Connection dbCon = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		List<Event> results = new ArrayList<Event>();
		try {
			dbCon = DataAccess.getConnection();
			sql = "SELECT * FROM event_user e where e.cate_id = ?";
			ps = dbCon.prepareStatement(sql);
			ps.setLong(1, cateId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Event event = new Event();
				event.setEventId(rs.getLong("event_id"));
				event.setName(rs.getString("event_name"));
				event.setTime(rs.getString("time"));
				event.setLocation(rs.getString("location"));
				event.setDescription(rs.getString("description"));
				event.setImage_url(rs.getString("image_url"));
				results.add(event);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(rs, ps, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return results;
	}

	public int deleteEvent(long eventId) {
		Connection dbCon = null;
		PreparedStatement ps = null;
		int updateRows = 0;
		String sql = null;
		try {
			dbCon = DataAccess.getConnection();
			dbCon.setAutoCommit(false);
			sql = "delete from event where event_id = ?";
			ps = dbCon.prepareStatement(sql);
			ps.setLong(1, eventId);
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

	public List<User> getParticipantsByEvent(long eventId) {
		Connection dbCon = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		List<User> results = new ArrayList<User>();
		try {
			dbCon = DataAccess.getConnection();
			sql = "SELECT * FROM event_user eu where eu.event_id = ?";
			ps = dbCon.prepareStatement(sql);
			ps.setLong(1, eventId);
			rs = ps.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setFacebookId(BigInteger.valueOf(rs.getLong("facebook_id")));
				u.setName(rs.getString("user_name"));
				u.setUserId(rs.getLong("user_id"));
				results.add(u);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(rs, ps, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return results;
	}

	public int addParticipant(long cate_id, long event_id) {
		Connection dbCon = null;
		PreparedStatement ps = null;
		int updateRows = 0;
		String sql = null;
		try {
			dbCon = DataAccess.getConnection();
			dbCon.setAutoCommit(false);
			sql = "insert into category_event (cate_id, event_id) values (?, ?)";
			ps = dbCon.prepareStatement(sql);
			ps.setLong(1, cate_id);
			ps.setLong(2, event_id);
			
			System.out.println("event_id: " + event_id);
			
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

	public int updateCategory(long new_cate_id, long event_id) {
		Connection dbCon = null;
		PreparedStatement ps = null;
		int updateRows = 0;
		String sql = null;
		try {
			dbCon = DataAccess.getConnection();
			dbCon.setAutoCommit(false);
			sql = "update category_event set cate_id = ? where event_id = ?";
			ps = dbCon.prepareStatement(sql);
			ps.setLong(1, new_cate_id);
			ps.setLong(2, event_id);
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
