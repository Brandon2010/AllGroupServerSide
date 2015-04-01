package com.AllGroup.Bean;

public class PostItem {
	private long postId;
	private long userId;
	private long eventId;
	private String content;
	private String time;
	
	public PostItem(long postId, long userId, long eventId, String content,
			String time) {
		super();
		this.postId = postId;
		this.userId = userId;
		this.eventId = eventId;
		this.content = content;
		this.time = time;
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	
}
