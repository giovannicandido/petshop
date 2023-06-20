package br.com.targettrust.spring.petshop.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimalDTO {
    private Long id;
    private String nome;
    private Integer quantidadePatas;
    private Boolean peconhento;
    private String idCliente;

    @NotNull
    private TipoAnimal tipo;
}
