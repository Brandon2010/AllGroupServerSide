package com.AllGroup.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.AllGroup.DAO.PostDAO;
import com.AllGroup.DAO.UserDAO;
import com.AllGroup.Util.JsonTools;

import com.AllGroup.Bean.PostItem;
import com.AllGroup.Bean.User;

public class PostServlet extends HttpServlet {
	
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";
	private ServletContext sc;
	private PostDAO pd;

	/**
	 * Constructor of the object.
	 */
	public PostServlet() {
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
		String postOperation = request.getParameter("postOperation");
		if (postOperation.equals("getPostsEvent")) {
			getPostsByEvent(request, response);
		} else if (postOperation.equals("postById")) {
			getPostsById(request, response);
		} else if (postOperation.equals("add")) {
			addPost(request, response);
		} else if (postOperation.equals("delete")) {
			deletePost(request, response);
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
		pd = new PostDAO();
		super.init();
	}
	
	private void getPostsByEvent(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		long eventId = Long.parseLong(request.getParameter("id"));
		List<PostItem> posts = pd.getPostsByEvent(eventId);
		if (posts.size() == 0) {
			response.setStatus(404);
		} else {
			String result = JsonTools.createJsonString("posts", posts);
			response.setStatus(200);
			out.println(result);
		}
		
		out.flush();
		out.close();
		
	}
	
	private void getPostsById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		long postId = Long.parseLong(request.getParameter("id"));
		PostItem post = pd.getPostById(postId);
		if (post == null) {
			response.setStatus(404);
		} else {
			String result = JsonTools.createJsonString("post", post);
			response.setStatus(200);
			out.println(result);
		}
		
		out.flush();
		out.close();
	}
	
	private void addPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		long userId = Long.parseLong(request.getParameter("userId"));
		long eventId = Long.parseLong(request.getParameter("eventId"));
		String content = request.getParameter("content");
		String time = request.getParameter("time");
		int update = pd.addPost(userId, eventId, content, time);
		UserDAO ud = new UserDAO();
		User user = ud.searchUser(userId);
		if (update == 0) {
			response.setStatus(500);
		} else {
			List<PostItem> posts = pd.getPostsByEvent(eventId);
			String result = JsonTools.createJsonString("posts", posts);
			response.setStatus(200);
			out.println(result);
//			String userStr = JsonTools.createJsonString("user", user);
//			System.out.println(userStr);
//			out.println(userStr);
		}
		
		out.flush();
		out.close();
	}
	
	private void deletePost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		long postId = Long.parseLong("postId");
		int update = pd.deletePost(postId);
		if (update == 0) {
			response.setStatus(500);
		} else {
			long eventId = Long.parseLong("eventId");
			List<PostItem> posts = pd.getPostsByEvent(eventId);
			String result = JsonTools.createJsonString("posts", posts);
			response.setStatus(200);
			out.println(result);
		}
		
		out.flush();
		out.close();
	}

}
