package com.example.Youx.repositories;


import com.example.Youx.data.vo.PermissoesUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PermissoesUsuariosRepository extends JpaRepository<PermissoesUsuario, Integer> {
}
