package com.example.Youx.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;


@Entity
public class Enfermeiro implements UserDetails {

    @Id
    private String cpf;
    @Column(unique = true, nullable = false)
    private String nome;
    @Column(nullable = false)
    @JsonIgnore
    private String senha;

    public Enfermeiro() {
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Enfermeiro(String cpf, String nome, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
    }

    @ManyToMany
    @JoinTable(name = "Permissoes_Usuarios",
            joinColumns = @JoinColumn(name = "cpf"),
            inverseJoinColumns = @JoinColumn(name = "id_permissao"))
    private List<Role> roles;

    public String getCpf() {
        return cpf;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @JsonIgnore
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Enfermeiro that)) return false;

        return Objects.equals(cpf, that.cpf);
    }

    @Override
    public int hashCode() {
        return cpf != null ? cpf.hashCode() : 0;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
