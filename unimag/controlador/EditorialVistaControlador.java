package org.unimag.controlador;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.unimag.vista.editorial.VistaEditorialCrear;
import org.unimag.vista.editorial.VistaEditorialListar;

public class EditorialVistaControlador {

    public static Pane crearEditorial(Stage stage, double ancho, double alto) {
        return new VistaEditorialCrear(stage, ancho, alto);
    }

    public static Pane listarEditorial(Stage stage, double ancho, double alto) {
        return new VistaEditorialListar(stage, ancho, alto);
    }
}