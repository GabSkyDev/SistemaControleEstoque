package com.api.ControleEstoque.controller;

import com.api.ControleEstoque.data.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/produto")
public class viewProdutoController {

    private List<Produto> produtos = new ArrayList<>();
    private int proximoId = 1;

    // Página de listagem
    @GetMapping("/listagem")
    public String listarProdutos(Model model) {
        model.addAttribute("produtos", produtos);
        return "viewProdutoListagem";
    }

    // Página de formulário
    @GetMapping("/novo")
    public String novoProduto(Model model) {
        model.addAttribute("produto", new Produto());
        return "viewProdutoCadastro";
    }

    // Envio do formulário
    @PostMapping("/salvar")
    public String salvarProduto(@ModelAttribute Produto produto) {
        if (produto.getId() == null) {
            produto.setId(proximoId++);
            produtos.add(produto);
        } else {
            for (int i = 0; i < produtos.size(); i++) {
                if (produtos.get(i).getId().equals(produto.getId())) {
                    produtos.set(i, produto);
                    break;
                }
            }
        }
        return "redirect:/produto/listagem";
    }

    // Editar produto
    @GetMapping("/editar/{id}")
    public String editarProduto(@PathVariable Integer id, Model model) {
        Produto produto = produtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("ID do produto inválido: " + id));
        model.addAttribute("produto", produto);
        return "viewProdutoCadastro";
    }

    // Excluir produto
    @GetMapping("/excluir/{id}")
    public String excluirProduto(@PathVariable Integer id) {
        produtos.removeIf(p -> p.getId().equals(id));
        return "redirect:/produto/listagem";
    }
}
