package mx.itlp.AmazonAPI.Controllers;

import java.util.List;

//import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
//Importaciones de las notaciones
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.itlp.AmazonAPI.Models.Categoria;
import mx.itlp.AmazonAPI.Models.Producto;
import mx.itlp.AmazonAPI.Repository.CategoriaJDBC;

//Se agregan las notaciones de Lombok
@RestController
@RequestMapping("/Categorias")

public class CategoriaController {

    @Autowired
    CategoriaJDBC repo;

    @GetMapping
    // Se regresa una lista de categorias
    public List<Categoria> consultar() {
        return repo.consultar();
    }

    @GetMapping("/{id}")
    // En lugar de regresar un categoria, se utiliza un ResponseEntity. El metodo
    // tiene como parametro un ID, debido a que es una variable de la ruta se
    // ddeclara con un @PathVariable
    public ResponseEntity<Categoria> buscar(@PathVariable int id) {
        // Se define un "resultado" en valor nulo
        Categoria resultado = null;

        // En caso de no encontrar el elemento por ID, se utiliza una excepcion, en
        // donde en lugar de marcar un error 500, muestra el estatus 204 sin contenido.
        // Con esto no se truena el back-end, no se debe acostumbrar a ver excepciones
        try {
            // TODO: handle exception
            // Se obtiene el elemento buscado por ID y lo almacena en la variable
            // "resultado"
            resultado = repo.buscar(id);
            // La respuesta que regresa el controlar un objeto "ResponseEntity", regresando
            // el "resultado" y un codigo de estado
            return new ResponseEntity<Categoria>(resultado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    @PostMapping
    public ResponseEntity<String> insertar(@RequestParam String nombre) {
        try {
            repo.insertar(nombre);
            return new ResponseEntity<>("La categoria se agrego con exito", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("No se pudo insertar la categoria", HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificar(@PathVariable int id, @RequestParam String nombre) {
        int resultado = repo.modificar(id, nombre);
        if (resultado > 0) {
            return new ResponseEntity<>("La categoria fue actualizada", HttpStatus.OK);

        } else {
            return new ResponseEntity<>("No se pudo modificar la categoria", HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> desactivar(@PathVariable int id) {
        repo.desactivar(id);
        return new ResponseEntity<>("La categoria fue eliminada", HttpStatus.OK);
    }

    @GetMapping("/{id}/productos")
    public ResponseEntity<List<Producto>> buscarProductos(@PathVariable int id) {
        try {
            List<Producto> resultados = repo.buscarProductos(id);

            if (resultados == null || resultados.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(resultados, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
