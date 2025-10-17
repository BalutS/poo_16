package org.unimag.vista.autor;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.unimag.controlador.AutorControladorGrabar;
import org.unimag.dto.AutorDto;
import org.unimag.recurso.constante.Configuracion;
import org.unimag.recurso.utilidad.Marco;
import org.unimag.recurso.utilidad.Mensaje;

public class VistaAutorCrear extends StackPane {

    private static final int H_GAP = 10;
    private static final int V_GAP = 20;
    private static final int ALTO_FILA = 40;
    private static final int ANCHO_FILA = 35;
    private static final int TAMANIO_FUENTE = 20;
    private static final int ALTO_CAJA = 35;
    private static final double ANCHO = 0.8;

    private static final double AJUSTE_TITULO = 0.1;

    private final GridPane miGrilla;
    private final Rectangle miMarco;

    private TextField txtNombreAutor;
    private ComboBox<String> cbmGeneroAutor;

    public VistaAutorCrear(Stage esce, double ancho, double alto) {
        setAlignment(Pos.CENTER);
        miGrilla = new GridPane();
        miMarco = Marco.crear(esce, Configuracion.MARCO_ANCHO_PORCENTAJE, Configuracion.MARCO_ALTO_PORCENTAJE,
                Configuracion.DEGRADE_ARREGLO, Configuracion.DEGRADDE_BORDE);
        getChildren().add(miMarco);
        configurarMiGrilla(ancho, alto);
        crearTitulo();
        crearFormulario();

        colocarFrmElegante();
        getChildren().add(miGrilla);

    }

    private void configurarMiGrilla(double ancho, double alto) {
        double miAnchoGrilla = ancho * Configuracion.GRILLA_ANCHO_PORCENTAJE;
        miGrilla.setHgap(H_GAP);
        miGrilla.setVgap(V_GAP);
        miGrilla.setPrefSize(miAnchoGrilla, alto);
        miGrilla.setMinSize(miAnchoGrilla, alto);
        miGrilla.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        ColumnConstraints col0 = new ColumnConstraints();
        ColumnConstraints col1 = new ColumnConstraints();

        col0.setPrefWidth(250);
        col1.setPrefWidth(400);
        col1.setHgrow(Priority.ALWAYS);

        miGrilla.getColumnConstraints().addAll(col0, col1);

        for (int i = 0; i < 8; i++) {
            RowConstraints fila = new RowConstraints();
            fila.setMinHeight(ALTO_FILA);
            fila.setMaxHeight(ALTO_FILA);
            miGrilla.getRowConstraints().add(fila);

        }
    }

    private void crearTitulo() {
        Text miTitulo = new Text("Formulario crear autor");
        miTitulo.setFill(Color.web("#000000"));
        miTitulo.setFont(Font.font("Verdana", FontWeight.BOLD, 28));
        GridPane.setHalignment(miTitulo, HPos.CENTER);
        GridPane.setMargin(miTitulo, new Insets(30, 0, 0, 0));
        // columna, fila, colSpan,RowSpan
        miGrilla.add(miTitulo, 0, 0, 2, 1);

    }

    private void crearFormulario() {
        Label lblNombre = new Label("Nombre autor:");
        lblNombre.setFont(Font.font("Arial", FontPosture.ITALIC,
                TAMANIO_FUENTE));
        miGrilla.add(lblNombre, 0, 2);

        txtNombreAutor = new TextField();
        txtNombreAutor.setPromptText("Digita el nombre del autor");
        GridPane.setHgrow(txtNombreAutor, Priority.ALWAYS);
        txtNombreAutor.setPrefHeight(ALTO_CAJA);
        miGrilla.add(txtNombreAutor, 1, 2);

        Label lblGenero = new Label("Género autor:");
        lblGenero.setFont(Font.font("Arial", FontPosture.ITALIC,
                TAMANIO_FUENTE));
        miGrilla.add(lblGenero, 0, 3);

        cbmGeneroAutor = new ComboBox<>();
        cbmGeneroAutor.setMaxWidth(Double.MAX_VALUE);
        cbmGeneroAutor.setPrefHeight(ALTO_CAJA);
        cbmGeneroAutor.getItems().addAll("Selecciona un género", "Masculino", "Femenino");
        cbmGeneroAutor.getSelectionModel().select(0);
        miGrilla.add(cbmGeneroAutor, 1, 3);

        Button btnGrabar = new Button("Grabar autor");
        btnGrabar.setTextFill(Color.RED);
        btnGrabar.setMaxWidth(Double.MAX_VALUE);
        btnGrabar.setFont(Font.font("Verdana", TAMANIO_FUENTE));
        btnGrabar.setOnAction((e) -> {
            guardarElAutor();
        });

        miGrilla.add(btnGrabar, 1, 5);

    }

    private Boolean obtenerGenero() {
        String miTexto = cbmGeneroAutor.getValue();
        if ("Masculino".equals(miTexto)) {
            return true;
        }
        if ("Femenino".equals(miTexto)) {
            return false;
        }

        return null;
    }

    private void limpiarFormulario() {
        txtNombreAutor.setText("");
        cbmGeneroAutor.getSelectionModel().select(0);
        txtNombreAutor.requestFocus();
    }

    private Boolean formularioCompleto() {
        if (txtNombreAutor.getText().isBlank()) {
            Mensaje.mostrar(Alert.AlertType.WARNING, this.getScene().getWindow(),
                    "Validación", "Por favor ingrese un nombre para el autor.");
            txtNombreAutor.requestFocus();
            return false;
        }
        if (cbmGeneroAutor.getSelectionModel().getSelectedIndex() == 0) {
            Mensaje.mostrar(Alert.AlertType.WARNING, this.getScene().getWindow(),
                    "Validación", "Por favor seleccione un género.");
            cbmGeneroAutor.requestFocus();
            return false;
        }
        return true;
    }

    private void guardarElAutor() {
        if (formularioCompleto()) {
            AutorDto dto = new AutorDto();
            dto.setNombreAutor(txtNombreAutor.getText());
            dto.setGeneroAutor(obtenerGenero());

            if (AutorControladorGrabar.crearAutor(dto)) {
                Mensaje.mostrar(Alert.AlertType.INFORMATION, null,
                        "Registro exitoso", "El autor se ha guardado correctamente.");
                limpiarFormulario();

            } else {
                Mensaje.mostrar(Alert.AlertType.ERROR, null,
                        "Error", "No se pudo guardar el autor.");
            }
        }
    }

    private void colocarFrmElegante() {
        Runnable calcular = () -> {
            double alturaMarco = miMarco.getHeight();
            if (alturaMarco > 0) {
                double desplazamiento = alturaMarco * AJUSTE_TITULO;
                miGrilla.setTranslateY(-alturaMarco / 2 + desplazamiento);
            }
        };
        calcular.run();
        miMarco.heightProperty().addListener((obs, antes, despues) -> {
            calcular.run();
        });
    }
}