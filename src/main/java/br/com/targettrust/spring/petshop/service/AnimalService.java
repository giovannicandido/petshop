package br.com.targettrust.spring.petshop.service;

import br.com.targettrust.spring.petshop.controller.dto.AnimalDTO;
import br.com.targettrust.spring.petshop.controller.dto.TipoAnimal;
import br.com.targettrust.spring.petshop.controller.mapper.AnimalDTOMapper;
import br.com.targettrust.spring.petshop.model.Animal;
import br.com.targettrust.spring.petshop.model.Mamifero;
import br.com.targettrust.spring.petshop.model.Reptil;
import br.com.targettrust.spring.petshop.repository.AnimalRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AnimalService {
    private final AnimalRepository repository;

    @PostConstruct
    public void init() {
        var animal1 = new Mamifero();
        animal1.setNome("Cachorro");
        var animal2 = new Reptil();
        animal2.setNome("Cobra");
        animal2.setPeconhento(true);

        repository.save(animal1);
        repository.save(animal2);
    }

    public AnimalDTO criarNovo(AnimalDTO animalDTO) {
        // criar o animal correto
        Animal animal = AnimalDTOMapper.toModel(animalDTO);
        var saved = repository.save(animal);

        animalDTO.setId(saved.getId());
        return animalDTO;

        // salvar usando o repository
    }
}
