package io.github.pessoas.nossobancodigital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.pessoas.nossobancodigital.entity.ArquivoCpf;

@Repository
public interface ArquivoCpfRepository extends JpaRepository<ArquivoCpf, Long>{
    
}
