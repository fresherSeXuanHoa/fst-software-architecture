package fst.spring.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fst.spring.jwt.authen.UserPrincipal;
import fst.spring.jwt.entity.Token;
import fst.spring.jwt.entity.User;
import fst.spring.jwt.service.TokenService;
import fst.spring.jwt.service.UserService;
import fst.spring.jwt.util.JwtUtil;

@RestController
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private TokenService tokenService;

	@PostMapping("/register")
	public User register(@RequestBody User user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

		return userService.createUser(user);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {

		UserPrincipal userPrincipal = userService.findByUsername(user.getUsername());

		if (null == user || !new BCryptPasswordEncoder().matches(user.getPassword(), userPrincipal.getPassword())) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account or password is not valid!");
		}

		Token token = new Token();
		token.setToken(jwtUtil.generateToken(userPrincipal));

		token.setTokenExpDate(jwtUtil.generateExpirationDate());
		token.setCreatedBy(userPrincipal.getUserId());
		tokenService.createToken(token);

		return ResponseEntity.ok(token.getToken());
	}

	@GetMapping("/hello")
	@PreAuthorize("hasAnyAuthority('USER_READ')")
	public ResponseEntity hello() {
		return ResponseEntity.ok("hello");
	}

	// Object principal = SecurityContextHolder
	// .getContext().getAuthentication().getPrincipal();
	//
	// if (principal instanceof UserDetails) {
	// UserPrincipal userPrincipal = (UserPrincipal) principal;
	// }

}
