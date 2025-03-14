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
import java.util.Set;

@Setter
@Getter
@Repository
public class UsuarioJPARepository implements IUsuarioRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public Usuario obtener(int id) throws SQLException {
        return em.find(Usuario.class, id);
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
        return Set.of();
    }
}
