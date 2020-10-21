package io.github.pessoas.nossobancodigital.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.apache.commons.lang3.RandomStringUtils;

@Entity
public class Conta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String agencia;
    private String conta;
    private String codigoBanco;
    private BigDecimal saldo;

    @OneToOne
    private Cliente cliente;

    public Conta() {
    }

    public Conta(Cliente cliente) {

        int lengthAgencia = 4;
        int lengthConta = 6;
        int lengthCodigoBanco = 3;
        
        this.agencia = RandomStringUtils.randomNumeric(lengthAgencia);
        this.conta = RandomStringUtils.randomNumeric(lengthConta);
        this.codigoBanco = RandomStringUtils.randomNumeric(lengthCodigoBanco);

        this.saldo = BigDecimal.ZERO;

        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getCodigoBanco() {
        return codigoBanco;
    }

    public void setCodigoBanco(String codigoBanco) {
        this.codigoBanco = codigoBanco;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
        Conta other = (Conta) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Conta [agencia=" + agencia + ", cliente=" + cliente + ", codigoBanco=" + codigoBanco + ", conta="
                + conta  + ", saldo=" + saldo + "]";
    }

    
    
}
