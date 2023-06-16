package br.com.targettrust.spring.petshop.controller;

import br.com.targettrust.spring.petshop.model.Cliente;
import br.com.targettrust.spring.petshop.service.ClienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/cliente")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteController {
    private final ClienteService service;

    @GetMapping
    public List<Cliente> listarCliente(
            @RequestParam(value = "nome", required = false) String nome
    ) {
        return service.listarPessoas(nome);
    }

    @PostMapping
    public Cliente criarCliente(@RequestBody @Valid Cliente cliente) {
        return service.salvar(cliente);
    }

    @DeleteMapping("/{id}")
    public void deletarCliente(@PathVariable("id") String cpf) {
        service.deletarCliente(cpf);
    }
}
