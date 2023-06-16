package br.com.targettrust.spring.petshop.repository;

import br.com.targettrust.spring.petshop.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
