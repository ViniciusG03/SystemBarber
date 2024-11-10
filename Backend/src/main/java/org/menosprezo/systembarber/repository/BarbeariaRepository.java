package org.menosprezo.systembarber.repository;

import org.menosprezo.systembarber.model.Barbearia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarbeariaRepository extends JpaRepository<Barbearia, Long> {
    List<Barbearia> findByLocalizacaoContainingIgnoreCase(String localizacao);
}

