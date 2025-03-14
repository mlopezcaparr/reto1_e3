package com.banana.bananawhatsapp.persistencia;

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
public class UsuarioJPARepository implements IUsuarioRepository{

    @PersistenceContext
    EntityManager em;

    @Override
    public Usuario obtener(int id) throws SQLException {

        return em.find(Usuario.class, id);
    }

    @Override
    @Transactional
    public Usuario crear(Usuario usuario) throws SQLException {
        if(usuario.valido())
        em.persist(usuario);
        return usuario;
    }

    @Override
    @Transactional
    public Usuario actualizar(Usuario usuario) throws SQLException {
        if(usuario.valido()) {
            Usuario usr = em.find(Usuario.class, usuario.getId());
            usr.setNombre(usuario.getNombre());
            usr.setEmail(usuario.getEmail());
            usr.setAlta(usuario.getAlta());
            //usr.setActivo(usuario.getActivo());
        }
        return usuario;
    }

    @Override
    public boolean borrar(Usuario usuario) throws SQLException {
        if(usuario.valido()){
            em.remove(usuario);
            return true;
        }
        return false;
    }

    @Override
    public Set<Usuario> obtenerPosiblesDestinatarios(Integer id, Integer max) throws SQLException {
        if(id <= 0) crear(new Usuario(id, null, null, null, true));
        var a = em.createNativeQuery("SELECT u.* FROM usuario u, mensaje m where m.from_user = :bus and u.id = m.to_user  ", Usuario.class);
        a.setParameter("bus", id);
        Set<Usuario> set = new HashSet<Usuario>(a.getResultList());
        return set;
    }
}
