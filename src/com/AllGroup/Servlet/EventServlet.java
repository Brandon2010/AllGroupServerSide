package com.AllGroup.Servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.AllGroup.Bean.Event;
import com.AllGroup.Bean.User;
import com.AllGroup.DAO.EventDAO;
import com.AllGroup.Util.JsonTools;

public class EventServlet extends HttpServlet {
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";
	private static final String PICTURE_CONTENT_TYPE = "image/jpeg";
	private ServletContext sc;
	private EventDAO ed;

	/**
	 * Constructor of the object.
	 */
	public EventServlet() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		sc = this.getServletContext();
		ed = new EventDAO();
		super.init();
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType(CONTENT_TYPE);
		String eventOperation = request.getParameter("eventOperation");
		if (eventOperation.equals("add")) {
			addEvent(request, response);
		} else if (eventOperation.equals("findEvents")) {
			findEvents(request, response);
		} else if (eventOperation.equals("getEventId")) {
			getEventById(request, response);
		} else if (eventOperation.equals("getEventCate")) {
			getEventsByCate(request, response);
		} else if (eventOperation.equals("delete")) {
			deleteEvent(request, response);
		} else if (eventOperation.equals("getPartEvent")) {
			getParticipantByEvent(request, response);
		} else if (eventOperation.equals("addPart")) {
			addParticipant(request, response);
		} else if (eventOperation.equals("updateCate")) {
			updateCategory(request, response);
		} else if (eventOperation.equals("image")) {
			getImageOfEvent(request, response);
		}
	} 

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	private void addEvent(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String time = request.getParameter("time");
		String description = request.getParameter("description");
		String location = request.getParameter("location");
		int update = ed.createEvent(name, time, description, location);

		if (update == 0) {
			response.setStatus(500);
		} else {
			long cateId = Long.parseLong(request.getParameter("cateId"));
			long event_id = ed.getEventId(name, cateId);
			ed.addParticipant(cateId, event_id);

			List<Event> events = ed.getEventsByCate(cateId);
			String result = JsonTools.createJsonString("events", events);
			response.setStatus(200);
			out.println(result);
		}

		out.flush();
		out.close();
	}

	private void findEvents(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String key = request.getParameter("key");
		String value = request.getParameter("value");
		List<Event> events = ed.findEvents(key, value);
		if (events.size() == 0) {
			response.setStatus(404);
		} else {
			String result = JsonTools.createJsonString("events", events);
			response.setStatus(200);
			out.println(result);
		}

		out.flush();
		out.close();
	}

	private void getEventById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		long id = Long.parseLong(request.getParameter("id"));
		Event e = ed.getEventById(id);

		if (e == null) {
			response.setStatus(404);
		} else {
			String result = JsonTools.createJsonString("event", e);
			response.setStatus(200);
			out.println(result);
		}

		out.flush();
		out.close();
	}

	private void getEventsByCate(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		long cateId = Long.parseLong(request.getParameter("id"));
		List<Event> events = ed.getEventsByCate(cateId);

		String result = JsonTools.createJsonString("events", events);
		response.setStatus(200);
		out.println(result);

		out.flush();
		out.close();
	}

	private void deleteEvent(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		long eventId = Long.parseLong(request.getParameter("id"));
		ed.deleteEvent(eventId);

		long cateId = Long.parseLong(request.getParameter("cateId"));
		List<Event> events = ed.getEventsByCate(cateId);
		String result = JsonTools.createJsonString("events", events);
		response.setStatus(200);
		out.println(result);

		out.flush();
		out.close();
	}

	private void getParticipantByEvent(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		long eventId = Long.parseLong(request.getParameter("id"));

		List<User> participants = ed.getParticipantsByEvent(eventId);
		if (participants.size() == 0) {
			response.setStatus(404);
		} else {
			String result = JsonTools.createJsonString("participants",
					participants);
			response.setStatus(200);
			out.println(result);
		}

		out.flush();
		out.close();
	}

	private void addParticipant(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		long cateId = Long.parseLong(request.getParameter("cateId"));
		long eventId = Long.parseLong(request.getParameter("eventId"));

		int update = ed.addParticipant(cateId, eventId);

		if (update == 0) {
			response.setStatus(500);
		} else {
			List<User> participants = ed.getParticipantsByEvent(eventId);
			String result = JsonTools.createJsonString("participants",
					participants);
			response.setStatus(200);
			out.println(result);

		}

		out.flush();
		out.close();

	}

	private void updateCategory(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		long new_cate_id = Long.parseLong(request.getParameter("newId"));
		long event_id = Long.parseLong(request.getParameter("eventId"));
		
		int update = ed.updateCategory(new_cate_id, event_id);
		if (update == 0) {
			response.setStatus(500);
		} else {
			response.setStatus(200);
		}
		
		
	}
	
	private void getImageOfEvent(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType(PICTURE_CONTENT_TYPE);
		response.setStatus(200);
		OutputStream out = response.getOutputStream();
		String imagePath = request.getParameter("path");
		System.out.println("path" + imagePath);
		FileInputStream imageIn = new FileInputStream(imagePath);
		BufferedInputStream bin = new BufferedInputStream(imageIn);
		BufferedOutputStream bout = new BufferedOutputStream(out);
		byte buffer[] = new byte[1024];
		int bytes = 0;
		while ((bytes = bin.read(buffer)) != -1) {
			bout.write(buffer, 0, bytes);
		}
		bin.close();
		imageIn.close();
		bout.flush();
		bout.close();
		out.close();
	}

}
