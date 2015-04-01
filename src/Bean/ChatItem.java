package Bean;

public class ChatItem {
	private long chatId;
	private long userId;
	private long eventId;
	private String content;
	private String time;
	
	public ChatItem(long chatId, long userId, long eventId, String content,
			String time) {
		super();
		this.chatId = chatId;
		this.userId = userId;
		this.eventId = eventId;
		this.content = content;
		this.time = time;
	}

	public long getChatId() {
		return chatId;
	}

	public void setChatId(long chatId) {
		this.chatId = chatId;
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
