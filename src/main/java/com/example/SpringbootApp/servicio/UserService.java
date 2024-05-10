package com.example.SpringbootApp.servicio;

import java.util.List;
import java.util.Optional;

import com.example.SpringbootApp.modelo.UserVO;



public interface UserService {

  public <S extends UserVO> S save(S usuario);

  public Optional<UserVO> findUserByNif(String nif);

  public Optional<UserVO> findUserById(int id);

  public Optional<UserVO> findUserById2(String nif);

  public Optional<List<String>> findNamesWithRol(String rol);

  public Optional<List<UserVO>> findUsersByNameAndLastName(String name, String lastName);


}
