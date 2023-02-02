package com.example.Youx.data.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;


public class PacientesVo implements Serializable {

    private String cpf;
    private String nome;
    private LocalDate dataNascimento;
    private Double peso;
    private Double Altura;
    private String UF;

    public PacientesVo() {
    }


    public PacientesVo(String cpf, String nome,LocalDate  dataNascimento, Double peso, Double altura, String UF) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.peso = peso;
        Altura = altura;
        this.UF = UF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate  getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate  dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return Altura;
    }

    public void setAltura(Double altura) {
        Altura = altura;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

}
