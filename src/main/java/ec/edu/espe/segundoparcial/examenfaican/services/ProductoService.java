package ec.edu.espe.segundoparcial.examenfaican.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import ec.edu.espe.segundoparcial.examenfaican.dao.ProductoRepository;
import ec.edu.espe.segundoparcial.examenfaican.domain.Comentario;
import ec.edu.espe.segundoparcial.examenfaican.domain.Producto;

import java.util.List;

@Slf4j
@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarProductosPorRuc(String rucEmpresa) {
        log.info("Obteniendo lista de productos para la empresa con RUC: {}", rucEmpresa);
        return productoRepository.findByRucEmpresaOrderByDescripcion(rucEmpresa);
    }

    public Producto obtenerProducto(String codUnico) {
        log.info("Obteniendo producto con código único: {}", codUnico);
        Producto producto = productoRepository.findByCodUnico(codUnico);
        if (producto != null) {
            log.info("Producto obtenido: {}", producto);
        } else {
            log.error("No se encontró un producto con código único: {}", codUnico);
        }
        return producto;
    }

    public List<Comentario> obtenerComentariosPorProducto(String codUnico) {
        log.info("Obteniendo comentarios para el producto con código único: {}", codUnico);
        Producto producto = productoRepository.findByCodUnico(codUnico);
        if (producto != null) {
            log.info("Comentarios obtenidos: {}", producto.getComentarios());
            return producto.getComentarios();
        } else {
            log.error("No se encontró un producto con código único: {}", codUnico);
            return null;
        }
    }

    @Transactional
    public Producto crearProducto(Producto producto) {
        log.info("Creando un nuevo producto: {}", producto);
        return productoRepository.save(producto);
    }

    @Transactional
    public void agregarComentario(String codUnico, Comentario comentario) {
        log.info("Agregando comentario al producto con código único: {}", codUnico);
        Producto producto = productoRepository.findByCodUnico(codUnico);
        if (producto != null) {
            List<Comentario> comentarios = producto.getComentarios();
            comentarios.add(comentario);
            producto.setComentarios(comentarios);
            productoRepository.save(producto);
            log.info("Comentario agregado exitosamente: {}", comentario);
        } else {
            log.error("No se encontró un producto con código único: {}", codUnico);
        }
    }
}
