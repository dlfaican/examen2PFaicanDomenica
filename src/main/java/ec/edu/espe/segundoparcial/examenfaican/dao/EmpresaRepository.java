package ec.edu.espe.segundoparcial.examenfaican.dao;

import ec.edu.espe.segundoparcial.examenfaican.domain.Empresa;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmpresaRepository extends MongoRepository<Empresa, String> {
     Empresa findByRuc(String ruc);
}
