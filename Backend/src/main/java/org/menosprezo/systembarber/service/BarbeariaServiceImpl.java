package org.menosprezo.systembarber.service;

import org.menosprezo.systembarber.dto.BarbeariaDTO;
import org.menosprezo.systembarber.model.Barbearia;
import org.menosprezo.systembarber.repository.BarbeariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BarbeariaServiceImpl implements BarbeariaService {

    @Autowired
    private BarbeariaRepository barbeariaRepository;

    @Override
    public Barbearia criarBarbearia(BarbeariaDTO barbeariaDTO) {
        Barbearia barbearia = new Barbearia();
        mapearDTOParaEntidade(barbeariaDTO, barbearia);
        return barbeariaRepository.save(barbearia);
    }

    @Override
    public Optional<Barbearia> buscarPorId(Long id) {
        return barbeariaRepository.findById(id);
    }

    @Override
    public List<Barbearia> buscarPorLocalizacao(String localizacao) {
        if (localizacao == null || localizacao.isEmpty()) {
            return barbeariaRepository.findAll();
        }
        return barbeariaRepository.findByLocalizacaoContainingIgnoreCase(localizacao);
    }

    @Override
    public List<Barbearia> listarTodas() {
        return barbeariaRepository.findAll();
    }

    @Override
    public Barbearia atualizarBarbearia(Long id, BarbeariaDTO barbeariaDTO) {
        return barbeariaRepository.findById(id).map(barbearia -> {
            mapearDTOParaEntidade(barbeariaDTO, barbearia);
            return barbeariaRepository.save(barbearia);
        }).orElseThrow(() -> new RuntimeException("Barbearia não encontrada com o ID: " + id));
    }

    @Override
    public void deletarBarbearia(Long id) {
        if (barbeariaRepository.existsById(id)) {
            barbeariaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Barbearia não encontrada com o ID: " + id);
        }
    }

    private void mapearDTOParaEntidade(BarbeariaDTO dto, Barbearia barbearia) {
        barbearia.setNome(dto.getNome());
        barbearia.setEmail(dto.getEmail());
        barbearia.setTelefone(dto.getTelefone());
        barbearia.setFormasPagamento(dto.getFormasPagamento());

        // Define quantidade de funcionários diretamente
        barbearia.setQuantidadeFuncionarios(dto.getQuantidadeFuncionarios());

        barbearia.setProprietario(dto.getProprietario());
        barbearia.setLocalizacao(dto.getLocalizacao());

        // Define dias de serviço diretamente
        barbearia.setDiasServico(dto.getDiasServico());

        barbearia.setHorarioFuncionamento(dto.getHorarioFuncionamento());
    }



}
