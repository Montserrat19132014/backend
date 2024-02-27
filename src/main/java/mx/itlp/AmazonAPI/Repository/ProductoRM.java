package mx.itlp.AmazonAPI.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.itlp.AmazonAPI.Models.Producto;

public class ProductoRM implements RowMapper<Producto>{

    @Override
    public Producto mapRow(ResultSet rs, int arg1) throws SQLException {
        Producto producto = new Producto();
        producto.setId(rs.getInt("id"));
        producto.setNombre(rs.getString("nombre"));
        producto.setPrecioProducto(rs.getDouble("precio"));
        producto.setColor(rs.getString("color"));
        producto.setMarca(rs.getString("marca"));
        producto.setDescripcion(rs.getString("descripcion"));
        producto.setPeso(rs.getDouble("peso"));
        producto.setDimensiones(rs.getString("dimensiones"));

        return producto;
    }
    
}
