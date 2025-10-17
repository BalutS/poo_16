package org.unimag.vista.gestor;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.unimag.controlador.EditorialVistaControlador;
import org.unimag.controlador.AutorVistaControlador;
import org.unimag.controlador.LibroVistaControlador;
import org.unimag.recurso.constante.Configuracion;

public class VistaCabecera extends HBox {

    private final int menuAncho = 150;
    private final int menuAlto = 35;
    private final int menuEspacio = 10;

    private final Stage miEscenario;
    private final BorderPane miPanelPrincipal;

    public VistaCabecera(Stage esce, BorderPane bpan, double altoCabecera) {
        miEscenario = esce;
        miPanelPrincipal = bpan;
        setAlignment(Pos.CENTER_LEFT);
        setSpacing(menuEspacio);
        setPadding(new Insets(0, 20, 0, 20));
        setPrefHeight(altoCabecera);
        setStyle(Configuracion.CABECERA_ESTILO_FONDO);

        crearMenus();

    }

    private void agregarBotonMenu(ComboBox<String> miMenu) {
        miMenu.setCursor(Cursor.HAND);
        miMenu.setPrefWidth(menuAncho);
        miMenu.setPrefHeight(menuAlto);
        getChildren().add(miMenu);
    }

    private void crearMenus() {
        menuEditorial();
        menuAutor();
        menuLibro();
    }

    private void menuEditorial() {
        ComboBox<String> miBoton = new ComboBox<>();
        miBoton.getItems().addAll("Editorial", "Crear", "Listar");
        miBoton.getSelectionModel().select(0);
        miBoton.setOnAction((e) -> {
            String seleccion = miBoton.getSelectionModel().getSelectedItem();
            if ("Crear".equals(seleccion)) {
                miPanelPrincipal.setCenter(
                        EditorialVistaControlador.crearEditorial(
                                miEscenario,
                                Configuracion.ANCHO_APP,
                                Configuracion.ALTO_CABECERA)
                );
            } else if ("Listar".equals(seleccion)) {
                miPanelPrincipal.setCenter(
                        EditorialVistaControlador.listarEditorial(
                                miEscenario,
                                Configuracion.ANCHO_APP,
                                Configuracion.ALTO_CABECERA)
                );
            }
        });
        agregarBotonMenu(miBoton);
    }

    private void menuAutor() {
        ComboBox<String> miBoton = new ComboBox<>();
        miBoton.getItems().addAll("Autor", "Crear", "Listar");
        miBoton.getSelectionModel().select(0);
        miBoton.setOnAction((e) -> {
            String seleccion = miBoton.getSelectionModel().getSelectedItem();
            if ("Crear".equals(seleccion)) {
                miPanelPrincipal.setCenter(
                        AutorVistaControlador.crearAutor(
                                miEscenario,
                                Configuracion.ANCHO_APP,
                                Configuracion.ALTO_CABECERA)
                );
            } else if ("Listar".equals(seleccion)) {
                miPanelPrincipal.setCenter(
                        AutorVistaControlador.listarAutor(
                                miEscenario,
                                Configuracion.ANCHO_APP,
                                Configuracion.ALTO_CABECERA)
                );
            }
        });
        agregarBotonMenu(miBoton);
    }

    private void menuLibro() {
        ComboBox<String> miBoton = new ComboBox<>();
        miBoton.getItems().addAll("Libro", "Crear", "Listar");
        miBoton.getSelectionModel().select(0);
        miBoton.setOnAction((e) -> {
            String seleccion = miBoton.getSelectionModel().getSelectedItem();
            if ("Crear".equals(seleccion)) {
                miPanelPrincipal.setCenter(
                        LibroVistaControlador.crearLibro(
                                miEscenario,
                                Configuracion.ANCHO_APP,
                                Configuracion.ALTO_CABECERA)
                );
            } else if ("Listar".equals(seleccion)) {
                miPanelPrincipal.setCenter(
                        LibroVistaControlador.listarLibro(
                                miEscenario,
                                Configuracion.ANCHO_APP,
                                Configuracion.ALTO_CABECERA)
                );
            }
        });
        agregarBotonMenu(miBoton);
    }

}