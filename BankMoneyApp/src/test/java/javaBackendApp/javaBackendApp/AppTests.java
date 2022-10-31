package javaBackendApp.javaBackendApp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import javaBackendApp.javaBackendApp.dto.TransferDto;
import javaBackendApp.javaBackendApp.entity.Account;
import javaBackendApp.javaBackendApp.repository.AccountRepository;
import javaBackendApp.javaBackendApp.service.AccountService;


@SpringBootTest
class AppTests {

	@Mock
	AccountRepository repo;
	
	
	@InjectMocks
	AccountService service;
	
	Float bal=10F;
	
	Long accountNo=1L;
	
	String name="demo";
	
	@Test
	public void testgetBalance() {
		
		
		when(repo.findById(1L)).thenReturn(Optional.of(new Account(accountNo, name,bal)));
		
		assertEquals(bal, service.getAccountBalance(accountNo).getBody().getData().getAccountHolderBalance());
	}
	
	
	@Test
	public void testSendMoney() {
		
		Long accountFromNo = 1L;
		Long accountToNo = 2L;
		
		Float amount= 10F;
		
		Float zero =0F;
		
		Float finalAmount =20F;
		
		TransferDto dto = new TransferDto();
		
		dto.setAmount(amount);
		dto.setFromAccountNo(accountFromNo);
		dto.setToAccountNo(accountToNo);
		
		Account fromAccount = new Account(accountFromNo,name,bal);
		
		Account toAccount = new Account(accountToNo,name,bal);
		
		when(repo.getAccountForUpdate(accountFromNo)).thenReturn(Optional.of(fromAccount));
		when(repo.getAccountForUpdate(accountToNo)).thenReturn(Optional.of(toAccount));
		
		service.sendMoney(dto);
		
		assertEquals(zero,fromAccount.getAccountHolderBalance());
		assertEquals(finalAmount,toAccount.getAccountHolderBalance());
		
	}

}
