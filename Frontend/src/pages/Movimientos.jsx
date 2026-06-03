import { useEffect, useState } from 'react';
import PageHeader from '../components/PageHeader.jsx';
import StatusMessage from '../components/StatusMessage.jsx';
import { catalogoService } from '../services/catalogoService.js';
import { movimientoService } from '../services/movimientoService.js';
import { productoService } from '../services/productoService.js';

const emptyForm = {
  productoId: '',
  usuarioId: '',
  cantidad: 1
};

export default function Movimientos() {
  const [form, setForm] = useState(emptyForm);
  const [productos, setProductos] = useState([]);
  const [usuarios, setUsuarios] = useState([]);
  const [movimientos, setMovimientos] = useState([]);
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  useEffect(() => {
    cargarDatos();
  }, []);

  async function cargarDatos() {
    try {
      const [productosResponse, usuariosResponse, movimientosResponse] = await Promise.all([
        productoService.listar(),
        catalogoService.listarUsuarios(),
        movimientoService.listar()
      ]);
      setProductos(productosResponse.data);
      setUsuarios(usuariosResponse.data);
      setMovimientos(movimientosResponse.data);
    } catch (err) {
      setError('No fue posible cargar la informacion de movimientos.');
    }
  }

  function handleChange(event) {
    const { name, value } = event.target;
    setForm((current) => ({ ...current, [name]: value }));
  }

  function payload() {
    return {
      productoId: Number(form.productoId),
      usuarioId: Number(form.usuarioId),
      cantidad: Number(form.cantidad)
    };
  }

  async function registrar(tipo) {
    setMessage('');
    setError('');
    try {
      if (tipo === 'entrada') {
        await movimientoService.registrarEntrada(payload());
      }
      if (tipo === 'salida') {
        await movimientoService.registrarSalida(payload());
      }
      if (tipo === 'devolucion') {
        await movimientoService.registrarDevolucion(payload());
      }
      setMessage('Movimiento registrado correctamente.');
      setForm(emptyForm);
      await cargarDatos();
    } catch (err) {
      setError(err.response?.data?.message || 'No fue posible registrar el movimiento.');
    }
  }

  return (
    <section>
      <PageHeader title="Movimientos" subtitle="Entradas, salidas y devoluciones de inventario" />
      <StatusMessage type="success">{message}</StatusMessage>
      <StatusMessage type="error">{error}</StatusMessage>

      <div className="form-panel">
        <div className="form-grid">
          <label>
            Producto
            <select name="productoId" value={form.productoId} onChange={handleChange} required>
              <option value="">Seleccione</option>
              {productos.map((producto) => (
                <option key={producto.id} value={producto.id}>{producto.nombre} - Stock: {producto.cantidad}</option>
              ))}
            </select>
          </label>
          <label>
            Usuario
            <select name="usuarioId" value={form.usuarioId} onChange={handleChange} required>
              <option value="">Seleccione</option>
              {usuarios.map((usuario) => (
                <option key={usuario.id} value={usuario.id}>{usuario.nombreUsuario}</option>
              ))}
            </select>
          </label>
          <label>
            Cantidad
            <input name="cantidad" type="number" min="1" value={form.cantidad} onChange={handleChange} required />
          </label>
        </div>
        <div className="actions">
          <button type="button" onClick={() => registrar('entrada')}>Registrar entrada</button>
          <button type="button" className="secondary" onClick={() => registrar('salida')}>Registrar salida</button>
          <button type="button" className="secondary" onClick={() => registrar('devolucion')}>Registrar devolucion</button>
        </div>
      </div>

      <div className="table-wrapper">
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Tipo</th>
              <th>Cantidad</th>
              <th>Fecha</th>
              <th>Producto</th>
              <th>Usuario</th>
            </tr>
          </thead>
          <tbody>
            {movimientos.map((movimiento) => (
              <tr key={movimiento.id}>
                <td>{movimiento.id}</td>
                <td>{movimiento.tipo}</td>
                <td>{movimiento.cantidad}</td>
                <td>{movimiento.fechaHora}</td>
                <td>{movimiento.productoId}</td>
                <td>{movimiento.usuarioId}</td>
              </tr>
            ))}
            {movimientos.length === 0 && (
              <tr>
                <td colSpan="6" className="empty-state">No hay movimientos registrados.</td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </section>
  );
}
