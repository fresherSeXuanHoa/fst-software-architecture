package fst.spring.cloud.ams.erukaclient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fst.spring.cloud.ams.erukaclient.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
