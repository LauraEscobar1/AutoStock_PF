import api from './api';

const BASE = '/api/proveedores';

export const proveedorService = {
  listar: () => api.get(BASE),
  crear: (data) => api.post(BASE, data),
  actualizar: (id, data) => api.put(`${BASE}/${id}`, data),
  eliminar: (id) => api.delete(`${BASE}/${id}`)
};
