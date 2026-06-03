import api from './api';

export const catalogoService = {
  listarCategorias: () => api.get('/api/categorias'),
  listarProveedores: () => api.get('/api/proveedores'),
  listarUsuarios: () => api.get('/api/usuarios')
};
