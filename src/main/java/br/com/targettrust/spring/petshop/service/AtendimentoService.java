package br.com.targettrust.spring.petshop.service;

import br.com.targettrust.spring.petshop.controller.dto.AtendimentoDTO;
import br.com.targettrust.spring.petshop.controller.dto.IniciarDTO;
import br.com.targettrust.spring.petshop.model.*;
import br.com.targettrust.spring.petshop.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AtendimentoService {
    private final AtendimentoRepository repository;
    private final ClienteRepository clienteRepository;
    private final AnimalRepository animalRepository;
    private final UnidadeRepository unidadeRepository;
    private final SecurityUser securityUser;
    public List<AtendimentoView> listar(EstadoAtendimento estado) {
        if (estado == null) {
            return repository.findAllAtendimentoForView();
        }

        return repository.findAllAtendimentoForView(estado);
    }

    public void iniciarAtendimento(IniciarDTO iniciarDTO) {
        Atendimento atendimento = new Atendimento();
        Cliente cliente = clienteRepository.findById(iniciarDTO.getIdCliente())
                .orElseThrow(() -> new RuntimeException("cliente não encontrado no banco"));
        Unidade unidade = unidadeRepository.findById(iniciarDTO.getIdUnidade())
                .orElseThrow(() -> new RuntimeException("unidade não encontrada no banco"));
        Animal animal = animalRepository.findById(iniciarDTO.getIdAnimal())
                .orElseThrow(() -> new RuntimeException("animal não encontrado no banco"));
        Atendente atendente = securityUser.getCurrentAtendente();

        atendimento.setAtendente(atendente);
        atendimento.setCliente(cliente);
        atendimento.setUnidade(unidade);
        atendimento.setAnimal(animal);

        atendimento.setEstado(EstadoAtendimento.EM_ABERTO);
        atendimento.setPagamentoEfetuado(false);
        atendimento.setValorConsulta(null);

        repository.save(atendimento);

    }
}
