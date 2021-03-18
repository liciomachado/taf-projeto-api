package com.rest.taf.DTO;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.rest.taf.enums.IndiceTaf;
import com.rest.taf.model.IndicePorExercicio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class retornoExercicioAtual {

	@Enumerated(value = EnumType.STRING)
	private List<IndicePorExercicio> indiceTaf;
	
	private IndiceTaf resultadoFinal;
}
