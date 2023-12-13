package reserva.ifpb.ambiental.animal;

import org.springframework.web.bind.annotation.CrossOrigin;

import jakarta.validation.constraints.NotNull;

@CrossOrigin(origins = "*")
public record DadosAtualizacaoAnimal(
        @NotNull
        Long id,
        String nome,
        String nomeCientifico,
        String descricao,
        Tipo tipo,
        String imagem) {
	

}