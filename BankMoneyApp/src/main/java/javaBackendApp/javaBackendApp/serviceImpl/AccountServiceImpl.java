package javaBackendApp.javaBackendApp.serviceImpl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javaBackendApp.javaBackendApp.config.AES256;
import javaBackendApp.javaBackendApp.dto.AccountRequest;
import javaBackendApp.javaBackendApp.dto.ResponseAccount;
import javaBackendApp.javaBackendApp.dto.ResponseAccountList;
import javaBackendApp.javaBackendApp.dto.ResponseVoid;
import javaBackendApp.javaBackendApp.dto.TransferDto;
import javaBackendApp.javaBackendApp.entity.Account;
import javaBackendApp.javaBackendApp.repository.AccountRepository;
import javaBackendApp.javaBackendApp.service.AccountService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;


@Service
public class AccountServiceImpl implements AccountService{
	
	
	@Autowired
	AccountRepository repo;



	@Override
	public ResponseEntity<ResponseAccount> getAccountBalance(Long accountNo) {
		
		ResponseAccount response = new ResponseAccount();
		
		try {
			Optional<Account> account = repo.findById(accountNo);
			
			if(account.isPresent()) {
			
				Account acc = new Account();
				acc.setAccountHolderBalance(account.get().getAccountHolderBalance());
				acc.setAccountNo(account.get().getAccountNo());
				
				String accountHolderName= AES256.decrypt(account.get().getAccountHolderName());
				
				acc.setAccountHolderName(accountHolderName);
			response.setStatus("200");
			response.setMessage("account info fetched successfully");
			response.setData(acc);
			return new ResponseEntity<ResponseAccount>(response,HttpStatus.OK);
			}
			response.setStatus("400");
			response.setMessage("account does not esist");
			return new ResponseEntity<ResponseAccount>(response,HttpStatus.BAD_REQUEST);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			response.setStatus("500");
			response.setMessage("Something went wrong");
			
			return new ResponseEntity<ResponseAccount>(response,HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		
	}

	@Override
	@Transactional
	public ResponseEntity<ResponseVoid> sendMoney(TransferDto transferDto) {
		
		ResponseVoid response = new ResponseVoid();
		try {
		Optional<Account> accountFrom = repo.getAccountForUpdate(transferDto.getFromAccountNo());
		
		if(accountFrom.isEmpty()) {
			response.setStatus("400");
			response.setMessage("from account does not esist");
			return new ResponseEntity<ResponseVoid>(response,HttpStatus.BAD_REQUEST);
			
		}
		
		Account fromAccount= accountFrom.get();
		
     Optional<Account> accountTo = repo.getAccountForUpdate(transferDto.getToAccountNo());
		
		if(accountFrom.isEmpty()) {
			response.setStatus("400");
			response.setMessage("To account does not esist");
			return new ResponseEntity<ResponseVoid>(response,HttpStatus.BAD_REQUEST);
			
		}
		
		Account toAccount= accountTo.get();	
		
		if(fromAccount.getAccountHolderBalance().compareTo(transferDto.getAmount())<0) {
			response.setMessage("Account does not have sufficient balance,please enter less amount");
			response.setStatus("400");
			return new ResponseEntity<ResponseVoid>(response,HttpStatus.BAD_REQUEST);
		}
		
		fromAccount.setAccountHolderBalance(fromAccount.getAccountHolderBalance()-transferDto.getAmount());
		toAccount.setAccountHolderBalance(toAccount.getAccountHolderBalance()+transferDto.getAmount());
		repo.save(fromAccount);
		repo.save(toAccount);
		
		response.setMessage("money transfer succesfull");
		response.setStatus("200");
		return new ResponseEntity<ResponseVoid>(response,HttpStatus.OK);
	}catch(Exception e){
		e.printStackTrace();
		response.setStatus("500");
		response.setMessage("Something went wrong");
		
		return new ResponseEntity<ResponseVoid>(response,HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	}

	@Override
	public ResponseEntity<ResponseVoid> saveAccount(AccountRequest dto) {
		ResponseVoid response = new ResponseVoid();

		try {
     	String accountHolderName = AES256.encrypt(dto.getAccountHolderName());
		Account account = new Account();
		account.setAccountHolderName(accountHolderName);
		account.setAccountHolderBalance(dto.getAccountHolderBalance());
        
        repo.save(account);
        
        response.setMessage("account saved succesfully");
		response.setStatus("200");
		return new ResponseEntity<ResponseVoid>(response,HttpStatus.OK);
	}catch(Exception e) {
		e.printStackTrace();
		response.setStatus("500");
		response.setMessage("Something went wrong");
		
		return new ResponseEntity<ResponseVoid>(response,HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	}

	@Override
	public ResponseEntity<ResponseAccountList> getAllAccount() {
		ResponseAccountList response = new ResponseAccountList();
		try {
			List<Account> resList = repo.findAll();
			List<Account> res = new ArrayList<>();
			
			for(Account a:resList) {
				Account acc = new Account();
				acc.setAccountHolderBalance(a.getAccountHolderBalance());
				acc.setAccountNo(a.getAccountNo());
				
				String accountHolderName = AES256.decrypt(a.getAccountHolderName());
				acc.setAccountHolderName(accountHolderName);
				
				res.add(acc);
			}
			
			   response.setMessage("account details fetched succesfully");
				response.setStatus("200");
				response.setData(res);
				return new ResponseEntity<ResponseAccountList>(response,HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			response.setStatus("500");
			response.setMessage("Something went wrong");
			
			return new ResponseEntity<ResponseAccountList>(response,HttpStatus.INTERNAL_SERVER_ERROR);		
		}
		
	}
}
