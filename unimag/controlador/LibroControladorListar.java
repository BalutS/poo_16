package org.unimag.controlador;

import java.util.List;
import org.unimag.dto.LibroDto;
import org.unimag.servicio.LibroServicio;

public class LibroControladorListar {

    public static List<LibroDto> obtenerLibros() {
        LibroServicio miDao = new LibroServicio();
        List<LibroDto> arreglo = miDao.selectFrom();
        return arreglo;
    }

    public static int obtenerCantidadLibros() {
        LibroServicio miDao = new LibroServicio();
        int cantidad = miDao.numRows();
        return cantidad;
    }

}