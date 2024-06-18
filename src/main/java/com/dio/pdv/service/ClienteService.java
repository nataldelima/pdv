package com.dio.pdv.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dio.pdv.entity.Cliente;
import com.dio.pdv.repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }
}
