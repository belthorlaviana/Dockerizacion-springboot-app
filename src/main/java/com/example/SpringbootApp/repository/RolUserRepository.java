package com.example.SpringbootApp.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringbootApp.modelo.RolUserVO;
import com.example.SpringbootApp.modelo.UserVO;

@Repository
public interface RolUserRepository extends CrudRepository<RolUserVO, Integer> {

  public Optional<List<RolUserVO>> findByUser(UserVO user);
}
