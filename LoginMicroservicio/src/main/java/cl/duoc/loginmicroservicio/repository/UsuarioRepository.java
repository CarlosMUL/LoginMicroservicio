package cl.duoc.loginmicroservicio.repository;

import cl.duoc.loginmicroservicio.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByNombreusuario(String nombreusuario);
}
