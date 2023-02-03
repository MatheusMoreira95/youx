package com.example.Youx.rersources;


import com.example.Youx.data.vo.EnfermeiroVo;
import com.example.Youx.data.vo.security.CriptografiaCPF;
import com.example.Youx.services.EnfermeiroService;
import com.example.Youx.services.exceptions.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping(value = "/enfermeiro")
public class EnfermeiroResource {

    @Autowired
    EnfermeiroService services;
    CriptografiaCPF criptografiaCPF = new CriptografiaCPF();

    //@PreAuthorize("hasAnyAuthority('PERMISSAO_ADM' ,'PERMISSAO_MEDICO')")
    @GetMapping(value = "/{cpf}")
    public ResponseEntity<EnfermeiroVo> findById(@PathVariable String cpf) {
        EnfermeiroVo obj = services.findById(cpf);
        return ResponseEntity.ok().body(obj);
    }

   // @PreAuthorize("hasAnyAuthority('PERMISSAO_MEDICO','PERMISSAO_ADM' )")
    @GetMapping
    public ResponseEntity<List<EnfermeiroVo>> findAll() {
        List<EnfermeiroVo> list = services.findAll();
        list.forEach((obj) -> obj.setCpf(criptografiaCPF.descriptografar(obj.getCpf())));
        return ResponseEntity.ok().body(list);
    }


  //  @PreAuthorize("hasAnyAuthority('PERMISSAO_MEDICO')")
    @PostMapping
    public ResponseEntity<EnfermeiroVo> insert(@RequestBody EnfermeiroVo obj) throws ValidationException {
        try {
            obj = services.insert(obj);
            return ResponseEntity.ok().body(obj);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }


}
