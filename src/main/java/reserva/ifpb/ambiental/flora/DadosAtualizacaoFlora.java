package reserva.ifpb.ambiental.flora;

import org.springframework.web.bind.annotation.CrossOrigin;

import jakarta.validation.constraints.NotNull;

@CrossOrigin(origins = "*")
public record DadosAtualizacaoFlora(
        @NotNull
        Long id,
        String nome,
        String nomeCientifico,
        String descricao,
        TipoFlora tipo,
        String imagem) {

}
