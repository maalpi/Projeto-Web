package reserva.ifpb.ambiental.flora;

public record DadosListagemFlora(Long id, String nome, String nomeCientifico, String descricao, TipoFlora tipo, String imagem) {
	public DadosListagemFlora(Flora flora) {
		this(flora.getId(),flora.getNome(), flora.getNomeCientifico(), flora.getDescricao(), flora.getTipo(), flora.getImagem());
	}   
}
