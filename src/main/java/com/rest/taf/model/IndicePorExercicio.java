package com.rest.taf.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.rest.taf.enums.ExerciciosDoTaf;
import com.rest.taf.enums.IndiceTaf;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndicePorExercicio {

	private int idade;
	
	private int quantidadeExecutada;
	
	@Enumerated(value = EnumType.STRING)
	private ExerciciosDoTaf exerciciosDoTaf;
	
	@Enumerated(value = EnumType.STRING)
	private IndiceTaf indiceTaf;
	
}
