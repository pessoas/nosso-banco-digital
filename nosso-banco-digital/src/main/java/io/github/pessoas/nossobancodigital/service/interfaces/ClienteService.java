package io.github.pessoas.nossobancodigital.service.interfaces;

import java.util.Optional;

import io.github.pessoas.nossobancodigital.entity.Cliente;
import io.github.pessoas.nossobancodigital.entity.projections.ClienteDadosProjection;

public interface ClienteService {
    public Cliente save(Cliente cliente);
    public Optional<Cliente> findByCpf(String cpf);
    public Optional<ClienteDadosProjection> findClienteDadosByEmail(String email);
}
