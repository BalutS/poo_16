package org.unimag.controlador;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.unimag.vista.libro.VistaLibroCrear;
import org.unimag.vista.libro.VistaLibroListar;

public class LibroVistaControlador {

    public static Pane crearLibro(Stage stage, double ancho, double alto) {
        return new VistaLibroCrear(stage, ancho, alto);
    }

    public static Pane listarLibro(Stage stage, double ancho, double alto) {
        return new VistaLibroListar(stage, ancho, alto);
    }
}