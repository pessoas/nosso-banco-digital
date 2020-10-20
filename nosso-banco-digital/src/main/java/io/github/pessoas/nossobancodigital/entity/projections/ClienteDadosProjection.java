package io.github.pessoas.nossobancodigital.entity.projections;

import java.time.LocalDate;

import io.github.pessoas.nossobancodigital.entity.Endereco;

public interface ClienteDadosProjection {
    String getNome();
    String getSobrenome();
    String getCpf();
    String getEmail();
    LocalDate getDataNascimento();
    Endereco getEndereco();
    String getLinkArquivoCpf();
}
