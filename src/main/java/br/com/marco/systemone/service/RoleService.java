package br.com.marco.systemone.service;

import org.springframework.stereotype.Service;

import br.com.marco.systemone.model.Role;
import br.com.marco.systemone.repository.RoleRepository;

@Service
public class RoleService {
	
	private final RoleRepository repository;
	
	public RoleService(RoleRepository repository) {
		this.repository = repository;
	}
	
	public Role findRoleByAuthority(String authority) {
		return repository
                .findRoleByAuthority(authority)
                .orElseThrow(() ->
                        new RuntimeException(String.format("Role %s not found", authority))
                ); 
	}
	

}
