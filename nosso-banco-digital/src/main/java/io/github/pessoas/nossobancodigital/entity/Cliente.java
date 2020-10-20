package io.github.pessoas.nossobancodigital.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.caelum.stella.bean.validation.CPF;
import io.github.pessoas.nossobancodigital.entity.validators.MaiorDezoito;

@Entity
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "O nome precisa ser preenchido")
    String nome;

    @NotBlank(message = "O sobrenome precisa ser preenchido")
    String sobrenome;

    @NotBlank
    @Column(unique = true)
    @CPF(message = "CPF inválido")
    String cpf;

    @NotBlank(message = "O e-mail precisa ser preenchido")
    @Email(message = "Formato de e-mail inválido")
    @Column(unique = true)
    String email;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @MaiorDezoito(message = "Cliente precisa ser maior de 18 anos")
    LocalDate dataNascimento;

    @OneToOne
    private Endereco endereco;


    @OneToOne
    private ArquivoCpf arquivoCpf;

    private String linkArquivoCpf;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Cliente() {
    }

    public Cliente(@NotBlank(message = "O nome precisa ser preenchido") String nome,
            @NotBlank(message = "O sobrenome precisa ser preenchido") String sobrenome, @NotBlank String cpf,
            @NotBlank(message = "O e-mail precisa ser preenchido") @Email(message = "Formato de e-mail inválido") String email,
            @NotBlank LocalDate dataNascimento) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public ArquivoCpf getArquivoCpf() {
        return arquivoCpf;
    }

    public void setArquivoCpf(ArquivoCpf arquivoCpf) {
        this.arquivoCpf = arquivoCpf;
    }

    public String getLinkArquivoCpf() {
        return linkArquivoCpf;
    }

    public void setLinkArquivoCpf(String linkArquivoCpf) {
        this.linkArquivoCpf = linkArquivoCpf;
    }

    
    
}
