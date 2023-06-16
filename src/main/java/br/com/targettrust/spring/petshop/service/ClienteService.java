package br.com.targettrust.spring.petshop.service;

import br.com.targettrust.spring.petshop.model.Cliente;
import br.com.targettrust.spring.petshop.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClienteService {
    private final ClienteRepository repository;

    public List<Cliente> listarPessoas(String nome) {
        if(nome == null) {
            return repository.findAll();
        } else {
            return repository.findAllByNomeIsLike(nome + "%");
        }
    }

    public Cliente salvar(Cliente cliente) {
        if(repository.findById(cliente.getCpf())
                .isPresent()) {
            throw new RuntimeException("Cliente já existe no banco");
        };
        return repository.save(cliente);
    }

    public void deletarCliente(String cpf) {
        repository.deleteById(cpf);
    }
}
