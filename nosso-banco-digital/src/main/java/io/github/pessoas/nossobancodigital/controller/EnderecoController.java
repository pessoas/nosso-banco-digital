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
import org.springframework.web.util.UriComponentsBuilder;

import io.github.pessoas.nossobancodigital.entity.Cliente;
import io.github.pessoas.nossobancodigital.entity.Endereco;
import io.github.pessoas.nossobancodigital.service.interfaces.ClienteService;
import io.github.pessoas.nossobancodigital.service.interfaces.EnderecoService;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {
    
    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/{cpf}")
    public ResponseEntity<?> saveEndereco(@PathVariable String cpf, @RequestBody @Valid Endereco novoEndereco) {
        
        Optional<Cliente> noBanco = clienteService.findByCpf(cpf);
        if(!noBanco.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Endereco salvo = enderecoService.save(novoEndereco);

        Cliente clienteSalvo = noBanco.get();

        clienteSalvo.setEndereco(salvo);
        clienteService.save(clienteSalvo);

        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .build()
            .toUri();

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
            .newInstance()
            .scheme(uri.getScheme())
            .schemeSpecificPart(uri.getSchemeSpecificPart())
            .userInfo(uri.getUserInfo())
            .host(uri.getHost())
            .port(uri.getPort())
            .fragment(uri.getFragment());
    
    
        URI location = uriComponentsBuilder
            .path("/api/documentos/{cpf}")
            .buildAndExpand(clienteSalvo.getCpf(),clienteSalvo.getEmail())
            .toUri();

        return ResponseEntity.created(location).build();
    }
}
