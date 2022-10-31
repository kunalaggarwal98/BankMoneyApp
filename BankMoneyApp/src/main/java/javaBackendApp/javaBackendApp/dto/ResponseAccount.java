package javaBackendApp.javaBackendApp.dto;

import javaBackendApp.javaBackendApp.entity.Account;

public class ResponseAccount extends ResponseMessage {
	
	private Account data;

	public Account getData() {
		return data;
	}

	public void setData(Account data) {
		this.data = data;
	}

}
