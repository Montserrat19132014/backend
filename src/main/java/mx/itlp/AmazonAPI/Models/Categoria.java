package mx.itlp.AmazonAPI.Models;

//Importaciones de las notaciones
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Se agregan las notaciones de Lombok
@Getter
@Setter
@NoArgsConstructor

public class Categoria {
    // Aqui se representa el objeto (tabla)
    int id;
    String nombre;
}
