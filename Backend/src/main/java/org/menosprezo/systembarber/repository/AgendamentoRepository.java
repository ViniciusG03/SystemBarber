package org.menosprezo.systembarber.repository;

import org.menosprezo.systembarber.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
