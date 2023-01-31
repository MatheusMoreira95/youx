package com.example.Youx.services;

import com.example.Youx.data.vo.EnfermeiroVo;
import com.example.Youx.data.vo.security.CriptografiaCPF;
import com.example.Youx.entities.Enfermeiro;
import com.example.Youx.mapper.DozerMapper;
import com.example.Youx.repositories.EnfermeiroRepository;
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

    CriptografiaCPF criptografiaCPF = new CriptografiaCPF();
    @Autowired
    PasswordEncoder encoder;


    //recebe EnfermeiroVo da chamada do metodo
    public EnfermeiroVo insert(EnfermeiroVo enfermeiro) throws ValidationException {

        if (verificarCpf(criptografiaCPF.criptografar(enfermeiro.getCpf()))) {
            enfermeiro.setSenha(encoder.encode(enfermeiro.getSenha()));
            //convert EnfermeiroVO em Enfermeiro
            var obj = DozerMapper.parseObject(enfermeiro, Enfermeiro.class);
            obj.setCpf(criptografiaCPF.criptografar(obj.getCpf()));
            //Salva o objeto Enfermeiro e convert para EnfermeiroVo para retorno do metodo
            var vo = DozerMapper.parseObject(repository.save(obj), EnfermeiroVo.class);
            return vo;
        } else {
            //caso já tenha o CPF cadastrado retorna uma exceção
            throw new ResourceUnprocessableException(enfermeiro.getCpf());
        }
    }

    public EnfermeiroVo findById(String cpf) {
        String cpfcriptografado = criptografiaCPF.criptografar(cpf);
        var obj = repository.findById(cpfcriptografado).orElseThrow(() -> new ResourceNotFoundException(cpfcriptografado));
        obj.setCpf(criptografiaCPF.descriptografar(obj.getCpf()));
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