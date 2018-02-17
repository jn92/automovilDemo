package com.automovil.demo.exception;

import java.util.List;

import com.automovil.demo.message.Message;
import com.automovil.demo.message.MessageList;


public class ServiceException extends Exception{

	private static final long serialVersionUID = 1L;
	
	protected MessageList messageList = new MessageList();

	
	public ServiceException(String label, String descripcion) {
		this.messageList.addError(label, descripcion);
	}

	public ServiceException(MessageList messageList2) {
		this.messageList = messageList2;
	}

	public MessageList getMessageList() {
		return messageList;
	}

	public void setMessageList(MessageList messageList) {
		this.messageList = messageList;
	}
	
	public List<Message> getErrorList() {
		return this.messageList.getMessageList();
	}
}
