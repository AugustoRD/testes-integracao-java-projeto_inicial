package br.com.alura.leilao;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;

import org.junit.Test;

import br.com.alura.leilao.dao.UsuarioDao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;

public class UsuarioDaoTest {

    private UsuarioDao dao;

    @Test
    public void testeBuscaDeUsuarioPorUsername() {

        EntityManager em = JPAUtil.getEntityManager();

        this.dao = new UsuarioDao(em);

        Usuario usuario = new Usuario("fulano", "fulano@email.com", "123456");

        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();

        Usuario encontrado = dao.buscarPorUsername("fulano");

        assertNotNull(encontrado);
    }

}
