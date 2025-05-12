package com.api.ControleEstoque.controller;

import com.api.ControleEstoque.data.Estoque;
import com.api.ControleEstoque.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/estoque")
public class viewEstoqueController {
    
    @Autowired
    EstoqueService estoqueService;

    // Página Inicial
    @GetMapping("/inicial")
    public String paginaInicial(Model model){
        model.addAttribute("totalProdutos", estoqueService.totalProdutos());
        model.addAttribute("totalEstoque", estoqueService.totalQuantidadeEstoque());
        model.addAttribute("produtosBaixoEstoque", estoqueService.produtosComEstoqueBaixo());
        return "viewPaginaInicial";
    }

    // Página de listagem
    @GetMapping("/listagem")
    public String listarEstoques(Model model) {
        model.addAttribute("estoques", estoqueService.listarTodos());
        return "viewEstoqueListagem";
    }

    // Página de formulário
    @GetMapping("/novo")
    public String novoEstoque(Model model) {
        model.addAttribute("produtos", estoqueService.listarProdutos());
        model.addAttribute("estoque", new Estoque());
        return "viewEstoqueCadastro";
    }

    // Envio do formulário
    @PostMapping("/salvar")
    public String salvarEstoque(@ModelAttribute Estoque estoque) {
        estoqueService.registrarMovimentacao(estoque);
        return "redirect:/estoque/listagem";
    }

    // Editar estoque
    @GetMapping("/editar/{id}")
    public String editarEstoque(@PathVariable Integer id, Model model) {
        Estoque estoque = estoqueService.buscarPorId(id);
        estoqueService.deletar(id);
        model.addAttribute("estoque", estoque);
        return "viewEstoqueCadastro";
    }

    // Excluir estoque
    @GetMapping("/excluir/{id}")
    public String excluirEstoque(@PathVariable Integer id) {
        estoqueService.deletar(id);
        return "redirect:/estoque/listagem";
    }
}
