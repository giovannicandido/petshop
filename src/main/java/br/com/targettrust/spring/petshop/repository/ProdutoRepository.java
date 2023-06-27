package br.com.targettrust.spring.petshop.repository;

import br.com.targettrust.spring.petshop.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
