package br.com.targettruest.spring.petshop.service;

import br.com.targettruest.spring.petshop.model.Cliente;
import br.com.targettruest.spring.petshop.repository.ClienteRepository;
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
        return repository.save(cliente);
    }
}
