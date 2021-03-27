package com.rest.taf.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

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
	
	@Column(name = "diaSemana")
	private int diaSemana;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "usuario_id_usuario")
	private Usuario usuario;
	
	@Column(name = "dataExercicio")
	@JsonFormat(pattern="HH:mm dd/MM/yyyy")
	private LocalDateTime dataExercicio;
	
}
