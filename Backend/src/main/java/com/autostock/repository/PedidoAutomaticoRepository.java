package com.autostock.repository;

import com.autostock.model.PedidoAutomatico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoAutomaticoRepository extends JpaRepository<PedidoAutomatico, Long> {
}
