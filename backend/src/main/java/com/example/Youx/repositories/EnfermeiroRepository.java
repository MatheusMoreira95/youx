package com.example.Youx.repositories;

import com.example.Youx.entities.Enfermeiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface EnfermeiroRepository  extends JpaRepository<Enfermeiro, String> {

    public Optional<Enfermeiro> findByNome(String nome);
    /*@Query(value = "select * from enfermeiro where nome = ?1 and senha = ?2", nativeQuery = true)
     Optional<Enfermeiro> enfermeiro (String username, String password);*/

}
