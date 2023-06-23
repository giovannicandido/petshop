package br.com.targettrust.spring.petshop.repository;

import br.com.targettrust.spring.petshop.model.Atendimento;
import br.com.targettrust.spring.petshop.model.EstadoAtendimento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface AtendimentoView {
    String getNomeAtendente();
    String getNomeUnidade();
    String getNomeAnimal();

    String getNomeCliente();

    Long getId();
    LocalDateTime getData();
    BigDecimal getValorConsulta();
    EstadoAtendimento getEstado();
    Boolean getPagamentoEfetuado();
}
