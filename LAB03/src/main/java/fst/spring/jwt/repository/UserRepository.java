package fst.spring.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fst.spring.jwt.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
