import { NavLink } from 'react-router-dom';

const links = [
  { to: '/dashboard', label: 'Dashboard' },
  { to: '/productos', label: 'Productos' },
  { to: '/movimientos', label: 'Movimientos' },
  { to: '/proveedores', label: 'Proveedores' }
];

export default function Sidebar() {
  return (
    <aside className="sidebar">
      <div className="brand">
        <span className="brand-mark">AS</span>
        <div>
          <h1>AutoStock</h1>
          <p>Inventario</p>
        </div>
      </div>

      <nav className="nav-menu">
        {links.map((link) => (
          <NavLink key={link.to} to={link.to} className="nav-link">
            {link.label}
          </NavLink>
        ))}
      </nav>
    </aside>
  );
}
