package com.practica.productos.app;

import com.practica.productos.modelo.Producto;
import com.practica.productos.servicio.ProductoService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//ffhgh
public class Main extends Application{
@Override
public void start(Stage stage) {
TextField campo = new TextField();
Button boton = new Button("Mostrar");
TextArea area = new TextArea();
area.setEditable(false);
ProductoService servicio = new ProductoService();
boton.setOnAction(e -> {
    servicio.agregar(new Producto(campo.getText()));
String texto = "";
for (Producto p : servicio.listar()) {
texto += p.getNombre() + "\n";
}
try {
    Producto p = new Producto(campo.getText());
    area.setText(p.getNombre());
} catch (Exception ex) {
    area.setText(ex.getMessage());
}
});
VBox layout = new VBox(10, campo, boton, area);
Scene scene = new Scene(layout, 300, 200);
stage.setScene(scene);
stage.show();
}
}

