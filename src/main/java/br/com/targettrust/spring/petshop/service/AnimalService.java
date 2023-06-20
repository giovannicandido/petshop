package br.com.targettrust.spring.petshop.service;

import br.com.targettrust.spring.petshop.controller.dto.AnimalDTO;
import br.com.targettrust.spring.petshop.controller.dto.TipoAnimal;
import br.com.targettrust.spring.petshop.controller.mapper.AnimalDTOMapper;
import br.com.targettrust.spring.petshop.model.Animal;
import br.com.targettrust.spring.petshop.model.Cliente;
import br.com.targettrust.spring.petshop.model.Mamifero;
import br.com.targettrust.spring.petshop.model.Reptil;
import br.com.targettrust.spring.petshop.repository.AnimalRepository;
import br.com.targettrust.spring.petshop.repository.ClienteRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AnimalService {
    private final AnimalRepository repository;
    private final ClienteRepository clienteRepository;

//    @PostConstruct
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
        Cliente cliente = clienteRepository.findById(animalDTO.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente de id " + animalDTO.getIdCliente() + " não existe no banco"));
        animal.setCliente(cliente);
        var saved = repository.save(animal);

        animalDTO.setId(saved.getId());
        return animalDTO;
    }

    public List<Animal> findAll() {
        return repository.findAll();
    }
}
