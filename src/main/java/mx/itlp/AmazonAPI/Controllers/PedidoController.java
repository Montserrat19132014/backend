package mx.itlp.AmazonAPI.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.itlp.AmazonAPI.Repository.PedidoJDBC;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/Pedidos")

public class PedidoController {

    // private static final Logger logger =
    // LoggerFactory.getLogger(PedidoController.class);

    @Autowired
    PedidoJDBC repository;

    @PostMapping("/Usuario/{Usuario_id}/Producto/{Producto_id}")
    public ResponseEntity<String> insertarNuevo(
            @PathVariable int Usuario_id,
            @PathVariable int Producto_id,
            @RequestParam int cantidad) {

        boolean insercionExitosa = repository.insertarNuevo(Usuario_id, Producto_id, cantidad);

        if (insercionExitosa) {
            return new ResponseEntity<>("El producto se agregó con éxito", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("No se pudo insertar el producto", HttpStatus.CONFLICT);
        }

    }

    @PutMapping("/Usuario/{Usuario_id}/Producto/{Producto_id}")
    public ResponseEntity<String> modificar(
            @PathVariable int Usuario_id,
            @PathVariable int Producto_id,
            @RequestParam int cantidad) {
        int resultado = repository.cambiarCantidad(Usuario_id, Producto_id, cantidad);
        if (resultado > 0) {
            return new ResponseEntity<>("La categoria fue actualizada", HttpStatus.OK);

        } else {
            return new ResponseEntity<>("No se pudo modificar la categoria", HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/Usuario/{Usuario_id}/Producto/{Producto_id}")
    public ResponseEntity<String> eliminar(
            @PathVariable int Usuario_id,
            @PathVariable int Producto_id) {
        boolean eliminacionExitosa = repository.eliminar(Usuario_id, Producto_id);

        if (eliminacionExitosa) {
            return new ResponseEntity<>("La eliminación se realizó con éxito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se pudo realizar la eliminacion", HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/Insertar/Usuario/{Usuario_id}/Producto/{Producto_id}")
    public ResponseEntity<String> insertar(
            @PathVariable int Usuario_id,
            @PathVariable int Producto_id,
            @RequestParam int cantidad) {

        boolean insercionExitosa = repository.insertar(Usuario_id, Producto_id, cantidad);

        if (insercionExitosa) {
            return new ResponseEntity<>("El producto/cantidad se añadió con éxito", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("No se pudo añadió el producto/cantidad", HttpStatus.CONFLICT);
        }

    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<Map<String, Object>> consultar(@PathVariable int id) {
        try {
            
            Map<String, Object> resultado = repository.consultar(id);

            
            if (resultado == null || ((List<?>) resultado.get("encabezado")).isEmpty() || ((List<?>) resultado.get("lista")).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
