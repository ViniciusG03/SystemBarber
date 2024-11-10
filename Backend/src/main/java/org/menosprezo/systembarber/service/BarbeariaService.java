package org.menosprezo.systembarber.service;

import org.menosprezo.systembarber.dto.BarbeariaDTO;
import org.menosprezo.systembarber.model.Barbearia;

import java.util.List;
import java.util.Optional;

public interface BarbeariaService {
    Barbearia criarBarbearia(BarbeariaDTO barbeariaDTO);
    Optional<Barbearia> buscarPorId(Long id);
    List<Barbearia> listarTodas();
    Barbearia atualizarBarbearia(Long id, BarbeariaDTO barbeariaDTO);
    void deletarBarbearia(Long id);
    List<Barbearia> buscarPorLocalizacao(String localizacao);
}
