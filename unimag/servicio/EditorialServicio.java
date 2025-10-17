package org.unimag.servicio;

import com.poo.persistence.NioFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.unimag.api.ApiOperacionBD;
import org.unimag.dto.EditorialDto;
import org.unimag.modelo.Editorial;
import org.unimag.recurso.constante.Persistencia;

public class EditorialServicio implements ApiOperacionBD<EditorialDto, Integer> {

    private NioFile miArchivo;
    private String nombrePersistencia;

    public EditorialServicio() {
        nombrePersistencia = Persistencia.NOMBRE_EDITORIAL;
        try {
            miArchivo = new NioFile(nombrePersistencia);
        } catch (IOException ex) {
            Logger.getLogger(EditorialServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getSerial() {
        int id = 0;
        try {
            id = miArchivo.ultimoCodigo() + 1;
        } catch (IOException ex) {
            Logger.getLogger(EditorialServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    @Override
    public EditorialDto insertInto(EditorialDto dto, String ruta) {
        Editorial objEditorial = new Editorial();
        objEditorial.setIdEditorial(getSerial());
        objEditorial.setNombreEditorial(dto.getNombreEditorial());
        objEditorial.setPaisEditorial(dto.getPaisEditorial());
        objEditorial.setFormatoEditorial(dto.getFormatoEditorial());

        String filaGrabar
                = objEditorial.getIdEditorial() + Persistencia.SEPARADOR_COLUMNAS
                + objEditorial.getNombreEditorial() + Persistencia.SEPARADOR_COLUMNAS
                + objEditorial.getPaisEditorial() + Persistencia.SEPARADOR_COLUMNAS
                + objEditorial.getFormatoEditorial();
        if (miArchivo.agregarRegistro(filaGrabar)) {
            dto.setIdEditorial(objEditorial.getIdEditorial());
            return dto;
        }

        return null;
    }

    @Override
    public List<EditorialDto> selectFrom() {
        List<EditorialDto> arregloEditorialDtos = new ArrayList<>();
        List<String> arregloDatos = miArchivo.obtenerDatos();

        for (String cadena : arregloDatos) {
            try {
                cadena = cadena.replace("@", "");
                String[] columnas = cadena.split(Persistencia.SEPARADOR_COLUMNAS);

                int codigo = Integer.parseInt(columnas[0].trim());
                String nombre = columnas[1].trim();
                String pais = columnas[2].trim();
                Short formato = Short.parseShort(columnas[3].trim());
                LibroServicio libroServicio = new LibroServicio();
                short cantidadLibros = (short) libroServicio.countByEditorial(codigo);

                arregloEditorialDtos.add(new EditorialDto(codigo, nombre, pais, formato, cantidadLibros));
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        return arregloEditorialDtos;
    }

    @Override
    public int numRows() {
        int cantidad = 0;
        try {
            cantidad = miArchivo.cantidadFilas();
        } catch (IOException ex) {
            Logger.getLogger(EditorialServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cantidad;
    }

    public Boolean delectFrom(Integer coidgo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public EditorialDto updateSet(Integer codigo, EditorialDto objeto, String ruta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public EditorialDto getOne(Integer codigo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean deleteFrom(Integer codigo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}