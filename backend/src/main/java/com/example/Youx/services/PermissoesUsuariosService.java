package com.example.Youx.services;

import com.example.Youx.data.vo.PermissoesUsuario;
import com.example.Youx.repositories.PermissoesUsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissoesUsuariosService {

    @Autowired
    PermissoesUsuariosRepository permissoesUsuariosRepository;

    public PermissoesUsuario inserir(PermissoesUsuario permissoesUsuario) {
        return permissoesUsuariosRepository.save(permissoesUsuario);

    }
    public List<PermissoesUsuario> findAll() {
         return  permissoesUsuariosRepository.findAll();

    }
}
