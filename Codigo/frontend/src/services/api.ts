const BASE_URL = 'http://localhost:8080/api';

async function request<T>(path: string, options?: RequestInit): Promise<T> {
  const res = await fetch(`${BASE_URL}${path}`, {
    headers: { 'Content-Type': 'application/json' },
    ...options,
  });

  const data = await res.json();

  if (!res.ok) {
    throw new Error(data.erro || 'Erro na requisição');
  }

  return data as T;
}

export const api = {
  login: (email: string, senha: string) =>
    request('/auth/login', {
      method: 'POST',
      body: JSON.stringify({ email, senha }),
    }),

  cadastrarAluno: (payload: object) =>
    request('/cadastro/aluno', {
      method: 'POST',
      body: JSON.stringify(payload),
    }),

  cadastrarEmpresa: (payload: object) =>
    request('/cadastro/empresa', {
      method: 'POST',
      body: JSON.stringify(payload),
    }),

  listarInstituicoes: () =>
    request('/instituicoes'),

  listarVantagens: (empresaId: number) =>
    request(`/vantagens/empresa/${empresaId}`),

  listarTodasVantagens: () =>
    request('/vantagens'),

  criarVantagem: (empresaId: number, payload: object) =>
    request(`/vantagens/empresa/${empresaId}`, {
      method: 'POST',
      body: JSON.stringify(payload),
    }),

  atualizarVantagem: (id: number, payload: object) =>
    request(`/vantagens/${id}`, {
      method: 'PUT',
      body: JSON.stringify(payload),
    }),

  deletarVantagem: (id: number) =>
    request(`/vantagens/${id}`, { method: 'DELETE' }),

  atualizarPerfilAluno: (id: number, payload: object) =>
    request(`/perfil/aluno/${id}`, {
      method: 'PUT',
      body: JSON.stringify(payload),
    }),

  atualizarPerfilEmpresa: (id: number, payload: object) =>
    request(`/perfil/empresa/${id}`, {
      method: 'PUT',
      body: JSON.stringify(payload),
    }),

  // ── TRANSAÇÕES
  /** Lista alunos da mesma instituição (para o select do professor) */
  listarAlunosDaInstituicao: (instituicaoId: number) =>
    request(`/transacoes/alunos/instituicao/${instituicaoId}`),

  /** Professor envia moedas para um aluno */
  enviarMoedas: (payload: {
    professorId: number;
    alunoId: number;
    quantidade: number;
    mensagem?: string;
  }) =>
    request('/transacoes/enviar', { method: 'POST', body: JSON.stringify(payload) }),

  /** Extrato de envios do professor */
  extratoDosProfessor: (professorId: number) =>
    request(`/transacoes/professor/${professorId}`),

  /** Extrato de recebimentos do aluno */
  extratoDoAluno: (alunoId: number) =>
    request(`/transacoes/aluno/${alunoId}`),

  // ── RESGATES

  /** Aluno resgata uma vantagem */
  resgatar: (alunoId: number, vantagemId: number) =>
    request('/resgates', {
      method: 'POST',
      body: JSON.stringify({ alunoId, vantagemId }),
    }),

  /** Histórico de resgates do aluno */
  resgatesDoAluno: (alunoId: number) =>
    request(`/resgates/aluno/${alunoId}`),

  /** Resgates pendentes/confirmados da empresa */
  resgatesDaEmpresa: (empresaId: number) =>
    request(`/resgates/empresa/${empresaId}`),

  /** Empresa confirma entrega da vantagem */
  confirmarResgate: (resgateId: number) =>
    request(`/resgates/${resgateId}/confirmar`, { method: 'PUT' }),
};