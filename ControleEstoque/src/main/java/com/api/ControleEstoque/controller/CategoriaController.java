package com.api.ControleEstoque.controller;

import com.api.ControleEstoque.data.Categoria;
import com.api.ControleEstoque.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/listar")
    public ResponseEntity<List> listarTodas() {
        List<Categoria> categorias = categoriaService.listarTodas();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Integer id) {
        Categoria categoria = categoriaService.buscarPorId(id);
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @PostMapping("/novo")
    public ResponseEntity<Categoria> salvar(@RequestBody Categoria categoria) {
        Categoria novaCategoria = categoriaService.salvar(categoria);
        return new ResponseEntity<>(novaCategoria, HttpStatus.OK);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        categoriaService.deletarPorId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
