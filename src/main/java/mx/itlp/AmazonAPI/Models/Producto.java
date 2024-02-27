package mx.itlp.AmazonAPI.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class Producto {
    //Aqui se presenta el objeto, es decir la tabla 
    int id;
    String nombre;
    double precioProducto;
    String color;
    String marca;
    String descripcion;
    double peso;
    String dimensiones;
}
