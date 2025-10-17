package org.unimag.dto;

public class AutorDto {

    private Integer idAutor;
    private String nombreAutor;
    private Boolean generoAutor;
    private Short cantidadLibrosAutor;

    public AutorDto() {
    }

    public AutorDto(Integer idAutor, String nombreAutor, Boolean generoAutor, Short cantidadLibrosAutor) {
        this.idAutor = idAutor;
        this.nombreAutor = nombreAutor;
        this.generoAutor = generoAutor;
        this.cantidadLibrosAutor = cantidadLibrosAutor;
    }

    public Integer getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Integer idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public Boolean getGeneroAutor() {
        return generoAutor;
    }

    public void setGeneroAutor(Boolean generoAutor) {
        this.generoAutor = generoAutor;
    }

    public Short getCantidadLibrosAutor() {
        return cantidadLibrosAutor;
    }

    public void setCantidadLibrosAutor(Short cantidadLibrosAutor) {
        this.cantidadLibrosAutor = cantidadLibrosAutor;
    }

    @Override
    public String toString() {
        return nombreAutor;
    }
}