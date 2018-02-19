package com.automovil.demo.message;

import net.sf.json.JSONObject;

//import net.minidev.json.JSONObject;

public abstract class Message {

	private String label;

	private String message;

	public Message(String label, String message) {
		this.label = label;
		this.message = message;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public abstract String getType();

	public String toJSON() {
		JSONObject json = new JSONObject();
		json.put("type", this.getType());
		json.put("label", this.getLabel());
		json.put("message", this.getMessage());
		return json.toString();
	}
}
