import api from './api';

const BASE = '/api/movimientos';

export const movimientoService = {
  listar: () => api.get(BASE),
  registrarEntrada: (data) => api.post(`${BASE}/entrada`, data),
  registrarSalida: (data) => api.post(`${BASE}/salida`, data),
  registrarDevolucion: (data) => api.post(`${BASE}/devolucion`, data)
};
