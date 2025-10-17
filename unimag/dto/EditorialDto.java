package org.unimag.dto;

public class EditorialDto {

    private Integer idEditorial;
    private String nombreEditorial;
    private String PaisEditorial;
    private Short formatoEditorial;
    private Short cantidadLibrosEditorial;

    public EditorialDto() {
    }

    public EditorialDto(Integer idEditorial, String nombreEditorial, String PaisEditorial, Short formatoEditorial, Short cantidadLibrosEditorial) {
        this.idEditorial = idEditorial;
        this.nombreEditorial = nombreEditorial;
        this.PaisEditorial = PaisEditorial;
        this.formatoEditorial = formatoEditorial;
        this.cantidadLibrosEditorial = cantidadLibrosEditorial;
    }

    public Integer getIdEditorial() {
        return idEditorial;
    }

    public void setIdEditorial(Integer idEditorial) {
        this.idEditorial = idEditorial;
    }

    public String getNombreEditorial() {
        return nombreEditorial;
    }

    public void setNombreEditorial(String nombreEditorial) {
        this.nombreEditorial = nombreEditorial;
    }

    public String getPaisEditorial() {
        return PaisEditorial;
    }

    public void setPaisEditorial(String PaisEditorial) {
        this.PaisEditorial = PaisEditorial;
    }

    public Short getFormatoEditorial() {
        return formatoEditorial;
    }

    public void setFormatoEditorial(Short formatoEditorial) {
        this.formatoEditorial = formatoEditorial;
    }

    public Short getCantidadLibrosEditorial() {
        return cantidadLibrosEditorial;
    }

    public void setCantidadLibrosEditorial(Short cantidadLibrosEditorial) {
        this.cantidadLibrosEditorial = cantidadLibrosEditorial;
    }

    @Override
    public String toString() {
        return nombreEditorial;
    }
}