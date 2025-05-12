package com.api.ControleEstoque.controller;

import com.api.ControleEstoque.data.Estoque;
import com.api.ControleEstoque.service.EstoqueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
    
    @Autowired
    EstoqueService estoqueService;
    
    @GetMapping("/listar")
    public ResponseEntity<List> listarTodas(){
        List<Estoque> estoques = estoqueService.listarTodos();
        return new ResponseEntity<>(estoques, HttpStatus.OK);
    }
    
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Estoque> buscarPorId(@PathVariable Integer id){
        Estoque estoque = estoqueService.buscarPorId(id);
        return new ResponseEntity<>(estoque, HttpStatus.OK);
    }
    
    @PostMapping("/novo")
    public ResponseEntity<Estoque> salvar(@RequestBody Estoque estoque) {
        Estoque novoEstoque = estoqueService.registrarMovimentacao(estoque);
        return new ResponseEntity<>(novoEstoque, HttpStatus.OK);
    }
    
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        estoqueService.deletar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
