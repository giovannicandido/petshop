package br.com.targettrust.spring.petshop.service;

import br.com.targettrust.spring.petshop.model.Atendente;
import br.com.targettrust.spring.petshop.repository.AtendenteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecurityUser {

    // simula um usuario logado
    private final String EMAIL_USUARIO_LOGADO = "teste@teste.com";
    private final AtendenteRepository atendenteRepository;

    /**
     * Simula a localização do usuario corrente logado no sistema
     * @return
     */
    public Atendente getCurrentAtendente() {
        // pegaria o email do usuario logado atualmente
        // depende da implementação do Spring Security

        Atendente atendente = atendenteRepository.findByUserName(EMAIL_USUARIO_LOGADO);

        if(atendente == null) {
            throw new RuntimeException("Não foi localizado o atendente no banco com esse " +
                    "nome de usuário");
        }

        return atendente;

    }
}
