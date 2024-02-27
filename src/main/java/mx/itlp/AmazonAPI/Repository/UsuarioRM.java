package mx.itlp.AmazonAPI.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.itlp.AmazonAPI.Models.Usuario;

public class UsuarioRM implements RowMapper<Usuario>{

    @Override
    public Usuario mapRow(ResultSet rs, int arg1) throws SQLException {
        //Se crea un objeto
        Usuario usuario = new Usuario();

        //Se agregan setter
        usuario.setId(rs.getInt("id"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setSubtotal(rs.getDouble("subtotal"));
        usuario.setCantidad_productos(rs.getInt("cantidad_productos"));

        return usuario;

    }

}
