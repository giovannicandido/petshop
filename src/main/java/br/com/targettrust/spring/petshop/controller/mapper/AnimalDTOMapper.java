package br.com.targettrust.spring.petshop.controller.mapper;

import br.com.targettrust.spring.petshop.controller.dto.AnimalDTO;
import br.com.targettrust.spring.petshop.controller.dto.TipoAnimal;
import br.com.targettrust.spring.petshop.model.Animal;
import br.com.targettrust.spring.petshop.model.Mamifero;
import br.com.targettrust.spring.petshop.model.Reptil;

public class AnimalDTOMapper {

    public static Animal toModel(AnimalDTO animalDTO) {
        Animal animal;

        if(animalDTO.getTipo() == TipoAnimal.REPTIL) {
            var reptil = new Reptil();
            reptil.setNome(animalDTO.getNome());
            reptil.setPeconhento(animalDTO.getPeconhento());
            animal = reptil;
        } else {
            var mamifero = new Mamifero();
            mamifero.setNome(animalDTO.getNome());
            mamifero.setQuantidadePatas(animalDTO.getQuantidadePatas());
            animal = mamifero;
        }
        return animal;
    }
    public static AnimalDTO toDTO(Animal animal) {
        AnimalDTO dto = new AnimalDTO();
        dto.setId(animal.getId());
        dto.setNome(animal.getNome());
        if(animal instanceof Reptil) {
            dto.setTipo(TipoAnimal.REPTIL);
            dto.setPeconhento(((Reptil) animal).getPeconhento());
            return dto;
        }

        if (animal instanceof Mamifero) {
            dto.setTipo(TipoAnimal.MAMIFERO);
            dto.setQuantidadePatas(((Mamifero) animal).getQuantidadePatas());
            return dto;
        }

        return null;
    }
}
