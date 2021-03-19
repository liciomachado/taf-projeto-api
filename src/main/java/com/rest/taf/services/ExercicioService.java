package com.rest.taf.services;

import java.util.List;

import com.rest.taf.enums.IndiceTaf;
import com.rest.taf.model.Exercicio;
import com.rest.taf.model.IndicePorExercicio;
import com.rest.taf.model.Indices;

public interface ExercicioService {

	List<IndicePorExercicio> buscaIndiceDoExercicio(Exercicio exercicio, List<Indices> findByIdadeAndGenero);
	
	IndiceTaf defineIndicePorResultadosDeExercicios(List<IndicePorExercicio> indicesExaminados);

	Exercicio salvar(Exercicio exercicio);

	
}
