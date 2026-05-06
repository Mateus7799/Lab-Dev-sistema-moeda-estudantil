import { useEffect, useState } from "react";
import { getEmpresas, criarEmpresa } from "../api";
import Modal from "../components/Modal";
import "./Empresas.css";

function Empresas() {
  const [empresas, setEmpresas] = useState([]);
  const [open, setOpen] = useState(false);

  const [form, setForm] = useState({
    nome: "",
    email: "",
    cnpj: ""
  });

  useEffect(() => {
    carregar();
  }, []);

  const carregar = () => {
    getEmpresas().then(res => setEmpresas(res.data));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    criarEmpresa(form).then(() => {
      setOpen(false);
      setForm({ nome: "", email: "", cnpj: "" });
      carregar();
    });
  };

  return (
    <div className="page">
      <h1>Empresas Parceiras</h1>

      <button onClick={() => setOpen(true)}>+ Nova Empresa</button>

      <ul className="lista">
        {empresas.map(e => (
          <li key={e.id}>
            <strong>{e.nome}</strong> — {e.email}
          </li>
        ))}
      </ul>

      <Modal isOpen={open} onClose={() => setOpen(false)}>
        <h2>Cadastrar Empresa</h2>

        <form onSubmit={handleSubmit}>
          <input
            placeholder="Nome"
            value={form.nome}
            onChange={e => setForm({ ...form, nome: e.target.value })}
          />
          <input
            placeholder="Email"
            value={form.email}
            onChange={e => setForm({ ...form, email: e.target.value })}
          />
          <input
            placeholder="CNPJ"
            value={form.cnpj}
            onChange={e => setForm({ ...form, cnpj: e.target.value })}
          />

          <button type="submit">Salvar</button>
        </form>
      </Modal>
    </div>
  );
}

export default Empresas;