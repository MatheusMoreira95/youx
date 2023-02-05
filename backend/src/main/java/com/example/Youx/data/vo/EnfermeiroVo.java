package com.example.Youx.data.vo;


import com.example.Youx.entities.enums.Permissao;

public class EnfermeiroVo {

    private String cpf;
    private String nome;
    private String senha;

    public EnfermeiroVo() {
    }

    public EnfermeiroVo(String cpf, String nome, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


}