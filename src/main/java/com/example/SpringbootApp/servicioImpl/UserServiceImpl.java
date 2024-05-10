package com.example.SpringbootApp.servicioImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringbootApp.modelo.UserVO;
import com.example.SpringbootApp.repository.UserRepository;
import com.example.SpringbootApp.servicio.UserService;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepo;

  @Override
  public <S extends UserVO> S save(S usuario) {

    return userRepo.save(usuario);
  }

  @Override
  public Optional<UserVO> findUserByNif(String nif) {

    return userRepo.findByNif(nif);
  }

  @Override
  public Optional<UserVO> findUserById2(String nif) {

    return userRepo.findByNif2(nif);
  }

  @Override
  public Optional<UserVO> findUserById(int id) {

    return userRepo.findById(id);
  }

  public Optional<List<String>> findNamesWithRol(String rol) {

    return userRepo.findNamesWithRol(rol);
  }

  @Override
  public Optional<List<UserVO>> findUsersByNameAndLastName(String name, String lastName) {

    return userRepo.findUsersByNameAndLastName(name, lastName);
  }



}
