package com.rest.taf.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.taf.enums.Genero;
import com.rest.taf.model.Indices;

public interface IndicesRepository extends JpaRepository<Indices, Long> {

	List<Indices> findByIdadeAndGenero(int idade, Genero genero);
}
