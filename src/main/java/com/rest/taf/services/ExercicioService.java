package com.rest.taf.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.rest.taf.enums.ExerciciosDoTaf;
import com.rest.taf.enums.IndiceTaf;
import com.rest.taf.model.Exercicio;
import com.rest.taf.model.IndicePorExercicio;
import com.rest.taf.model.Indices;

@Service
public class ExercicioService {

	public static List<IndicePorExercicio> buscaIndiceDoExercicio(Exercicio exercicio, List<Indices> findByIdadeAndGenero) {
		List<IndicePorExercicio> iPE = new ArrayList<>();
		
		for (Indices indices : findByIdadeAndGenero) {
			if (indices.getExerciciosDoTaf() == ExerciciosDoTaf.CORRIDA) {
				IndicePorExercicio newIPE = new IndicePorExercicio();
				newIPE.setExerciciosDoTaf(ExerciciosDoTaf.CORRIDA);
				newIPE.setIdade(exercicio.getUsuario().getIdade());
				newIPE.setQuantidadeExecutada(exercicio.getCorrida());
				
				if (exercicio.getCorrida() < indices.getInsuficiente()) {
					newIPE.setIndiceTaf(IndiceTaf.INSUFICIENTE);
				}
				if (exercicio.getCorrida() >= indices.getRegular()) {
					newIPE.setIndiceTaf(IndiceTaf.REGULAR);
				}
				if (exercicio.getCorrida() >= indices.getBom()) {
					newIPE.setIndiceTaf(IndiceTaf.BOM);
				}
				if (exercicio.getCorrida() >= indices.getMuitoBom()) {
					newIPE.setIndiceTaf(IndiceTaf.MUITO_BOM);
				}
				if (exercicio.getCorrida() >= indices.getExcelente()) {
					newIPE.setIndiceTaf(IndiceTaf.EXCELENTE);
				}
				iPE.add(newIPE);
			}
			
			if (indices.getExerciciosDoTaf() == ExerciciosDoTaf.FLEXAO) {
				IndicePorExercicio newIPE = new IndicePorExercicio();
				newIPE.setExerciciosDoTaf(ExerciciosDoTaf.FLEXAO);
				newIPE.setIdade(exercicio.getUsuario().getIdade());
				newIPE.setQuantidadeExecutada(exercicio.getFlexao());
				
				if (exercicio.getFlexao() < indices.getInsuficiente()) {
					newIPE.setIndiceTaf(IndiceTaf.INSUFICIENTE);
				}
				if (exercicio.getFlexao() >= indices.getRegular()) {
					newIPE.setIndiceTaf(IndiceTaf.REGULAR);
				}
				if (exercicio.getFlexao() >= indices.getBom()) {
					newIPE.setIndiceTaf(IndiceTaf.BOM);
				}
				if (exercicio.getFlexao() >= indices.getMuitoBom()) {
					newIPE.setIndiceTaf(IndiceTaf.MUITO_BOM);
				}
				if (exercicio.getFlexao() >= indices.getExcelente()) {
					newIPE.setIndiceTaf(IndiceTaf.EXCELENTE);
				}
				iPE.add(newIPE);
			}
			if (indices.getExerciciosDoTaf() == ExerciciosDoTaf.ABDOMINAL) {
				IndicePorExercicio newIPE = new IndicePorExercicio();
				newIPE.setExerciciosDoTaf(ExerciciosDoTaf.ABDOMINAL);
				newIPE.setIdade(exercicio.getUsuario().getIdade());
				newIPE.setQuantidadeExecutada(exercicio.getAbdominal());
				
				if (exercicio.getAbdominal() < indices.getInsuficiente()) {
					newIPE.setIndiceTaf(IndiceTaf.INSUFICIENTE);
				}
				if (exercicio.getAbdominal() >= indices.getRegular()) {
					newIPE.setIndiceTaf(IndiceTaf.REGULAR);
				}
				if (exercicio.getAbdominal() >= indices.getBom()) {
					newIPE.setIndiceTaf(IndiceTaf.BOM);
				}
				if (exercicio.getAbdominal() >= indices.getMuitoBom()) {
					newIPE.setIndiceTaf(IndiceTaf.MUITO_BOM);
				}
				if (exercicio.getAbdominal() >= indices.getExcelente()) {
					newIPE.setIndiceTaf(IndiceTaf.EXCELENTE);
				}
				iPE.add(newIPE);
			}
			if (indices.getExerciciosDoTaf() == ExerciciosDoTaf.BARRA) {
				IndicePorExercicio newIPE = new IndicePorExercicio();
				newIPE.setExerciciosDoTaf(ExerciciosDoTaf.BARRA);
				newIPE.setIdade(exercicio.getUsuario().getIdade());
				newIPE.setQuantidadeExecutada(exercicio.getBarra());
				
				if (exercicio.getBarra() < indices.getInsuficiente()) {
					newIPE.setIndiceTaf(IndiceTaf.INSUFICIENTE);
				}
				if (exercicio.getBarra() >= indices.getRegular()) {
					newIPE.setIndiceTaf(IndiceTaf.REGULAR);
				}
				if (exercicio.getBarra() >= indices.getBom()) {
					newIPE.setIndiceTaf(IndiceTaf.BOM);
				}
				if (exercicio.getBarra() >= indices.getMuitoBom()) {
					newIPE.setIndiceTaf(IndiceTaf.MUITO_BOM);
				}
				if (exercicio.getBarra() >= indices.getExcelente()) {
					newIPE.setIndiceTaf(IndiceTaf.EXCELENTE);
				}
				iPE.add(newIPE);
			}
		}
		return iPE;
	}
	
	public static IndiceTaf defineIndicePorResultadosDeExercicios(List<IndicePorExercicio> indicesExaminados){
		IndiceTaf indiceFinal = null;
		int valorIndice = 10;
		
		for (IndicePorExercicio indicePorExercicio : indicesExaminados) {
			if(indicePorExercicio.getIndiceTaf() == IndiceTaf.INSUFICIENTE) {
				if(IndiceTaf.INSUFICIENTE.ordinal() < valorIndice) {
					indiceFinal = IndiceTaf.INSUFICIENTE; valorIndice = IndiceTaf.INSUFICIENTE.ordinal();
				}
			}
			if(indicePorExercicio.getIndiceTaf() == IndiceTaf.REGULAR) {
				if(IndiceTaf.REGULAR.ordinal() < valorIndice) {
					indiceFinal = IndiceTaf.REGULAR; valorIndice = IndiceTaf.REGULAR.ordinal();
				}
			}
			if(indicePorExercicio.getIndiceTaf() == IndiceTaf.BOM) {
				if(IndiceTaf.BOM.ordinal() < valorIndice) {
					indiceFinal = IndiceTaf.BOM; valorIndice = IndiceTaf.BOM.ordinal();
				}
			}
			if(indicePorExercicio.getIndiceTaf() == IndiceTaf.MUITO_BOM) {
				if(IndiceTaf.MUITO_BOM.ordinal() < valorIndice) {
					indiceFinal = IndiceTaf.MUITO_BOM; valorIndice = IndiceTaf.MUITO_BOM.ordinal();
				}
			}
			if(indicePorExercicio.getIndiceTaf() == IndiceTaf.EXCELENTE) {
				if(IndiceTaf.EXCELENTE.ordinal() < valorIndice) {
					indiceFinal = IndiceTaf.EXCELENTE; valorIndice = IndiceTaf.EXCELENTE.ordinal();
				}
			}
		}
		return indiceFinal;
	}

	
}
