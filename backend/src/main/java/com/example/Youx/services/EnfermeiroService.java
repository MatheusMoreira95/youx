package com.example.Youx.services;

import com.example.Youx.data.vo.EnfermeiroVo;
import com.example.Youx.data.vo.PacientesVo;
import com.example.Youx.entities.Enfermeiro;
import com.example.Youx.entities.Pacientes;
import com.example.Youx.mapper.DozerMapper;
import com.example.Youx.repositories.EnfermeiroRepository;
import com.example.Youx.repositories.PermissoesUsuariosRepository;
import com.example.Youx.services.exceptions.ResourceNotFoundException;
import com.example.Youx.services.exceptions.ResourceUnprocessableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;

@Service
public class EnfermeiroService {

    @Autowired
    EnfermeiroRepository repository;

    @Autowired
    PasswordEncoder encoder;


    //recebe EnfermeiroVo da chamada do metodo
    public EnfermeiroVo insert(EnfermeiroVo enfermeiro) throws ValidationException {

        if (verificarCpf(enfermeiro.getCpf())) {
            enfermeiro.setSenha(encoder.encode(enfermeiro.getSenha()));
            //convert EnfermeiroVO em Enfermeiro
            var obj = DozerMapper.parseObject(enfermeiro, Enfermeiro.class);
            //Salva o objeto Enfermeiro e convert para EnfermeiroVo para retorno do metodo
            var vo = DozerMapper.parseObject(repository.save(obj), EnfermeiroVo.class);
            return vo;
        } else {
            //caso já tenha o CPF cadastrado retorna uma exceção
            throw new ResourceUnprocessableException(enfermeiro.getCpf());
        }
    }

    public EnfermeiroVo findById(String cpf) {
        var obj = repository.findById(cpf).orElseThrow(() -> new ResourceNotFoundException(cpf));
        return DozerMapper.parseObject(obj, EnfermeiroVo.class);
    }

    public List<EnfermeiroVo> findAll() {
        return DozerMapper.parseListObjects(repository.findAll(), EnfermeiroVo.class);

    }

    public Boolean verificarCpf(String cpf) {
        //verifica direto no repository se existe o registro
        Optional<Enfermeiro> enfermeiro = repository.findById(cpf);
        Boolean valid = false;
        //caso não encontre o CPF retorna true
        if (enfermeiro.isEmpty()) {
            valid = true;
        }
        return valid;
    }


}