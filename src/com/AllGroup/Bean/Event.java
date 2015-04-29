package com.AllGroup.Bean;

public class Event {
	private long eventId;
	private String name;
	private String time;
	private String description;
	private String location;
	private String image_url;

	public Event() {
		super();
	}
	
	public Event(long eventId, long cateId, String name, String time,
			String description, String location) {
		super();
		this.eventId = eventId;
		this.name = name;
		this.time = time;
		this.description = description;
		this.location = location;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * @return the image_url
	 */
	public String getImage_url() {
		return image_url;
	}

	/**
	 * @param image_url the image_url to set
	 */
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	
}
