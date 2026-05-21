package br.com.alura.leilao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.dao.UsuarioDao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;

public class UsuarioDaoTest {

    private UsuarioDao dao;
    private EntityManager em;

    @BeforeEach
    public void setUp() {
        this.em = JPAUtil.getEntityManager();
        this.dao = new UsuarioDao(em);
        em.getTransaction().begin();
    }

    @AfterEach
    public void tearDown() {
        em.getTransaction().rollback();
    }

    private Usuario criarUsuario() {
        Usuario usuario = new Usuario("fulano", "fulano@email.com", "123456");

        em.persist(usuario);
        return usuario;
    }

    @Test
    public void deveriaEncontrarUsuarioCadastrado() {

        Usuario usuario = criarUsuario();

        Usuario encontrado = dao.buscarPorUsername(usuario.getNome());

        assertNotNull(encontrado);
    }

    @Test
    public void naoDeveriaEncontrarUsuarioCadastrado() {

        criarUsuario();

        assertThrows(NoResultException.class,
                () -> dao.buscarPorUsername("ciclano"));
    }

    @Test
    public void deveriaRemoverUsuarioCadastrado() {

        Usuario usuario = criarUsuario();

        dao.deletar(usuario);

        assertThrows(NoResultException.class,
                () -> dao.buscarPorUsername(usuario.getNome()));
    }

}
