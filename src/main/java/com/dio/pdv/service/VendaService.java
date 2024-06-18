package com.dio.pdv.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dio.pdv.entity.Produto;
import com.dio.pdv.entity.Venda;
import com.dio.pdv.repository.ProdutoRepository;
import com.dio.pdv.repository.VendaRepository;

@Service
public class VendaService {
    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Venda> findAll() {
        return vendaRepository.findAll();
    }

    public Optional<Venda> findById(Long id) {
        return vendaRepository.findById(id);
    }

    public Venda save(Venda venda) {
        venda.setDataHora(LocalDateTime.now());
        return vendaRepository.save(venda);
    }

    public void deleteById(Long id) {
        vendaRepository.deleteById(id);
    }

    public Venda addProduto(Long vendaId, Long produtoId) {
        Optional<Venda> vendaOpt = vendaRepository.findById(vendaId);
        Optional<Produto> produtoOpt = produtoRepository.findById(produtoId);

        if (vendaOpt.isPresent() && produtoOpt.isPresent()) {
            Venda venda = vendaOpt.get();
            Produto produto = produtoOpt.get();
            venda.getProdutos().add(produto);
            return vendaRepository.save(venda);
        } else {
            throw new RuntimeException("Venda ou Produto não encontrado");
        }
    }
}
