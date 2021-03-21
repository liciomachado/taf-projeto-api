package com.rest.taf.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rest.taf.DTO.CredenciaisDTO;
import com.rest.taf.DTO.TokenDTO;
import com.rest.taf.enums.Genero;
import com.rest.taf.exception.SenhaInvalidaException;
import com.rest.taf.model.Usuario;
import com.rest.taf.repositories.UsuarioRepository;
import com.rest.taf.security.JwtService;
import com.rest.taf.services.impl.UsuarioServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;

	private final UsuarioServiceImpl usuarioService;
	private final JwtService jwtService;

	Usuario user = Usuario.builder().nome("Mauricio").idade(18).genero(Genero.MASCULINO).build();

	@PostMapping("/save")
	public ResponseEntity<?> salvar(@RequestBody Usuario usuario) {
		try {
			Optional<Usuario> busca = usuarioRepository.findByEmail(usuario.getEmail());
			if (busca.isPresent()) {
				return ResponseEntity.badRequest().body("Email já cadastrado");
			} else {
				// usuario.setSenha("123321");
				usuario.setSenha(new BCryptPasswordEncoder(12).encode(usuario.getSenha()));
				Usuario saved = usuarioRepository.save(usuario);
				return ResponseEntity.ok(saved);
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e);
		}
	}

	@PostMapping("/auth")
	public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais) {
		try {
			Usuario usuario = Usuario.builder().email(credenciais.getEmail()).senha(credenciais.getSenha()).build();
			Usuario usuarioAutenticado = usuarioService.autenticar(usuario);

			int idade = usuarioAutenticado.geraIdadeUsuario();
			
			int size = usuarioAutenticado.getExercicio().size();
			LocalDateTime dataUltimoExercicio = null;
			if (size > 0) { dataUltimoExercicio = usuarioAutenticado.getExercicio().get(size - 1).getDataExercicio();}
			
			String token = jwtService.gerarToken(usuario);
			var tokenDto = TokenDTO.builder().id(usuarioAutenticado.getId()).nome(usuarioAutenticado.getNome())
					.email(usuarioAutenticado.getEmail()).idade(idade).indiceTaf(usuarioAutenticado.getIndiceTaf())
					.dataUltimoExercicio(dataUltimoExercicio)
					.token(token).build();
			return tokenDto;

		} catch (UsernameNotFoundException | SenhaInvalidaException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
	}

	

	
}
