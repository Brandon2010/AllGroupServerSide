package Bean;

public class Participant {
	private long userId;
	private long eventId;
	private long partId;
	
	public Participant(long userId, long eventId, long partId) {
		super();
		this.userId = userId;
		this.eventId = eventId;
		this.partId = partId;
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

	public long getPartId() {
		return partId;
	}

	public void setPartId(long partId) {
		this.partId = partId;
	}
	
	
}
