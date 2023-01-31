package com.example.Youx.data.vo;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

//classe criada para inserir a permissão de enfermeiro
//usa mesmo nome e caracteristica da tabela criada em Enfermeiro no relacionamento ManyToMany
@Entity
@Table(name = "permissoes_usuarios")
public class PermissoesUsuario implements Serializable {

    @Id
    private String cpf;
    private Integer id_permissao;

    public PermissoesUsuario(String cpf, Integer id_permissao) {
        this.cpf = cpf;
        this.id_permissao = id_permissao;
    }

    public PermissoesUsuario() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getId_permissao() {
        return id_permissao;
    }

    public void setId_permissao(Integer id_permissao) {
        this.id_permissao = id_permissao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PermissoesUsuario that)) return false;

        if (!Objects.equals(cpf, that.cpf)) return false;
        return Objects.equals(id_permissao, that.id_permissao);
    }

    @Override
    public int hashCode() {
        int result = cpf != null ? cpf.hashCode() : 0;
        result = 31 * result + (id_permissao != null ? id_permissao.hashCode() : 0);
        return result;
    }
}
