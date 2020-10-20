package io.github.pessoas.nossobancodigital.service.implementations;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.pessoas.nossobancodigital.entity.Cliente;
import io.github.pessoas.nossobancodigital.entity.Conta;
import io.github.pessoas.nossobancodigital.entity.projections.ClienteDadosProjection;
import io.github.pessoas.nossobancodigital.repository.ClienteRepository;
import io.github.pessoas.nossobancodigital.service.interfaces.ClienteService;

@Service
public class ClienteServiceImplementation implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    private final RestTemplate restTemplate;

    public ClienteServiceImplementation(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Optional<Cliente> findByCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    @Override
    public Optional<ClienteDadosProjection> findClienteDadosByEmail(String email) {
        
        return clienteRepository.findByEmail(email);
    }

    @Override
    @Async
    public CompletableFuture<Conta> aceite(String cpf) {
        String url = String.format("http://localhost:8080/api/contas/%s", cpf);

        Cliente cliente = clienteRepository.findByCpf(cpf).get();
        cliente.setAceito(true);
        clienteRepository.save(cliente);

        Conta conta = restTemplate.postForObject(url, cliente, Conta.class);

        return CompletableFuture.completedFuture(conta);
    }
    
}
