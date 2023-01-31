package com.example.Youx.services;

import com.example.Youx.data.vo.EnfermeiroVo;
import com.example.Youx.entities.Enfermeiro;
import com.example.Youx.mapper.DozerMapper;
import com.example.Youx.repositories.EnfermeiroRepository;
import com.example.Youx.repositories.PermissoesUsuariosRepository;
import com.example.Youx.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EnfermeiroService {

    @Autowired
     EnfermeiroRepository repository;

    @Autowired
    PasswordEncoder encoder;





    //recebe EnfermeiroVo da chamada do metodo
    public EnfermeiroVo insert(EnfermeiroVo enfermeiro) {

        enfermeiro.setSenha(encoder.encode(enfermeiro.getSenha()));
        //convert EnfermeiroVO em Enfermeiro
        var obj = DozerMapper.parseObject(enfermeiro, Enfermeiro.class);
        //Salva o objeto Enfermeiro e convert para EnfermeiroVo para retorno do metodo
        var vo = DozerMapper.parseObject(repository.save(obj), EnfermeiroVo.class);
        return vo;
    }

    public EnfermeiroVo findById(String cpf) {
        var obj = repository.findById(cpf).orElseThrow(() -> new ResourceNotFoundException(cpf));
        return DozerMapper.parseObject(obj, EnfermeiroVo.class);
    }


}