package com.api.ControleEstoque.controller;

import com.api.ControleEstoque.data.Categoria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/categoria")
public class viewCategoriaController {

    private List<Categoria> categorias = new ArrayList<>();
    private int proximoId = 1;

    // Página de listagem
    @GetMapping("/listagem")
    public String listarCategorias(Model model) {
        model.addAttribute("categorias", categorias);
        return "viewCategoriaListagem";
    }

    // Página de formulário
    @GetMapping("/novo")
    public String novaCategoria(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "viewCategoriaCadastro";
    }

    // Envio do formulário
    @PostMapping("/salvar")
    public String salvarCategoria(@ModelAttribute Categoria categoria) {
        if (categoria.getId() == null) {
            categoria.setId(proximoId++);
            categorias.add(categoria);
        } else {
            for (int i = 0; i < categorias.size(); i++) {
                if (categorias.get(i).getId().equals(categoria.getId())) {
                    categorias.set(i, categoria);
                    break;
                }
            }
        }
        return "redirect:/categoria/listagem";
    }

    // Editar categoria
    @GetMapping("/editar/{id}")
    public String editarCategoria(@PathVariable Integer id, Model model) {
        Categoria categoria = categorias.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("ID da categoria inválido: " + id));

        model.addAttribute("categoria", categoria);
        return "viewCategoriaCadastro";
    }

    // Excluir categoria
    @GetMapping("/excluir/{id}")
    public String excluirCategoria(@PathVariable Integer id) {
        categorias.removeIf(c -> c.getId().equals(id));
        return "redirect:/categoria/listagem";
    }
}
