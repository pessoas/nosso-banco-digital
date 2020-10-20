package io.github.pessoas.nossobancodigital.service.interfaces;

import io.github.pessoas.nossobancodigital.entity.Cliente;
import io.github.pessoas.nossobancodigital.entity.Conta;

public interface ContaService {
    public Conta novaConta(Cliente cliente);
}
