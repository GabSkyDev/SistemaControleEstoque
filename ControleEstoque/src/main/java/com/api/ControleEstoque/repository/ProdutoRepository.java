package com.api.ControleEstoque.repository;

import com.api.ControleEstoque.data.Produto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
    List<Produto> findByQuantidadeLessThan(Integer limte);
}