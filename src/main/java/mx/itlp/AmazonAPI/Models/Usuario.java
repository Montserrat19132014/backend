package mx.itlp.AmazonAPI.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class Usuario {
    int id;
    String nombre;
    double subtotal;
    int cantidad_productos;
}
