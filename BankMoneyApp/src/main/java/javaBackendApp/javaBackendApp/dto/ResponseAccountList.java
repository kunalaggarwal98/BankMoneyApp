package javaBackendApp.javaBackendApp.dto;

import java.util.List;

import javaBackendApp.javaBackendApp.entity.Account;

public class ResponseAccountList extends ResponseMessage{
	
	
	private List<Account> data;

	public List<Account> getData() {
		return data;
	}

	public void setData(List<Account> data) {
		this.data = data;
	}

}
