package com.boxiang.share.system.po;

import java.io.Serializable;

public class Sequence implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Sequence(){}
	
	public Sequence(String id) {
		this.id = id;
	}
	
	
	/**
	 * 16位唯一id标识
	 */
	private String id;

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
