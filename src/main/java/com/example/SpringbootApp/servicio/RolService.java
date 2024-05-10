package com.example.SpringbootApp.servicio;

import java.util.Optional;

import com.example.SpringbootApp.modelo.RolVO;



public interface RolService {

  public <S extends RolVO> S save(S rol);

  public Optional<RolVO> findRolesByDenominacion(String denominacion);

  public Optional<RolVO> findRolById(int id);

}
