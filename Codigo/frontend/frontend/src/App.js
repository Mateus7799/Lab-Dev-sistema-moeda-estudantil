import { BrowserRouter, Routes, Route } from "react-router-dom";
import Header from "./components/Header";

import Home from "./pages/Home";
import Alunos from "./pages/Alunos";
import Empresas from "./pages/Empresas";

function App() {
  return (
    <BrowserRouter>
      <Header />

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/alunos" element={<Alunos />} />
        <Route path="/empresas" element={<Empresas />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;