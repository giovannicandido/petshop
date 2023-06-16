package br.com.targettrust.spring.petshop.repository;

import br.com.targettrust.spring.petshop.model.Mamifero;
import br.com.targettrust.spring.petshop.model.Reptil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MamiferoRepository extends JpaRepository<Mamifero, Long> {
}
