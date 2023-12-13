package reserva.ifpb.ambiental.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
    Usuario findByTipo(TipoUsuario tipo);
    Usuario findByEmail(String email);
   
  
}
