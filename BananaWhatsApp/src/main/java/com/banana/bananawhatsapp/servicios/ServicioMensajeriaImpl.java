package com.banana.bananawhatsapp.servicios;

import com.banana.bananawhatsapp.exceptions.MensajeException;
import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import com.banana.bananawhatsapp.persistencia.IMensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ServicioMensajeriaImpl implements IServicioMensajeria {

    @Autowired
    IMensajeRepository mensajeriaRepo;

    @Override
    public Mensaje enviarMensaje(Usuario remitente, Usuario destinatario, String texto) throws UsuarioException, MensajeException, SQLException {
         Mensaje mensaje = new Mensaje(null, remitente, destinatario, texto, LocalDate.now());
         return mensajeriaRepo.crear(mensaje);
    }

    @Override
    public List<Mensaje> mostrarChatConUsuario(Usuario remitente, Usuario destinatario) throws UsuarioException, MensajeException, SQLException {
        return mensajeriaRepo.obtenerChat(remitente, destinatario);
    }

    @Override
    public boolean borrarChatConUsuario(Usuario remitente, Usuario destinatario) throws Exception {
        return mensajeriaRepo.borrarEntre(remitente, destinatario);
    }
}
