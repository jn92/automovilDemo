package com.automovil.demo.message;

public class WarningMessage extends Message{

	private String type = "warning";
	
	public WarningMessage(String label, String message) {
		super(label, message);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
