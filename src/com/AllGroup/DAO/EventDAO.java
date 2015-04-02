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

import com.AllGroup.Util.DataAccess;
import com.AllGroup.Bean.Event;
import com.AllGroup.Bean.Participant;

/**
 * @author wangxi
 * 
 */
public class EventDAO {

	public int createEvent(long cateId, String name, String time,
			String description, String location) {
		Connection dbCon = null;
		PreparedStatement ps = null;
		int updateRows = 0;
		String sql = null;
		try {
			dbCon = DataAccess.getConnection();
			dbCon.setAutoCommit(false);
			sql = "insert into event (cate_id, name, description, time, location) values (?, ?, ?, ?, ?)";
			ps = dbCon.prepareStatement(sql);
			ps.setLong(1, cateId);
			ps.setString(2, name);
			ps.setString(3, description);
			ps.setString(4, time);
			ps.setString(5, location);
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
			sql = "SELECT * FROM event e where e."+searchKey+" like '%%" + searchValue + "%'";
			ps = dbCon.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Event event = new Event();
				event.setEventId(rs.getLong("event_id"));
				event.setCateId(rs.getLong("cate_id"));
				event.setName(rs.getString("name"));
				event.setTime(rs.getString("time"));
				event.setLocation(rs.getString("location"));
				event.setDescription(rs.getString("description"));
				results.add(event);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
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
				event.setCateId(rs.getLong("cate_id"));
				event.setName(rs.getString("name"));
				event.setTime(rs.getString("time"));
				event.setLocation(rs.getString("location"));
				event.setDescription(rs.getString("description"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				DataAccess.close(rs, ps, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return event;
	}
	
	public List<Event> getEventsByCate(long cateId) {
		Connection dbCon = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		List<Event> results = new ArrayList<Event>();
		try {
			dbCon = DataAccess.getConnection();
			sql = "SELECT * FROM event e where e.cate_id = ?";
			ps = dbCon.prepareStatement(sql);
			ps.setLong(1, cateId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Event event = new Event();
				event.setEventId(rs.getLong("event_id"));
				event.setCateId(rs.getLong("cate_id"));
				event.setName(rs.getString("name"));
				event.setTime(rs.getString("time"));
				event.setLocation(rs.getString("location"));
				event.setDescription(rs.getString("description"));
				results.add(event);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
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
	
	public List<Participant> getParticipantsByEvent(long eventId) {
		Connection dbCon = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		List<Participant> results = new ArrayList<Participant>();
		try {
			dbCon = DataAccess.getConnection();
			sql = "SELECT * FROM particpant p where p.event_id = ?";
			ps = dbCon.prepareStatement(sql);
			ps.setLong(1, eventId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Participant p = new Participant();
				p.setEventId(eventId);
				p.setPartId(rs.getLong("part_id"));
				p.setUserId(rs.getLong("user_id"));
				results.add(p);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				DataAccess.close(rs, ps, dbCon);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return results;
	}
	
	public int addParticipant(long user_id, long event_id) {
		Connection dbCon = null;
		PreparedStatement ps = null;
		int updateRows = 0;
		String sql = null;
		try {
			dbCon = DataAccess.getConnection();
			dbCon.setAutoCommit(false);
			sql = "insert into participant (user_id, event_id) values (?, ?)";
			ps = dbCon.prepareStatement(sql);
			ps.setLong(1, user_id);
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
