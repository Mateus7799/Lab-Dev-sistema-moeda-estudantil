package com.moedaestudantil;

import com.moedaestudantil.model.*;
import com.moedaestudantil.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

// Imports necessários para o Supplier e verificação de existência
import java.util.function.Supplier;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final AlunoRepository alunoRepository;
    private final UsuarioRepository usuarioRepository;
    private final InstituicaoRepository instituicaoRepository;
    private final ProfessorRepository professorRepository;

    public DataInitializer(AlunoRepository alunoRepository, UsuarioRepository usuarioRepository,
                           InstituicaoRepository instituicaoRepository, ProfessorRepository professorRepository) {
        this.alunoRepository = alunoRepository;
        this.usuarioRepository = usuarioRepository;
        this.instituicaoRepository = instituicaoRepository;
        this.professorRepository = professorRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        // 1. Instituições
        if (instituicaoRepository.count() == 0) {
            instituicaoRepository.save(new Instituicao("Universidade Federal de Minas Gerais (UFMG)"));
            instituicaoRepository.save(new Instituicao("Pontifícia Universidade Católica de Minas Gerais (PUC-MG)"));
            instituicaoRepository.save(new Instituicao("Centro Federal de Educação Tecnológica de Minas Gerais (CEFET-MG)"));
            log.info("Instituições criadas.");
        }

        Instituicao instDefault = instituicaoRepository.findAll().get(0);

        // 2. Criação dos usuários de teste (Usa o método auxiliar que criamos)
        criarUsuarioSeNaoExistir("aluno.teste@sistema.local", "00000000000", () -> {
            Aluno a = new Aluno();
            a.setNome("Aluno de Testes");
            a.setEmail("aluno.teste@sistema.local");
            a.setSenha("123456");
            a.setTipo(Usuario.TipoUsuario.ALUNO);
            a.setCpf("00000000000");
            a.setCurso("Engenharia");
            a.setSaldoMoedas(9999);
            a.setInstituicao(instDefault);
            a.setIsTestUser(true);
            return a;
        });

        criarUsuarioSeNaoExistir("professor.teste@teste.com", "22222222222", () -> {
            Professor p = new Professor();
            p.setNome("Prof. Carlos Silva");
            p.setEmail("professor.teste@teste.com");
            p.setSenha("123456");
            p.setTipo(Usuario.TipoUsuario.PROFESSOR);
            p.setCpf("22222222222");
            p.setDepartamento("Computação");
            p.setSaldoMoedas(1000);
            p.setInstituicao(instDefault);
            return p;
        });
    }

    private void criarUsuarioSeNaoExistir(String email, String cpf, Supplier<Usuario> creator) {
        // Verifica se já existe por E-mail OU por CPF para evitar a violação de constraint
        boolean existe = usuarioRepository.findByEmail(email).isPresent() ||
                usuarioRepository.findByCpf(cpf).isPresent();

        if (!existe) {
            usuarioRepository.save(creator.get());
            log.info("Usuário {} criado com sucesso.", email);
        } else {
            log.info("Usuário {} ou CPF {} já existe, ignorando seed.", email, cpf);
        }
    }
}