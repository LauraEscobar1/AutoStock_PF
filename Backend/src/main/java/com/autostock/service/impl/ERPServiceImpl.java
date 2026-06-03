package com.autostock.service.impl;

import com.autostock.service.ERPService;
import org.springframework.stereotype.Service;

@Service
public class ERPServiceImpl implements ERPService {

    @Override
    public String sincronizarCompras() {
        return "Compras sincronizadas correctamente";
    }

    @Override
    public String sincronizarVentas() {
        return "Ventas sincronizadas correctamente";
    }

    @Override
    public String sincronizarFacturas() {
        return "Facturas sincronizadas correctamente";
    }
}
