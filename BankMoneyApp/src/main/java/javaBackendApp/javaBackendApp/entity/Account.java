package javaBackendApp.javaBackendApp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long accountNo;
	
	
	private String accountHolderName;
	
	
	private Float accountHolderBalance;


	public Long getAccountNo() {
		return accountNo;;;;;;;;
	}


	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}


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


	public Account(Long accountNo, String accountHolderName, Float accountHolderBalance) {
		super();
		this.accountNo = accountNo;
		this.accountHolderName = accountHolderName;
		this.accountHolderBalance = accountHolderBalance;
	}
	
	public Account() {}
		

}
