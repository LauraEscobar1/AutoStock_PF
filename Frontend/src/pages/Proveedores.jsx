import { useEffect, useState } from 'react';
import PageHeader from '../components/PageHeader.jsx';
import StatusMessage from '../components/StatusMessage.jsx';
import { proveedorService } from '../services/proveedorService.js';

const emptyForm = {
  nombre: '',
  contacto: '',
  descuentos: 0
};

export default function Proveedores() {
  const [proveedores, setProveedores] = useState([]);
  const [form, setForm] = useState(emptyForm);
  const [editingId, setEditingId] = useState(null);
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  useEffect(() => {
    cargarProveedores();
  }, []);

  async function cargarProveedores() {
    try {
      const response = await proveedorService.listar();
      setProveedores(response.data);
    } catch (err) {
      setError('No fue posible listar proveedores.');
    }
  }

  function handleChange(event) {
    const { name, value } = event.target;
    setForm((current) => ({ ...current, [name]: value }));
  }

  async function handleSubmit(event) {
    event.preventDefault();
    setMessage('');
    setError('');
    const payload = {
      ...form,
      descuentos: Number(form.descuentos)
    };

    try {
      if (editingId) {
        await proveedorService.actualizar(editingId, payload);
        setMessage('Proveedor actualizado correctamente.');
      } else {
        await proveedorService.crear(payload);
        setMessage('Proveedor creado correctamente.');
      }
      setEditingId(null);
      setForm(emptyForm);
      await cargarProveedores();
    } catch (err) {
      setError(err.response?.data?.message || 'No fue posible guardar el proveedor.');
    }
  }

  function editar(proveedor) {
    setEditingId(proveedor.id);
    setForm({
      nombre: proveedor.nombre || '',
      contacto: proveedor.contacto || '',
      descuentos: proveedor.descuentos ?? 0
    });
  }

  async function eliminar(id) {
    setMessage('');
    setError('');
    try {
      await proveedorService.eliminar(id);
      setMessage('Proveedor eliminado correctamente.');
      await cargarProveedores();
    } catch (err) {
      setError('No fue posible eliminar el proveedor.');
    }
  }

  return (
    <section>
      <PageHeader title="Proveedores" subtitle="Administracion de proveedores" />
      <StatusMessage type="success">{message}</StatusMessage>
      <StatusMessage type="error">{error}</StatusMessage>

      <form className="form-panel" onSubmit={handleSubmit}>
        <div className="form-grid">
          <label>
            Nombre
            <input name="nombre" value={form.nombre} onChange={handleChange} required />
          </label>
          <label>
            Contacto
            <input name="contacto" value={form.contacto} onChange={handleChange} required />
          </label>
          <label>
            Descuentos
            <input name="descuentos" type="number" min="0" step="0.01" value={form.descuentos} onChange={handleChange} />
          </label>
        </div>
        <div className="actions">
          <button type="submit">{editingId ? 'Actualizar' : 'Crear'} proveedor</button>
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
              <th>Nombre</th>
              <th>Contacto</th>
              <th>Descuentos</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            {proveedores.map((proveedor) => (
              <tr key={proveedor.id}>
                <td>{proveedor.nombre}</td>
                <td>{proveedor.contacto}</td>
                <td>{proveedor.descuentos}</td>
                <td className="row-actions">
                  <button type="button" className="secondary" onClick={() => editar(proveedor)}>Editar</button>
                  <button type="button" className="danger" onClick={() => eliminar(proveedor.id)}>Eliminar</button>
                </td>
              </tr>
            ))}
            {proveedores.length === 0 && (
              <tr>
                <td colSpan="4" className="empty-state">No hay proveedores registrados.</td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </section>
  );
}
