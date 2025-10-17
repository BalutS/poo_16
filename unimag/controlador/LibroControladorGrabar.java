package org.unimag.controlador;

import org.unimag.dto.LibroDto;
import org.unimag.servicio.LibroServicio;

public class LibroControladorGrabar {
    
    public static Boolean crearLibro (LibroDto dto){
        Boolean correcto = false;

        LibroServicio libroServicio = new LibroServicio();
        LibroDto dtoRespuesta;
        dtoRespuesta = libroServicio.insertInto(dto, "");
        if (dtoRespuesta!=null) {
            correcto = true;

        }
        return correcto;



    }

}