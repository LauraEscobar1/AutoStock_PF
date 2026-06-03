import { Navigate, Route, Routes } from 'react-router-dom';
import Sidebar from './components/Sidebar.jsx';
import Dashboard from './pages/Dashboard.jsx';
import Movimientos from './pages/Movimientos.jsx';
import Productos from './pages/Productos.jsx';
import Proveedores from './pages/Proveedores.jsx';

export default function App() {
  return (
    <div className="app-shell">
      <Sidebar />
      <main className="main-content">
        <Routes>
          <Route path="/" element={<Navigate to="/dashboard" replace />} />
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/productos" element={<Productos />} />
          <Route path="/movimientos" element={<Movimientos />} />
          <Route path="/proveedores" element={<Proveedores />} />
        </Routes>
      </main>
    </div>
  );
}
