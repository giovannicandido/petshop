package br.com.targettrust.spring.petshop.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("rep1")
public class Reptil extends Animal {

    private Boolean peconhento;
}
