package com.api.ControleEstoque.repository;

import com.api.ControleEstoque.data.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque, Integer>{
    
}