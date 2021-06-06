package com.gestionclubdeportivo.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gestionclubdeportivo.entidad.User;
import com.gestionclubdeportivo.repositorio.UserRepository;

public class UserService implements UserDetailsService {

	@Autowired
	UserRepository userrepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userrepository.findByUsername(username);
	}

	public Iterable<User> findAll() {
		return userrepository.findAll();
	}

	public User findByUsername(String username) {
		return userrepository.findByUsername(username);
	}

	public void save(User user) {
		userrepository.save(user);
	}

	public User findById(Long id) {
		return userrepository.findById(id).orElse(null);
	}

	public void deleteById(Long id) {
		userrepository.deleteById(id);
	}
	
	public List<User> findAllByOrderByRol(){
		return userrepository.findAllByOrderByRol();
	}

}
