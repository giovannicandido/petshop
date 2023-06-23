package br.com.targettrust.spring.petshop.controller.mapper;

import br.com.targettrust.spring.petshop.controller.dto.AtendimentoDTO;
import br.com.targettrust.spring.petshop.model.Atendimento;
import br.com.targettrust.spring.petshop.repository.AtendimentoView;

public class AtendimentoDTOMapper {
    public static AtendimentoDTO toDto(AtendimentoView atendimento) {
        return AtendimentoDTO.builder()
                .id(atendimento.getId())
                .estado(atendimento.getEstado())
                .pagamentoEfetuado(atendimento.getPagamentoEfetuado())
                .valorConsulta(atendimento.getValorConsulta())
                .data(atendimento.getData())
                // get direto na entidade com relacionamento funciona
//                .nomeAtendente(atendimento.getAtendente().getNome())
//                .nomeUnidade(atendimento.getUnidade().getNome())
//                .nomeAnimal(atendimento.getAnimal().getNome())
                .nomeAtendente(atendimento.getNomeAtendente())
                .nomeUnidade(atendimento.getNomeUnidade())
                .nomeCliente(atendimento.getNomeCliente())
                .nomeAnimal(atendimento.getNomeAnimal())


                .build();
    }
}
