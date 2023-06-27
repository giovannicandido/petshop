package br.com.targettrust.spring.petshop.controller.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FinalizarAtendimentoDTO {
    @Min(0)
    @Max(100000)
    private BigDecimal valorConsulta;

    @NotNull
    private Boolean pagamentoEfetuado;
}
