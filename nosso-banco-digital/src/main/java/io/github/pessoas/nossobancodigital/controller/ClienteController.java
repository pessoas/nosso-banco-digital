package io.github.pessoas.nossobancodigital.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import io.github.pessoas.nossobancodigital.entity.Cliente;
import io.github.pessoas.nossobancodigital.service.interfaces.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<?> saveCliente(@Valid @RequestBody Cliente cliente){
        Cliente novo = clienteService.save(cliente);
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
            .path("/api/enderecos/{cpf}")
            .buildAndExpand(novo.getCpf())
            .toUri();

        return ResponseEntity.created(location).build();
    }

}
