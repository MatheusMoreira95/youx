package com.example.Youx.rersources;

import com.example.Youx.data.vo.PacientesVo;
import com.example.Youx.services.PacientesService;
import com.example.Youx.services.exceptions.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping(value = "/pacientes")
public class PacientesResource {

    @Autowired
    private PacientesService services;

    //@PreAuthorize("hasAnyRole('PERMISSAO_ADM','PERMISSAO_USER')")
    // annotation vai ser descontinuida junto com  @EnableGlobalMethodSecurity
    @GetMapping
    public ResponseEntity<List<PacientesVo>> findAll() {
        List<PacientesVo> list = services.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{cpf}")
    public ResponseEntity<PacientesVo> findById(@PathVariable String cpf) {
        PacientesVo obj = services.findById(cpf);
        return ResponseEntity.ok().body(obj);
    }

    // @PreAuthorize("hasRole('PERMISSAO_ADM')")
    // annotation vai ser descontinuida junto com  @EnableGlobalMethodSecurity
    @PostMapping
    public ResponseEntity<PacientesVo> insert(@RequestBody PacientesVo obj) throws ValidationException {
        try {
            obj = services.insert(obj);
            return ResponseEntity.ok().body(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
