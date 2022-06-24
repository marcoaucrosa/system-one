package br.com.marco.systemone.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.marco.systemone.model.User;
import br.com.marco.systemone.service.RoleService;
import br.com.marco.systemone.service.UserService;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

	
	private final UserService userService;
	private final RoleService roleService;
	
	public UserController(UserService userService, RoleService roleService) {
		this.userService = userService;
		this.roleService = roleService;
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<User>> findAllUsers() {
		return ResponseEntity.ok().body(userService.findAllUsers());
	}
	
	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		user.addRole(roleService.findRoleByAuthority("USER"));
		return ResponseEntity.ok().body(userService.saveUser(user));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
		user.setId(id);
		return ResponseEntity.ok().body(userService.saveUser(user));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removeUser(@PathVariable Long id) {
		userService.removeUser(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/login")
	public ResponseEntity<Void> login(){
		return ResponseEntity.ok().build();
	}
}
