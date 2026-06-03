package com.autostock.service.impl;

import com.autostock.dto.PedidoAutomaticoDTO;
import com.autostock.model.PedidoAutomatico;
import com.autostock.repository.PedidoAutomaticoRepository;
import com.autostock.service.PedidoAutomaticoService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PedidoAutomaticoServiceImpl implements PedidoAutomaticoService {

    private final PedidoAutomaticoRepository pedidoAutomaticoRepository;

    public PedidoAutomaticoServiceImpl(PedidoAutomaticoRepository pedidoAutomaticoRepository) {
        this.pedidoAutomaticoRepository = pedidoAutomaticoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoAutomaticoDTO> listarTodos() {
        return pedidoAutomaticoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PedidoAutomaticoDTO buscarPorId(Long id) {
        return toDTO(buscarPedidoAutomatico(id));
    }

    @Override
    public PedidoAutomaticoDTO guardar(PedidoAutomaticoDTO dto) {
        PedidoAutomatico pedidoAutomatico = new PedidoAutomatico();
        asignarDatos(pedidoAutomatico, dto);
        pedidoAutomatico.generarPedido();
        return toDTO(pedidoAutomaticoRepository.save(pedidoAutomatico));
    }

    @Override
    public PedidoAutomaticoDTO actualizar(Long id, PedidoAutomaticoDTO dto) {
        PedidoAutomatico pedidoAutomatico = buscarPedidoAutomatico(id);
        asignarDatos(pedidoAutomatico, dto);
        pedidoAutomatico.generarPedido();
        return toDTO(pedidoAutomaticoRepository.save(pedidoAutomatico));
    }

    @Override
    public void eliminar(Long id) {
        PedidoAutomatico pedidoAutomatico = buscarPedidoAutomatico(id);
        pedidoAutomaticoRepository.delete(pedidoAutomatico);
    }

    private PedidoAutomatico buscarPedidoAutomatico(Long id) {
        return pedidoAutomaticoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("PedidoAutomatico no encontrado con id: " + id));
    }

    private void asignarDatos(PedidoAutomatico pedidoAutomatico, PedidoAutomaticoDTO dto) {
        pedidoAutomatico.setCantidad(dto.getCantidad());
        pedidoAutomatico.setFechaGenerado(dto.getFechaGenerado());
    }

    private PedidoAutomaticoDTO toDTO(PedidoAutomatico pedidoAutomatico) {
        PedidoAutomaticoDTO dto = new PedidoAutomaticoDTO();
        dto.setId(pedidoAutomatico.getId());
        dto.setCantidad(pedidoAutomatico.getCantidad());
        dto.setFechaGenerado(pedidoAutomatico.getFechaGenerado());
        return dto;
    }
}
