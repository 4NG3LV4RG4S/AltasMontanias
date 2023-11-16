package com.example.altasmontaas;

public class Imagen {
    private String titulo;
    private String descripcion;
    private int resourceId;

    public Imagen(String titulo, String descripcion, int resourceId) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.resourceId = resourceId;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getResourceId() {
        return resourceId;
    }
}
