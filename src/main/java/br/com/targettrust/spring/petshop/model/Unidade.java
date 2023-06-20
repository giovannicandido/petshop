package br.com.targettrust.spring.petshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@SequenceGenerator(name = "unidade_seq", sequenceName = "unidade_seq", allocationSize = 1)
public class Unidade {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unidade_seq")
    protected Long id;

    @Column(length = 50, nullable = false)
    private String nome;
    private String endereco;

    @ManyToMany
    @JoinTable(name = "unidade_cliente",
            joinColumns = {
            @JoinColumn(name = "unidade_id_custom",
            referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_unidade_cliente_unidade")
            )},
            inverseJoinColumns = @JoinColumn(name = "cliente_cpf", referencedColumnName = "cpf",
                    foreignKey = @ForeignKey(name = "fk_unidade_cliente_cliente"))
    )
    @JsonIgnore
    private List<Cliente> clientes;
}
