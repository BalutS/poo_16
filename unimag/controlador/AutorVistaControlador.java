package org.unimag.controlador;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.unimag.vista.autor.VistaAutorCrear;
import org.unimag.vista.autor.VistaAutorListar;

public class AutorVistaControlador {

    public static Pane crearAutor(Stage stage, double ancho, double alto) {
        return new VistaAutorCrear(stage, ancho, alto);
    }

    public static Pane listarAutor(Stage stage, double ancho, double alto) {
        return new VistaAutorListar(stage, ancho, alto);
    }
}