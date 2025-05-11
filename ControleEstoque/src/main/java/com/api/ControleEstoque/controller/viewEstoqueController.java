package com.api.ControleEstoque.controller;

import com.api.ControleEstoque.data.Estoque;
import com.api.ControleEstoque.data.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/estoque")
public class viewEstoqueController {

    private List<Estoque> estoques = new ArrayList<>();
    private List<Produto> produtos = new ArrayList<>();
    private int proximoIdEstoque = 1;
    private int proximoIdProduto = 1;

    // Página Inicial
    @GetMapping("/inicial")
    public String paginaInicial(Model model) {
        int totalProdutos = produtos.size();
        int totalEstoque = produtos.stream().mapToInt(Produto::getQuantidade).sum();
        List<Produto> produtosBaixoEstoque = produtos.stream()
                .filter(p -> p.getQuantidade() < p.getEstoqueMinimo())
                .toList();

        model.addAttribute("totalProdutos", totalProdutos);
        model.addAttribute("totalEstoque", totalEstoque);
        model.addAttribute("produtosBaixoEstoque", produtosBaixoEstoque);
        return "viewPaginaInicial";
    }

    // Página de listagem
    @GetMapping("/listagem")
    public String listarEstoques(Model model) {
        model.addAttribute("estoques", estoques);
        return "viewEstoqueListagem";
    }

    // Página de formulário
    @GetMapping("/novo")
    public String novoEstoque(Model model) {
        model.addAttribute("produtos", produtos);
        model.addAttribute("estoque", new Estoque());
        return "viewEstoqueCadastro";
    }

    // Envio do formulário
    @PostMapping("/salvar")
    public String salvarEstoque(@ModelAttribute Estoque estoque) {
        if (estoque.getId() == null) {
            estoque.setId(proximoIdEstoque++);
            estoques.add(estoque);
        } else {
            for (int i = 0; i < estoques.size(); i++) {
                if (estoques.get(i).getId().equals(estoque.getId())) {
                    estoques.set(i, estoque);
                    break;
                }
            }
        }
        return "redirect:/estoque/listagem";
    }

    // Editar estoque
    @GetMapping("/editar/{id}")
    public String editarEstoque(@PathVariable Integer id, Model model) {
        Estoque estoque = estoques.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("ID do estoque inválido: " + id));

        estoques.removeIf(e -> e.getId().equals(id));
        model.addAttribute("estoque", estoque);
        model.addAttribute("produtos", produtos);
        return "viewEstoqueCadastro";
    }

    // Excluir estoque
    @GetMapping("/excluir/{id}")
    public String excluirEstoque(@PathVariable Integer id) {
        estoques.removeIf(e -> e.getId().equals(id));
        return "redirect:/estoque/listagem";
    }
}
