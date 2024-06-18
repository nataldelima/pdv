package com.dio.pdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dio.pdv.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
