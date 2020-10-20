package io.github.pessoas.nossobancodigital.service.implementations;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import io.github.pessoas.nossobancodigital.entity.ArquivoCpf;
import io.github.pessoas.nossobancodigital.repository.ArquivoCpfRepository;
import io.github.pessoas.nossobancodigital.service.interfaces.ArquivoCpfService;

@Service
public class ArquivoCpfServiceImplementation implements ArquivoCpfService {

    @Autowired
    private ArquivoCpfRepository arquivoCpfRepository;

    @Override
    public ArquivoCpf salvar(MultipartFile arquivo) throws IOException {
        String nomeArquivo = StringUtils.cleanPath(arquivo.getOriginalFilename());
        ArquivoCpf novoArquivo = new ArquivoCpf(nomeArquivo, arquivo.getContentType(), arquivo.getBytes());

        return arquivoCpfRepository.save(novoArquivo);
    }

    @Override
    public ArquivoCpf getArquivo(Long id) {
        
        return arquivoCpfRepository.findById(id).get();
    }
    
}
