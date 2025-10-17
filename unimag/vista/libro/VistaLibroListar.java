package org.unimag.vista.libro;

import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.unimag.controlador.LibroControladorListar;
import org.unimag.dto.LibroDto;
import org.unimag.recurso.constante.Configuracion;
import org.unimag.recurso.utilidad.Marco;

public class VistaLibroListar extends StackPane {

    private final Rectangle marco;
    private final Stage miEscenario;
    private final VBox cajaVertical;
    private final TableView<LibroDto> miTabla;

    private static final String ESTILO_CENTRAR
            = "-fx-alignment: CENTER;";
    private static final String ESTILO_DERECHA
            = "-fx-alignment: CENTER-RIGHT;";
    private static final String ESTILO_IZQUIERDA
            = "-fx-alignment: CENTER-LEFT;";

    public VistaLibroListar(
            Stage ventanaPadre, double ancho, double alto
    ) {
        setAlignment(Pos.CENTER);
        miEscenario = ventanaPadre;
        marco = Marco.crear(
                miEscenario,
                Configuracion.MARCO_ALTO_PORCENTAJE,
                Configuracion.MARCO_ANCHO_PORCENTAJE,
                Configuracion.DEGRADE_ARREGLO,
                Configuracion.DEGRADDE_BORDE
        );
        miTabla = new TableView<>();
        cajaVertical = new VBox(35);
        getChildren().add(marco);

        configurarCajaVertical();
        crearTitulo();
        crearTabla();

    }

    private void configurarCajaVertical() {
        cajaVertical.setAlignment(Pos.TOP_CENTER);
        cajaVertical.prefWidthProperty()
                .bind(miEscenario.widthProperty());
        cajaVertical.prefHeightProperty()
                .bind(miEscenario.heightProperty());
    }

    private void crearTitulo() {
        Region bloqueSeparador = new Region();
        bloqueSeparador.prefHeightProperty().bind(
                miEscenario.heightProperty().multiply(0.05));
        int cant = LibroControladorListar.obtenerCantidadLibros();
        Text titulo = new Text("Lista de libros (" + cant + ")");
        titulo.setFill(Color.web("#de3d37"));
        titulo.setFont(Font.font("Verdana", FontWeight.BOLD, 24));

        cajaVertical.getChildren().addAll(bloqueSeparador, titulo);
    }

    private TableColumn<LibroDto, Integer> crearColumnaCodigo() {
        TableColumn<LibroDto, Integer> columna = new TableColumn<>("cod");
        columna.setCellValueFactory(new PropertyValueFactory<>("idLibro"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.10));
        columna.setStyle(ESTILO_CENTRAR);
        return columna;
    }

    private TableColumn<LibroDto, String> crearColumnaNombre() {
        TableColumn<LibroDto, String> columna = new TableColumn<>("Nombre");
        columna.setCellValueFactory(new PropertyValueFactory<>("nombreLibro"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.20));
        columna.setStyle(ESTILO_IZQUIERDA);
        return columna;
    }

    private TableColumn<LibroDto, String> crearColumnaPrecio() {
        TableColumn<LibroDto, String> columna = new TableColumn<>("Precio");
        columna.setCellValueFactory(obj -> {
            String precio = String.format("%.2f", obj.getValue().getPrecioLibro());
            return new SimpleStringProperty(precio);
        });
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.15));
        columna.setStyle(ESTILO_DERECHA);
        return columna;
    }

    private TableColumn<LibroDto, Short> crearColumnaAnio() {
        TableColumn<LibroDto, Short> columna = new TableColumn<>("Año");
        columna.setCellValueFactory(new PropertyValueFactory<>("anioLibro"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.10));
        columna.setStyle(ESTILO_CENTRAR);
        return columna;
    }

    private TableColumn<LibroDto, String> crearColumnaEditorial() {
        TableColumn<LibroDto, String> columna = new TableColumn<>("Editorial");
        columna.setCellValueFactory(obj -> {
            String editorial = obj.getValue().getIdEditorial().getNombreEditorial() + " (" + obj.getValue().getIdEditorial().getPaisEditorial() + ")";
            return new SimpleStringProperty(editorial);
        });
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.25));
        columna.setStyle(ESTILO_IZQUIERDA);
        return columna;
    }

    private TableColumn<LibroDto, String> crearColumnaAutor() {
        TableColumn<LibroDto, String> columna = new TableColumn<>("Autor");
        columna.setCellValueFactory(obj -> {
            String genero = obj.getValue().getIdAutor().getGeneroAutor() ? "Masculino" : "Femenino";
            String autor = obj.getValue().getIdAutor().getNombreAutor() + " (" + genero + ")";
            return new SimpleStringProperty(autor);
        });
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.20));
        columna.setStyle(ESTILO_IZQUIERDA);
        return columna;
    }

    private void configurarColumnas() {
        miTabla.getColumns().addAll(
                List.of(
                        crearColumnaCodigo(),
                        crearColumnaNombre(),
                        crearColumnaPrecio(),
                        crearColumnaAnio(),
                        crearColumnaEditorial(),
                        crearColumnaAutor()
                )
        );
    }

    private void crearTabla() {
        configurarColumnas();

        List<LibroDto> arrLibros = LibroControladorListar.obtenerLibros();
        ObservableList<LibroDto> datosTabla = FXCollections.observableArrayList(arrLibros);

        miTabla.setItems(datosTabla);
        miTabla.setPlaceholder(new Text("No hay libros registrados."));

        miTabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        miTabla.maxWidthProperty().bind(miEscenario.widthProperty().multiply(0.80));
        miTabla.maxHeightProperty().bind(miEscenario.heightProperty().multiply(0.60));
        miEscenario.heightProperty().addListener((o, oldVal, newVal)
                -> miTabla.setPrefHeight(newVal.doubleValue())
        );
        VBox.setVgrow(miTabla, Priority.ALWAYS);

        cajaVertical.getChildren().add(miTabla);
        getChildren().add(cajaVertical);
    }

}