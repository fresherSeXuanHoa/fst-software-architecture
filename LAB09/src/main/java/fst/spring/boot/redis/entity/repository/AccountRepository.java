package fst.spring.boot.redis.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fst.spring.boot.redis.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
