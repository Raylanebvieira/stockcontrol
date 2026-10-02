package com.raylane.stockcontrol.service;

import com.raylane.stockcontrol.repository.MovimentacaoEstoqueRepository;
import com.raylane.stockcontrol.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import com.raylane.stockcontrol.model.Produto;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @Test
    void deveAtualizarProdutoSemAlterarQuantidade() {
        Produto produtoExistente = new Produto();

        produtoExistente.setId(1L);
        produtoExistente.setNome("Monitor LED 24");
        produtoExistente.setDescricao("Monitor Full HD");
        produtoExistente.setPreco(new java.math.BigDecimal("899.90"));
        produtoExistente.setQuantidade(10);
        produtoExistente.setEstoqueMinimo(3);

        Produto produtoAtualizado = new Produto();

        produtoAtualizado.setNome("Monitor LED 24 Ultra");
        produtoAtualizado.setDescricao("Monitor Full HD atualizado");
        produtoAtualizado.setPreco(new java.math.BigDecimal("999.90"));
        produtoAtualizado.setEstoqueMinimo(4);

        when(produtoRepository.findById(1L))
                .thenReturn(Optional.of(produtoExistente));

        when(produtoRepository.save(produtoExistente))
                .thenReturn(produtoExistente);

        Optional<Produto> resultado =
                produtoService.atualizar(1L, produtoAtualizado);

        assertTrue(resultado.isPresent());

        assertEquals("Monitor LED 24 Ultra", resultado.get().getNome());
        assertEquals(new java.math.BigDecimal("999.90"), resultado.get().getPreco());
        assertEquals(4, resultado.get().getEstoqueMinimo());
        assertEquals(10, resultado.get().getQuantidade());
    }

    @Test
    void deveImpedirSaidaQuandoEstoqueForInsuficiente() {

        Produto produto = new Produto();

        produto.setId(1L);
        produto.setNome("Monitor LED 24");
        produto.setQuantidade(10);

        when(produtoRepository.findById(1L))
                .thenReturn(Optional.of(produto));

        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> produtoService.saidaEstoque(1L, 15)
        );

        assertEquals(
                "Estoque insuficiente para realizar a saída.",
                excecao.getMessage()
        );
    }
}