package reserva.ifpb.ambiental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import reserva.ifpb.ambiental.animal.Animal;
import reserva.ifpb.ambiental.animal.AnimalRepository;
import reserva.ifpb.ambiental.animal.DadosAtualizacaoAnimal;
import reserva.ifpb.ambiental.animal.DadosCadastroAnimal;
import reserva.ifpb.ambiental.animal.DadosListagemAnimal;
import reserva.ifpb.ambiental.animal.Tipo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/animais")
@CrossOrigin(origins = "*")
public class AnimaisController {
	
	@Autowired
	private AnimalRepository repository;
	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody DadosCadastroAnimal dados) {
		System.out.println(dados);
		repository.save(new Animal(dados));
	}
	
	
	@GetMapping
    public Page<DadosListagemAnimal> listar(@RequestParam(value = "tipo", required = false) Tipo tipo,
            @PageableDefault(size = 5, sort = {"nome"}) Pageable paginacao) {
		
			Page<Animal> animaisPage;
		 if (tipo != null) {
			 animaisPage = repository.findByTipo(tipo, paginacao);
	        } else {
	        	animaisPage = repository.findAll(paginacao);
	        }
		 return animaisPage.map(DadosListagemAnimal::new);
		//return repository.findAll(paginacao).map(DadosListagemAnimal::new);
    }
	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizacaoAnimal dados) {
		var animal = repository.getReferenceById(dados.id());
		animal.atualizarInformacoes(dados);
		
	}
	
	
	@DeleteMapping("/{id}")
	@Transactional
	public void excluir(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
}
