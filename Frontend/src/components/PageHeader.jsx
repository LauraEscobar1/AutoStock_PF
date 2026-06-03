export default function PageHeader({ title, subtitle }) {
  return (
    <header className="page-header">
      <div>
        <h2>{title}</h2>
        {subtitle && <p>{subtitle}</p>}
      </div>
    </header>
  );
}
