package reserva.ifpb.ambiental.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import reserva.ifpb.ambiental.usuario.DadosAtualizacaoUsuario;
import reserva.ifpb.ambiental.usuario.DadosCadastroUsuario;
import reserva.ifpb.ambiental.usuario.DadosListagemUsuario;
import reserva.ifpb.ambiental.usuario.TipoUsuario;
import reserva.ifpb.ambiental.usuario.Usuario;
import reserva.ifpb.ambiental.usuario.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuariosController {

    @Autowired
    private UsuarioRepository usuarioRepository;

	@PostMapping
    @Transactional
    public void cadastrar(@RequestBody @ModelAttribute @Valid DadosCadastroUsuario dados) {
		
		
		if (dados.tipo().equals(TipoUsuario.ADMINISTRADOR)) {
            Usuario adminExistente = usuarioRepository.findByTipo(TipoUsuario.ADMINISTRADOR);
            if (adminExistente != null) {
                // Já existe um administrador cadastrado
                throw new RuntimeException("Já existe um administrador cadastrado.");
            }
        }
		Usuario usuario = new Usuario(dados);
        usuarioRepository.save(usuario);
    }

    @GetMapping
    public List<DadosListagemUsuario> listar() {
        return usuarioRepository.findAll().stream()
                .map(DadosListagemUsuario::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public DadosListagemUsuario obterDetalhes(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return new DadosListagemUsuario(usuario);
    }

    @PutMapping("/{id}")
    @Transactional
    public void atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoUsuario dados) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuario.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }
    
    @Controller
    @RequestMapping("/cadastro")
    public class GerenciamentoController {

        @GetMapping
        public String paginaGerenciamento() {
            return "cadastro"; // Nome do seu arquivo HTML para a página de gerenciamento
        }
    }
}