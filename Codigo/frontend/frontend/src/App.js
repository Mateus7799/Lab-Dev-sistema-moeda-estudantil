import { useEffect, useState } from "react";
import { getAlunos } from "./api";

function App() {
  const [alunos, setAlunos] = useState([]);

  useEffect(() => {
    getAlunos().then(res => {
      console.log(res.data);
      setAlunos(res.data);
    });
  }, []);

  return (
    <div>
      <h1>Lista de alunos</h1>
      {alunos.map((a, i) => (
        <p key={i}>{a.nome}</p>
      ))}
    </div>
  );
}

export default App;