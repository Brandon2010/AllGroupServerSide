package com.AllGroup.Servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.math.BigInteger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.AllGroup.DAO.*;
import com.AllGroup.Bean.*;
import com.AllGroup.Util.*;

public class UserServlet extends HttpServlet {

	private static final String CONTENT_TYPE = "text/html; charset=utf-8";
	private ServletContext sc;
	private UserDAO userDao;
	
	/**
	 * Constructor of the object.
	 */
	public UserServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);
		//PrintWriter out = response.getWriter();
		
		if (request.getParameter("userOperation") != null) {
			String userOperation = request.getParameter("userOperation");
			if (userOperation.equals("searchID")) {
				this.searchUserByFBId(request, response);
			}
			else if (userOperation.equals("searchName")) {
				this.searchUserByName(request, response);
			}
		}		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		sc = this.getServletContext();
		userDao = new UserDAO();
		super.init();
	}

	
	public void searchUserByFBId(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {		
		PrintWriter out = response.getWriter();
		BigInteger facebookId = new BigInteger(request.getParameter("facebookId"));			
		User user = userDao.searchUser(facebookId);
		
		if (user != null) {
			String jsonUser = JsonTools.createJsonString("user", user);
			response.setStatus(200);
			out.println(jsonUser);
		} else {
			response.setStatus(404);
			out.println("Not Found");
		}
		out.flush();
		out.close();
	}
	
	public void searchUserByName(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {		
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");	
		User user = userDao.searchUser(name);
		
		if (user != null) {
			String jsonUser = JsonTools.createJsonString("user", user);
			response.setStatus(200);
			out.println(jsonUser);
		} else {
			response.setStatus(404);
			out.println("Not Found");
		}
		out.flush();
		out.close();
	}
}
