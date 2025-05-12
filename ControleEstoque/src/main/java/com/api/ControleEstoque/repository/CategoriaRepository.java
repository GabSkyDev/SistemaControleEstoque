package com.api.ControleEstoque.repository;

import com.api.ControleEstoque.data.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

}