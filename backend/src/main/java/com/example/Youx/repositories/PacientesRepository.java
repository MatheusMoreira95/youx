package com.example.Youx.repositories;


import com.example.Youx.entities.Pacientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PacientesRepository extends JpaRepository<Pacientes, String> {


}
