package br.com.targettrust.spring.petshop.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AssociarUnidadeClienteDTO {
    private List<String> idCliente;
}
