package mx.itlp.AmazonAPI.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.itlp.AmazonAPI.Models.Pedido;

public class PedidoRM implements RowMapper<Pedido>{

    @Override
    public Pedido mapRow(ResultSet rs, int arg1) throws SQLException {
        Pedido pedido = new Pedido();
        pedido.setId(rs.getInt("id"));
        pedido.setImporte_producto(rs.getDouble("importe_producto"));
        pedido.setImporte_iva(rs.getDouble("importe_iva"));
        pedido.setTotal(rs.getDouble("total"));

        return pedido;
    }
    
}
