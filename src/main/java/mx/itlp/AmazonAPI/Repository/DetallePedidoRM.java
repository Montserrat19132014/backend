package mx.itlp.AmazonAPI.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.itlp.AmazonAPI.Models.DetallesPedido;

public class DetallePedidoRM implements RowMapper<DetallesPedido> {

    @Override
    public DetallesPedido mapRow(ResultSet rs, int arg1) throws SQLException {
        //Nuevo objeto
        DetallesPedido detallesPedido = new DetallesPedido();

        // Se agregan setter
        detallesPedido.setId(rs.getInt("id"));
        detallesPedido.setCantidad(rs.getInt("cantidad"));
        detallesPedido.setPrecio(rs.getDouble("precio"));
        detallesPedido.setImporte(rs.getDouble("precio"));
        detallesPedido.setPedidos_id(rs.getInt("pedidos_id"));
        detallesPedido.setProductos_id(rs.getInt("productos_id"));

        return detallesPedido;
    }

}
