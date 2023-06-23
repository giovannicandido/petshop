package br.com.targettrust.spring.petshop.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class IniciarDTO {
    @NotBlank
    private String idCliente;
    @NotNull
    @Min(0)
    private Long idUnidade;
    @NotNull
    @Min(0)
    private Long idAnimal;
}
