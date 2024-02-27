package mx.itlp.AmazonAPI.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class Pedido {
    int id;
    double importe_producto;
    double importe_iva;
    double total;
}
