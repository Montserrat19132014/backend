package mx.itlp.AmazonAPI.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class DetallesPedido {
    //Aqui se presenta el objeto, es decir la tabla 
    int id;
    int cantidad;
    double precio;
    double importe;
    int Pedidos_id;
    int Productos_id;
}
