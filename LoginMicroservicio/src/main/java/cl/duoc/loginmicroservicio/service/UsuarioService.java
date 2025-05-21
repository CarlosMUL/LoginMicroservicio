package cl.duoc.loginmicroservicio.service;

import cl.duoc.loginmicroservicio.model.Usuario;
import cl.duoc.loginmicroservicio.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario buscarPorNombreUsuario(String nombreUsuario) {
        return usuarioRepository.findByNombreusuario(nombreUsuario);
    }


    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void eliminarTodosLosUsuarios() {
        usuarioRepository.deleteAll();
    }
}
