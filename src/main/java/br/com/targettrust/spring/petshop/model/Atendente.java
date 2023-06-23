package br.com.targettrust.spring.petshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@SequenceGenerator(name = "atendente_seq", sequenceName = "atendente_seq", allocationSize = 1)
@Getter
@Setter
public class Atendente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "atendente_seq")
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String userName;

    @ManyToOne(fetch = FetchType.EAGER)
    private Unidade unidade;
}
