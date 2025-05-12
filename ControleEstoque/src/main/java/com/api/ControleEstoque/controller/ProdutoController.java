package com.api.ControleEstoque.controller;

import com.api.ControleEstoque.data.Produto;
import com.api.ControleEstoque.service.ProdutoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    
    @Autowired
    ProdutoService produtoService;
    
    @GetMapping("/listar")
    public ResponseEntity<List> listarTodas(){
        List<Produto> produtos = produtoService.listarTodos();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }
    
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Integer id){
        Produto produto = produtoService.buscarPorId(id);
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }
    
    @PostMapping("/novo")
    public ResponseEntity<Produto> salvar(@RequestBody Produto produto){
        var novoProduto = produtoService.salvar(produto);
        return new ResponseEntity<>(novoProduto, HttpStatus.OK);
    }
    
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        produtoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
