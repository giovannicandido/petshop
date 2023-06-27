package br.com.targettrust.spring.petshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Cliente {
    @Id
    @Column(length = 11)
    @NotBlank(message = "Cpf não pode ser vazio")
    private String cpf;

    @Column(length = 150, nullable = false)
    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;

    @ManyToMany(mappedBy = "clientes", cascade = CascadeType.REMOVE)
    @JsonIgnore
    List<Unidade> unidades;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    List<Animal> animais;
}
