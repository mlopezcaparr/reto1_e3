package com.banana.bananawhatsapp.servicios;

import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Usuario;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ServicioUsuarioImpl implements IServicioUsuarios {
    @Override
    public Usuario obtener(int id) throws UsuarioException {
        return null;
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) throws UsuarioException {
        return null;
    }

    @Override
    public boolean borrarUsuario(Usuario usuario) throws UsuarioException {
        return false;
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) throws UsuarioException {
        return null;
    }

    @Override
    public Set<Usuario> obtenerPosiblesDesinatarios(Usuario usuario, int max) throws UsuarioException {
        return Set.of();
    }
}
