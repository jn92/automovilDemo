package com.automovil.demo.message;

public class SuccessMessage extends Message {

	private String type = "success";

	public SuccessMessage(String label, String message) {
		super(label, message);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
