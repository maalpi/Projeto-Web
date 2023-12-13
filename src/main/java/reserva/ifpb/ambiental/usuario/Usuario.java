package reserva.ifpb.ambiental.usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;

    private String email;

    private String senha;

    
    public Usuario() {
        // Construtor padrão sem parâmetros
    }
    
    public Usuario(DadosCadastroUsuario dados) {
        this.nome = dados.nome();
        this.tipo = dados.tipo();
        this.email = dados.email();
        this.senha = dados.senha();
    }
    
    public void atualizarInformacoes(DadosAtualizacaoUsuario dados) {

    	if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.tipo() != null) {
            this.tipo = dados.tipo();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.senha() != null) {
            this.senha = dados.senha();
        }
    	
    }
    
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

}
