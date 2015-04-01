/**
 * 
 */
package com.AllGroup.Util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * This class is used to deal with the Database connection
 * 
 * @author XiWang
 * @version 1.0 2014-10-15
 */
public class DataAccess {

	private static final String DRIVERNAME = "com.mysql.jdbc.Driver";
	private static final String PROPERTIES_NAME = "/connection.properties";
	private static final String URL_KEY = "url";

	/**
	 * Get Access through JDBC
	 * 
	 * @return datbase connection
	 * @throws SQLException
	 *             database connection exception
	 * @throws IOException
	 *             exception of reading properties
	 * @throws ClassNotFoundException
	 *             exception of JDBC driver lost
	 */
	public static Connection getConnection() throws SQLException, IOException,
			ClassNotFoundException {
		Class.forName(DRIVERNAME);
		Properties properties = new Properties(); // define properties
		properties.load(DataAccess.class.getResourceAsStream(PROPERTIES_NAME)); 
		return DriverManager.getConnection(properties.getProperty(URL_KEY),
				properties);
	}

	/**
	 * Get access through using data source and connection pool
	 * 
	 * @param jndi
	 *            the database's name defined by server
	 * @return connection of database
	 * @throws NamingException
	 *             unregistered data source
	 * @throws SQLException
	 *             exception of database connection
	 */
	public static Connection getConnection(String jndi) throws NamingException,
			SQLException {

		Connection connection = null; 

		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/" + jndi); 
		connection = ds.getConnection(); 
		return connection;
	}

	/**
	 * Close Database Connection, Statement and Result Set
	 * 
	 * @param rs
	 *            the defined result set
	 * @param st
	 *            the defined statement
	 * @param con
	 *            the defined connection
	 * @throws SQLException
	 *             exception of closing database
	 */
	public static void close(ResultSet rs, Statement st, Connection con)
			throws SQLException {

		if (rs != null) {
			rs.close();
		}
		if (st != null) {
			st.close();
		}
		if (con != null) {
			con.close();
		}
	}

	/**
	 * Close Database Connection, Statement and Result Set
	 * 
	 * @param st
	 *            the defined statement
	 * @param con
	 *            the defined connection
	 * @throws SQLException
	 *             exception of closing database
	 */
	public static void close(Statement st, Connection con) throws SQLException {

		if (st != null) {
			st.close();
		}
		if (con != null) {
			con.close();
		}
	}

}
