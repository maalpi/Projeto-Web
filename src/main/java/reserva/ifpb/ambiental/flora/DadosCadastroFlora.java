package reserva.ifpb.ambiental.flora;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DadosCadastroFlora(@NotBlank String nome, 
		  @NotBlank String nomeCientifico,
		  @NotBlank String descricao,
		  @NotNull TipoFlora tipo, 
		  @NotBlank String imagem) {

}
