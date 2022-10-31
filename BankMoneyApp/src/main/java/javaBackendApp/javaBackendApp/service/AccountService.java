package javaBackendApp.javaBackendApp.service;

import org.springframework.http.ResponseEntity;

import javaBackendApp.javaBackendApp.dto.AccountRequest;
import javaBackendApp.javaBackendApp.dto.ResponseAccount;
import javaBackendApp.javaBackendApp.dto.ResponseAccountList;
import javaBackendApp.javaBackendApp.dto.ResponseVoid;
import javaBackendApp.javaBackendApp.dto.TransferDto;

public interface AccountService {
	
	ResponseEntity<ResponseAccountList> getAllAccount();
	
	ResponseEntity<ResponseAccount> getAccountBalance(Long accountNo);

	ResponseEntity<ResponseVoid> sendMoney(TransferDto transferDto);
	
	ResponseEntity<ResponseVoid> saveAccount(AccountRequest dto);

	
	
}
