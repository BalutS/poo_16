package org.unimag.controlador;

import java.util.List;
import org.unimag.dto.AutorDto;
import org.unimag.servicio.AutorServicio;

public class AutorControladorListar {

    public static List<AutorDto> obtenerAutores() {
        AutorServicio miDao = new AutorServicio();
        List<AutorDto> arreglo = miDao.selectFrom();
        return arreglo;
    }

    public static int obtenerCantidadAutores() {
        AutorServicio miDao = new AutorServicio();
        int cantidad = miDao.numRows();
        return cantidad;
    }

}