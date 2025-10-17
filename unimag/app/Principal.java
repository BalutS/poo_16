package org.unimag.app;

import javafx.application.Application;
import javafx.stage.Stage;
import org.unimag.vista.gestor.VistaGestor;

public class Principal extends Application{
    
    private VistaGestor vistaGestor;
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        vistaGestor = new VistaGestor(stage);
        stage.setTitle("Gestor de Biblioteca");
        
        stage.show();
    }
}