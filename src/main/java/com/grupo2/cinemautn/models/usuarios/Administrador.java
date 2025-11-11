package com.grupo2.cinemautn.models.usuarios;

import com.grupo2.cinemautn.service.ContenidoService;
import com.grupo2.cinemautn.models.contenido.Contenido;

public class Administrador extends Usuario {

    public Administrador() {
        super();
        this.setRol(Rol.ADMIN);
    }

    public Administrador(String nombre, String email, String contrasena) {
        super(nombre, email, contrasena, Rol.ADMIN);
    }

    // Gestionar contenido: si 'activo' true => crear o actualizar segun exista id, si false => eliminar
    public void gestionarContenido(ContenidoService contenidoService, Contenido contenido, boolean activo) {
        if (contenidoService == null || contenido == null) return;
        try {
            if (activo) {
                // si existe, actualizar; si no, crear
                try {
                    Contenido existente = contenidoService.leer(contenido.getId());
                    contenidoService.actualizar(contenido);
                } catch (Exception e) {
                    // si no existe, crear
                    contenidoService.crear(contenido);
                }
            } else {
                contenidoService.eliminar(contenido.getId());
            }
        } catch (Exception ignored) {
        }
    }
}
