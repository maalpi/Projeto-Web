package reserva.ifpb.ambiental.usuario;

public record DadosListagemUsuario(
        Long id,
        String nome,
        TipoUsuario tipo,
        String email
) {
    public DadosListagemUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getTipo(), usuario.getEmail());
    }
}
