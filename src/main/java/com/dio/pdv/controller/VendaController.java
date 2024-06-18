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

import com.dio.pdv.entity.Venda;
import com.dio.pdv.service.VendaService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/venda")
public class VendaController {
    @Autowired
    private VendaService vendaService;

    @GetMapping
    public List<Venda> findAll() {
        return vendaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> findById(@PathVariable Long id) {
        Optional<Venda> venda = vendaService.findById(id);
        return venda.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Venda create(@RequestBody Venda venda) {
        return vendaService.save(venda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venda> update(@PathVariable Long id, @RequestBody Venda venda) {
        if (!vendaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        venda.setId(id);
        Venda updatedVenda = vendaService.save(venda);
        return ResponseEntity.ok(updatedVenda);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!vendaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        vendaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{vendaId}/produtos/{produtoId}")
    public ResponseEntity<Venda> addProduto(@PathVariable Long vendaId, @PathVariable Long produtoId) {
        try {
            Venda venda = vendaService.addProduto(vendaId, produtoId);
            return ResponseEntity.ok(venda);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
