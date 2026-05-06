import { useEffect, useState } from "react";
import { getAlunos, criarAluno } from "../api";
import Modal from "../components/Modal";
import "./Alunos.css";

function Alunos() {
  const [alunos, setAlunos] = useState([]);
  const [open, setOpen] = useState(false);

  const [form, setForm] = useState({
    nome: "",
    email: "",
    cpf: "",
    curso: "",
  });

  useEffect(() => {
    carregar();
  }, []);

  const carregar = () => {
    getAlunos().then((res) => setAlunos(res.data));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    criarAluno(form).then(() => {
      setOpen(false);
      carregar();
    });
  };

  return (
    <div className="page">
      <h1>Alunos</h1>

      <button onClick={() => setOpen(true)}>+ Novo Aluno</button>

      <div className="card-container">
        {alunos.map((a) => (
          <div className="card" key={a.id}>
            <h3>{a.nome}</h3>

            <p>
              <strong>Email:</strong> {a.email}
            </p>
            <p>
              <strong>CPF:</strong> {a.cpf}
            </p>
            <p>
              <strong>Curso:</strong> {a.curso}
            </p>
            <p>
              <strong>Saldo:</strong> {a.saldoMoedas ?? 0} moedas
            </p>
          </div>
        ))}
      </div>

      <Modal isOpen={open} onClose={() => setOpen(false)}>
        <h2>Cadastrar Aluno</h2>

        <form onSubmit={handleSubmit}>
          <input
            placeholder="Nome"
            onChange={(e) => setForm({ ...form, nome: e.target.value })}
          />
          <input
            placeholder="Email"
            onChange={(e) => setForm({ ...form, email: e.target.value })}
          />
          <input
            placeholder="CPF"
            onChange={(e) => setForm({ ...form, cpf: e.target.value })}
          />
          <input
            placeholder="Curso"
            onChange={(e) => setForm({ ...form, curso: e.target.value })}
          />

          <button type="submit">Salvar</button>
        </form>
      </Modal>
    </div>
  );
}

export default Alunos;
