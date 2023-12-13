package reserva.ifpb.ambiental.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DadosCadastroUsuario(
        @NotBlank String nome,
        @NotNull TipoUsuario tipo,
        @NotBlank String email,
        @NotBlank String senha
) {
}
