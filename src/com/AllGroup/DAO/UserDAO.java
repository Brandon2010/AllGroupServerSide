package com.AllGroup.DAO;

import com.AllGroup.Bean.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigInteger;

import com.AllGroup.Util.DataAccess;

public class UserDAO {	
	
	public void createUser(String name, BigInteger facebookId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DataAccess.getConnection();
			conn.setAutoCommit(false);
			sql = "INSERT INTO user VALUES (DEFAULT, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, facebookId.longValue());	
			pstmt.setString(2, name);
			
			pstmt.execute();
			conn.commit();
			
		} catch (Exception e) {
			try {
				conn.rollback(); // Roll back operation
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(pstmt, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public User searchUser(String name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			conn = DataAccess.getConnection();
			sql = "SELECT * FROM user WHERE name=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				User user = new User(rs.getLong("user_id"), 
						BigInteger.valueOf(rs.getLong("facebook_id")),
						rs.getString("name"));
				return user;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(rs, pstmt, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;		
	}
	
	public User searchUser(BigInteger facebookId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			conn = DataAccess.getConnection();
			sql = "SELECT * FROM user WHERE facebook_id=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, facebookId.longValue());
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				User user = new User(rs.getLong("user_id"), 
						BigInteger.valueOf(rs.getLong("facebook_id")),
						rs.getString("name"));
				return user;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(rs, pstmt, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;	
	}
	
}
