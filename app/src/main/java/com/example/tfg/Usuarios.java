package com.example.tfg;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Usuarios {
    String id, nombre, email, password, foto;
    List<String> favoritos;

    public Usuarios() {
    }

    public Usuarios(String id, String nombre, String email, String password, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.foto = foto;
        this.favoritos = new List<String>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(@Nullable Object o) {
                return false;
            }

            @NonNull
            @Override
            public Iterator<String> iterator() {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(@NonNull T[] ts) {
                return null;
            }

            @Override
            public boolean add(String s) {
                return false;
            }

            @Override
            public boolean remove(@Nullable Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public boolean addAll(@NonNull Collection<? extends String> collection) {
                return false;
            }

            @Override
            public boolean addAll(int i, @NonNull Collection<? extends String> collection) {
                return false;
            }

            @Override
            public boolean removeAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public boolean retainAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean equals(@Nullable Object o) {
                return false;
            }

            @Override
            public int hashCode() {
                return 0;
            }

            @Override
            public String get(int i) {
                return null;
            }

            @Override
            public String set(int i, String s) {
                return null;
            }

            @Override
            public void add(int i, String s) {

            }

            @Override
            public String remove(int i) {
                return null;
            }

            @Override
            public int indexOf(@Nullable Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(@Nullable Object o) {
                return 0;
            }

            @NonNull
            @Override
            public ListIterator<String> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<String> listIterator(int i) {
                return null;
            }

            @NonNull
            @Override
            public List<String> subList(int i, int i1) {
                return null;
            }
        };
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void anadirFavorito(String id){
        this.favoritos.add(id);
    }

    public void eliminarFavorito(String id){
        this.favoritos.remove(id);
    }
}
