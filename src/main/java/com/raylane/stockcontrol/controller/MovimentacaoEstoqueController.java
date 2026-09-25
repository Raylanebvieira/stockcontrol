package com.raylane.stockcontrol.controller;

import com.raylane.stockcontrol.model.MovimentacaoEstoque;
import com.raylane.stockcontrol.repository.MovimentacaoEstoqueRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoEstoqueController {

    private final MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;

    public MovimentacaoEstoqueController(
            MovimentacaoEstoqueRepository movimentacaoEstoqueRepository) {

        this.movimentacaoEstoqueRepository = movimentacaoEstoqueRepository;
    }

    @GetMapping
    public List<MovimentacaoEstoque> listarTodas() {
        return movimentacaoEstoqueRepository.findAll();
    }

    @GetMapping("/produto/{produtoId}")
    public List<MovimentacaoEstoque> listarPorProduto(
            @PathVariable Long produtoId) {

        return movimentacaoEstoqueRepository.findByProdutoId(produtoId);
    }
}