package Bean;

public class Category {
	private long cateId;
	private String name;
	private long userId;
	
	public Category(long cateId, String name, long userId) {
		super();
		this.cateId = cateId;
		this.name = name;
		this.userId = userId;
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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
}
