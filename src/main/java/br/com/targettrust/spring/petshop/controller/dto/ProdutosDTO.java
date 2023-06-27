package br.com.targettrust.spring.petshop.controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProdutosDTO {
    @NotNull
    @Min(0)
    private Long idProduto;
}
