package com.banana.bananawhatsapp.controladores;

import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import com.banana.bananawhatsapp.servicios.IServicioMensajeria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.sql.SQLException;
import java.util.List;

@Controller
public class ControladorMensajes {
    @Autowired
    private IServicioMensajeria servicioMensajeria;

    public boolean enviarMensaje(Integer remitente, Integer destinatario, String texto) {
        try {
            Usuario uRemitente = new Usuario();
            uRemitente.setId(remitente);
            Usuario uDestinatario = new Usuario();
            uDestinatario.setId(destinatario);

            Mensaje mensaje = servicioMensajeria.enviarMensaje(uRemitente, uDestinatario, texto);
            System.out.println("Mensaje enviado: " + mensaje);
            return true;
        } catch (Exception e) {
            System.out.println("Ha habido un error: " + e.getMessage());
            throw new RuntimeException("Error al enviar mensaje", e);
        }

    }

    public boolean mostrarChat(Integer remitente, Integer destinatario) {
        try {
            Usuario uRemitente = new Usuario();
            uRemitente.setId(remitente);
            Usuario uDestinatario = new Usuario();
            uDestinatario.setId(destinatario);

            List<Mensaje> mensajes = servicioMensajeria.mostrarChatConUsuario(uRemitente, uDestinatario);
            if (mensajes != null && mensajes.size() > 0) {
                System.out.println("Mensajes entre: " + remitente + " y " + destinatario);
                for (Mensaje mensaje : mensajes) {
                    System.out.println(mensaje);
                }
            } else {
                System.out.println("NO hay mensajes entre: " + remitente + " y " + destinatario);
            }
            return true;
        } catch (Exception e) {
            System.out.println("Ha habido un error: " + e.getMessage());
            throw new RuntimeException("Error al mostrar el chat entre dos usuarios", e);
        }

    }

    public boolean eliminarChatConUsuario(Integer remitente, Integer destinatario) {
        try {
            Usuario uRemitente = new Usuario();
            uRemitente.setId(remitente);
            Usuario uDestinatario = new Usuario();
            uDestinatario.setId(destinatario);

            boolean isOK = servicioMensajeria.borrarChatConUsuario(uRemitente, uDestinatario);
            if (isOK) {
                System.out.println("Mensajes borrados entre: " + remitente + " y " + destinatario);
            } else {
                System.out.println("NO se han borrado mensajes entre: " + remitente + " y " + destinatario);
            }
            return isOK;
        } catch (Exception e) {
            System.out.println("Ha habido un error: " + e.getMessage());
            throw new RuntimeException("Error al eliminar el chat", e);
        }

    }


}
