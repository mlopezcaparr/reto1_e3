package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLException;
import java.util.List;

@Setter
@Getter
@Repository
public class MensajeriaJPARepository implements IMensajeRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public Mensaje crear(Mensaje mensaje) throws SQLException {
        em.persist(mensaje);
        return mensaje;
    }

    @Override
    public List<Mensaje> obtener(Usuario usuario) throws SQLException {
        return List.of();
    }

    @Override
    public boolean borrarEntre(Usuario remitente, Usuario destinatario) throws Exception {
        return false;
    }

    @Override
    public boolean borrarTodos(Usuario usuario) throws SQLException {
        return false;
    }
}
