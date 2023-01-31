package com.example.Youx.rersources;


import com.example.Youx.data.vo.EnfermeiroVo;
import com.example.Youx.services.EnfermeiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/enfermeiro")
public class EnfermeiroResource {

    @Autowired
    EnfermeiroService service;

    @GetMapping(value = "/{cpf}")
    public ResponseEntity<EnfermeiroVo> findById(@PathVariable String cpf) {
        EnfermeiroVo obj = service.findById(cpf);
        return ResponseEntity.ok().body(obj);
    }


    @PostMapping
    public ResponseEntity<EnfermeiroVo> insert(@RequestBody EnfermeiroVo obj) {

        obj = service.insert(obj);
    //    obj.setSenha();
        return ResponseEntity.ok().body(obj);
    }


}
