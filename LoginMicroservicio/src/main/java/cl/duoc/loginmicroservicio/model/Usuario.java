package cl.duoc.loginmicroservicio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USUARIO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDUSUARIO")
    private Long idusuario;

    @Column(name = "NOMBREUSUARIO", nullable = false, length = 255)
    private String nombreusuario;

    @Column(name = "CONTRASENA", nullable = false, length = 255)
    private String contrasena;

    @Column(name = "TIPOUSUARIO", nullable = false, length = 255)
    private String tipousuario;
}
