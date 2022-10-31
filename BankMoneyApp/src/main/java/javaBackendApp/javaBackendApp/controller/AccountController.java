package javaBackendApp.javaBackendApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import javaBackendApp.javaBackendApp.dto.AccountRequest;
import javaBackendApp.javaBackendApp.dto.ResponseAccount;
import javaBackendApp.javaBackendApp.dto.ResponseAccountList;
import javaBackendApp.javaBackendApp.dto.ResponseVoid;
import javaBackendApp.javaBackendApp.dto.TransferDto;
import javaBackendApp.javaBackendApp.service.AccountService;

@CrossOrigin(origins="*",allowCredentials="false",allowedHeaders="*")
@RestController
@RequestMapping({"/api"})
public class AccountController {
	
	
	@Autowired
	AccountService service;
	
	
	@GetMapping
	@ApiOperation(value = "API to fetch all account details", produces = "application/json")
	ResponseEntity<ResponseAccountList> getAllAccount(){
		return service.getAllAccount();
	}
	
	@GetMapping("/getAccountByno/{id}")
	@ApiOperation(value = "API to fetch account details", produces = "application/json")
	ResponseEntity<ResponseAccount> getAccountBalance(@PathVariable Long id){
		return service.getAccountBalance(id);
	}

	
	
	@PostMapping("/sendMoney")
	@ApiOperation(value = "API to sendMoney", produces = "application/json")
	ResponseEntity<ResponseVoid> sendMoney(@RequestBody TransferDto transferDto){
		return service.sendMoney(transferDto);
	}

	
	@PostMapping("/saveAccount")
	@ApiOperation(value = "API to saveAccount", produces = "application/json")
	ResponseEntity<ResponseVoid> saveAccount(@RequestBody AccountRequest dto){
		return service.saveAccount(dto);
	}
}
