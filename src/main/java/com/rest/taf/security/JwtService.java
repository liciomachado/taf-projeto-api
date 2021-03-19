package com.rest.taf.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rest.taf.model.Usuario;
import com.rest.taf.services.impl.UsuarioServiceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

	@Value("${security.jwt.expiracao}")
	private String expiracao;
	
	@Value("${security.jwt.chave-assinatura}")
	private String chaveAssinatura;
	
	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	public String gerarToken(Usuario usuario) {
		long expString = Long.valueOf(expiracao);
		LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
		Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
		Date data = Date.from(instant);

		/*
		 * HashMap<String, Object> claims = new HashMap<>(); claims.put("email",
		 * "email@email.com"); claims.put("roles", "ADMIN");
		 */
		
		return Jwts
					.builder()
					.setSubject(usuario.getEmail())
					.setExpiration(data)
					//.setClaims(claims)
					.signWith(SignatureAlgorithm.HS512, chaveAssinatura)
					.compact();
	}
	
	private Claims obterClaims ( String token) throws ExpiredJwtException {
		return Jwts
				.parser()
				.setSigningKey(chaveAssinatura)
				.parseClaimsJws(token)
				.getBody();
	}
	
	public boolean tokenValido(String token) {
		try {
			Claims claims = obterClaims(token);
			Date dataExpiracao = claims.getExpiration();
			LocalDateTime data = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			return !LocalDateTime.now().isAfter(data);
		} catch (Exception e) {
			return false;
		}
	}
	
	public String obterLoginUsuario(String token) throws ExpiredJwtException {
		return (String) obterClaims(token).getSubject();
	}
	
	public Optional<Usuario> pegaUsuarioPorToken(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");
		
		if(authorization != null && authorization.startsWith("Bearer")) {
			String token = authorization.split(" ")[1];
			String emailUsuario = (String) obterClaims(token).getSubject();
			return usuarioService.getUsuarioPorEmail(emailUsuario);
		}
		return null;
	}
}