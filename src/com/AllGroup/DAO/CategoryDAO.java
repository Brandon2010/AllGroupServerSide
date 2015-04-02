package com.AllGroup.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

import com.AllGroup.Bean.Category;
import com.AllGroup.Util.DataAccess;

public class CategoryDAO {
	
	// TODO: added the argument uId
	public void createCategory(long uId, String name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DataAccess.getConnection();
			sql = "INSERT INTO category VALUES (DEFAULT, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			// TODO: setLong?
			pstmt.setLong(1, uId);
			
			pstmt.setString(2, name);
			
			pstmt.executeQuery();

		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DataAccess.close(pstmt, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// TODO: added argument uId
	public Category getCategory(long uId, String name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			conn = DataAccess.getConnection();
			sql = "SELECT * FROM category WHERE user_id=? AND name=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, (int)uId);
			pstmt.setString(2, name);
			
			rs = pstmt.executeQuery();
			
			// TODO: does rs fetched by "name" only contain one item?
			if (rs.next()) {
				Category category = new Category(rs.getLong("cate_id"), 						
						rs.getString("name"),
						rs.getLong("user_id"));
				return category;
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
	
	public List<Category> getCategory(long uId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<Category> cates = new ArrayList<Category>();
		
		try {
			conn = DataAccess.getConnection();
			sql = "SELECT * FROM category WHERE user_id=?";
			pstmt = conn.prepareStatement(sql);
			
			// TODO: setLong?
			//pstmt.setInt(1, (int)uId);
			pstmt.setLong(1, uId);
			
			rs = pstmt.executeQuery();		
			
			while (rs.next()) {
				Category category = new Category(rs.getLong("cate_id"), 						
						rs.getString("name"),
						rs.getLong("user_id"));
				cates.add(category);
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
		
		return cates;	
	}
	
}
