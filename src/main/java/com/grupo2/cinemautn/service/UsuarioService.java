package com.grupo2.cinemautn.service;

import com.grupo2.cinemautn.interfaces.IGestionable;
import com.grupo2.cinemautn.models.usuarios.Usuario;
import com.grupo2.cinemautn.persistence.GestoraUsuariosJSON;

import java.util.*;

public class UsuarioService implements IGestionable<Usuario> {
    private Set<Usuario> usuarios = new HashSet<>();
    private GestoraUsuariosJSON gestora = new GestoraUsuariosJSON();

    public UsuarioService() {
        // cargar desde archivo si existe
        List<Usuario> lista = gestora.cargarPorDefecto();
        if (lista != null) {
            usuarios.addAll(lista);
        }
    }

    private void persistir() {
        // convertir set a ArrayList para la gestora
        gestora.guardarPorDefecto(new ArrayList<>(usuarios));
    }

    @Override
    public void crear(Usuario u) {
        if (u == null) return;
        usuarios.add(u);
        persistir();
    }

    @Override
    public Usuario leer(int id) {
        for (Usuario u : usuarios) {
            if (u.getIdUsuario() == id) return u;
        }
        return null;
    }

    @Override
    public void actualizar(Usuario u) {
        if (u == null) return;
        Usuario existente = leer(u.getIdUsuario());
        if (existente != null) {
            usuarios.remove(existente);
            usuarios.add(u);
            persistir();
        }
    }

    @Override
    public void eliminar(int id) {
        Usuario u = leer(id);
        if (u != null) {
            usuarios.remove(u);
            persistir();
        }
    }

    @Override
    public List<Usuario> listar() {
        return new ArrayList<>(usuarios);
    }

    public Usuario buscarPorCorreo(String correo) {
        if (correo == null) return null;
        for (Usuario u : usuarios) {
            if (correo.equalsIgnoreCase(u.getEmail())) return u;
        }
        return null;
    }
}
