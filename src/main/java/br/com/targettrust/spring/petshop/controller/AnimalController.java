package br.com.targettrust.spring.petshop.controller;

import br.com.targettrust.spring.petshop.controller.dto.AnimalDTO;
import br.com.targettrust.spring.petshop.controller.mapper.AnimalDTOMapper;
import br.com.targettrust.spring.petshop.service.AnimalService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/animal")
@AllArgsConstructor
public class AnimalController {
    private final AnimalService service;
    @PostMapping()
    public AnimalDTO salvarAnimal(@RequestBody @Valid AnimalDTO animalDTO) {
        return service.criarNovo(animalDTO);
    }

    @GetMapping()
    public List<AnimalDTO> listarTodos() {
        return service.findAll()
                .stream()
                .map((animal) -> AnimalDTOMapper.toDTO(animal))
                .collect(Collectors.toList());
    }
}
