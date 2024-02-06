package ec.edu.espe.segundoparcial.examenfaican.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.segundoparcial.examenfaican.domain.Empresa;
import ec.edu.espe.segundoparcial.examenfaican.services.EmpresaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping("/{ruc}")
    public ResponseEntity<Empresa> obtenerEmpresaPorRuc(@PathVariable("ruc") String ruc) {
        try {
            return ResponseEntity.ok(empresaService.obtenerPorRuc(ruc));
        } catch(RuntimeException e) {
            log.error("Error al obtener empresa por RUC: {}", ruc, e);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<Empresa> crearEmpresa(@RequestBody Empresa empresa) {
        log.info("Creacion de la empresa: {}", empresa);
        try {
            return ResponseEntity.ok(empresaService.crearEmpresa(empresa));
        } catch(RuntimeException e) {
            log.error("Error al crear la empresa", e);
            return ResponseEntity.badRequest().build();
        }
    }
}
