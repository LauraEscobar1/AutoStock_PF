import api from './api';

export const dashboardService = {
  contarProductos: () => api.get('/api/productos'),
  contarMovimientos: () => api.get('/api/movimientos'),
  contarProveedores: () => api.get('/api/proveedores'),
  contarFacturas: () => api.get('/api/facturas')
};
