import { Link } from "react-router-dom";
import "./Header.css";

function Header() {
  return (
    <header className="header">
      <h2>💰 Moeda Estudantil</h2>

      <nav>
        <Link to="/">Home</Link>
        <Link to="/alunos">Alunos</Link>
        <Link to="/empresas">Empresas</Link>
      </nav>
    </header>
  );
}

export default Header;