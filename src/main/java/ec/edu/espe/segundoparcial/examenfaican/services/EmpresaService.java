package ec.edu.espe.segundoparcial.examenfaican.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.edu.espe.segundoparcial.examenfaican.dao.EmpresaRepository;
import ec.edu.espe.segundoparcial.examenfaican.domain.Empresa;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Empresa obtenerPorRuc(String ruc) {
        log.info("Obteniendo empresa con RUC: {}", ruc);
        Empresa empresa = empresaRepository.findByRuc(ruc);
        if (empresa != null) {
            log.info("Empresa obtenida: {}", empresa);
        } else {
            log.error("No se encontró una empresa con RUC: {}", ruc);
        }
        return empresa;
    }

    @Transactional
    public Empresa crearEmpresa(Empresa empresa) {
        try {
            log.info("Creando una nueva empresa: {}", empresa);
            empresa.setFechaCreacion(new Date());
            empresa = empresaRepository.save(empresa);
            log.info("Empresa creada exitosamente: {}", empresa);
            return empresa;
        } catch (Exception e) {
            log.error("Error al crear la empresa.", e);
            throw new RuntimeException("Error al crear la empresa.", e);
        }
    }
}
