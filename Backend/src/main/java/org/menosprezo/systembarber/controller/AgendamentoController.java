package org.menosprezo.systembarber.controller;

import org.menosprezo.systembarber.dto.AgendamentoDTO;
import org.menosprezo.systembarber.model.Agendamento;
import org.menosprezo.systembarber.model.Barbearia;
import org.menosprezo.systembarber.repository.AgendamentoRepository;
import org.menosprezo.systembarber.repository.BarbeariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private BarbeariaRepository barbeariaRepository;

    @PostMapping
    public ResponseEntity<String> criarAgendamento(@RequestBody AgendamentoDTO agendamentoDTO) {
        System.out.println("Recebido DTO: " + agendamentoDTO);
        Barbearia barbearia = barbeariaRepository.findById(agendamentoDTO.getIdBarbearia())
                .orElseThrow(() -> new RuntimeException("Barbearia não encontrada"));

        Agendamento agendamento = new Agendamento();
        agendamento.setBarbearia(barbearia);
        agendamento.setNomeCliente(agendamentoDTO.getNomeCliente());
        agendamento.setEmailCliente(agendamentoDTO.getEmailCliente());
        agendamento.setTelefoneCliente(agendamentoDTO.getTelefoneCliente());
        agendamento.setFormaPagamento(Agendamento.FormaPagamento.valueOf(agendamentoDTO.getFormaPagamento().toUpperCase()));
        agendamento.setDataHorario(agendamentoDTO.getDataHorario());

        agendamentoRepository.save(agendamento);
        return ResponseEntity.ok("Agendamento realizado com sucesso!");
    }
}

