package reserva.ifpb.ambiental.animal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroAnimal(@NotBlank String nome, 
								  @NotBlank String nomeCientifico,
								  @NotBlank String descricao,
								  @NotNull Tipo tipo, 
								  @NotBlank String imagem) {

}
