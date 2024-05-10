package com.example.SpringbootApp.modelo;

import java.time.LocalDate;
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
@Table(name = "Users", uniqueConstraints = @UniqueConstraint(columnNames = "nif"))
public class UserVO {
  // ATRIBUTOS
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idUser;

  private String nif;

  private String name;

  private String lastName;

  private LocalDate dateBirth;

  private LocalDate dateSave;

  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
  private List<RolUserVO> rolUserVOregistries;


  // CONSTRUCTOR
  public UserVO(String nif, String name, String lastName, LocalDate dateBirth,
      List<RolUserVO> rolUserVOregistries) {
    super();
    this.nif = nif;
    this.name = name;
    this.lastName = lastName;
    this.dateBirth = dateBirth;
    this.dateSave = LocalDate.now();
    this.rolUserVOregistries = rolUserVOregistries;
  }



}
