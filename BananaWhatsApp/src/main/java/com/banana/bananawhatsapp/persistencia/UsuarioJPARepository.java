package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Repository
public class UsuarioJPARepository implements IUsuarioRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public Usuario obtener(int id) throws SQLException {
        Usuario usuario = em.find(Usuario.class, id);
        if (usuario==null){
            throw new UsuarioException();
        }
        return usuario;
    }

    @Override
    @Transactional
    public Usuario crear(Usuario usuario) throws SQLException {
        try {
            usuario.valido();
        } catch (UsuarioException e) {
            throw new SQLException(e);
        }
        em.persist(usuario);
        return usuario;
    }

    @Override
    @Transactional
    public Usuario actualizar(Usuario usuario) throws SQLException {
        try {
            usuario.valido();
        } catch (UsuarioException e) {
            throw new UsuarioException();
        }
        em.merge(usuario);
        return usuario;
    }

    @Override
    @Transactional
    public boolean borrar(Usuario usuario) throws SQLException {
        Usuario userToDelete = em.find(Usuario.class, usuario.getId());
        userToDelete.setActivo(false);
        em.merge(userToDelete);
        return !userToDelete.isActivo();
    }

    @Override
    public Set<Usuario> obtenerPosiblesDestinatarios(Integer id, Integer max) throws SQLException {
        try {
            Usuario usuario = em.find(Usuario.class, id);
            usuario.valido();
        } catch (Exception e) {
            throw new UsuarioException();
        }
        var a = em.createNativeQuery("SELECT u.* FROM usuario u, mensaje m where m.from_user = :bus and u.id = m.to_user LIMIT :maxResults ", Usuario.class);
        a.setParameter("bus", id);
        a.setParameter("maxResults", max);
        Set<Usuario> set = new HashSet<Usuario>(a.getResultList());
        return set;
    }
}
