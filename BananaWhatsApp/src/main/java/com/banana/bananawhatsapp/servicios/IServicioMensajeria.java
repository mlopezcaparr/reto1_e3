package com.banana.bananawhatsapp.servicios;

import com.banana.bananawhatsapp.exceptions.MensajeException;
import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface IServicioMensajeria {

    public Mensaje enviarMensaje(Usuario remitente, Usuario destinatario, String texto) throws UsuarioException, MensajeException, SQLException;

    public List<Mensaje> mostrarChatConUsuario(Usuario remitente, Usuario destinatario) throws UsuarioException, MensajeException, SQLException;

    public boolean borrarChatConUsuario(Usuario remitente, Usuario destinatario) throws Exception;

}
