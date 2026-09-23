package com.raylane.stockcontrol.service;

import com.raylane.stockcontrol.model.Produto;
import com.raylane.stockcontrol.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
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
                    produto.setQuantidade(produtoAtualizado.getQuantidade());
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

                    return produtoRepository.save(produto);
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

                    return produtoRepository.save(produto);
                });
    }
}