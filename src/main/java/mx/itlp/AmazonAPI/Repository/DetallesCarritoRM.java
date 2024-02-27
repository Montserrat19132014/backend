package mx.itlp.AmazonAPI.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.itlp.AmazonAPI.Models.DetallesCarrito;

public class DetallesCarritoRM implements RowMapper<DetallesCarrito>{

    @Override
    public DetallesCarrito mapRow(ResultSet rs, int arg1) throws SQLException {
        //Se crea un nuevo objeto\
        DetallesCarrito detallesCarrito = new DetallesCarrito();

        //Se agregan los setter
        detallesCarrito.setId(rs.getInt("id"));
        detallesCarrito.setCantidad(rs.getInt("cantidad"));
        detallesCarrito.setPrecio(rs.getDouble("precio"));
        detallesCarrito.setImporte(rs.getDouble("importe"));

        return detallesCarrito;
        
    }

}
