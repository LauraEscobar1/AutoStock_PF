import { useEffect, useState } from 'react';
import PageHeader from '../components/PageHeader.jsx';
import StatusMessage from '../components/StatusMessage.jsx';
import { catalogoService } from '../services/catalogoService.js';
import { productoService } from '../services/productoService.js';

const emptyForm = {
  nombre: '',
  codigo: '',
  descripcion: '',
  cantidad: 0,
  precioUnitario: '',
  ubicacion: '',
  estado: 'DISPONIBLE',
  categoriaId: '',
  proveedorId: ''
};

export default function Productos() {
  const [productos, setProductos] = useState([]);
  const [categorias, setCategorias] = useState([]);
  const [proveedores, setProveedores] = useState([]);
  const [form, setForm] = useState(emptyForm);
  const [editingId, setEditingId] = useState(null);
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  useEffect(() => {
    cargarProductos();
    cargarCatalogos();
  }, []);

  async function cargarProductos() {
    try {
      const response = await productoService.listar();
      setProductos(response.data);
    } catch (err) {
      setError('No fue posible listar productos.');
    }
  }

  async function cargarCatalogos() {
    try {
      const [categoriasResponse, proveedoresResponse] = await Promise.all([
        catalogoService.listarCategorias(),
        catalogoService.listarProveedores()
      ]);
      setCategorias(categoriasResponse.data);
      setProveedores(proveedoresResponse.data);
    } catch (err) {
      setError('No fue posible cargar categorias o proveedores.');
    }
  }

  function handleChange(event) {
    const { name, value } = event.target;
    setForm((current) => ({ ...current, [name]: value }));
  }

  function buildPayload() {
    return {
      ...form,
      cantidad: Number(form.cantidad),
      precioUnitario: Number(form.precioUnitario),
      categoriaId: Number(form.categoriaId),
      proveedorId: Number(form.proveedorId)
    };
  }

  async function handleSubmit(event) {
    event.preventDefault();
    setMessage('');
    setError('');

    try {
      if (editingId) {
        await productoService.actualizar(editingId, buildPayload());
        setMessage('Producto actualizado correctamente.');
      } else {
        await productoService.crear(buildPayload());
        setMessage('Producto creado correctamente.');
      }

      setForm(emptyForm);
      setEditingId(null);
      await cargarProductos();
    } catch (err) {
      setError(err.response?.data?.message || 'No fue posible guardar el producto.');
    }
  }

  function editar(producto) {
    setEditingId(producto.id);
    setForm({
      nombre: producto.nombre || '',
      codigo: producto.codigo || '',
      descripcion: producto.descripcion || '',
      cantidad: producto.cantidad ?? 0,
      precioUnitario: producto.precioUnitario ?? '',
      ubicacion: producto.ubicacion || '',
      estado: producto.estado || 'DISPONIBLE',
      categoriaId: producto.categoria?.id || '',
      proveedorId: producto.proveedor?.id || ''
    });
  }

  async function eliminar(id) {
    setMessage('');
    setError('');
    try {
      await productoService.eliminar(id);
      setMessage('Producto eliminado correctamente.');
      await cargarProductos();
    } catch (err) {
      setError('No fue posible eliminar el producto.');
    }
  }

  return (
    <section>
      <PageHeader title="Productos" subtitle="Gestion de inventario" />
      <StatusMessage type="success">{message}</StatusMessage>
      <StatusMessage type="error">{error}</StatusMessage>

      <form className="form-panel" onSubmit={handleSubmit}>
        <div className="form-grid">
          <label>
            Nombre
            <input name="nombre" value={form.nombre} onChange={handleChange} required />
          </label>
          <label>
            Codigo
            <input name="codigo" value={form.codigo} onChange={handleChange} required />
          </label>
          <label>
            Cantidad
            <input name="cantidad" type="number" min="0" value={form.cantidad} onChange={handleChange} required />
          </label>
          <label>
            Precio unitario
            <input name="precioUnitario" type="number" min="0.01" step="0.01" value={form.precioUnitario} onChange={handleChange} required />
          </label>
          <label>
            Ubicacion
            <input name="ubicacion" value={form.ubicacion} onChange={handleChange} />
          </label>
          <label>
            Estado
            <select name="estado" value={form.estado} onChange={handleChange}>
              <option value="DISPONIBLE">Disponible</option>
              <option value="AGOTADO">Agotado</option>
              <option value="DETERIORADO">Deteriorado</option>
            </select>
          </label>
          <label>
            Categoria
            <select name="categoriaId" value={form.categoriaId} onChange={handleChange} required>
              <option value="">Seleccione</option>
              {categorias.map((categoria) => (
                <option key={categoria.id} value={categoria.id}>{categoria.nombre}</option>
              ))}
            </select>
          </label>
          <label>
            Proveedor
            <select name="proveedorId" value={form.proveedorId} onChange={handleChange} required>
              <option value="">Seleccione</option>
              {proveedores.map((proveedor) => (
                <option key={proveedor.id} value={proveedor.id}>{proveedor.nombre}</option>
              ))}
            </select>
          </label>
          <label className="full-width">
            Descripcion
            <textarea name="descripcion" value={form.descripcion} onChange={handleChange} rows="3" />
          </label>
        </div>
        <div className="actions">
          <button type="submit">{editingId ? 'Actualizar' : 'Crear'} producto</button>
          {editingId && (
            <button type="button" className="secondary" onClick={() => { setEditingId(null); setForm(emptyForm); }}>
              Cancelar
            </button>
          )}
        </div>
      </form>

      <div className="table-wrapper">
        <table>
          <thead>
            <tr>
              <th>Codigo</th>
              <th>Nombre</th>
              <th>Cantidad</th>
              <th>Precio</th>
              <th>Estado</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            {productos.map((producto) => (
              <tr key={producto.id}>
                <td>{producto.codigo}</td>
                <td>{producto.nombre}</td>
                <td>{producto.cantidad}</td>
                <td>{producto.precioUnitario}</td>
                <td>{producto.estado}</td>
                <td className="row-actions">
                  <button type="button" className="secondary" onClick={() => editar(producto)}>Editar</button>
                  <button type="button" className="danger" onClick={() => eliminar(producto.id)}>Eliminar</button>
                </td>
              </tr>
            ))}
            {productos.length === 0 && (
              <tr>
                <td colSpan="6" className="empty-state">No hay productos registrados.</td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </section>
  );
}
