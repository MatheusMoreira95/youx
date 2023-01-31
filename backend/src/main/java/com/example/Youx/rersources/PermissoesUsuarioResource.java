package com.example.Youx.rersources;

import com.example.Youx.data.vo.PermissoesUsuario;
import com.example.Youx.services.PermissoesUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/permissoes")
public class PermissoesUsuarioResource {

    @Autowired
    PermissoesUsuariosService permissoesUsuariosService;

    @PostMapping
    public ResponseEntity<PermissoesUsuario> save(@RequestBody PermissoesUsuario permissoesUsuario){
        permissoesUsuariosService.inserir(permissoesUsuario);
        return ResponseEntity.ok().body(permissoesUsuario);
    }

    @GetMapping
    public ResponseEntity<List<PermissoesUsuario>> findAll() {
        List<PermissoesUsuario> list = permissoesUsuariosService.findAll();
        return ResponseEntity.ok().body(list);
    }
}
