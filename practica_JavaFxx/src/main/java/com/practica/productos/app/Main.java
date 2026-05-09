package com.practica.productos.app;

import com.practica.productos.modelo.Producto;
import com.practica.productos.servicio.ProductoService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    ProductoService servicio = new ProductoService();

    @Override
    public void start(Stage stage) {
        TextField campo = new TextField();
        TextArea area = new TextArea();
        area.setEditable(false);
        Button btnAgregar = new Button("Agregar");
        Button btnEliminar = new Button("Eliminar");
        Button btnBuscar = new Button("Buscar");
        btnAgregar.setOnAction(e -> {
            try {
                servicio.agregar(new Producto(campo.getText()));
                actualizarLista(area);
                campo.clear();
            } catch (Exception ex) {
                area.setText("Error: " + ex.getMessage());
            }
        });
        btnEliminar.setOnAction(e -> {
            servicio.eliminar(campo.getText());
            actualizarLista(area);
            campo.clear();
        });
        btnBuscar.setOnAction(e -> {
            Producto encontrado = servicio.buscar(campo.getText());
            if (encontrado != null) {
                area.setText("Resultado: " + encontrado.getNombre());
            } else {
                area.setText("Producto no encontrado.");
            }
        });
        HBox filaBotones = new HBox(10, btnAgregar, btnEliminar, btnBuscar);
        VBox layout = new VBox(10, campo, filaBotones, area);
        Scene scene = new Scene(layout, 400, 400);
        stage.setTitle("CRUD Completo - UMG");
        stage.setScene(scene);
        stage.show();
    }
    private void actualizarLista(TextArea area) {
        String texto = "";
        for (Producto p : servicio.listar()) {
            texto += p.getNombre() + "\n";
        }
        area.setText(texto);
    }
    public static void main(String[] args) {
        launch(args);
    }
}

