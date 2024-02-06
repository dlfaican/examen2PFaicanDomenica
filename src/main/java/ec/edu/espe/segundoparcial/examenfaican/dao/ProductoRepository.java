package ec.edu.espe.segundoparcial.examenfaican.dao;

import ec.edu.espe.segundoparcial.examenfaican.domain.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductoRepository extends MongoRepository<Producto, String> {
    List<Producto> findByRucEmpresaOrderByDescripcion(String rucEmpresa);
    Producto findByCodUnico(String codUnico);
}
