package io.github.pessoas.nossobancodigital.service.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.pessoas.nossobancodigital.entity.Cliente;
import io.github.pessoas.nossobancodigital.entity.projections.ClienteDadosProjection;
import io.github.pessoas.nossobancodigital.repository.ClienteRepository;
import io.github.pessoas.nossobancodigital.service.interfaces.ClienteService;

@Service
public class ClienteServiceImplementation implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

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
    
}
