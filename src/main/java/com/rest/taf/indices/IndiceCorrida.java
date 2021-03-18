package com.rest.taf.indices;

import javax.persistence.Column;

import com.rest.taf.model.Indices;


public class IndiceCorrida extends Indices {

	@Column(name = "tipo")
	private String corrida;
}
