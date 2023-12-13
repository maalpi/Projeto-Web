package reserva.ifpb.ambiental.flora;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "flora")
@Entity(name = "Flora")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Flora {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
	private String nome;
    
    private String nomeCientifico;
    
    private String descricao;
    
    @Enumerated(EnumType.STRING)
    private TipoFlora tipo;
    
    private String imagem;

    public Flora(DadosCadastroFlora dados) {
        this.setNome(dados.nome());
        this.setNomeCientifico(dados.nomeCientifico());
        this.setDescricao(dados.descricao());
        this.tipo = dados.tipo();
        this.setImagem(dados.imagem());
    }

	public void atualizarInformacoes(DadosAtualizacaoFlora dados) {
		if (dados.nome() != null) {
            this.setNome(dados.nome());
        }
        if (dados.nomeCientifico() != null) {
            this.setNomeCientifico(dados.nomeCientifico());
        }
        if (dados.descricao() != null) {
            this.setDescricao(dados.descricao());
        }
        if (dados.tipo() != null) {
            this.tipo = dados.tipo();
        }
        
        if (dados.imagem() != null) {
            this.setImagem(dados.imagem());
        }

    }
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeCientifico() {
		return nomeCientifico;
	}

	public void setNomeCientifico(String nomeCientifico) {
		this.nomeCientifico = nomeCientifico;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public TipoFlora getTipo() {
		return tipo;
	}


	
}
