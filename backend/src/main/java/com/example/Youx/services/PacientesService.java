package com.example.Youx.services;

import com.example.Youx.data.vo.PacientesVo;
import com.example.Youx.data.vo.security.CriptografiaCPF;
import com.example.Youx.entities.Pacientes;
import com.example.Youx.mapper.DozerMapper;
import com.example.Youx.repositories.PacientesRepository;
import com.example.Youx.services.exceptions.ResourceNotFoundException;
import com.example.Youx.services.exceptions.ResourceUnprocessableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;

@Service
public class PacientesService {

    @Autowired
    private final PacientesRepository repository;

    CriptografiaCPF criptografiaCPF = new CriptografiaCPF();


    public PacientesService(PacientesRepository repository) {

        this.repository = repository;
    }

    public List<PacientesVo> findAll() {
        return DozerMapper.parseListObjects(repository.findAll(), PacientesVo.class);

    }

    public PacientesVo findById(String cpf) {
        String cpfcriptografado = criptografiaCPF.criptografar(cpf);
        var obj = repository.findById(cpfcriptografado).orElseThrow(() -> new ResourceNotFoundException(cpfcriptografado));
        obj.setCpf(criptografiaCPF.descriptografar(obj.getCpf()));
        return DozerMapper.parseObject(obj, PacientesVo.class);
    }

    //metodo recebe um Vo como parametro
    public PacientesVo insert(PacientesVo pacientes) throws ValidationException {
        //verifica se o CPF já está cadastrado
        if (verificarCpf(criptografiaCPF.criptografar(pacientes.getCpf()))) {
            //converte PacientesVo em Pacientes para salvar no banco
            var obj = DozerMapper.parseObject(pacientes, Pacientes.class);
            obj.setCpf(criptografiaCPF.criptografar(obj.getCpf()));
            //Converte Pacientes para PacientesVo para retornar na chamada da função
            var vo = DozerMapper.parseObject(repository.save(obj), PacientesVo.class);
            return vo;
        } else {
            //caso já tenha o CPF cadastrado retorna uma exceção
            throw new ResourceUnprocessableException(pacientes.getCpf());

        }
    }

    public Boolean verificarCpf(String cpf) {
        //verifica direto no repository se existe o registro
        Optional<Pacientes> pacientes = repository.findById(cpf);
        Boolean valid = false;
        //caso não encontre o CPF retorna true
        if (pacientes.isEmpty()) {
            valid = true;
        }
        return valid;
    }


}
