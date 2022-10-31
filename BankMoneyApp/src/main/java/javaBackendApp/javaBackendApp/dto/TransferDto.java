package javaBackendApp.javaBackendApp.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class TransferDto {
	
	@NotNull
	@ApiModelProperty(required = true)
	private Long fromAccountNo;
	
	@NotNull
	@ApiModelProperty(required = true)
	private Long toAccountNo;
	
	@NotNull
	@ApiModelProperty(required = true)
	private Float amount;


	public Long getFromAccountNo() {
		return fromAccountNo;
	}


	public void setFromAccountNo(Long fromAccountNo) {
		this.fromAccountNo = fromAccountNo;
	}


	public Long getToAccountNo() {
		return toAccountNo;
	}


	public void setToAccountNo(Long toAccountNo) {
		this.toAccountNo = toAccountNo;
	}


	public Float getAmount() {
		return amount;
	}


	public void setAmount(Float amount) {
		this.amount = amount;
	}

}
