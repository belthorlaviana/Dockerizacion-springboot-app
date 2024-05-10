package com.example.SpringbootApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.example.SpringbootApp.modelo.RolUserVO;
import com.example.SpringbootApp.modelo.RolVO;
import com.example.SpringbootApp.modelo.UserVO;
import com.example.SpringbootApp.servicio.RolService;
import com.example.SpringbootApp.servicio.RolUserService;
import com.example.SpringbootApp.servicio.UserService;

@SpringBootTest
class SpringbootAppApplicationTests {


  @Autowired
  RolService rolService;

  @Autowired
  UserService usuarioService;

  @Autowired
  RolUserService rolUsuarioService;

  @Test
  void Testcase_1() {


  }

  // 3-Inserta registros en la tabla usuarios
  @Test
  void Testcase_2() {
    UserVO usuario = new UserVO("00000000-f", "Beltran", "Otero", LocalDate.of(1990, 9, 5),
        new ArrayList<RolUserVO>());

    UserVO usuario2 =
        new UserVO("11111111-f", "Vicente", "Mediavilla", LocalDate.of(1992, 10, 3),
            new ArrayList<RolUserVO>());

    UserVO usuario3 = new UserVO("22222222-f", "Maria", "Fernandez", LocalDate.of(1996, 6, 5),
        new ArrayList<RolUserVO>());

    UserVO usuario4 =
        new UserVO("33333333-f", "Elena", "Martinez", LocalDate.of(1999, 10, 11),
            new ArrayList<RolUserVO>());

    UserVO usuario5 = new UserVO("44444444-f", "Cristina", "Otero", LocalDate.of(2000, 11, 5),
        new ArrayList<RolUserVO>());

    UserVO usuario6 = new UserVO("55555555-f", "Alberto", "Romero", LocalDate.of(1991, 4, 1),
        new ArrayList<RolUserVO>());

    UserVO usuarioRepetido =
        new UserVO("12345671-f", "Beltran", "Otero", LocalDate.of(1990, 9, 5),
            new ArrayList<RolUserVO>());

    assertNotNull(usuarioService.save(usuario));
    assertNotNull(usuarioService.save(usuario2));
    assertNotNull(usuarioService.save(usuario3));
    assertNotNull(usuarioService.save(usuario4));
    assertNotNull(usuarioService.save(usuario5));
    assertNotNull(usuarioService.save(usuario6));
    assertNotNull(usuarioService.save(usuarioRepetido));
  }

  // 4-Asigna varios roles a un usuario que ya existe en la tabla
  @Disabled
  @Test
  void Testcase_3() {
    // busco usuario en la database
    UserVO usuario = usuarioService.findUserByNif("34567867-f").get();
    UserVO usuario2 = usuarioService.findUserByNif("99999999-f").get();
    // busco roles en la database
    RolVO rol1 = rolService.findRolesByDenominacion("administrador").get();
    RolVO rol2 = rolService.findRolesByDenominacion("usuario").get();

    // grabo en la base de datos
    assertNotNull(rolUsuarioService.save(new RolUserVO(usuario, rol1)));
    assertNotNull(rolUsuarioService.save(new RolUserVO(usuario, rol2)));
    assertNotNull(rolUsuarioService.save(new RolUserVO(usuario2, rol1)));
    // UsuarioVO usuario3 = usuarioService.findByNif2("34567867-f").get();
    // UsuarioVO usuario4 = usuarioService.findByNif2("34567867-f").get();
  }

  // 5-Asigna un rol a un usuario que ya lo tenía, controla el error y muestra el
  // mensaje correspondiente
  @Test
  @Disabled
  void Testcase_4() {
    String mensajeError = null;
    // busco usuario en la database
    UserVO usuario = usuarioService.findUserByNif("34567867-f").get();
    // busco roles en la database
    RolVO rol1 = rolService.findRolesByDenominacion("administrador").get();

    try {
      // al usuario ya se le asignó el rol "administrador" en el case anterior,ahora
      // tratamos de volver a darle el mismo rol.
      rolUsuarioService.save(new RolUserVO(usuario, rol1));
    } catch (DataIntegrityViolationException e) {
      mensajeError = e.getCause().getCause().toString();
    }

    assertTrue(mensajeError.contains("Duplicate entry '1-1' for key 'roles_usuarios"));

  }

  // 6-Intenta buscar un usuario que no existe en la base de datos y controla el
  // error mostrando el mensaje correspondiente.
  @Test
  @Disabled
  void Testcase_5() {
    UserVO usuario = null;
    String mensajeError = null;

    try {
      usuario = usuarioService.findUserByNif("34567844467-f").get();
    } catch (NoSuchElementException e) {
      mensajeError = e.getMessage();
    }
    assertEquals("No value present", mensajeError);
  }

  // 7- Utiliza el motor de búsquedas de Spring para mostrar todos los roles de un
  // usuario concreto
  @Test
  @Disabled
  void Testcase_6() {
    // busco usuario por id
    Optional<UserVO> usuario = usuarioService.findUserById(1);
    // forma 1:muestro roles
    System.out
        .println("-----------------ROLES DEL USUARIO CON ID=1 34567867-f -----------------------");
    if (usuario.isPresent()) {
      usuario.get().getRolUserVOregistries().forEach((registro) -> {
        System.out.println(registro.getRol().getDenomination());
      });
    }
    System.out
        .println("------------------------------------------------------------------------------");
    assertTrue(usuario.get().getRolUserVOregistries().size() == 2);
  }

  // 8- Utiliza el motor de búsquedas de Spring para mostrar todos los usuarios
  // con un rol en concreto
  @Test
  @Disabled
  void Testcase_7() {
    // busco el rol por id=1 = administrador
    Optional<RolVO> rol = rolService.findRolById(1);
    // forma 1:muestro usuarios con ese rol
    System.out
        .println("-----------------USUARIOS CON ROL ADMINISTRADOR-------------------------------");
    if (rol.isPresent()) {
      rol.get().getRolUserVOregistries().forEach((registro) -> {
        System.out.println(registro.getUser().getNif());
      });
    }
    System.out
        .println("------------------------------------------------------------------------------");
    assertEquals(2, rol.get().getRolUserVOregistries().size());

  }

  // 9-Utiliza un @Query para consultar el nombre de todos los usuarios con un rol
  // en concreto.
  @Test
  @Disabled
  void Testcase_8() {
    // muestro una lista de nombres con todos los usuarios con rol administrador
    Optional<List<String>> lista = usuarioService.findNamesWithRol("administrador");
    System.out
        .println("------LISTA DE NOMBRE DE USUARIOS CON ROL EN CONCRETO= ADMINISTRADOR----------");
    if (lista.isPresent()) {
      lista.get().forEach((nif) -> {
        System.out.println(nif);
      });
    }
    System.out
        .println("------------------------------------------------------------------------------");

  }

}
