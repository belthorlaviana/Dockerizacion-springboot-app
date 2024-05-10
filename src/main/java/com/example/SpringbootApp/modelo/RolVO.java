package com.example.SpringbootApp.modelo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Roles", uniqueConstraints = @UniqueConstraint(columnNames = "denomination"))
public class RolVO {
  // ATRIBUTOS
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idRol;

  private String denomination;

  @OneToMany(mappedBy = "rol", fetch = FetchType.EAGER)
  private List<RolUserVO> rolUserVOregistries;

  // CONSTRUCTOR
  public RolVO(String denomination, List<RolUserVO> rolUserVOregistries) {
    super();
    this.denomination = denomination;
    this.rolUserVOregistries = rolUserVOregistries;
  }
}
