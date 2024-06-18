package com.dio.pdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dio.pdv.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
