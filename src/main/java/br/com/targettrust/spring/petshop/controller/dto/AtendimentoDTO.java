package br.com.targettrust.spring.petshop.controller.dto;

import br.com.targettrust.spring.petshop.model.EstadoAtendimento;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class AtendimentoDTO {
    private Long id;
    private LocalDateTime data;
    private BigDecimal valorConsulta;
    private EstadoAtendimento estado;
    private Boolean pagamentoEfetuado;

    private String nomeAtendente;
    private String nomeUnidade;
    private String nomeCliente;
    private String nomeAnimal;



}
