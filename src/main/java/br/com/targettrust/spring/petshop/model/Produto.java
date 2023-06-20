package br.com.targettrust.spring.petshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@SequenceGenerator(name = "produto_seq", sequenceName = "produto_seq", allocationSize = 1)
@Getter
@Setter
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq")
    private Long id;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(length = 100)
    private String descricao;
}
