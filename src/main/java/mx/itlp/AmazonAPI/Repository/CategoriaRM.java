package mx.itlp.AmazonAPI.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.itlp.AmazonAPI.Models.Categoria;

public class CategoriaRM implements RowMapper<Categoria>{
    //Para importar el Row Mapper, se tiene que cuidar que se importe el de Spring ya que es que se esta utlizando
    //Las interfaces tienen metodos que el programador tiene que construir, en este caso hace falta un metodo, con ayuda del foco se agrega el metodo. Se elimina el metodo y el comentario que aparecen en el metodo agregado 

    @Override
    public Categoria mapRow(ResultSet rs, int arg1) throws SQLException {
        //Se crea un nuevo objeto\
        Categoria categoria = new Categoria();
        //Se agregan los setter de id y nombre
        // Si no aparece de manera automatica el columnLabel solo se ignora y se coloca el "id" y "nombre", en lugar de "columnLabel:"id"" y "columnLabel:"nombre""
        categoria.setId(rs.getInt("id"));
        categoria.setNombre(rs.getString("nombre"));
        return categoria;
    }
    

    


}
