package fst.spring.jwt.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fst.spring.jwt.authen.UserPrincipal;
import fst.spring.jwt.entity.User;
import fst.spring.jwt.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(User user) {
		return userRepository.saveAndFlush(user);
	}

	@Override
	public UserPrincipal findByUsername(String username) {
		User user = userRepository.findByUsername(username);
		UserPrincipal userPrincipal = new UserPrincipal();

		if (null != user) {

			Set<String> authorities = new HashSet<>();

			if (null != user.getRoles())

				user.getRoles().forEach(r -> {
					authorities.add(r.getRoleKey());
					r.getPermissions().forEach(p -> authorities.add(p.getPermissionKey()));
				});

			userPrincipal.setUserId(user.getId());
			userPrincipal.setUsername(user.getUsername());
			userPrincipal.setPassword(user.getPassword());
			userPrincipal.setAuthorities(authorities);

		}

		return userPrincipal;

	}

}