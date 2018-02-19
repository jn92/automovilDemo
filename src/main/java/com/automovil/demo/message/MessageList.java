package com.automovil.demo.message;

import java.util.ArrayList;
import java.util.List;

public class MessageList {
	private List<Message> messageList = new ArrayList<Message>();

	public void addSuccess(String label, String message) {
		this.messageList.add(new SuccessMessage(label, message));
	}

	public void addInformation(String label, String message) {
		this.messageList.add(new InformationMessage(label, message));
	}

	public void addWarning(String label, String message) {
		this.messageList.add(new WarningMessage(label, message));
	}

	public void addError(String label, String message) {
		this.messageList.add(new ErrorMessage(label, message));
	}

	public List<Message> getMessageList() {
		return this.messageList;
	}

	public boolean hasError() {
		for (Message message : this.messageList)
			if (message instanceof ErrorMessage)
				return true;
		return false;
	}
}
