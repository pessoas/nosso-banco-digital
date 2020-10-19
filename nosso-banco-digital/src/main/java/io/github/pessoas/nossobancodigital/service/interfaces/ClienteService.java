package io.github.pessoas.nossobancodigital.service.interfaces;

import java.util.Optional;

import io.github.pessoas.nossobancodigital.entity.Cliente;

public interface ClienteService {
    public Cliente save(Cliente cliente);
    public Optional<Cliente> findByCpf(String cpf);
}
