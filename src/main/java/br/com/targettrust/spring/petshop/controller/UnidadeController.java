package br.com.targettrust.spring.petshop.controller;

import br.com.targettrust.spring.petshop.controller.dto.AssociarUnidadeClienteDTO;
import br.com.targettrust.spring.petshop.service.UnidadeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/unidade")
@AllArgsConstructor
public class UnidadeController {
    private final UnidadeService unidadeService;
    @PostMapping(path = "/{idUnidade}/associar")
    public void associar(
            @PathVariable("idUnidade") Long idUnidade,
            @RequestBody AssociarUnidadeClienteDTO request) {

        unidadeService.associarUnidadeACliente(request, idUnidade);
    }
}
