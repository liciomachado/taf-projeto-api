package com.rest.taf.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.taf.exception.SenhaInvalidaException;
import com.rest.taf.model.Usuario;
import com.rest.taf.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UsuarioRepository repository;
	
	public Usuario autenticar(Usuario usuario) {
		Usuario user = loadUserByUsername(usuario.getEmail());
		boolean senhasBatem = encoder.matches(usuario.getSenha(), user.getPassword());
		if (senhasBatem) {
			return user;
		}
		throw new SenhaInvalidaException();
	}
	
	@Override
	public Usuario loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = repository.findByEmail(email)
				.orElseThrow( () -> new UsernameNotFoundException("Usuario não encontrado na base de dados"));				
		return usuario;
	}
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		return repository.save(usuario);
	}

}
