package com.rest.taf.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rest.taf.enums.Genero;
import com.rest.taf.enums.IndiceTaf;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Usuario")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Transient
	private int idade;

	@Column(name = "nome")
	private String nome;
	
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)	
	@Column(name = "senha")
	private String senha;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	@Column(name = "nascimento")
	private LocalDate nascimento;
	
	@Column(name = "genero")
	@Enumerated(value = EnumType.STRING)
	private Genero genero;
	
	@Column(name = "indiceTaf")
	@Enumerated(value = EnumType.STRING)
	private IndiceTaf indiceTaf;

	@Column(name = "email")
	private String email;
	
	@JsonBackReference
	@OneToMany(mappedBy = "usuario")
	private List<Exercicio> exercicio;
	
	public int geraIdadeUsuario() {
		// REALIZA CALCULO DE IDADE
		final LocalDate dataAtual = LocalDate.now();
		final Period periodo = Period.between(this.getNascimento(), dataAtual);
		int idade = periodo.getYears();
		return idade;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@JsonIgnore
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
			
}