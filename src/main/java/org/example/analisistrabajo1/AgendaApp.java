package org.example.analisistrabajo1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AgendaApp extends Application {

    // DAO
    private final PersonaDAO personaDAO = new PersonaDAO();
    private final TelefonoDAO telefonoDAO = new TelefonoDAO();

    // PERSONAS
    private TableView<Persona> tablaPersonas;
    private TextField txtNombre;
    private TextField txtDireccion;

    // TELEFONOS
    private TableView<Telefono> tablaTelefonos;
    private TextField txtTelefono;

    @Override
    public void start(Stage stage) {

        Label titulo = new Label("📇 AGENDA DE CONTACTOS");
        titulo.getStyleClass().add("header-title");

        HBox header = new HBox(titulo);
        header.getStyleClass().add("header-box");

        // CAMPOS Y TABLA DE PERSONAS
        txtNombre = new TextField();
        txtDireccion = new TextField();
        txtNombre.setPromptText("Nombre completo");
        txtDireccion.setPromptText("Dirección de residencia");

        Label lblNombre = new Label("Nombre:");
        Label lblDireccion = new Label("Dirección:");
        lblNombre.getStyleClass().add("field-label");
        lblDireccion.getStyleClass().add("field-label");

        tablaPersonas = new TableView<>();
        TableColumn<Persona, Integer> colIdPersona = new TableColumn<>("ID");
        colIdPersona.setCellValueFactory(new PropertyValueFactory<>("id"));
        colIdPersona.setPrefWidth(60);

        TableColumn<Persona, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colNombre.setPrefWidth(220);

        TableColumn<Persona, String> colDireccion = new TableColumn<>("Dirección");
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colDireccion.setPrefWidth(300);

        tablaPersonas.getColumns().addAll(colIdPersona, colNombre, colDireccion);
        tablaPersonas.setPrefHeight(160);

        // Botones Personas
        Button btnAgregarPersona = new Button("Agregar");
        Button btnModificarPersona = new Button("Modificar");
        Button btnEliminarPersona = new Button("Eliminar");
        Button btnLimpiarPersona = new Button("Limpiar");

        btnAgregarPersona.getStyleClass().add("btn-agregar");
        btnModificarPersona.getStyleClass().add("btn-modificar");
        btnEliminarPersona.getStyleClass().add("btn-eliminar");
        btnLimpiarPersona.getStyleClass().add("btn-limpiar");

        HBox botonesPersona = new HBox(10, btnAgregarPersona, btnModificarPersona, btnEliminarPersona, btnLimpiarPersona);

        GridPane camposPersona = new GridPane();
        camposPersona.setHgap(10);
        camposPersona.setVgap(10);
        camposPersona.add(lblNombre, 0, 0);
        camposPersona.add(txtNombre, 1, 0);
        camposPersona.add(lblDireccion, 0, 1);
        camposPersona.add(txtDireccion, 1, 1);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS);
        camposPersona.getColumnConstraints().addAll(new ColumnConstraints(), col2);

        Label tituloPersonas = new Label("PERSONAS");
        tituloPersonas.getStyleClass().add("section-title");

        VBox cardPersonas = new VBox(12, tituloPersonas, camposPersona, botonesPersona, tablaPersonas);
        cardPersonas.getStyleClass().add("card");

        // CAMPOS Y TABLA DE TELÉFONOS
        txtTelefono = new TextField();
        txtTelefono.setPromptText("Número telefónico");

        Label lblTelefono = new Label("Teléfono:");
        lblTelefono.getStyleClass().add("field-label");

        tablaTelefonos = new TableView<>();
        TableColumn<Telefono, Integer> colIdTelefono = new TableColumn<>("ID");
        colIdTelefono.setCellValueFactory(new PropertyValueFactory<>("id"));
        colIdTelefono.setPrefWidth(60);

        TableColumn<Telefono, Integer> colPersonaId = new TableColumn<>("ID Persona");
        colPersonaId.setCellValueFactory(new PropertyValueFactory<>("personaId"));
        colPersonaId.setPrefWidth(100);

        TableColumn<Telefono, String> colTelefono = new TableColumn<>("Teléfono");
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colTelefono.setPrefWidth(400);

        tablaTelefonos.getColumns().addAll(colIdTelefono, colPersonaId, colTelefono);
        tablaTelefonos.setPrefHeight(140);

        // Botones Teléfonos
        Button btnAgregarTelefono = new Button("Agregar");
        Button btnModificarTelefono = new Button("Modificar");
        Button btnEliminarTelefono = new Button("Eliminar");
        Button btnLimpiarTelefono = new Button("Limpiar");

        btnAgregarTelefono.getStyleClass().add("btn-agregar");
        btnModificarTelefono.getStyleClass().add("btn-modificar");
        btnEliminarTelefono.getStyleClass().add("btn-eliminar");
        btnLimpiarTelefono.getStyleClass().add("btn-limpiar");

        HBox botonesTelefono = new HBox(10, btnAgregarTelefono, btnModificarTelefono, btnEliminarTelefono, btnLimpiarTelefono);

        GridPane camposTelefono = new GridPane();
        camposTelefono.setHgap(10);
        camposTelefono.setVgap(10);
        camposTelefono.add(lblTelefono, 0, 0);
        camposTelefono.add(txtTelefono, 1, 0);
        camposTelefono.getColumnConstraints().addAll(new ColumnConstraints(), col2);

        Label tituloTelefonos = new Label("TELÉFONOS");
        tituloTelefonos.getStyleClass().add("section-title");

        VBox cardTelefonos = new VBox(12, tituloTelefonos, camposTelefono, botonesTelefono, tablaTelefonos);
        cardTelefonos.getStyleClass().add("card");

        // EVENTOS
        btnAgregarPersona.setOnAction(e -> agregarPersona());
        btnModificarPersona.setOnAction(e -> modificarPersona());
        btnEliminarPersona.setOnAction(e -> eliminarPersona());
        btnLimpiarPersona.setOnAction(e -> limpiarPersona());

        btnAgregarTelefono.setOnAction(e -> agregarTelefono());
        btnModificarTelefono.setOnAction(e -> modificarTelefono());
        btnEliminarTelefono.setOnAction(e -> eliminarTelefono());
        btnLimpiarTelefono.setOnAction(e -> limpiarTelefono());

        tablaPersonas.getSelectionModel().selectedItemProperty().addListener((obs, ant, selec) -> {
            if (selec != null) {
                txtNombre.setText(selec.getNombre());
                txtDireccion.setText(selec.getDireccion());
                cargarTelefonos(selec.getId());
            }
        });

        tablaTelefonos.getSelectionModel().selectedItemProperty().addListener((obs, ant, selec) -> {
            if (selec != null) {
                txtTelefono.setText(selec.getTelefono());
            }
        });

        // CONTENEDOR Y ESCENA
        VBox contenido = new VBox(20, header, cardPersonas, cardTelefonos);
        contenido.getStyleClass().add("root-container");

        ScrollPane scrollPane = new ScrollPane(contenido);
        scrollPane.setFitToWidth(true);

        Scene scene = new Scene(scrollPane, 900, 600);

        // CARGAR EL ARCHIVO CSS
        try {
            String cssPath = getClass().getResource("/style.css").toExternalForm();
            scene.getStylesheets().add(cssPath);
        } catch (Exception e) {
            System.out.println("No se pudo cargar el archivo CSS. Revisa la ruta.");
        }

        stage.setTitle("Gestor de Agenda");
        stage.setScene(scene);
        stage.show();

        cargarPersonas();
    }

    // MÉTODOS DE LÓGICA (Personas, Teléfonos, Mensajes)
    private void cargarPersonas() {
        ObservableList<Persona> lista = FXCollections.observableArrayList(personaDAO.obtenerPersonas());
        tablaPersonas.setItems(lista);
    }

    private void agregarPersona() {
        String nombre = txtNombre.getText();
        String direccion = txtDireccion.getText();
        if (nombre.isEmpty() || direccion.isEmpty()) {
            mostrarMensaje("Completa todos los campos.");
            return;
        }
        Persona persona = new Persona(nombre, direccion);
        personaDAO.agregarPersona(persona);
        cargarPersonas();
        limpiarPersona();
    }

    private void modificarPersona() {
        Persona seleccionada = tablaPersonas.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            mostrarMensaje("Selecciona una persona.");
            return;
        }
        seleccionada.setNombre(txtNombre.getText());
        seleccionada.setDireccion(txtDireccion.getText());
        personaDAO.modificarPersona(seleccionada);
        cargarPersonas();
    }

    private void eliminarPersona() {
        Persona seleccionada = tablaPersonas.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            mostrarMensaje("Selecciona una persona.");
            return;
        }
        personaDAO.eliminarPersona(seleccionada.getId());
        cargarPersonas();
        tablaTelefonos.getItems().clear();
        limpiarPersona();
    }

    private void limpiarPersona() {
        txtNombre.clear();
        txtDireccion.clear();
        tablaPersonas.getSelectionModel().clearSelection();
        tablaTelefonos.getItems().clear();
        txtTelefono.clear();
    }

    private void cargarTelefonos(int personaId) {
        ObservableList<Telefono> lista = FXCollections.observableArrayList(telefonoDAO.obtenerTelefonosPorPersona(personaId));
        tablaTelefonos.setItems(lista);
    }

    private void agregarTelefono() {
        Persona persona = tablaPersonas.getSelectionModel().getSelectedItem();
        if (persona == null) {
            mostrarMensaje("Primero selecciona una persona.");
            return;
        }
        String numero = txtTelefono.getText();
        if (numero.isEmpty()) {
            mostrarMensaje("Escribe un número de teléfono.");
            return;
        }
        Telefono telefono = new Telefono(persona.getId(), numero);
        telefonoDAO.agregarTelefono(telefono);
        cargarTelefonos(persona.getId());
        txtTelefono.clear();
    }

    private void modificarTelefono() {
        Telefono seleccionado = tablaTelefonos.getSelectionModel().getSelectedItem();
        Persona persona = tablaPersonas.getSelectionModel().getSelectedItem();
        if (seleccionado == null || persona == null) {
            mostrarMensaje("Selecciona una persona y un teléfono.");
            return;
        }
        seleccionado.setTelefono(txtTelefono.getText());
        seleccionado.setPersonaId(persona.getId());
        telefonoDAO.modificarTelefono(seleccionado);
        cargarTelefonos(persona.getId());
    }

    private void eliminarTelefono() {
        Telefono seleccionado = tablaTelefonos.getSelectionModel().getSelectedItem();
        Persona persona = tablaPersonas.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarMensaje("Selecciona un teléfono.");
            return;
        }
        telefonoDAO.eliminarTelefono(seleccionado.getId());
        if (persona != null) cargarTelefonos(persona.getId());
        txtTelefono.clear();
    }

    private void limpiarTelefono() {
        txtTelefono.clear();
        tablaTelefonos.getSelectionModel().clearSelection();
    }

    private void mostrarMensaje(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Agenda");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}