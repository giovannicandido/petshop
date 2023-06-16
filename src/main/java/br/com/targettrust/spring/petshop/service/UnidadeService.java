package br.com.targettrust.spring.petshop.service;

import br.com.targettrust.spring.petshop.controller.dto.AssociarUnidadeClienteDTO;
import br.com.targettrust.spring.petshop.model.Cliente;
import br.com.targettrust.spring.petshop.repository.ClienteRepository;
import br.com.targettrust.spring.petshop.repository.UnidadeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UnidadeService {
    private final ClienteRepository clienteRepository;
    private final UnidadeRepository unidadeRepository;
    public void associarUnidadeACliente(AssociarUnidadeClienteDTO clientes, Long idUnidade) {
        // pesquisar a unidade no banco
        var unidadeOptional = unidadeRepository.findById(idUnidade);
        if(!unidadeOptional.isPresent()) {
            throw new RuntimeException("Unidade não existe com esse id " + idUnidade);
        }

        var unidade = unidadeOptional.get();

        // pesquisar os clientes no banco

        List<Cliente> clientesNoBanco = new ArrayList<>();

//        clientes.getIdCliente().forEach((idCliente) -> {
//            var clienteNoBanco = clienteRepository.findById(idCliente)
//                    .orElseThrow(() -> new RuntimeException("Id cliente não localizado: " + idCliente));
//            clientesNoBanco.add(clienteNoBanco);
//        });

        clientesNoBanco = clienteRepository.findAllByIdIn(clientes.getIdCliente());

        //
        List<Cliente> clientesExistentes = unidade.getClientes();
        clientesExistentes.addAll(clientesNoBanco);
        unidade.setClientes(clientesExistentes);


        // salvar a unidade com os clientes
        unidadeRepository.save(unidade);
    }
}
