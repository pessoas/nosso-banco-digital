package io.github.pessoas.nossobancodigital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.pessoas.nossobancodigital.entity.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{
    
}
