package ec.edu.espe.segundoparcial.examenfaican.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.segundoparcial.examenfaican.domain.Comentario;
import ec.edu.espe.segundoparcial.examenfaican.domain.Producto;
import ec.edu.espe.segundoparcial.examenfaican.services.ProductoService;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/listar/{rucEmpresa}")
    public ResponseEntity<List<Producto>> listarProductosPorRuc(@PathVariable String rucEmpresa) {
        try {
            return ResponseEntity.ok(productoService.listarProductosPorRuc(rucEmpresa));
        } catch(RuntimeException e) {
            log.error("Error al obtener lista de productos por RUC: {}", rucEmpresa, e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{codUnico}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable String codUnico) {
        try {
            return ResponseEntity.ok(productoService.obtenerProducto(codUnico));
        } catch(RuntimeException e) {
            log.error("Error al obtener producto por código único: {}", codUnico, e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/comentarios/{codUnico}")
    public ResponseEntity<List<Comentario>> obtenerComentariosPorProducto(@PathVariable String codUnico) {
        try {
            return ResponseEntity.ok(productoService.obtenerComentariosPorProducto(codUnico));
        } catch(RuntimeException e) {
            log.error("Error al obtener comentarios por código único: {}", codUnico, e);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        try {
            return ResponseEntity.ok(productoService.crearProducto(producto));
        } catch(RuntimeException e) {
            log.error("Error al crear el producto", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/comentarios/agregar/{codUnico}")
    public ResponseEntity<Void> agregarComentario(@PathVariable String codUnico, @RequestBody Comentario comentario) {
        try {
            productoService.agregarComentario(codUnico, comentario);
            return ResponseEntity.ok().build();
        } catch(RuntimeException e) {
            log.error("Error al agregar comentario al producto con código único: {}", codUnico, e);
            return ResponseEntity.badRequest().build();
        }
    }
}
