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

import reserva.ifpb.ambiental.flora.DadosAtualizacaoFlora;
import reserva.ifpb.ambiental.flora.DadosCadastroFlora;
import reserva.ifpb.ambiental.flora.DadosListagemFlora;
import reserva.ifpb.ambiental.flora.Flora;
import reserva.ifpb.ambiental.flora.FloraRepository;
import reserva.ifpb.ambiental.flora.TipoFlora;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/flora")
@CrossOrigin(origins = "*")
public class FloraController {
	
	@Autowired
	private FloraRepository repository;
	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody DadosCadastroFlora dados) {
		System.out.println(dados);
		repository.save(new Flora(dados));
	}
	
	
	@GetMapping
    public Page<DadosListagemFlora> listar(@RequestParam(value = "tipo", required = false) TipoFlora tipo,
            @PageableDefault(size = 5, sort = {"nome"}) Pageable paginacao) {
		
			Page<Flora> floraPage;
		 if (tipo != null) {
			 floraPage = repository.findByTipo(tipo, paginacao);
	        } else {
	        	floraPage = repository.findAll(paginacao);
	        }
		 return floraPage.map(DadosListagemFlora::new);
		//return repository.findAll(paginacao).map(DadosListagemFlora::new);
    }
	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizacaoFlora dados) {
		var flora = repository.getReferenceById(dados.id());
		flora.atualizarInformacoes(dados);
		
	}
	
	
	@DeleteMapping("/{id}")
	@Transactional
	public void excluir(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
}
