package com.dio.pdv.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dio.pdv.entity.Produto;
import com.dio.pdv.service.ProdutoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> findAll() {
        return produtoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id) {
        Optional<Produto> produto = produtoService.findById(id);
        return produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Produto create(@RequestBody Produto produto) {
        return produtoService.save(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody Produto produto) {
        if (!produtoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        produto.setId(id);
        Produto updatedProduto = produtoService.save(produto);
        return ResponseEntity.ok(updatedProduto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!produtoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        produtoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
