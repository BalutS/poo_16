package org.unimag.vista.libro;

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
import org.unimag.controlador.AutorControladorListar;
import org.unimag.controlador.EditorialControladorListar;
import org.unimag.controlador.LibroControladorGrabar;
import org.unimag.dto.AutorDto;
import org.unimag.dto.EditorialDto;
import org.unimag.dto.LibroDto;
import org.unimag.recurso.constante.Configuracion;
import org.unimag.recurso.utilidad.Marco;
import org.unimag.recurso.utilidad.Mensaje;

public class VistaLibroCrear extends StackPane {

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

    private TextField txtNombreLibro;
    private TextField txtPrecioLibro;
    private TextField txtAnioLibro;
    private ComboBox<EditorialDto> cbmEditorial;
    private ComboBox<AutorDto> cbmAutor;

    public VistaLibroCrear(Stage esce, double ancho, double alto) {
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
        Text miTitulo = new Text("Formulario crear libro");
        miTitulo.setFill(Color.web("#000000"));
        miTitulo.setFont(Font.font("Verdana", FontWeight.BOLD, 28));
        GridPane.setHalignment(miTitulo, HPos.CENTER);
        GridPane.setMargin(miTitulo, new Insets(30, 0, 0, 0));
        miGrilla.add(miTitulo, 0, 0, 2, 1);

    }

    private void crearFormulario() {
        Label lblNombre = new Label("Nombre libro:");
        lblNombre.setFont(Font.font("Arial", FontPosture.ITALIC,
                TAMANIO_FUENTE));
        miGrilla.add(lblNombre, 0, 2);

        txtNombreLibro = new TextField();
        txtNombreLibro.setPromptText("Digita el nombre del libro");
        GridPane.setHgrow(txtNombreLibro, Priority.ALWAYS);
        txtNombreLibro.setPrefHeight(ALTO_CAJA);
        miGrilla.add(txtNombreLibro, 1, 2);

        Label lblPrecio = new Label("Precio libro:");
        lblPrecio.setFont(Font.font("Arial", FontPosture.ITALIC,
                TAMANIO_FUENTE));
        miGrilla.add(lblPrecio, 0, 3);

        txtPrecioLibro = new TextField();
        txtPrecioLibro.setPromptText("Digita el precio del libro");
        GridPane.setHgrow(txtPrecioLibro, Priority.ALWAYS);
        txtPrecioLibro.setPrefHeight(ALTO_CAJA);
        miGrilla.add(txtPrecioLibro, 1, 3);

        Label lblAnio = new Label("Año libro:");
        lblAnio.setFont(Font.font("Arial", FontPosture.ITALIC,
                TAMANIO_FUENTE));
        miGrilla.add(lblAnio, 0, 4);

        txtAnioLibro = new TextField();
        txtAnioLibro.setPromptText("Digita el año del libro");
        GridPane.setHgrow(txtAnioLibro, Priority.ALWAYS);
        txtAnioLibro.setPrefHeight(ALTO_CAJA);
        miGrilla.add(txtAnioLibro, 1, 4);

        Label lblEditorial = new Label("Editorial:");
        lblEditorial.setFont(Font.font("Arial", FontPosture.ITALIC,
                TAMANIO_FUENTE));
        miGrilla.add(lblEditorial, 0, 5);

        cbmEditorial = new ComboBox<>();
        cbmEditorial.setMaxWidth(Double.MAX_VALUE);
        cbmEditorial.setPrefHeight(ALTO_CAJA);
        cbmEditorial.getItems().addAll(EditorialControladorListar.obtenerEditoriales());
        miGrilla.add(cbmEditorial, 1, 5);

        Label lblAutor = new Label("Autor:");
        lblAutor.setFont(Font.font("Arial", FontPosture.ITALIC,
                TAMANIO_FUENTE));
        miGrilla.add(lblAutor, 0, 6);

        cbmAutor = new ComboBox<>();
        cbmAutor.setMaxWidth(Double.MAX_VALUE);
        cbmAutor.setPrefHeight(ALTO_CAJA);
        cbmAutor.getItems().addAll(AutorControladorListar.obtenerAutores());
        miGrilla.add(cbmAutor, 1, 6);

        Button btnGrabar = new Button("Grabar libro");
        btnGrabar.setTextFill(Color.RED);
        btnGrabar.setMaxWidth(Double.MAX_VALUE);
        btnGrabar.setFont(Font.font("Verdana", TAMANIO_FUENTE));
        btnGrabar.setOnAction((e) -> {
            guardarElLibro();
        });

        miGrilla.add(btnGrabar, 1, 7);

    }

