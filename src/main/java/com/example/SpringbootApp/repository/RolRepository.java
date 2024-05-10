package com.example.SpringbootApp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringbootApp.modelo.RolVO;

@Repository
public interface RolRepository extends CrudRepository<RolVO, Integer> {

  public Optional<RolVO> findByDenomination(String denomination);

}
