package com.rest.taf.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.taf.DTO.RetornoExercicioAtual;
import com.rest.taf.enums.IndiceTaf;
import com.rest.taf.model.Exercicio;
import com.rest.taf.model.IndicePorExercicio;
import com.rest.taf.model.Indices;
import com.rest.taf.model.Usuario;
import com.rest.taf.repositories.IndicesRepository;
import com.rest.taf.repositories.UsuarioRepository;
import com.rest.taf.security.JwtService;
import com.rest.taf.services.ExercicioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/exercicio")
@RequiredArgsConstructor
public class ExercicioController {

	private final IndicesRepository indicesRepository;
	private final UsuarioRepository usuarioRepository;
	private final ExercicioService exercicioService;
	private final JwtService jwtService;

	@GetMapping("/{id}")
	public ResponseEntity<?> getIndiceTeste(@PathVariable("id") Long id) {
		
		Optional<Usuario> usuarioPorId = usuarioRepository.findById(id);
		if (usuarioPorId.isPresent()) {
			List<Indices> findByIdadeAndGenero = indicesRepository
					.findByIdadeAndGenero(usuarioPorId.get().geraIdadeUsuario(), usuarioPorId.get().getGenero());
			return ResponseEntity.ok(findByIdadeAndGenero);
		}
		return ResponseEntity.badRequest().body("ID não encontrado");
	}

	@PostMapping("/salvar")
	public ResponseEntity<?> salvarExercicio(@Valid @RequestBody Exercicio exercicio, HttpServletRequest request) {
		Optional<Usuario> usuarioPorId = jwtService.pegaUsuarioPorToken(request);
		exercicio.setDataExercicio(LocalDateTime.now());
		var dia = usuarioPorId.get().novoDia();
		if (usuarioPorId.isPresent()) {
			List<Indices> findByIdadeAndGenero = indicesRepository
					.findByIdadeAndGenero(usuarioPorId.get().geraIdadeUsuario(), usuarioPorId.get().getGenero());
			exercicio.setUsuario(usuarioPorId.get());
			exercicio.setDiaSemana(dia);
			
			try {
				Exercicio saved = exercicioService.salvar(exercicio);
				List<IndicePorExercicio> buscaIndiceDoExercicio = exercicioService.buscaIndiceDoExercicio(exercicio,findByIdadeAndGenero);
				IndiceTaf defineIndicePorResultadosDeExercicios = exercicioService.defineIndicePorResultadosDeExercicios(buscaIndiceDoExercicio);
				usuarioPorId.get().setDiaDesafio(dia);
				if(usuarioPorId.get().getIndiceTaf() == null) {
					usuarioPorId.get().setIndiceTaf(defineIndicePorResultadosDeExercicios);					
					
				}
				usuarioRepository.save(usuarioPorId.get());
				
				//RETORNO DTO
				return ResponseEntity.ok(RetornoExercicioAtual.builder().indiceTaf(buscaIndiceDoExercicio)
						.dataExercicio(saved.getDataExercicio())
						.resultadoFinal(defineIndicePorResultadosDeExercicios)
						.diaDesafio(dia)
						.build());
			} catch (Exception e) {
				return ResponseEntity.badRequest().body("Erro ao salvar exercicio");
			}
		}
		return ResponseEntity.badRequest().body("Usuario não encontrado");
	}

	@GetMapping("/necessario/{id}")
	public ResponseEntity<?> getIndiceNecessarioPorExercicio(@PathVariable("id") Long id) {

		Optional<Usuario> usuarioPorId = usuarioRepository.findById(id);
		if (usuarioPorId.isPresent()) {
			List<Indices> findByIdadeAndGenero = indicesRepository
					.findByIdadeAndGenero(usuarioPorId.get().geraIdadeUsuario(), usuarioPorId.get().getGenero());

			var ultimoExercicio = usuarioPorId.get().getExercicio().size();
			if (ultimoExercicio > 0) {
				List<IndicePorExercicio> buscaIndiceDoExercicio = exercicioService.buscaIndiceDoExercicio(
						usuarioPorId.get().getExercicio().get(ultimoExercicio - 1), findByIdadeAndGenero);
				IndiceTaf defineIndicePorResultadosDeExercicios = exercicioService
						.defineIndicePorResultadosDeExercicios(buscaIndiceDoExercicio);

				return ResponseEntity.ok(RetornoExercicioAtual.builder().indiceTaf(buscaIndiceDoExercicio)
						.resultadoFinal(defineIndicePorResultadosDeExercicios).build());
			}
			return ResponseEntity.badRequest().body("Não foram encontrados exercicios para verificar indices");
		}
		return ResponseEntity.badRequest().body("Erro ao realizar busca");
	}
	
	@GetMapping("/semana/{semana}")
	public ResponseEntity<?> getSemanaDeExercicio(@PathVariable("semana") Long semana, HttpServletRequest request) {
		Optional<Usuario> usuarioPorId = jwtService.pegaUsuarioPorToken(request);
		int semanaDia = (int) (semana * 7);
		List<Exercicio> listaPorSemana = usuarioPorId.get().getExercicio().stream()
				.filter(c -> c.getDiaSemana() > (semanaDia - 7) && c.getDiaSemana() <= (semanaDia))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(listaPorSemana);
		//return ResponseEntity.badRequest().body("Erro ao realizar busca");
	}
	
}
