package com.example.aula.repository;


import com.example.aula.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Cliente, Long> {
    //testyeete
}
