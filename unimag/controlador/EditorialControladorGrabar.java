package org.unimag.controlador;

import org.unimag.dto.EditorialDto;
import org.unimag.servicio.EditorialServicio;

public class EditorialControladorGrabar {
    
    public static Boolean crearEditorial (EditorialDto dto){
        Boolean correcto = false;

        EditorialServicio editorialServicio = new EditorialServicio();
        EditorialDto dtoRespuesta;
        dtoRespuesta = editorialServicio.insertInto(dto, "");
        if (dtoRespuesta!=null) {
            correcto = true;

        }
        return correcto;



    }

}