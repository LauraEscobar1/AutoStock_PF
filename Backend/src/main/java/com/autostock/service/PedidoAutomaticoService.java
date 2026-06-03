package com.autostock.service;

import com.autostock.dto.PedidoAutomaticoDTO;
import java.util.List;

public interface PedidoAutomaticoService {

    List<PedidoAutomaticoDTO> listarTodos();

    PedidoAutomaticoDTO buscarPorId(Long id);

    PedidoAutomaticoDTO guardar(PedidoAutomaticoDTO dto);

    PedidoAutomaticoDTO actualizar(Long id, PedidoAutomaticoDTO dto);

    void eliminar(Long id);
}
