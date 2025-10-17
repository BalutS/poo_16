package org.unimag.controlador;

import java.util.List;
import org.unimag.dto.EditorialDto;
import org.unimag.servicio.EditorialServicio;

public class EditorialControladorListar {

    public static List<EditorialDto> obtenerEditoriales() {
        EditorialServicio miDao = new EditorialServicio();
        List<EditorialDto> arreglo = miDao.selectFrom();
        return arreglo;
    }

    public static int obtenerCantidadEditoriales() {
        EditorialServicio miDao = new EditorialServicio();
        int cantidad = miDao.numRows();
        return cantidad;
    }

}