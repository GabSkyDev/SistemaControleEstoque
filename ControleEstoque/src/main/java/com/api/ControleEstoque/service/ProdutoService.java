package com.api.ControleEstoque.service;

import com.api.ControleEstoque.data.Produto;
import com.api.ControleEstoque.repository.ProdutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;
    
    public Produto salvar(Produto produto){
        return produtoRepository.save(produto);
    }
    
    public Produto buscarPorId(Integer id){
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
    }
    
    public List<Produto> listarTodos(){
        return produtoRepository.findAll();
    }
    
    public void deletar(Integer id){
        if (!produtoRepository.existsById(id)){
            throw new RuntimeException("Produto não existe!");
        }
        produtoRepository.deleteById(id);
    }
    
    public List<Produto> buscarEstoqueBaixo(int limite){
        return produtoRepository.findByQuantidadeLessThan(limite);
    }
    
    public Produto atualizar(Integer id, Produto produtoRequest){
        Produto produto = buscarPorId(id);
        
        produto.setNome(produtoRequest.getNome());
        produto.setDescricao(produtoRequest.getDescricao());
        produto.setPreco(produtoRequest.getPreco());
        produto.setQuantidade(produtoRequest.getQuantidade());
        produto.setEstoqueMinimo(produtoRequest.getEstoqueMinimo());
        
        return produtoRepository.save(produto);
    }
}