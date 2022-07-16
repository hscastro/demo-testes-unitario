package com.hscastro.apirest.exceptions;

public class ObjectNotFoundException extends RuntimeException {
	
	private String msg;

	public ObjectNotFoundException(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


	
}
