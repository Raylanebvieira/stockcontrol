package com.raylane.stockcontrol.controller;

import com.raylane.stockcontrol.model.MovimentacaoEstoque;
import com.raylane.stockcontrol.repository.MovimentacaoEstoqueRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(
        name = "Movimentações de Estoque",
        description = "Operações para consulta do histórico de movimentações de estoque."
)
@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoEstoqueController {

    private final MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;

    public MovimentacaoEstoqueController(
            MovimentacaoEstoqueRepository movimentacaoEstoqueRepository) {

        this.movimentacaoEstoqueRepository = movimentacaoEstoqueRepository;
    }

    @Operation(
            summary = "Listar todas as movimentações",
            description = "Retorna o histórico completo de entradas e saídas de estoque."
    )
    @GetMapping
    public List<MovimentacaoEstoque> listarTodas() {
        return movimentacaoEstoqueRepository.findAll();
    }

    @Operation(
            summary = "Listar movimentações por produto",
            description = "Retorna o histórico de movimentações de um produto específico."
    )
    @GetMapping("/produto/{produtoId}")
    public List<MovimentacaoEstoque> listarPorProduto(
            @PathVariable Long produtoId) {

        return movimentacaoEstoqueRepository.findByProdutoId(produtoId);
    }
}