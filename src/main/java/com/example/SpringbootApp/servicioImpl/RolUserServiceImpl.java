package com.example.SpringbootApp.servicioImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringbootApp.modelo.RolUserVO;
import com.example.SpringbootApp.modelo.UserVO;
import com.example.SpringbootApp.repository.RolUserRepository;
import com.example.SpringbootApp.servicio.RolUserService;

@Service
public class RolUserServiceImpl implements RolUserService {

  @Autowired
  private RolUserRepository rolUsuarioRepo;

  @Override
  public <S extends RolUserVO> S save(S RolUsuario) {

    return rolUsuarioRepo.save(RolUsuario);
  }

  @Override
  public Iterable<RolUserVO> findAll() {
    // TODO Auto-generated method stub
    return rolUsuarioRepo.findAll();
  }

  @Override
  public Optional<List<RolUserVO>> findRolUsuarioVObyUsuario(UserVO user) {
    // TODO Auto-generated method stub
    return rolUsuarioRepo.findByUser(user);
  }

}
