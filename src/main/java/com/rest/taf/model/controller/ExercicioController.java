package com.rest.taf.model.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.taf.enums.Genero;
import com.rest.taf.model.Indices;
import com.rest.taf.model.Usuario;
import com.rest.taf.repositories.IndicesRepository;
import com.rest.taf.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/exercicio")
@RequiredArgsConstructor
public class ExercicioController {

	@Autowired
	private IndicesRepository indicesRepository;

	@Autowired
	UsuarioRepository usuarioRepository;
	
	Usuario user = Usuario.builder().nome("Mauricio").idade(18).genero(Genero.MASCULINO).build();

	@GetMapping("/{id}")
	public ResponseEntity<?> getIndiceTeste(@PathVariable("id") Long id) {
		Optional<Usuario> usuarioPorId = usuarioRepository.findById(id);
		if (usuarioPorId.isPresent()) {
			List<Indices> findByIdadeAndGenero = indicesRepository.findByIdadeAndGenero(usuarioPorId.get().geraIdadeUsuario(), usuarioPorId.get().getGenero());
			return ResponseEntity.ok(findByIdadeAndGenero);			
		}
		return ResponseEntity.badRequest().body("ID não encontrado");
	}
	
	
//	@GetMapping("/{idade}")
//	public ResponseEntity<?> getIndiceTeste(@PathVariable("idade") int idade) {
//		user.setIdade(idade);
//		Exercicio exer = new Exercicio();
//		exer.setCorrida(3200);
//		exer.setFlexao(33);
//		exer.setAbdominal(63);
//		exer.setBarra(13);
//		exer.setUsuario(user);
//		
//		List<Indices> findByIdadeAndGenero = indicesRepository.findByIdadeAndGenero(idade,exer.getUsuario().getGenero());
//		
//		List<IndicePorExercicio> buscaIndiceDoExercicio = ExercicioService.buscaIndiceDoExercicio(exer,findByIdadeAndGenero);
//		IndiceTaf defineIndicePorResultadosDeExercicios = ExercicioService
//				.defineIndicePorResultadosDeExercicios(buscaIndiceDoExercicio);
//		
//		return ResponseEntity.ok(retornoExercicioAtual.builder().indiceTaf(buscaIndiceDoExercicio)
//				.resultadoFinal(defineIndicePorResultadosDeExercicios).build());
//	}
}
