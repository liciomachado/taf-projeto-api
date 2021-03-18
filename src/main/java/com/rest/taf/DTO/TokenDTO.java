package com.rest.taf.DTO;

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
	
	private IndiceTaf indiceTaf;
	
	private String token;
}
