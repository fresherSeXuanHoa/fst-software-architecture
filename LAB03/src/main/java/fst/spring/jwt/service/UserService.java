package fst.spring.jwt.service;

import fst.spring.jwt.authen.UserPrincipal;
import fst.spring.jwt.entity.User;

public interface UserService {
	User createUser(User user);

	UserPrincipal findByUsername(String username);
}
