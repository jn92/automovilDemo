package com.automovil.demo.message;

public class ErrorMessage extends Message {

	private String type = "error";

	public ErrorMessage(String label, String message) {
		super(label, message);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
