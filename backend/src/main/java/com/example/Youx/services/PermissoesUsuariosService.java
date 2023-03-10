package com.example.Youx.services;

import com.example.Youx.data.vo.PermissoesUsuario;
import com.example.Youx.data.vo.security.CriptografiaCPF;
import com.example.Youx.repositories.PermissoesUsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissoesUsuariosService {


    CriptografiaCPF criptografiaCPF = new CriptografiaCPF();
    @Autowired
    PermissoesUsuariosRepository permissoesUsuariosRepository;

    public PermissoesUsuario inserir(PermissoesUsuario permissoesUsuario) {
        permissoesUsuario.setCpf(criptografiaCPF.criptografar(permissoesUsuario.getCpf()));
        return permissoesUsuariosRepository.save(permissoesUsuario);
    }
    public List<PermissoesUsuario> findAll() {
         return  permissoesUsuariosRepository.findAll();

    }
}
