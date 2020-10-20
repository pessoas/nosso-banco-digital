package io.github.pessoas.nossobancodigital.service.interfaces;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import io.github.pessoas.nossobancodigital.entity.ArquivoCpf;

public interface ArquivoCpfService {
    public ArquivoCpf salvar(MultipartFile arquivo) throws IOException;
    public ArquivoCpf getArquivo(Long id);
}
