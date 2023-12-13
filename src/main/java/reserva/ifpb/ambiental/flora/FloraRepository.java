package reserva.ifpb.ambiental.flora;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface FloraRepository extends JpaRepository<Flora, Long> {
	Page<Flora> findByTipo(TipoFlora tipo, Pageable pageable);

}
