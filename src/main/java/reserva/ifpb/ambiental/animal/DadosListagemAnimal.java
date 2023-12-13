package reserva.ifpb.ambiental.animal;

public record DadosListagemAnimal(Long id, String nome, String nomeCientifico, String descricao, Tipo tipo, String imagem) {
	public DadosListagemAnimal(Animal animal) {
        this(animal.getId(),animal.getNome(), animal.getNomeCientifico(), animal.getDescricao(), animal.getTipo(), animal.getImagem());
    }

}
