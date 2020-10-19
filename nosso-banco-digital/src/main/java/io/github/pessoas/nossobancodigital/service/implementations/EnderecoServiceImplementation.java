package io.github.pessoas.nossobancodigital.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.pessoas.nossobancodigital.entity.Endereco;
import io.github.pessoas.nossobancodigital.repository.EnderecoRepository;
import io.github.pessoas.nossobancodigital.service.interfaces.EnderecoService;

@Service
public class EnderecoServiceImplementation implements EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Override
    public Endereco save(Endereco novoEndereco) {
        return enderecoRepository.save(novoEndereco); 
    }
    
}
