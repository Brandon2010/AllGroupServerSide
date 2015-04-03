package com.AllGroup.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.AllGroup.DAO.*;
import com.AllGroup.Bean.*;
import com.AllGroup.Util.*;


public class CategoryServlet extends HttpServlet {

	private static final String CONTENT_TYPE = "text/html; charset=utf-8";
	private ServletContext sc;
	private CategoryDAO cateDao;
	
	/**
	 * Constructor of the object.
	 */
	public CategoryServlet() {
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
		
		String cateOperation = request.getParameter("cateOperation");
		if (cateOperation.equals("create")) {
			this.create(request, response);
		}
		else if (cateOperation.equals("getIdName")) {
			this.getByIdName(request, response);
		}
		else if (cateOperation.equals("getId")) {
			this.getById(request, response);
		}
		else if (cateOperation.equals("delete")) {
			this.delete(request, response);
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
		this.doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		sc = this.getServletContext();
		cateDao = new CategoryDAO();
		super.init();
	}

	private void create(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		
		long userId = Long.parseLong(request.getParameter("userId"));
		String name = request.getParameter("name");
		
		cateDao.createCategory(userId, name);		
		
		Category cate = cateDao.getCategory(userId, name);
		String jsonCate = JsonTools.createJsonString("category", cate);
		response.setStatus(200);
		out.println(jsonCate);
		
		out.flush();
		out.close();
	}
	
	private void getById(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		long userId = Long.parseLong(request.getParameter("userId"));
		ArrayList<Category> cates = (ArrayList<Category>) cateDao.getCategory(userId);
		
		String jsonCate = JsonTools.createJsonString("categories", cates);
		response.setStatus(200);
		out.println(jsonCate);
		
		out.flush();
		out.close();
	}
	
	private void getByIdName(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		long userId = Long.parseLong(request.getParameter("userId"));
		String name = request.getParameter("name");
		Category cate = cateDao.getCategory(userId, name);
		
		String jsonCate = JsonTools.createJsonString("category", cate);
		response.setStatus(200);
		out.println(jsonCate);
		
		out.flush();
		out.close();
	}
	
	private void delete(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		//PrintWriter out = response.getWriter();
		long cateId = Long.parseLong(request.getParameter("cateId"));
		
		cateDao.deleteCategory(cateId);
		
	}
}
