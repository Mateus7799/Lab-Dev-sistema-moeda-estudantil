import axios from "axios";

const API = axios.create({
  baseURL: "http://localhost:8080"
});

export const getAlunos = () => API.get("/alunos");
export const criarAluno = (aluno) => API.post("/alunos", aluno);

export const getEmpresas = () => API.get("/empresas");
export const criarEmpresa = (empresa) => API.post("/empresas", empresa);