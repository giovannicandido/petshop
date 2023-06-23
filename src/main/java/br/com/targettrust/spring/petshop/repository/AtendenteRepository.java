package br.com.targettrust.spring.petshop.repository;

import br.com.targettrust.spring.petshop.model.Atendente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtendenteRepository extends JpaRepository<Atendente, Long> {

    Atendente findByUserName(String username);
}
