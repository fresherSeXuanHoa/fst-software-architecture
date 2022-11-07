package fst.spring.cloud.ams.erukaclient.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fst.spring.cloud.ams.erukaclient.entity.Account;
import fst.spring.cloud.ams.erukaclient.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	AccountRepository accountRepository;

	public List<Account> findAll() {
		return accountRepository.findAll();
	}
}
