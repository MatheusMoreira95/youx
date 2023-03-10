package com.example.Youx.services;

import com.example.Youx.data.vo.EnfermeiroVo;
import com.example.Youx.data.vo.PacientesVo;
import com.example.Youx.data.vo.PermissoesUsuario;
import com.example.Youx.data.vo.security.CriptografiaCPF;
import com.example.Youx.entities.Enfermeiro;
import com.example.Youx.entities.Pacientes;
import com.example.Youx.entities.enums.Permissao;
import com.example.Youx.mapper.DozerMapper;
import com.example.Youx.repositories.EnfermeiroRepository;
import com.example.Youx.services.exceptions.DatabaseException;
import com.example.Youx.services.exceptions.ResourceNotFoundException;
import com.example.Youx.services.exceptions.ResourceUnprocessableException;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    PermissoesUsuariosService permissoesUsuariosService;

    //recebe EnfermeiroVo da chamada do metodo
    public EnfermeiroVo insert(EnfermeiroVo enfermeiro) throws ValidationException {

        if (verificarCpf(criptografiaCPF.criptografar(enfermeiro.getCpf()))) {
            enfermeiro.setSenha(encoder.encode(enfermeiro.getSenha()));
            //convert EnfermeiroVO em Enfermeiro
            var obj = DozerMapper.parseObject(enfermeiro, Enfermeiro.class);
            obj.setCpf(criptografiaCPF.criptografar(obj.getCpf()));
            //Salva o objeto Enfermeiro e convert para EnfermeiroVo para retorno do metodo
            var vo = DozerMapper.parseObject(repository.save(obj), EnfermeiroVo.class);
            PermissoesUsuario permissoesUsuario =   new PermissoesUsuario(criptografiaCPF.descriptografar(vo.getCpf()),2);
            permissoesUsuariosService.inserir(permissoesUsuario);
            return vo;
        } else {
            //caso j?? tenha o CPF cadastrado retorna uma exce????o
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

    public void delete(String cpf){
        String cpf_cripto = criptografiaCPF.criptografar(cpf);
        try {
            repository.deleteById(cpf_cripto);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(cpf_cripto);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
    public Boolean verificarCpf(String cpf) {
        //verifica direto no repository se existe o registro
        Optional<Enfermeiro> enfermeiro = repository.findById(cpf);
        Boolean valid = false;
        //caso n??o encontre o CPF retorna true
        if (enfermeiro.isEmpty()) {
            valid = true;
        }
        return valid;
    }
    public EnfermeiroVo update(String cpf , EnfermeiroVo obj){
        String cpf_cripto = criptografiaCPF.criptografar(cpf);
        try {
            Enfermeiro entity = repository.getReferenceById(cpf_cripto);
            var obj2 = DozerMapper.parseObject(obj, Enfermeiro.class);
            obj2.setSenha(encoder.encode(obj2.getSenha()));
            updata(entity,obj2);
            var vo = DozerMapper.parseObject(repository.save(entity),EnfermeiroVo.class);
            return vo;
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(cpf);
        }
    }

    private void updata(Enfermeiro entity, Enfermeiro obj) {

        entity.setNome(obj.getNome());
        entity.setSenha(obj.getSenha());
    }


}