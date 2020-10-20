package io.github.pessoas.nossobancodigital.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import io.github.pessoas.nossobancodigital.entity.ArquivoCpf;
import io.github.pessoas.nossobancodigital.entity.Cliente;
import io.github.pessoas.nossobancodigital.service.interfaces.ArquivoCpfService;
import io.github.pessoas.nossobancodigital.service.interfaces.ClienteService;

@RestController
@RequestMapping("/api/documentos")
public class ArquivoCpfController {

    @Autowired
    private ArquivoCpfService arquivoCpfService;

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/{cpf}")
    public ResponseEntity<?> uploadArquivo(@PathVariable("cpf") String cpf,
            @RequestParam("arquivo") MultipartFile arquivo) {

        Optional<Cliente> noBanco = clienteService.findByCpf(cpf);

        if (!noBanco.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Cliente clienteSalvo = noBanco.get();

        if (clienteSalvo.getEndereco() == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        try {

            ArquivoCpf arquivoCpf = arquivoCpfService.salvar(arquivo);
            
            clienteSalvo.setArquivoCpf(arquivoCpf);
            

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

            String link = uriComponentsBuilder.path("/api/documentos/arquivos/{id}").buildAndExpand(arquivoCpf.getId()).toString();

            clienteSalvo.setLinkArquivoCpf(link);
            clienteService.save(clienteSalvo);
            

            URI location = uriComponentsBuilder
                .path("/api/clientes/checagem/{email}")
                .buildAndExpand(clienteSalvo.getEmail())
                .toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>("Não foi possivel fazer o upload do arquivo", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/arquivos/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {

        ArquivoCpf arquivo = arquivoCpfService.getArquivo(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + arquivo.getNome() + "\"")
                .body(arquivo.getDados());
    }
}
