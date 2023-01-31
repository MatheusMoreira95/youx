package com.example.Youx.repositories;

import com.example.Youx.entities.Enfermeiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface EnfermeiroRepository  extends JpaRepository<Enfermeiro, String> {

    public Optional<Enfermeiro> findByNome(String nome);

}
