package br.com.targettrust.spring.petshop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@SequenceGenerator(name = "atendimento_seq", sequenceName = "atendimento_seq", allocationSize = 1)
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Atendimento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "atendimento_seq")
    private Long id;
    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime data;
    @Column(length = 100)
    private String nomeAtendente;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean pagamentoEfetuado;
    private BigDecimal valorConsulta;
    private EstadoAtendimento estado;

    @ManyToOne
    @JoinColumn(name = "unidade_id", foreignKey = @ForeignKey(name = "fk_atendimento_unidade"), nullable = false)
    private Unidade unidade;
    @ManyToOne
    @JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "fk_atendimento_cliente"), nullable = false)
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "animal_id", foreignKey = @ForeignKey(name = "fk_atendimento_animal"), nullable = false)
    private Animal animal;

    @ManyToMany
    @JoinTable(name = "atendimento_produto",
            joinColumns = @JoinColumn(name = "atendimento_id", foreignKey = @ForeignKey(name = "fk_atendimento_produto_atendimento")),
            inverseJoinColumns = @JoinColumn(name = "produto_id", foreignKey = @ForeignKey(name = "fk_atendimento_produto_produto"))
    )
    private List<Produto> produtos;

}
