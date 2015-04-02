package com.AllGroup.Bean;

import java.math.BigInteger;

public class User {
	private long userId;
	private BigInteger facebookId;
	private String name;
	
	public User() {
		super();
	}
	
	public User(long userId, BigInteger facebookId, String name) {
		super();
		this.userId = userId;
		this.facebookId = facebookId;
		this.name = name;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public BigInteger getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(BigInteger facebookId) {
		this.facebookId = facebookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
