package com.example.Youx.rersources;

import com.example.Youx.data.vo.PermissoesUsuario;
import com.example.Youx.services.PermissoesUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/permissoes")
public class PermissoesUsuarioResource {

    @Autowired
    PermissoesUsuariosService permissoesUsuariosService;

    @PreAuthorize("hasAuthority('PERMISSAO_MEDICO')")
    @PostMapping
    public ResponseEntity<PermissoesUsuario> save(@RequestBody PermissoesUsuario permissoesUsuario){
        permissoesUsuariosService.inserir(permissoesUsuario);
        return ResponseEntity.ok().body(permissoesUsuario);
    }

    @PreAuthorize("hasAuthority('PERMISSAO_MEDICO')")
    @GetMapping
    public ResponseEntity<List<PermissoesUsuario>> findAll() {
        List<PermissoesUsuario> list = permissoesUsuariosService.findAll();
        return ResponseEntity.ok().body(list);
    }
}
