package io.github.pessoas.nossobancodigital.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.pessoas.nossobancodigital.entity.Cliente;
import io.github.pessoas.nossobancodigital.entity.projections.ClienteDadosProjection;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    public Optional<Cliente> findByCpf(String cpf);
    public Optional<ClienteDadosProjection> findByEmail(String email);
}
