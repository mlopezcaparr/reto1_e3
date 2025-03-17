package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Setter
@Getter
@Repository
public class MensajeriaJPARepository implements IMensajeRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Mensaje crear(Mensaje mensaje) throws SQLException {
        if (mensaje.valido()) em.persist(mensaje);
            return mensaje;
    }

    @Override
    public List<Mensaje> obtener(Usuario usuario) throws SQLException {
        //return List.of();
        if(usuario.valido()) {
            TypedQuery<Mensaje> query = em.createQuery(
                    "SELECT m FROM Mensaje m WHERE m.remitente = :usuario OR m.destinatario = :usuario", Mensaje.class);
            query.setParameter("usuario", usuario);
            return query.getResultList();
        } else {
            return null;
        }
    }

    @Override
    public List<Mensaje> obtenerChat(Usuario remitente, Usuario destinatario) throws SQLException {
        if(remitente.valido() && destinatario.valido()) {
            TypedQuery<Mensaje> query = em.createQuery(
                    "SELECT m FROM Mensaje m WHERE (m.remitente = :remitente AND m.destinatario = :destinatario)" +
                            " OR (m.remitente = :destinatario AND m.destinatario = :remitente)", Mensaje.class);
            query.setParameter("remitente", remitente);
            query.setParameter("destinatario", destinatario);
            return query.getResultList();
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public boolean borrarEntre(Usuario remitente, Usuario destinatario) throws Exception {
        if(remitente.valido() && destinatario.valido()) {
            TypedQuery<Mensaje> query = em.createQuery(
                    "SELECT m FROM Mensaje m WHERE (m.remitente = :remitente AND m.destinatario = :destinatario)" +
                            " OR (m.remitente = :destinatario AND m.destinatario = :remitente)", Mensaje.class);
            query.setParameter("remitente", remitente);
            query.setParameter("destinatario", destinatario);
            List<Mensaje> mensajes = query.getResultList();
            if (!mensajes.isEmpty()) {
                for (Mensaje mensaje : mensajes) {
                    em.remove(mensaje); // Eliminar el mensaje
                }
                return true;
            } else {
                return true; // No se encontraron mensajes para eliminar
            }
        } else {
            throw new Exception("El remitente no es válido.");
        }
    }

    @Override
    @Transactional
    public boolean borrarTodos(Usuario usuario) throws SQLException {
        if(usuario.valido()) {
            TypedQuery<Mensaje> query = em.createQuery(
                    "SELECT m FROM Mensaje m WHERE m.remitente = :usuario OR m.destinatario = :usuario", Mensaje.class);
            query.setParameter("usuario", usuario);
            List<Mensaje> mensajes = query.getResultList();
            if (!mensajes.isEmpty()) {
                for (Mensaje mensaje : mensajes) {
                    em.remove(mensaje); // Eliminar el mensaje
                }
                return true;
            } else {
                return true; // No se encontraron mensajes para eliminar
            }
        } else {
            throw new SQLException("El usuario no es válido.");
        }
    }
}
