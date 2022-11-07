package fst.spring.cloud.ams.accountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fst.spring.cloud.ams.accountservice.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

}
