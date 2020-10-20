package io.github.pessoas.nossobancodigital.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.pessoas.nossobancodigital.entity.Cliente;
import io.github.pessoas.nossobancodigital.service.interfaces.ClienteService;
import io.github.pessoas.nossobancodigital.service.interfaces.ContaService;

@RestController
@RequestMapping("/api/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @Autowired
    private ClienteService clienteService;
    
    @PostMapping("/{cpf}")
    public ResponseEntity<?> salvar(@PathVariable String cpf){
        
        Optional<Cliente> noBanco = this.clienteService.findByCpf(cpf);
        if(!noBanco.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Cliente clienteSalvo = noBanco.get();

        if(clienteSalvo.getEndereco() == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if(clienteSalvo.getLinkArquivoCpf() == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        contaService.novaConta(noBanco.get());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
}
