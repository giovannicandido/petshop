package br.com.targettruest.spring.petshop.repository;

import br.com.targettruest.spring.petshop.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
    List<Cliente> findAllByNomeIsLike(String nome);
}
