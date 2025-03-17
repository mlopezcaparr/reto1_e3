package com.banana.bananawhatsapp.servicios;

import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Usuario;
import com.banana.bananawhatsapp.persistencia.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ServicioUsuarioImpl implements IServicioUsuarios {

    @Autowired
    IUsuarioRepository usuarioRepo;

    @Override
    public Usuario obtener(int id) throws UsuarioException {
        try {
            return usuarioRepo.obtener(id);
        } catch (Exception e) {
            throw new UsuarioException();
        }
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) throws UsuarioException {
        try {
            return usuarioRepo.crear(usuario);
        } catch (Exception e) {
            throw new UsuarioException();
        }
    }

    @Override
    public boolean borrarUsuario(Usuario usuario) throws UsuarioException {
        try {
            return usuarioRepo.borrar(usuario);
        } catch (Exception e) {
            throw new UsuarioException();
        }
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) throws UsuarioException {
        try {
            return usuarioRepo.actualizar(usuario);
        } catch (Exception e) {
            throw new UsuarioException();
        }
    }

    @Override
    public Set<Usuario> obtenerPosiblesDesinatarios(Usuario usuario, int max) throws UsuarioException {
        try {
            return usuarioRepo.obtenerPosiblesDestinatarios(usuario.getId(), max);
        } catch (Exception e) {
            throw new UsuarioException();
        }
    }
}
