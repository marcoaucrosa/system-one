package br.com.marco.systemone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.marco.systemone.model.User;
import br.com.marco.systemone.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	private final UserRepository repository;
	
	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	public List<User> findAllUsers() {
		return repository.findAll();
	}

	public User saveUser(User t) {
		t.setPassword(new BCryptPasswordEncoder().encode(t.getPassword()));
		t.setEnabled(true);
		return repository.save(t);
	}

	public void removeUser(Long id) {
		repository.deleteById(id);
		
	}

	public Optional<User> findUserById(Long id) {
		return repository.findById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository
                .findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("Username %s not found", username))
                );
	}
	
	
}
