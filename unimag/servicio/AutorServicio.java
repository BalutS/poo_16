package org.unimag.servicio;

import com.poo.persistence.NioFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.unimag.api.ApiOperacionBD;
import org.unimag.dto.AutorDto;
import org.unimag.modelo.Autor;
import org.unimag.recurso.constante.Persistencia;

public class AutorServicio implements ApiOperacionBD<AutorDto, Integer> {

    private NioFile miArchivo;
    private String nombrePersistencia;

    public AutorServicio() {
        nombrePersistencia = Persistencia.NOMBRE_AUTOR;
        try {
            miArchivo = new NioFile(nombrePersistencia);
        } catch (IOException ex) {
            Logger.getLogger(AutorServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getSerial() {
        int id = 0;
        try {
            id = miArchivo.ultimoCodigo() + 1;
        } catch (IOException ex) {
            Logger.getLogger(AutorServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    @Override
    public AutorDto insertInto(AutorDto dto, String ruta) {
        Autor objAutor = new Autor();
        objAutor.setIdAutor(getSerial());
        objAutor.setNombreAutor(dto.getNombreAutor());
        objAutor.setGeneroAutor(dto.getGeneroAutor());

        String filaGrabar
                = objAutor.getIdAutor() + Persistencia.SEPARADOR_COLUMNAS
                + objAutor.getNombreAutor() + Persistencia.SEPARADOR_COLUMNAS
                + objAutor.getGeneroAutor();
        if (miArchivo.agregarRegistro(filaGrabar)) {
            dto.setIdAutor(objAutor.getIdAutor());
            return dto;
        }

        return null;
    }

    @Override
    public List<AutorDto> selectFrom() {
        List<AutorDto> arregloAutorDtos = new ArrayList<>();
        List<String> arregloDatos = miArchivo.obtenerDatos();

        for (String cadena : arregloDatos) {
            try {
                cadena = cadena.replace("@", "");
                String[] columnas = cadena.split(Persistencia.SEPARADOR_COLUMNAS);

                int codigo = Integer.parseInt(columnas[0].trim());
                String nombre = columnas[1].trim();
                Boolean genero = Boolean.valueOf(columnas[2].trim());
                LibroServicio libroServicio = new LibroServicio();
                short cantidadLibros = (short) libroServicio.countByAutor(codigo);

                arregloAutorDtos.add(new AutorDto(codigo, nombre, genero, cantidadLibros));
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        return arregloAutorDtos;
    }

    @Override
    public int numRows() {
        int cantidad = 0;
        try {
            cantidad = miArchivo.cantidadFilas();
        } catch (IOException ex) {
            Logger.getLogger(AutorServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cantidad;
    }

    public Boolean delectFrom(Integer coidgo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AutorDto updateSet(Integer codigo, AutorDto objeto, String ruta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AutorDto getOne(Integer codigo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean deleteFrom(Integer codigo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}