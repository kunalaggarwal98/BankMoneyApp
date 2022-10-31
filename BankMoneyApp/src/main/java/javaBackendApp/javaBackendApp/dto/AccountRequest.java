package javaBackendApp.javaBackendApp.dto;

public class AccountRequest {
	
	
	private String accountHolderName;
	
	
	private Float accountHolderBalance;


	public String getAccountHolderName() {
		return accountHolderName;
	}


	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}


	public Float getAccountHolderBalance() {
		return accountHolderBalance;
	}


	public void setAccountHolderBalance(Float accountHolderBalance) {
		this.accountHolderBalance = accountHolderBalance;
	}

}
