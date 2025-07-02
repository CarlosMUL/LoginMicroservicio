package cl.duoc.loginmicroservicio.service;

import cl.duoc.loginmicroservicio.model.Usuario;
import cl.duoc.loginmicroservicio.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> buscarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuario(Long idUsuario) {
        return usuarioRepository.findById(idUsuario).get();
    }

    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }
}

