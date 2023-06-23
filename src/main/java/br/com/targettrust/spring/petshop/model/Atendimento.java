package br.com.targettrust.spring.petshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean pagamentoEfetuado;
    private BigDecimal valorConsulta;
    // instrui o jpa a salvar string e não numero quando usar o enum
    @Enumerated(EnumType.STRING)
    private EstadoAtendimento estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "atendente_id", foreignKey = @ForeignKey(name = "fk_atendimento_atendente"), nullable = false)
    private Atendente atendente;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unidade_id", foreignKey = @ForeignKey(name = "fk_atendimento_unidade"), nullable = false)
    private Unidade unidade;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "fk_atendimento_cliente"), nullable = false)
    private Cliente cliente;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_id", foreignKey = @ForeignKey(name = "fk_atendimento_animal"), nullable = false)
    private Animal animal;

    @ManyToMany
    @JoinTable(name = "atendimento_produto",
            joinColumns = @JoinColumn(name = "atendimento_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_atendimento_produto_atendimento")),
            inverseJoinColumns = @JoinColumn(name = "produto_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_atendimento_produto_produto"))
    )
    private List<Produto> produtos;

}
