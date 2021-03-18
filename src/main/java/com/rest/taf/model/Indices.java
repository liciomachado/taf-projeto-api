package com.rest.taf.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rest.taf.enums.ExerciciosDoTaf;
import com.rest.taf.enums.Genero;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Indices")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Indices {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "idade")
	private int idade;
	
	@Column(name = "exercicioDoTaf")
	@Enumerated(value = EnumType.STRING)
	private ExerciciosDoTaf exerciciosDoTaf;
	
	@Column(name = "genero")
	@Enumerated(value = EnumType.STRING)
	private Genero genero;

	@Column(name = "insuficiente")
	private int insuficiente;
	
	@Column(name = "regular")
	private int regular;
	
	@Column(name = "bom")
	private int bom;
	
	@Column(name = "muitoBom")
	private int muitoBom;
	
	@Column(name = "excelente")
	private int excelente;
	
}
