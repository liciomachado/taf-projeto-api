package com.rest.taf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Exercicio")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exercicio {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "flexao")
	private int flexao;

	@Column(name = "abdominal")
	private int abdominal;
	
	@Column(name = "barra")
	private int barra;
	
	@Column(name = "corrida")
	private int corrida;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id_usuario")
	private Usuario usuario;
	
}
