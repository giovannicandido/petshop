package br.com.targettrust.spring.petshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@SequenceGenerator(name = "animal_seq", sequenceName = "animal_seq", allocationSize = 1)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_animal")
public abstract class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "animal_seq")
    protected Long id;

    @Column(length = 150)
    protected String nome;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false, foreignKey =
    @ForeignKey(name = "fk_animal_cliente"))
    protected Cliente cliente;

}
