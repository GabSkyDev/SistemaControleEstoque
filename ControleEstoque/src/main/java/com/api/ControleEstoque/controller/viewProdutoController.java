package com.api.ControleEstoque.controller;

import com.api.ControleEstoque.data.Categoria;
import com.api.ControleEstoque.data.Produto;
import com.api.ControleEstoque.service.CategoriaService;
import com.api.ControleEstoque.service.ProdutoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/produto")
public class viewProdutoController {
    
    @Autowired
    ProdutoService produtoService;
    
    @Autowired
    CategoriaService categoriaService;

    // Página de listagem
    @GetMapping("/listagem")
    public String listarProdutos(Model model) {
        List<Produto> produtos = produtoService.listarTodos();
        model.addAttribute("produtos", produtos);
        return "viewProdutoListagem"; 
    }

    // Página de formulário
    @GetMapping("/novo")
    public String novoProduto(Model model) {
        model.addAttribute("produto", new Produto());
        List<Categoria> categorias = categoriaService.listarTodas();
        model.addAttribute("categorias", categorias);
        return "viewProdutoCadastro"; 
    }

    // Envio do formulário
    @PostMapping("/salvar")
    public String salvarProduto(@ModelAttribute Produto produto) {
        produtoService.salvar(produto);
        return "redirect:/produto/listagem";
    }

    // Editar produto
    @GetMapping("/editar/{id}")
    public String editarProduto(@PathVariable Integer id, Model model) {
        Produto produto = produtoService.buscarPorId(id);
        model.addAttribute("produto", produto);
        return "viewProdutoCadastro";
    }

    // Excluir produto
    @GetMapping("/excluir/{id}")
    public String excluirProduto(@PathVariable Integer id) {
        produtoService.deletar(id);
        return "redirect:/produto/listagem";
    }
}
