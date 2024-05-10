package com.example.SpringbootApp.servicioImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringbootApp.modelo.RolVO;
import com.example.SpringbootApp.repository.RolRepository;
import com.example.SpringbootApp.servicio.RolService;



@Service
public class RolServiceImpl implements RolService {

  @Autowired
  private RolRepository rolRepo;

  @Override
  public <S extends RolVO> S save(S rol) {

    return rolRepo.save(rol);
  }

  @Override
  public Optional<RolVO> findRolesByDenominacion(String denomination) {

    return rolRepo.findByDenomination(denomination);
  }

  @Override
  public Optional<RolVO> findRolById(int id) {

    return rolRepo.findById(id);
  }

}
