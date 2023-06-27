package br.com.targettrust.spring.petshop.controller;

import br.com.targettrust.spring.petshop.controller.dto.AtendimentoDTO;
import br.com.targettrust.spring.petshop.controller.dto.FinalizarAtendimentoDTO;
import br.com.targettrust.spring.petshop.controller.dto.IniciarDTO;
import br.com.targettrust.spring.petshop.controller.dto.ProdutosDTO;
import br.com.targettrust.spring.petshop.controller.mapper.AtendimentoDTOMapper;
import br.com.targettrust.spring.petshop.model.EstadoAtendimento;
import br.com.targettrust.spring.petshop.repository.AtendimentoView;
import br.com.targettrust.spring.petshop.service.AtendimentoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/atendimento")
@AllArgsConstructor
public class AtendimentoController {
    private final AtendimentoService service;

    @GetMapping
    public List<AtendimentoDTO> listarAtendimento(
            @RequestParam(name = "estado", required = false) EstadoAtendimento estado
    ) {
      return service.listar(estado)
              .stream()
              // igual a proxima linha
//              .map((atendimentoView) -> AtendimentoDTOMapper.toDto(atendimentoView))
              .map(AtendimentoDTOMapper::toDto)
              .collect(Collectors.toList());
    }

    @PostMapping(value = "/iniciar")
    public void iniciarAtendimento(@RequestBody @Valid IniciarDTO iniciarDTO) {
        service.iniciarAtendimento(iniciarDTO);
    }

    @PostMapping("/{id}/produtos")
    public void adicionarProdutos(@PathVariable(name = "id") Long id,
                                  @RequestBody List<ProdutosDTO> request) {
        service.adicionarProdutos(id,request);
    }

    @DeleteMapping("/{id}/produtos")
    public void removerProdutos(@PathVariable(name = "id") Long id,
                                  @RequestBody List<ProdutosDTO> request) {
        service.removerProdutos(id,request);
    }

    @PostMapping("/{id}/finalizar")
    public void finalizarAtendimento(@PathVariable(name = "id") Long id,
                                     @RequestBody @Valid FinalizarAtendimentoDTO request) {
        service.finalizarAtendimento(id, request);
    }
}
