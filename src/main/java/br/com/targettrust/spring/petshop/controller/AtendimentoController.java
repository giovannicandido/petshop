package br.com.targettrust.spring.petshop.controller;

import br.com.targettrust.spring.petshop.controller.dto.AtendimentoDTO;
import br.com.targettrust.spring.petshop.controller.dto.IniciarDTO;
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
}
