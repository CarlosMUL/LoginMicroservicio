package cl.duoc.loginmicroservicio;

import cl.duoc.loginmicroservicio.model.Usuario;
import cl.duoc.loginmicroservicio.repository.UsuarioRepository;
import cl.duoc.loginmicroservicio.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class LoginMicroservicioApplicationTests {
    @Mock
    private UsuarioRepository  usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);


    }
    @Test
    public void testBuscarUsuarios() {
        List<Usuario> lista = new ArrayList<>();

        Usuario usuario1 = new Usuario();
        usuario1.setIdUsuario(15L);
        usuario1.setNombreUsuario("Miguel Perez");
        usuario1.setContrasena("@Miguelito224");
        usuario1.setTipoUsuario("ADMIN");
        lista.add(usuario1);

        when(usuarioRepository.findAll()).thenReturn(lista);

        List<Usuario>resultadoBusqueda = usuarioService.buscarUsuarios();
        assertEquals(1, resultadoBusqueda.size());
        verify(usuarioRepository,times(1)).findAll();
    }
    @Test
    public void pruebaBuscarUsuario() {
        Usuario usuario1 = new Usuario();
        usuario1.setIdUsuario(15L);
        usuario1.setNombreUsuario("Miguel Perez");
        usuario1.setContrasena("@Miguelito224");
        usuario1.setTipoUsuario("ADMIN");



        when (usuarioRepository.findById(15L)).thenReturn(Optional.of(usuario1));

        Usuario sucursalBuscada = usuarioService.buscarUsuario(15L);
        assertEquals(15L, sucursalBuscada.getIdUsuario());
        verify(usuarioRepository,times(1)).findById(15L);

    }

    @Test
    public void pruebaGuardarUsuario() {
        Usuario usuario1 = new Usuario();
        usuario1.setIdUsuario(33L);
        usuario1.setNombreUsuario("Miguel Perez");
        usuario1.setContrasena("@Miguelito224");
        usuario1.setTipoUsuario("ADMIN");

        when (usuarioRepository.save(usuario1)).thenReturn(usuario1);
        Usuario usuariolGuardada = usuarioService.guardarUsuario(usuario1);
        assertEquals(33L, usuariolGuardada.getIdUsuario());
        verify(usuarioRepository,times(1)).save(usuario1);

    }

    @Test
    public void pruebaEliminarUsuario() {
        String idUsuario = "15L";
        doNothing().when(usuarioRepository).deleteById(15L);
        usuarioService.eliminarUsuario(15L);

        verify(usuarioRepository,times(1)).deleteById(15L);

    }

    @Test
    public void pruebaEditarUsuario(){

        Usuario usuario1 = new Usuario();
        usuario1.setIdUsuario(33L);
        usuario1.setNombreUsuario("Miguel Perez");
        usuario1.setContrasena("@Miguelito224");
        usuario1.setTipoUsuario("ADMIN");

        Usuario usuarioEditado = new Usuario();
        usuarioEditado.setIdUsuario(15L);
        usuarioEditado.setNombreUsuario("Florencio Venegas");
        usuarioEditado.setContrasena("pedrito335");
        usuarioEditado.setTipoUsuario("CLIENTE");

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioEditado);
        when(usuarioRepository.existsById(15L)).thenReturn(true);
        Usuario resultado = usuarioService.guardarUsuario(usuarioEditado);

        assertNotNull(resultado);
        assertEquals(15L, resultado.getIdUsuario());
        assertEquals("Florencio Venegas", resultado.getNombreUsuario());
        assertEquals("pedrito335", resultado.getContrasena());
        assertEquals("CLIENTE", resultado.getTipoUsuario());

        verify(usuarioRepository, times(1)).save(usuarioEditado);
    }
}
