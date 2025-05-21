package cl.duoc.loginmicroservicio.Controller;

import cl.duoc.loginmicroservicio.model.Usuario;
import cl.duoc.loginmicroservicio.repository.UsuarioRepository;
import cl.duoc.loginmicroservicio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/gestionusuario")
public class UserController {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/{nombreUsuario}/{contrasena}")
    public ResponseEntity<?> autenticarUsuario(@PathVariable String nombreUsuario, @PathVariable String contrasena) {
        try {
            Usuario usuario = usuarioService.buscarPorNombreUsuario(nombreUsuario);
            String tipoUsuario = "no existe";
            boolean autenticado = false;
            System.out.println(usuario);

            if (usuario != null) {
                tipoUsuario = usuario.getTipousuario().toString();
                if (contrasena.equals(usuario.getContrasena())) {
                    autenticado = true;
                }
            }

            Map<String, Object> respuesta = Map.of(
                    "autenticado", autenticado,
                    "tipoUsuario", tipoUsuario
            );

            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error al autenticar el usuario equisde."));
        }
    }

    @GetMapping("/{nombreUsuario}/{contrasena}/{tipoUsuario}")
    public ResponseEntity<?> crearUsuario(@PathVariable String nombreUsuario, @PathVariable String contrasena, @PathVariable String tipoUsuario) {
        try {
            Usuario usuario = new Usuario();
            usuario.setNombreusuario(nombreUsuario);
            usuario.setContrasena(contrasena);
            usuario.setTipousuario(tipoUsuario);
            Usuario usuarioCreado = usuarioService.crearUsuario(usuario);
            return ResponseEntity.ok(usuarioCreado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en la autenticación.");
        }
    }

    @GetMapping("/eliminar-todos")
    public ResponseEntity<?> eliminarTodosLosUsuarios() {
        try {
            usuarioService.eliminarTodosLosUsuarios();
            return ResponseEntity.ok("Todos los usuarios han sido eliminados correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar los usuarios.");
        }
    }
}
