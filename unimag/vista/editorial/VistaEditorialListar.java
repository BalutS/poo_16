package org.unimag.vista.editorial;

import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
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
import org.unimag.controlador.EditorialControladorListar;
import org.unimag.dto.EditorialDto;
import org.unimag.recurso.constante.Configuracion;
import org.unimag.recurso.utilidad.Marco;

public class VistaEditorialListar extends StackPane {

    private final Rectangle marco;
    private final Stage miEscenario;
    private final VBox cajaVertical;
    private final TableView<EditorialDto> miTabla;

    private static final String ESTILO_CENTRAR
            = "-fx-alignment: CENTER;";
    private static final String ESTILO_DERECHA
            = "-fx-alignment: CENTER-RIGHT;";
    private static final String ESTILO_IZQUIERDA
            = "-fx-alignment: CENTER-LEFT;";

    public VistaEditorialListar(
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
        int cant = EditorialControladorListar.obtenerCantidadEditoriales();
        Text titulo = new Text("Lista de editoriales (" + cant + ")");
        titulo.setFill(Color.web("#de3d37"));
        titulo.setFont(Font.font("Verdana", FontWeight.BOLD, 24));

        cajaVertical.getChildren().addAll(bloqueSeparador, titulo);
    }

    private TableColumn<EditorialDto, Integer> crearColumnaCodigo() {
        TableColumn<EditorialDto, Integer> columna = new TableColumn<>("cod");
        columna.setCellValueFactory(new PropertyValueFactory<>("idEditorial"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.10));
        columna.setStyle(ESTILO_CENTRAR);
        return columna;
    }

    private TableColumn<EditorialDto, String> crearColumnaNombre() {
        TableColumn<EditorialDto, String> columna = new TableColumn<>("Nombre");
        columna.setCellValueFactory(new PropertyValueFactory<>("nombreEditorial"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.20));
        columna.setStyle(ESTILO_IZQUIERDA);
        return columna;
    }

    private TableColumn<EditorialDto, String> crearColumnaPais() {
        TableColumn<EditorialDto, String> columna = new TableColumn<>("País");
        columna.setCellValueFactory(new PropertyValueFactory<>("PaisEditorial"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.20));
        columna.setStyle(ESTILO_IZQUIERDA);
        return columna;
    }

    private TableColumn<EditorialDto, String> crearColumnaFormato() {
        TableColumn<EditorialDto, String> columna = new TableColumn<>("Formato");
        columna.setCellValueFactory(obj -> {
            String formato;
            switch (obj.getValue().getFormatoEditorial()) {
                case 1:
                    formato = "Impreso";
                    break;
                case 2:
                    formato = "Digital";
                    break;
                case 3:
                    formato = "Impreso y Digital";
                    break;
                default:
                    formato = "Desconocido";
                    break;
            }
            return new SimpleStringProperty(formato);
        });

        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.20));
        columna.setStyle(ESTILO_CENTRAR);
        return columna;
    }
    
    private TableColumn<EditorialDto, Integer> crearColumnaCantidadLibros() {
        TableColumn<EditorialDto, Integer> columna = new TableColumn<>("Cantidad Libros");
        columna.setCellValueFactory(new PropertyValueFactory<>("cantidadLibrosEditorial"));
        columna.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.20));
        columna.setStyle(ESTILO_CENTRAR);
        return columna;
    }

    private void configurarColumnas() {
        miTabla.getColumns().addAll(
                List.of(
                        crearColumnaCodigo(),
                        crearColumnaNombre(),
                        crearColumnaPais(),
                        crearColumnaFormato(),
                        crearColumnaCantidadLibros()
                )
        );
    }

    private void crearTabla() {
        configurarColumnas();

        List<EditorialDto> arrEditoriales = EditorialControladorListar.obtenerEditoriales();
        ObservableList<EditorialDto> datosTabla = FXCollections.observableArrayList(arrEditoriales);

        miTabla.setItems(datosTabla);
        miTabla.setPlaceholder(new Text("No hay editoriales registradas."));

        // Evita scroll horizontal
        miTabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        // Responsividad
        miTabla.maxWidthProperty().bind(miEscenario.widthProperty().multiply(0.50));
        miTabla.maxHeightProperty().bind(miEscenario.heightProperty().multiply(0.50));
        miEscenario.heightProperty().addListener((o, oldVal, newVal)
                -> miTabla.setPrefHeight(newVal.doubleValue())
        );
        VBox.setVgrow(miTabla, Priority.ALWAYS);

        cajaVertical.getChildren().add(miTabla);
        getChildren().add(cajaVertical);
    }

}