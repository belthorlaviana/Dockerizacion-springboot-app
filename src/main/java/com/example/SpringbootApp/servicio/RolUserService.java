package com.example.SpringbootApp.servicio;

import java.util.List;
import java.util.Optional;

import com.example.SpringbootApp.modelo.RolUserVO;
import com.example.SpringbootApp.modelo.UserVO;



public interface RolUserService {

  public <S extends RolUserVO> S save(S RolUsuario);

  public Iterable<RolUserVO> findAll();

  public Optional<List<RolUserVO>> findRolUsuarioVObyUsuario(UserVO usuario);

}
