package br.com.targettrust.spring.petshop.controller;

import br.com.targettrust.spring.petshop.controller.dto.AnimalDTO;
import br.com.targettrust.spring.petshop.service.AnimalService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/animal")
@AllArgsConstructor
public class AnimalController {
    private final AnimalService service;
    @PostMapping()
    public AnimalDTO salvarAnimal(@RequestBody @Valid AnimalDTO animalDTO) {
        return service.criarNovo(animalDTO);
    }
}
