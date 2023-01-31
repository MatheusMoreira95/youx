package com.example.Youx.entities;

import com.example.Youx.entities.enums.Permissao;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@Entity
@Table(name = "Permissoes")
public class Role implements GrantedAuthority , Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permissao")
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private Permissao permissaoNome;

    @Override
    public String getAuthority() {
        return this.permissaoNome.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Permissao getPermissaoNome() {
        return permissaoNome;
    }

    public void setPermissaoNome(Permissao permissaoNome) {
        this.permissaoNome = permissaoNome;
    }
}
