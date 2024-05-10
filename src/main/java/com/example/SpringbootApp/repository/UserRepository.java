package com.example.SpringbootApp.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringbootApp.modelo.UserVO;

@Repository
public interface UserRepository extends CrudRepository<UserVO, Integer> {

  public Optional<UserVO> findByNif(String nif);


  @Query(value = "SELECT e FROM UserVO e JOIN FETCH e.rolUserVOregistries t where e.nif=?1")
  public Optional<UserVO> findByNif2(String nif);


  @Query(value = "SELECT  usu.nif\r\n" + "FROM tarea1.users usu\r\n"
      + "INNER JOIN tarea1.roles_users rol_usu\r\n" + "ON usu.idUser = rol_usu.idUser\r\n"
      + "INNER JOIN tarea1.roles rol\r\n" + "On rol_usu.idrol=rol.idrol\r\n"
      + "WHERE rol.denomination=?1", nativeQuery = true)
  public Optional<List<String>> findNamesWithRol(String rol);


  @Query(
      value = "SELECT e FROM UserVO e where e.name=?1 and e.lastName=?2")
  public Optional<List<UserVO>> findUsersByNameAndLastName(String name, String lastName);



}
