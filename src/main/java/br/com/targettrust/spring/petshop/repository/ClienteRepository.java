package br.com.targettrust.spring.petshop.repository;

import br.com.targettrust.spring.petshop.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
    List<Cliente> findAllByNomeIsLike(String nome);

    @Query("select c from Cliente c where c.cpf in :cpfs")
    List<Cliente> findAllByIdIn(List<String> cpfs);
}
