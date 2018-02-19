package com.automovil.demo.message;

public class InformationMessage extends Message {

	private String type = "information";

	public InformationMessage(String label, String message) {
		super(label, message);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
