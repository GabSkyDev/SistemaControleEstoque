package com.api.ControleEstoque.service;

import com.api.ControleEstoque.data.Estoque;
import com.api.ControleEstoque.data.Produto;
import com.api.ControleEstoque.data.TipoMovimentacao;
import com.api.ControleEstoque.repository.EstoqueRepository;
import com.api.ControleEstoque.repository.ProdutoRepository;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private final ProdutoRepository produtoRepository;

    public EstoqueService(EstoqueRepository estoqueRepository, ProdutoRepository produtoRepository) {
        this.estoqueRepository = estoqueRepository;
        this.produtoRepository = produtoRepository;
    }

    public Estoque registrarMovimentacao(Estoque estoque) {
        Produto produto = produtoRepository.findById(estoque.getProduto().getId()).orElse(null);

        if (produto == null) {
            throw new RuntimeException("Produto não encontrado!");
        }

        Integer quantidadeAtual = produto.getQuantidade();
        Integer quantidadeMovimentada = estoque.getQuantidade();

        if (estoque.getTipo() == TipoMovimentacao.ENTRADA) {
            produto.setQuantidade(quantidadeAtual + quantidadeMovimentada);
        } else if (estoque.getTipo() == TipoMovimentacao.SAIDA) {
            if (quantidadeMovimentada > quantidadeAtual) {
                throw new RuntimeException("Estoque insuficiente para saída");
            }
            produto.setQuantidade(quantidadeAtual - quantidadeMovimentada);
        }

        produtoRepository.save(produto);
        estoque.setData(LocalDateTime.now());
        return estoqueRepository.save(estoque);
    }

    public List<Estoque> listarTodos() {
        return estoqueRepository.findAll();
    }

    public Estoque buscarPorId(Integer id) {
        return estoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado!"));
    }

    public void deletar(Integer id) {
        if (!estoqueRepository.existsById(id)) {
            throw new RuntimeException("Estoque não existe!");
        }
        estoqueRepository.deleteById(id);
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public List<Produto> produtosComEstoqueBaixo() {
        return produtoRepository.findAll()
                .stream()
                .filter(p -> p.getQuantidade() < p.getEstoqueMinimo())
                .toList();
    }

    public int totalProdutos() {
        return (int) produtoRepository.count();
    }

    public int totalQuantidadeEstoque() {
        return produtoRepository.findAll()
                .stream()
                .mapToInt(Produto::getQuantidade)
                .sum();
    }
}