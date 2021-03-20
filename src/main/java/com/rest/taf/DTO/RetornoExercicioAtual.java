package com.rest.taf.DTO;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class RetornoExercicioAtual {

	@Enumerated(value = EnumType.STRING)
	private List<IndicePorExercicio> indiceTaf;
	
	@JsonFormat(pattern="HH:mm dd/MM/yyyy")
	private LocalDateTime dataExercicio;	
	
	private IndiceTaf resultadoFinal;
}
