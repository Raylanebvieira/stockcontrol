package com.raylane.stockcontrol.controller;

import com.raylane.stockcontrol.model.Produto;
import com.raylane.stockcontrol.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.raylane.stockcontrol.dto.ProdutoRequestDTO;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

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

    @GetMapping
    public List<Produto> listar() {
        return produtoService.listarTodos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        if (produtoService.excluir(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
    @PatchMapping("/{id}/entrada")
    public ResponseEntity<Produto> entradaEstoque(
            @PathVariable Long id,
            @RequestParam Integer quantidade) {

        return produtoService.entradaEstoque(id, quantidade)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PatchMapping("/{id}/saida")
    public ResponseEntity<Produto> saidaEstoque(
            @PathVariable Long id,
            @RequestParam Integer quantidade) {

        return produtoService.saidaEstoque(id, quantidade)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/estoque-baixo")
    public List<Produto> listarProdutosComEstoqueBaixo() {
        return produtoService.listarProdutosComEstoqueBaixo();
    }
}