package com.grupo2.cinemautn.models.usuarios;

import com.grupo2.cinemautn.models.contenido.Contenido;
import com.grupo2.cinemautn.models.contenido.Calificacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario {
    //atributos
    private static int contador = 0;
    private int idUsuario;
    private String nombre;
    private String email;
    private String contrasena;
    private boolean estado;
    private Rol rol;
    private ListaFavoritos listaFavoritos;

    //constructor
    public Usuario(String nombre, String email, String contrasena, Rol rol) {
        contador ++;
        this.idUsuario = contador;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.estado = true; //activo por defecto
        this.rol = rol;
        this.listaFavoritos = new ListaFavoritos();
    }

    public Usuario() {
        contador ++;
        this.idUsuario = contador;
        this.estado = true; //activo por defecto
        listaFavoritos = new ListaFavoritos();
    }

    //getters y setters

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public ListaFavoritos getListaFavoritos() {
        return listaFavoritos;
    }

    public void setListaFavoritos(ListaFavoritos listaFavoritos) {
        this.listaFavoritos = listaFavoritos;
    }

    public void setIdUsuario(int id) {
        this.idUsuario = id;
        if (id > contador) contador = id;
    }

    //otros métodos

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", estado=" + estado +
                ", rol=" + rol +
                ", listaFavoritos=" + listaFavoritos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(email, usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }

    public void agregarAFavoritos(Contenido contenido) {
        if (contenido == null) return;
        if (this.listaFavoritos == null) this.listaFavoritos = new ListaFavoritos();
        this.listaFavoritos.agregarFavorito(contenido);
    }

    public void eliminarDeFavoritos(Contenido contenido) {
        if (contenido == null || this.listaFavoritos == null) return;
        this.listaFavoritos.eliminarFavorito(contenido);
    }

    public List<Contenido> verFavoritos() {
        if (this.listaFavoritos == null) return new ArrayList<>();
        return new ArrayList<>(this.listaFavoritos.getFavoritos());
    }

    public String verContenido(Contenido contenido) {
        if (contenido == null) return "Contenido no disponible";
        return contenido.toString();
    }

    public void calificar(Contenido contenido, double estrellas) {
        if (contenido == null) return;
        // Crear una calificación temporal con ids por defecto (0)
        Calificacion c = new Calificacion(0, this.idUsuario, contenido.getId(), estrellas, true);
        contenido.agregarCalificacion(c);
    }
}