    private void limpiarFormulario() {
        txtNombreLibro.setText("");
        txtPrecioLibro.setText("");
        txtAnioLibro.setText("");
        cbmEditorial.getSelectionModel().select(null);
        cbmAutor.getSelectionModel().select(null);
        txtNombreLibro.requestFocus();
    }

    private Boolean formularioCompleto() {
        if (txtNombreLibro.getText().isBlank()) {
            Mensaje.mostrar(Alert.AlertType.WARNING, this.getScene().getWindow(),
                    "Validación", "Por favor ingrese un nombre para el libro.");
            txtNombreLibro.requestFocus();
            return false;
        }
        if (txtPrecioLibro.getText().isBlank()) {
            Mensaje.mostrar(Alert.AlertType.WARNING, this.getScene().getWindow(),
                    "Validación", "Por favor ingrese un precio para el libro.");
            txtPrecioLibro.requestFocus();
            return false;
        }
        try {
            Double.parseDouble(txtPrecioLibro.getText());
        } catch (NumberFormatException e) {
            Mensaje.mostrar(Alert.AlertType.WARNING, this.getScene().getWindow(),
                    "Validación", "El precio del libro debe ser un número.");
            txtPrecioLibro.requestFocus();
            return false;
        }
        if (txtAnioLibro.getText().isBlank()) {
            Mensaje.mostrar(Alert.AlertType.WARNING, this.getScene().getWindow(),
                    "Validación", "Por favor ingrese un año para el libro.");
            txtAnioLibro.requestFocus();
            return false;
        }
        try {
            Short.parseShort(txtAnioLibro.getText());
        } catch (NumberFormatException e) {
            Mensaje.mostrar(Alert.AlertType.WARNING, this.getScene().getWindow(),
                    "Validación", "El año del libro debe ser un número.");
            txtAnioLibro.requestFocus();
            return false;
        }
        if (cbmEditorial.getSelectionModel().getSelectedItem() == null) {
            Mensaje.mostrar(Alert.AlertType.WARNING, this.getScene().getWindow(),
                    "Validación", "Por favor seleccione una editorial.");
            cbmEditorial.requestFocus();
            return false;
        }
        if (cbmAutor.getSelectionModel().getSelectedItem() == null) {
            Mensaje.mostrar(Alert.AlertType.WARNING, this.getScene().getWindow(),
                    "Validación", "Por favor seleccione un autor.");
            cbmAutor.requestFocus();
            return false;
        }

        return true;
    }

    private void guardarElLibro() {
        if (formularioCompleto()) {
            LibroDto dto = new LibroDto();
            dto.setNombreLibro(txtNombreLibro.getText());
            dto.setPrecioLibro(Double.parseDouble(txtPrecioLibro.getText()));
            dto.setAnioLibro(Short.parseShort(txtAnioLibro.getText()));
            

            if (LibroControladorGrabar.crearLibro(dto)) {
                Mensaje.mostrar(Alert.AlertType.INFORMATION, null,
                        "Registro exitoso", "El libro se ha guardado correctamente.");
                limpiarFormulario();

            } else {
                Mensaje.mostrar(Alert.AlertType.ERROR, null,
                        "Error", "No se pudo guardar el libro.");
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