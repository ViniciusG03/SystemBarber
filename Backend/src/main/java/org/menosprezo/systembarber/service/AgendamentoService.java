package org.menosprezo.systembarber.service;

import org.menosprezo.systembarber.dto.AgendamentoDTO;
import org.menosprezo.systembarber.model.Agendamento;
import org.menosprezo.systembarber.model.Barbearia;
import org.menosprezo.systembarber.repository.AgendamentoRepository;
import org.menosprezo.systembarber.repository.BarbeariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private BarbeariaRepository barbeariaRepository;

    public Agendamento criarAgendamento(AgendamentoDTO agendamentoDTO) {
        Optional<Barbearia> barbearia = barbeariaRepository.findById(agendamentoDTO.getIdBarbearia());
        if (barbearia.isEmpty()) {
            throw new RuntimeException("Barbearia não encontrada");
        }
        Agendamento agendamento = new Agendamento();
        agendamento.setBarbearia(barbearia.get());
        agendamento.setNomeCliente(agendamentoDTO.getNomeCliente());
        agendamento.setEmailCliente(agendamentoDTO.getEmailCliente());
        agendamento.setTelefoneCliente(agendamentoDTO.getTelefoneCliente());
        agendamento.setFormaPagamento(Agendamento.FormaPagamento.valueOf(agendamentoDTO.getFormaPagamento().toUpperCase()));
        agendamento.setDataHorario(agendamentoDTO.getDataHorario());
        return agendamentoRepository.save(agendamento);
    }
}
