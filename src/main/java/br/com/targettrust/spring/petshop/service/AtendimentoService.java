package br.com.targettrust.spring.petshop.service;

import br.com.targettrust.spring.petshop.controller.dto.FinalizarAtendimentoDTO;
import br.com.targettrust.spring.petshop.controller.dto.IniciarDTO;
import br.com.targettrust.spring.petshop.controller.dto.ProdutosDTO;
import br.com.targettrust.spring.petshop.model.*;
import br.com.targettrust.spring.petshop.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AtendimentoService {
    private final AtendimentoRepository repository;
    private final ClienteRepository clienteRepository;
    private final AnimalRepository animalRepository;
    private final UnidadeRepository unidadeRepository;
    private final ProdutoRepository produtoRepository;
    private final SecurityUser securityUser;

    public List<AtendimentoView> listar(EstadoAtendimento estado) {
        if (estado == null) {
            return repository.findAllAtendimentoForView();
        }

        return repository.findAllAtendimentoForView(estado);
    }

    public void iniciarAtendimento(IniciarDTO iniciarDTO) {
        Atendimento atendimento = new Atendimento();

        Cliente cliente = clienteRepository.findById(iniciarDTO.getIdCliente())
                .orElseThrow(() -> new RuntimeException("cliente não encontrado no banco"));
        Unidade unidade = unidadeRepository.findById(iniciarDTO.getIdUnidade())
                .orElseThrow(() -> new RuntimeException("unidade não encontrada no banco"));
        Animal animal = animalRepository.findById(iniciarDTO.getIdAnimal())
                .orElseThrow(() -> new RuntimeException("animal não encontrado no banco"));

        // valida se cliente é dono do animal em questão
        if (clientNaoForDonoDoPet(iniciarDTO.getIdAnimal(), cliente)
        ) {
            throw new RuntimeException("Cliente de id " + iniciarDTO.getIdCliente() + " não é dono do animal " + iniciarDTO.getIdAnimal());
        }

        Atendente atendente = securityUser.getCurrentAtendente();

        atendimento.setAtendente(atendente);
        atendimento.setCliente(cliente);
        atendimento.setUnidade(unidade);
        atendimento.setAnimal(animal);

        atendimento.setEstado(EstadoAtendimento.EM_ABERTO);
        atendimento.setPagamentoEfetuado(false);
        atendimento.setValorConsulta(null);

        repository.save(atendimento);

    }

    private static boolean clientNaoForDonoDoPet(Long idAnimal, Cliente cliente) {
        return cliente.getAnimais().
                stream()
                .filter((ani) -> Objects.equals(ani.getId(), idAnimal))
                .findFirst().isEmpty();
    }

    public void adicionarProdutos(Long idAtendimento, List<ProdutosDTO> request) {
        Atendimento atendimento = validaAtendimento(idAtendimento);

        List<Produto> produtosExistentes = atendimento.getProdutos();

        List<Produto> produtosAdicionar = new ArrayList<>();

        // Maneira procedural de fazer
        /*
        for(ProdutosDTO produtoDTO : request) {
            final var produto = produtoRepository.findById(produtoDTO.getIdProduto())
                    .orElseThrow(() -> new RuntimeException("id produto "
                            + produtoDTO.getIdProduto() + " não existe na base de dados"));
            produtosAdicionar.add(produto);
        }
         * fim maneira procedural
         */

        produtosExistentes.addAll(produtosAdicionar);

        // Maneira Funcional de Fazer
        final var produtosExistentes2 = request
                .stream()
                .map((produtoDTO) -> produtoRepository.findById(produtoDTO.getIdProduto()))
                .peek(produto -> {
                    if(produto.isEmpty()) {
                        throw new RuntimeException("produto não existente na base de dados");
                    }
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        produtosExistentes.addAll(produtosExistentes2);

        // Opcional já que a lista é alterada dentro de atendimento pelo método acima
        atendimento.setProdutos(produtosExistentes);

        repository.save(atendimento);


    }

    public void removerProdutos(Long idAtendimento, List<ProdutosDTO> request) {
        Atendimento atendimento = validaAtendimento(idAtendimento);
        final var idsParaRemover = request.stream().map(ProdutosDTO::getIdProduto).toList();

        List<Produto> novaLista = atendimento.getProdutos()
                .stream()
                .filter(produto -> !idsParaRemover.contains(produto.getId()))
                .collect(Collectors.toList());

        atendimento.setProdutos(novaLista);
        repository.save(atendimento);
    }

    public void finalizarAtendimento(Long idAtendimento, FinalizarAtendimentoDTO request) {
        Atendimento atendimento = validaAtendimento(idAtendimento);

        // todo pensar em regras que facam sentido na finalizacao

        atendimento.setDataFinalizacao(LocalDateTime.now());
        atendimento.setValorConsulta(request.getValorConsulta());
        atendimento.setPagamentoEfetuado(request.getPagamentoEfetuado());
        atendimento.setEstado(EstadoAtendimento.FINALIZADO);
        repository.save(atendimento);
    }

    private Atendimento validaAtendimento(Long idAtendimento) {
        Atendimento atendimento = repository.findById(idAtendimento)
                .orElseThrow(() -> new RuntimeException("id atendimento não localizado"));

        if(atendimento.getEstado() != EstadoAtendimento.EM_ABERTO) {
            throw new RuntimeException("atendimento selecionado não está em aberto");
        }
        return atendimento;
    }
}
