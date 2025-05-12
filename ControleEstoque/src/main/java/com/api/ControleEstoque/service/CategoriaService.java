package com.api.ControleEstoque.service;

import com.api.ControleEstoque.data.Categoria;
import com.api.ControleEstoque.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public Categoria salvar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria buscarPorId(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com ID: " + id));
    }

    public void deletarPorId(Integer id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoria não existe com ID: " + id);
        }
        categoriaRepository.deleteById(id);
    }
}
