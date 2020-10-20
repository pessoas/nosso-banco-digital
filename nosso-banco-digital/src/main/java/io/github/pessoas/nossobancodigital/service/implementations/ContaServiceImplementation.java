package io.github.pessoas.nossobancodigital.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.pessoas.nossobancodigital.entity.Cliente;
import io.github.pessoas.nossobancodigital.entity.Conta;
import io.github.pessoas.nossobancodigital.repository.ContaRepository;
import io.github.pessoas.nossobancodigital.service.interfaces.ContaService;

@Service
public class ContaServiceImplementation implements ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Override
    public void novaConta(Cliente cliente) {

        Conta novaConta = new Conta(cliente);

        contaRepository.save(novaConta);

    }
    
}
