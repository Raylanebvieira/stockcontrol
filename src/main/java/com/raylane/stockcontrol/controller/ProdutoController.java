package com.raylane.stockcontrol.controller;

import com.raylane.stockcontrol.dto.ProdutoRequestDTO;
import com.raylane.stockcontrol.model.Produto;
import com.raylane.stockcontrol.service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Produtos",
        description = "Operações para gerenciamento de produtos e controle de estoque."
)
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Operation(
            summary = "Cadastrar produto",
            description = "Cadastra um novo produto no sistema."
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto cadastrar(
            @Valid @RequestBody ProdutoRequestDTO dto) {

        Produto produto = new Produto();

        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setQuantidade(dto.getQuantidade());
        produto.setEstoqueMinimo(dto.getEstoqueMinimo());

        return produtoService.salvar(produto);
    }

    @Operation(
            summary = "Listar produtos",
            description = "Retorna todos os produtos cadastrados no sistema."
    )
    @GetMapping
    public List<Produto> listar() {
        return produtoService.listarTodos();
    }

    @Operation(
            summary = "Buscar produto por ID",
            description = "Retorna um produto específico a partir do seu identificador."
    )
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(
            @PathVariable Long id) {

        return produtoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Atualizar produto",
            description = "Atualiza os dados cadastrais de um produto existente."
    )
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProdutoRequestDTO dto) {

        Produto produto = new Produto();

        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setQuantidade(dto.getQuantidade());
        produto.setEstoqueMinimo(dto.getEstoqueMinimo());

        return produtoService.atualizar(id, produto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Excluir produto",
            description = "Remove um produto cadastrado no sistema."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        if (produtoService.excluir(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Registrar entrada de estoque",
            description = "Adiciona uma quantidade ao estoque do produto e registra a movimentação."
    )
    @PatchMapping("/{id}/entrada")
    public ResponseEntity<Produto> entradaEstoque(
            @PathVariable Long id,
            @RequestParam Integer quantidade) {

        return produtoService.entradaEstoque(id, quantidade)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Registrar saída de estoque",
            description = "Remove uma quantidade do estoque do produto e registra a movimentação."
    )
    @PatchMapping("/{id}/saida")
    public ResponseEntity<Produto> saidaEstoque(
            @PathVariable Long id,
            @RequestParam Integer quantidade) {

        return produtoService.saidaEstoque(id, quantidade)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Listar produtos com estoque baixo",
            description = "Retorna os produtos cuja quantidade atual é menor ou igual ao estoque mínimo."
    )
    @GetMapping("/estoque-baixo")
    public List<Produto> listarProdutosComEstoqueBaixo() {
        return produtoService.listarProdutosComEstoqueBaixo();
    }
}