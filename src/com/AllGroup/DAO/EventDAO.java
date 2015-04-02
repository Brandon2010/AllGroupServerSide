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
import com.AllGroup.Bean.Event;;

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
	
	public List<Event> findProducts(String searchKey, String searchValue) {
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
				//Event event = new Event(rs.getLong());
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
}
