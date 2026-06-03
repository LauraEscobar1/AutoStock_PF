import api from './api';

const BASE = '/api/productos';

export const productoService = {
  listar: () => api.get(BASE),
  crear: (data) => api.post(BASE, data),
  actualizar: (id, data) => api.put(`${BASE}/${id}`, data),
  eliminar: (id) => api.delete(`${BASE}/${id}`),
  consultarStock: (id) => api.get(`${BASE}/${id}/stock`)
};
