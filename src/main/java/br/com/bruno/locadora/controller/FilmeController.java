package br.com.bruno.locadora.controller;

import br.com.bruno.locadora.controller.dto.FilmeDto;
import br.com.bruno.locadora.controller.form.FilmeForm;
import br.com.bruno.locadora.modelo.Filme;
import br.com.bruno.locadora.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/filmes")
public class FilmeController {	
	
	@Autowired
	private FilmeRepository filmeRepository;
	
	@GetMapping
	public List<FilmeDto> listarFilmes (String nomeDoFilme){
		if (nomeDoFilme == null) {
			List<Filme> filmes = filmeRepository.findAll();
			return FilmeDto.converter(filmes);			
		} else {
			List<Filme> filmes = filmeRepository.findByNomeDoFilme(nomeDoFilme);
			return FilmeDto.converter(filmes);	
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<FilmeDto> cadastrarFilme (@RequestBody FilmeForm form, UriComponentsBuilder uriBuilder){
		Filme filme = form.converter();
		filmeRepository.save(filme);
		URI uri = uriBuilder.path("/filmes/{id}").buildAndExpand(filme.getId()).toUri();
		return ResponseEntity.created(uri).body(new FilmeDto(filme));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<FilmeDto> atualizarFilme(@PathVariable Long id ,@RequestBody FilmeForm form){
		Optional<Filme> f = filmeRepository.findById(id);
		if (f.isPresent()){
		Filme filme = form.atualizar(id, filmeRepository);
		return ResponseEntity.ok(new FilmeDto(filme));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> removerFilme(@PathVariable Long id ){
		Optional<Filme> f = filmeRepository.findById(id);
		if (f.isPresent()){
		filmeRepository.deleteById(id);
		return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	

}
