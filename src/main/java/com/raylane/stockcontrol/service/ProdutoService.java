package com.raylane.stockcontrol.service;

import com.raylane.stockcontrol.model.Produto;
import com.raylane.stockcontrol.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.raylane.stockcontrol.model.MovimentacaoEstoque;
import com.raylane.stockcontrol.repository.MovimentacaoEstoqueRepository;
import java.time.LocalDateTime;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;

    public ProdutoService(
            ProdutoRepository produtoRepository,
            MovimentacaoEstoqueRepository movimentacaoEstoqueRepository) {

        this.produtoRepository = produtoRepository;
        this.movimentacaoEstoqueRepository = movimentacaoEstoqueRepository;
    }

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public Optional<Produto> atualizar(Long id, Produto produtoAtualizado) {

        return produtoRepository.findById(id)
                .map(produto -> {

                    produto.setNome(produtoAtualizado.getNome());
                    produto.setDescricao(produtoAtualizado.getDescricao());
                    produto.setPreco(produtoAtualizado.getPreco());
                    produto.setEstoqueMinimo(produtoAtualizado.getEstoqueMinimo());

                    return produtoRepository.save(produto);
                });
    }

    public boolean excluir(Long id) {

        if (!produtoRepository.existsById(id)) {
            return false;
        }

        produtoRepository.deleteById(id);
        return true;
    }

    public Optional<Produto> entradaEstoque(Long id, Integer quantidade) {

        return produtoRepository.findById(id)
                .map(produto -> {

                    if (quantidade <= 0) {
                        throw new IllegalArgumentException(
                                "A quantidade de entrada deve ser maior que zero."
                        );
                    }

                    int novaQuantidade = produto.getQuantidade() + quantidade;

                    produto.setQuantidade(novaQuantidade);

                    Produto produtoAtualizado = produtoRepository.save(produto);

                    MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();

                    movimentacao.setTipo("ENTRADA");
                    movimentacao.setQuantidade(quantidade);
                    movimentacao.setDataHora(LocalDateTime.now());
                    movimentacao.setProduto(produtoAtualizado);

                    movimentacaoEstoqueRepository.save(movimentacao);

                    return produtoAtualizado;
                });
    }
    public Optional<Produto> saidaEstoque(Long id, Integer quantidade) {

        return produtoRepository.findById(id)
                .map(produto -> {

                    if (quantidade <= 0) {
                        throw new IllegalArgumentException(
                                "A quantidade de saída deve ser maior que zero."
                        );
                    }

                    if (quantidade > produto.getQuantidade()) {
                        throw new IllegalArgumentException(
                                "Estoque insuficiente para realizar a saída."
                        );
                    }

                    int novaQuantidade = produto.getQuantidade() - quantidade;

                    produto.setQuantidade(novaQuantidade);

                    Produto produtoAtualizado = produtoRepository.save(produto);

                    MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();

                    movimentacao.setTipo("SAIDA");
                    movimentacao.setQuantidade(quantidade);
                    movimentacao.setDataHora(LocalDateTime.now());
                    movimentacao.setProduto(produtoAtualizado);

                    movimentacaoEstoqueRepository.save(movimentacao);

                    return produtoAtualizado;
                });
    }
    public List<Produto> listarProdutosComEstoqueBaixo() {
        return produtoRepository.buscarProdutosComEstoqueBaixo();
    }
}