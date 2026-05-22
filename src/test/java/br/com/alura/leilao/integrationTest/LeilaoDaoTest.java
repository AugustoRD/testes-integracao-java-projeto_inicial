package br.com.alura.leilao.integrationTest;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.dao.LeilaoDao;
import br.com.alura.leilao.integrationTest.util.JPAUtil;
import br.com.alura.leilao.integrationTest.util.builder.LeilaoBuilder;
import br.com.alura.leilao.integrationTest.util.builder.UsuarioBuilder;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;

public class LeilaoDaoTest {

    private LeilaoDao dao;
    private EntityManager em;

    @BeforeEach
    public void setUp() {
        this.em = JPAUtil.getEntityManager();
        this.dao = new LeilaoDao(em);
        em.getTransaction().begin();
    }

    @AfterEach
    public void tearDown() {
        em.getTransaction().rollback();
    }

    @Test
    public void deveriaCadastrarLeilao() {

        Usuario usuario = new UsuarioBuilder()
                .comNome("fulano")
                .comEmail("fulano@email.com")
                .comSenha("123456")
                .criar();
        em.persist(usuario);

        Leilao leilao = new LeilaoBuilder()
                .comNome("Mochila")
                .comValorInicial("500")
                .comDataAbertura(LocalDate.now())
                .comUsuario(usuario)
                .criar();

        leilao = dao.salvar(leilao);

        Leilao salvo = dao.buscarPorId(leilao.getId());

        assertNotNull(salvo);
    }

    @Test
    public void deveriaAtualizarLeilao() {

        Usuario usuario = new UsuarioBuilder()
                .comNome("fulano")
                .comEmail("fulano@email.com")
                .comSenha("123456")
                .criar();
        em.persist(usuario);

        Leilao leilao = new LeilaoBuilder()
                .comNome("Mochila")
                .comValorInicial("500")
                .comDataAbertura(LocalDate.now())
                .comUsuario(usuario)
                .criar();

        leilao = dao.salvar(leilao);

        leilao.setNome("Celular");
        leilao.setValorInicial(new BigDecimal("500"));

        leilao = dao.salvar(leilao);

        Leilao salvo = dao.buscarPorId(leilao.getId());

        assertEquals("Celular", salvo.getNome());
        assertEquals(new BigDecimal("500"), salvo.getValorInicial());
    }

}
