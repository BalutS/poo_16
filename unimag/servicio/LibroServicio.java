package org.unimag.servicio;

import com.poo.persistence.NioFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.unimag.api.ApiOperacionBD;
import org.unimag.dto.AutorDto;
import org.unimag.dto.EditorialDto;
import org.unimag.dto.LibroDto;
import org.unimag.modelo.Autor;
import org.unimag.modelo.Editorial;
import org.unimag.modelo.Libro;
import org.unimag.recurso.constante.Persistencia;

public class LibroServicio implements ApiOperacionBD<LibroDto, Integer> {

    private NioFile miArchivo;
    private String nombrePersistencia;

    public LibroServicio() {
        nombrePersistencia = Persistencia.NOMBRE_LIBRO;
        try {
            miArchivo = new NioFile(nombrePersistencia);
        } catch (IOException ex) {
            Logger.getLogger(LibroServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getSerial() {
        int id = 0;
        try {
            id = miArchivo.ultimoCodigo() + 1;
        } catch (IOException ex) {
            Logger.getLogger(LibroServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    @Override
    public LibroDto insertInto(LibroDto dto, String ruta) {
        EditorialDto editorialDto = dto.getIdEditorial();
        Editorial editorial = new Editorial();
        editorial.setIdEditorial(editorialDto.getIdEditorial());
        editorial.setNombreEditorial(editorialDto.getNombreEditorial());
        editorial.setPaisEditorial(editorialDto.getPaisEditorial());
        editorial.setFormatoEditorial(editorialDto.getFormatoEditorial());
        editorial.setCantidadLibrosEditorial(editorialDto.getCantidadLibrosEditorial());

        AutorDto autorDto = dto.getIdAutor();
        Autor autor = new Autor();
        autor.setIdAutor(autorDto.getIdAutor());
        autor.setNombreAutor(autorDto.getNombreAutor());
        autor.setGeneroAutor(autorDto.getGeneroAutor());
        autor.setCantidadLibrosAutor(autorDto.getCantidadLibrosAutor());

        Libro objLibro = new Libro();
        objLibro.setIdLibro(getSerial());
        objLibro.setNombreLibro(dto.getNombreLibro());
        objLibro.setPrecioLibro(dto.getPrecioLibro());
        objLibro.setAnioLibro(dto.getAnioLibro());
        objLibro.setIdEditorial(editorial);
        objLibro.setIdAutor(autor);

        String filaGrabar
                = objLibro.getIdLibro() + Persistencia.SEPARADOR_COLUMNAS
                + objLibro.getNombreLibro() + Persistencia.SEPARADOR_COLUMNAS
                + String.format("%.2f", objLibro.getPrecioLibro()) + Persistencia.SEPARADOR_COLUMNAS
                + objLibro.getAnioLibro() + Persistencia.SEPARADOR_COLUMNAS
                + objLibro.getIdEditorial().getIdEditorial() + Persistencia.SEPARADOR_COLUMNAS
                + objLibro.getIdAutor().getIdAutor();
        if (miArchivo.agregarRegistro(filaGrabar)) {
            dto.setIdLibro(objLibro.getIdLibro());
            return dto;
        }

        return null;
    }

    @Override
    public List<LibroDto> selectFrom() {
        List<LibroDto> arregloLibroDtos = new ArrayList<>();
        List<String> arregloDatos = miArchivo.obtenerDatos();
        EditorialServicio editorialServicio = new EditorialServicio();
        AutorServicio autorServicio = new AutorServicio();

        for (String cadena : arregloDatos) {
            try {
                cadena = cadena.replace("@", "");
                String[] columnas = cadena.split(Persistencia.SEPARADOR_COLUMNAS);

                int codigo = Integer.parseInt(columnas[0].trim());
                String nombre = columnas[1].trim();
                Double precio = Double.valueOf(columnas[2].trim());
                Short anio = Short.parseShort(columnas[3].trim());
                Integer idEditorial = Integer.parseInt(columnas[4].trim());
                Integer idAutor = Integer.parseInt(columnas[5].trim());

               
                LibroDto libroDto = new LibroDto(codigo, nombre, precio, anio, null, null);

          

                arregloLibroDtos.add(libroDto);
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        return arregloLibroDtos;
    }

    @Override
    public int numRows() {
        int cantidad = 0;
        try {
            cantidad = miArchivo.cantidadFilas();
        } catch (IOException ex) {
            Logger.getLogger(LibroServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cantidad;
    }

    public Boolean delectFrom(Integer coidgo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LibroDto updateSet(Integer codigo, LibroDto objeto, String ruta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LibroDto getOne(Integer codigo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int countByEditorial(Integer idEditorial) {
        int count = 0;
        List<String> arregloDatos = miArchivo.obtenerDatos();
        for (String cadena : arregloDatos) {
            try {
                cadena = cadena.replace("@", "");
                String[] columnas = cadena.split(Persistencia.SEPARADOR_COLUMNAS);
                if (Integer.parseInt(columnas[4].trim()) == idEditorial) {
                    count++;
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        return count;
    }
    
    public int countByAutor(Integer idAutor) {
        int count = 0;
        List<String> arregloDatos = miArchivo.obtenerDatos();
        for (String cadena : arregloDatos) {
            try {
                cadena = cadena.replace("@", "");
                String[] columnas = cadena.split(Persistencia.SEPARADOR_COLUMNAS);
                if (Integer.parseInt(columnas[5].trim()) == idAutor) {
                    count++;
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        return count;
    }

    @Override
    public Boolean deleteFrom(Integer codigo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}