package mx.itlp.AmazonAPI.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PedidoJDBC {
    @Autowired
    JdbcTemplate conexion;

    String sql;

    // Agregar nuevos productos al carrito de compras. Aquí ya se conoce/sabe que
    // ese producto no esta en el carrito y por eso se agrega como un nuevo.
    public boolean insertarNuevo(int Usuario_id, int Producto_id, int cantidad) {
        sql = "INSERT INTO detalles_carrito (cantidad, precio, Productos_id, Usuario_id) SELECT ?, precio, ?, ?\n" +
                "FROM productos p WHERE p.id = ? AND NOT EXISTS (SELECT 1 FROM detalles_carrito dc WHERE dc.Productos_id = p.id AND dc.Usuario_id = ?);\n";

        try {
            int tablaModificada = conexion.update(sql, cantidad, Producto_id, Usuario_id, Producto_id, Usuario_id);
            return tablaModificada > 0; // Si se insertó al menos una fila, devuelve true
        } catch (DataAccessException e) {
            return false; // En caso de error, devuelve false
        }
    }

    // Cambiar la cantidad de productos del carrito. Aquí ya se conoce/sabe que
    // producto está en el carrito
    public int cambiarCantidad(int Usuario_id, int Producto_id, int cantidad) {
        sql = "UPDATE detalles_carrito SET cantidad = cantidad + ? WHERE Productos_id = ? AND Usuario_id =?";
        return conexion.update(sql, cantidad, Producto_id, Usuario_id);
    }

    // Quitar o eliminar un producto del carrito de compras. Se elimina totalmente
    // no solo se desactiva
    public boolean eliminar(int Usuario_id, int Producto_id) {
        sql = "DELETE FROM detalles_carrito WHERE detalles_carrito.Productos_id = ? AND Usuario_id = ?;";

        try {
            int filasEliminadas = conexion.update(sql, Producto_id, Usuario_id);
            return filasEliminadas > 0;
        } catch (DataAccessException e) {
            return false;
        }
    }

    // Agregar productos que ya existen en el carrito, es decir aumenta la cantidad.
    // Si el producto no está en el carrito entonces se inserta uno nuevo
    public boolean insertar(int Usuario_id, int Producto_id, int cantidad) {
        sql = "INSERT INTO detalles_carrito (cantidad, precio, Productos_id, Usuario_id) VALUES (?, (SELECT precio FROM productos WHERE id = ?), ?, ?) ON DUPLICATE KEY UPDATE cantidad = cantidad + ?";

        try {
            int tablaModificada = conexion.update(sql, cantidad, Producto_id, Producto_id, Usuario_id, cantidad);
            return tablaModificada > 0; // Si se insertó al menos una fila, devuelve true
        } catch (DataAccessException e) {
            return false; // En caso de error, devuelve false
        }
    }

    // El metodo regresa un Map<String, Object>, uno para el encabezado y el otro
    // para la lista de productos
    public Map<String, Object> consultar(int id) throws Exception {
        String sqlEncabezado = "SELECT usuario.nombre, SUM(detalles_carrito.cantidad) AS cantidad, sum(detalles_carrito.importe) AS subtotal\n"
                + //
                "FROM usuario\n" + //
                "\tJOIN detalles_carrito ON usuario.id = detalles_carrito.Usuario_id\n" + //
                "    JOIN productos ON detalles_carrito.Productos_id = productos.id\n" + //
                "WHERE usuario.id = ?;";

        // Se pasa como primer parametro del "List<Map<String, Object>>" el encabezado
        List<Map<String, Object>> encabezado = conexion.queryForList(sqlEncabezado, id);


        String sqlLista = "SELECT productos.descripcion, detalles_carrito.cantidad AS cantidad, productos.precio AS precio, (productos.precio*detalles_carrito.cantidad) AS total, detalles_carrito.id,\n"
                + //
                "productos.id\n" + //
                "FROM usuario\n" + //
                "\tJOIN detalles_carrito ON usuario.id = detalles_carrito.Usuario_id\n" + //
                "    JOIN productos ON detalles_carrito.Productos_id = productos.id\n" + //
                "WHERE usuario.id = ?;";


        //Se pasa como segundo parametro del "List<Map<String, Object>>" la lista
        List<Map<String, Object>> lista = conexion.queryForList(sqlLista, id);


        //Se construye en Map llamado "union" el cual contiene el valor obtenido de encabezado y enseguida de la lista 
        Map<String, Object> union = new LinkedHashMap<>();
        union.put("encabezado", encabezado);
        union.put("lista", lista);

        return union;

    }
}