package io.github.pessoas.nossobancodigital.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.pessoas.nossobancodigital.entity.Cliente;
import io.github.pessoas.nossobancodigital.entity.Endereco;
import io.github.pessoas.nossobancodigital.service.interfaces.ClienteService;
import io.github.pessoas.nossobancodigital.service.interfaces.EnderecoService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Cliente cliente){
        Cliente novo = clienteService.save(cliente);
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{cpf}/endereco")
            .buildAndExpand(novo.getCpf())
            .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/{cpf}/endereco")
    public ResponseEntity<?> saveAddress(@PathVariable String cpf, @RequestBody Endereco novoEndereco) {
        
        Optional<Cliente> noBanco = clienteService.findByCpf(cpf);
        if(!noBanco.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Endereco salvo = enderecoService.save(novoEndereco);

        Cliente clienteSalvo = noBanco.get();

        clienteSalvo.setEndereco(salvo);
        clienteService.save(clienteSalvo);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{cpf}/foto")
            .buildAndExpand(clienteSalvo.getCpf())
            .toUri();

        return ResponseEntity.created(location).build();
    }
}
