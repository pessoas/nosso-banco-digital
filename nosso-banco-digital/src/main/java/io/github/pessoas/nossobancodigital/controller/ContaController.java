package io.github.pessoas.nossobancodigital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.pessoas.nossobancodigital.entity.Cliente;
import io.github.pessoas.nossobancodigital.service.interfaces.ContaService;

@RestController
@RequestMapping("/api/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping("/{cpf}")
    public ResponseEntity<?> salvar(@PathVariable String cpf, @RequestBody Cliente cliente){
        
        if(cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if(cliente.getEndereco() == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if(cliente.getLinkArquivoCpf() == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        contaService.novaConta(cliente);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
