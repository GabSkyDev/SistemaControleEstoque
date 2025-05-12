package com.api.ControleEstoque.controller;

import com.api.ControleEstoque.data.Categoria;
import com.api.ControleEstoque.service.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/categoria")
public class viewCategoriaController {
    
    @Autowired
    CategoriaService categoriaService;

    // Página de listagem
    @GetMapping("/listagem")
    public String listarCategorias(Model model) {
        List<Categoria> categorias = categoriaService.listarTodas();
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
        categoriaService.salvar(categoria);
        return "redirect:/categoria/listagem";
    }

    // Editar categoria
    @GetMapping("/editar/{id}")
    public String editarCategoria(@PathVariable Integer id, Model model) {
        Categoria categoria = categoriaService.buscarPorId(id);
        categoriaService.deletarPorId(id); 
        model.addAttribute("categoria", categoria);
        return "viewCategoriaCadastro";
    }

    // Excluir categoria
    @GetMapping("/excluir/{id}")
    public String excluirCategoria(@PathVariable Integer id) {
        categoriaService.deletarPorId(id);
        return "redirect:/categoria/listagem";
    }
}
