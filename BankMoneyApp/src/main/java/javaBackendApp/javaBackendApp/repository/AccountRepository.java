package javaBackendApp.javaBackendApp.repository;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;

import javaBackendApp.javaBackendApp.entity.Account;

import org.springframework.data.jpa.repository.Lock;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long>{
	
	
	
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Transactional
	@Query(value=" FROM Account con WHERE con.accountNo = ?1")
	Optional<Account> getAccountForUpdate(Long id);

}
