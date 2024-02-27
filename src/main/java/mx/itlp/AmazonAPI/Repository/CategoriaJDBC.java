package mx.itlp.AmazonAPI.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import mx.itlp.AmazonAPI.Models.Categoria;
import mx.itlp.AmazonAPI.Models.Producto;

@Repository
public class CategoriaJDBC {
    //Hace la autoconexion para el "jdbc template"
    @Autowired

    //Se crea un objeto del "Jdbc Template". Este objeto es al que se conecta el @autowired
    //Plantilla de operaciones. Con esta plantilla es donde se ejecuta el query
    JdbcTemplate conexion;

    String sql;

    //Consulta que regresa la lista de objetos
    public List<Categoria> consultar(){
        //Codigo sql a ejecutar
        sql = "SELECT id, nombre FROM categorias WHERE activo = 1";

        //Como se va a regresar una lista, checar al principio del metodo, se utiliza la lista de objetos <T>.
        //RowMapper: Cuando la plantilla mande a ejecutar la consulta envia un resultado "result set", con este resultado es con el que se va a llenar la lista. en esta ocasion se mapea un row (renglon) de la consulta, como son varios de manera automatica se hace un ciclo
       return conexion.query(sql, new CategoriaRM());
    }

    public Categoria buscar(int id) {
        //Codigo sql que busca por ID 
        sql = "SELECT id, nombre FROM categorias WHERE id = ? AND activo = 1";

        //El RM siempre se va a necesitar 
        return conexion.queryForObject(sql, new CategoriaRM(), id);
    }

    public void insertar(String nombre){
        sql = "INSERT INTO Categorias (nombre) VALUES (?)";
        conexion.update(sql, nombre);
    }

    public int modificar(int id, String nombre){
        sql = "UPDATE Categorias SET nombre = ? WHERE id = ? AND activo = 1";
        return conexion.update(sql, nombre, id);
    }

    public void desactivar (int id){
        sql = "UPDATE categorias SET activo = 0 WHERE id = ?";
        conexion.update(sql, id);
    }

    public List<Producto> buscarProductos(int id) {
        // Código SQL que busca por ID 
        sql = "SELECT productos.* FROM productos JOIN categorias ON productos.Categorias_id = categorias.id WHERE categorias.id = ? AND categorias.activo = 1";
    
        // El RM siempre se va a necesitar 
        return conexion.query(sql, new ProductoRM(), id);
    }

    

}


