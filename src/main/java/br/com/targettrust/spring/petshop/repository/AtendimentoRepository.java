package br.com.targettrust.spring.petshop.repository;

import br.com.targettrust.spring.petshop.model.Atendimento;
import br.com.targettrust.spring.petshop.model.EstadoAtendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

    List<Atendimento> findAllByEstado(EstadoAtendimento estadoAtendimento);

    @Query("select a.id as id, a.pagamentoEfetuado as pagamentoEfetuado, a.data as data," +
            " a.valorConsulta as valorConsulta, a.estado as estado, at.nome as nomeAtendente, cli.nome as nomeCliente,  " +
            "un.nome as nomeUnidade, ani.nome as nomeAnimal " +
            "from Atendimento a join a.atendente at join a.cliente cli " +
            "join a.unidade un join a.animal ani where a.estado = :estado")
    List<AtendimentoView> findAllAtendimentoForView(EstadoAtendimento estado);

    @Query("select a.id as id, a.pagamentoEfetuado as pagamentoEfetuado, a.data as data," +
            " a.valorConsulta as valorConsulta, a.estado as estado, at.nome as nomeAtendente, cli.nome as nomeCliente,  " +
            "un.nome as nomeUnidade, ani.nome as nomeAnimal " +
            "from Atendimento a join a.atendente at join a.cliente cli " +
            "join a.unidade un join a.animal ani")
    List<AtendimentoView> findAllAtendimentoForView();
}
