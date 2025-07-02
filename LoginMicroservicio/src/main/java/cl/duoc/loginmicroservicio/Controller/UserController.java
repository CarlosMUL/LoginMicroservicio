package cl.duoc.loginmicroservicio.Controller;

import cl.duoc.loginmicroservicio.model.Usuario;
import cl.duoc.loginmicroservicio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/s1/usuario")
public class UserController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<?> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.buscarUsuarios();
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay usuarios registrados.");
        } else {
            return ResponseEntity.ok(usuarios);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable Long idUsuario) {
        try {
            Usuario usuario = usuarioService.buscarUsuario(idUsuario);
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
            }
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar usuario.");
        }
    }

    @PostMapping
    public ResponseEntity<?> guardarUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario usuarioGuardado = usuarioService.guardarUsuario(usuario);
            return ResponseEntity.ok(usuarioGuardado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No se pudo guardar el usuario.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long idUsuario) {
        try {
            Usuario usuario = usuarioService.buscarUsuario(idUsuario);
            if (usuario == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
            }
            usuarioService.eliminarUsuario(idUsuario);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar usuario.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long idUsuario, @RequestBody Usuario usuarioActualizar) {
        try {
            Usuario usuarioExistente = usuarioService.buscarUsuario(idUsuario);
            if (usuarioExistente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
            }
            usuarioExistente.setNombreUsuario(usuarioActualizar.getNombreUsuario());
            usuarioExistente.setContrasena(usuarioActualizar.getContrasena());
            usuarioExistente.setTipoUsuario(usuarioActualizar.getTipoUsuario());
            usuarioService.guardarUsuario(usuarioExistente);
            return ResponseEntity.ok(usuarioExistente);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar usuario.");
        }
    }
}



