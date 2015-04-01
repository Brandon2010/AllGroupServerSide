package com.AllGroup.Bean;

public class Event {
	private long eventId;
	private long cateId;
	private String name;
	private String time;
	private String description;
	private String location;
	
	public Event(long eventId, long cateId, String name, String time,
			String description, String location) {
		super();
		this.eventId = eventId;
		this.cateId = cateId;
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

	public long getCateId() {
		return cateId;
	}

	public void setCateId(long cateId) {
		this.cateId = cateId;
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
	
	
	
}
