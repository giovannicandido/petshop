package br.com.targettrust.spring.petshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

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
}
