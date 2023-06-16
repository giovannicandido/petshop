package br.com.targettrust.spring.petshop.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("m1")
public class Mamifero extends Animal {
    private Integer quantidadePatas;
}
