package com.example.SpringbootApp.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles_users",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"idUser", "idRol"})})
public class RolUserVO {
  // ATRIBUTOS
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idRolUser;

  @ManyToOne
  @JoinColumn(name = "idUser")
  private UserVO user;

  @ManyToOne
  @JoinColumn(name = "idRol")
  private RolVO rol;

  // CONSTRUCTOR
  public RolUserVO(UserVO user, RolVO rol) {
    super();
    this.user = user;
    this.rol = rol;
  }

}
