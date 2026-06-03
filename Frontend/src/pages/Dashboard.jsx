import { useEffect, useState } from 'react';
import PageHeader from '../components/PageHeader.jsx';
import StatusMessage from '../components/StatusMessage.jsx';
import { dashboardService } from '../services/dashboardService.js';

const initialStats = {
  productos: 0,
  movimientos: 0,
  proveedores: 0,
  facturas: 0
};

export default function Dashboard() {
  const [stats, setStats] = useState(initialStats);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    cargarResumen();
  }, []);

  async function cargarResumen() {
    setLoading(true);
    setError('');
    try {
      const [productos, movimientos, proveedores, facturas] = await Promise.all([
        dashboardService.contarProductos(),
        dashboardService.contarMovimientos(),
        dashboardService.contarProveedores(),
        dashboardService.contarFacturas()
      ]);

      setStats({
        productos: productos.data.length,
        movimientos: movimientos.data.length,
        proveedores: proveedores.data.length,
        facturas: facturas.data.length
      });
    } catch (err) {
      setError('No fue posible cargar el resumen. Verifica que el backend este activo.');
    } finally {
      setLoading(false);
    }
  }

  return (
    <section>
      <PageHeader
        title="Dashboard"
        subtitle="Resumen general del sistema de inventario"
      />

      <StatusMessage type="error">{error}</StatusMessage>

      <div className="stats-grid">
        <article className="stat-card">
          <span>Total Productos</span>
          <strong>{loading ? '...' : stats.productos}</strong>
        </article>
        <article className="stat-card">
          <span>Total Movimientos</span>
          <strong>{loading ? '...' : stats.movimientos}</strong>
        </article>
        <article className="stat-card">
          <span>Total Proveedores</span>
          <strong>{loading ? '...' : stats.proveedores}</strong>
        </article>
        <article className="stat-card">
          <span>Total Facturas</span>
          <strong>{loading ? '...' : stats.facturas}</strong>
        </article>
      </div>
    </section>
  );
}
