package mx.itlp.AmazonAPI.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class DetallesCarrito {
    int id;
    int cantidad;
    double precio;
    double importe;
}
