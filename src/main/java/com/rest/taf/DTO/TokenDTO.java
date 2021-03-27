package com.rest.taf.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rest.taf.enums.Genero;
import com.rest.taf.enums.IndiceTaf;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {

	private Long id;
	
	private String nome;
	
	private String email;
	
	private int idade;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate nascimento;
	
	private Genero genero;
	
	private int diaDesafio;
	
	@JsonFormat(pattern="HH:mm dd/MM/yyyy")
	private LocalDateTime dataUltimoExercicio;
	
	private IndiceTaf indiceTaf;
	
	private String token;
}
